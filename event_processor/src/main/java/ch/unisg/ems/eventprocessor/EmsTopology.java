package ch.unisg.ems.eventprocessor;

import ch.unisg.ems.eventprocessor.loadprofile.UnitConverter;
import ch.unisg.ems.eventprocessor.model.Customer;
import ch.unisg.ems.eventprocessor.model.EntityConsumptionEvent;
import ch.unisg.ems.eventprocessor.model.EntityProductionEvent;
import ch.unisg.ems.eventprocessor.model.aggregations.ProductionAggregation;
import ch.unisg.ems.eventprocessor.model.join.ProductionEventWithCustomer;
import ch.unisg.ems.eventprocessor.serialization.ConsumptionEvent;
import ch.unisg.ems.eventprocessor.serialization.ProductionEvent;
import ch.unisg.ems.eventprocessor.serialization.json.ConsumptionEventSerdes;
import ch.unisg.ems.eventprocessor.serialization.json.JsonSerdes;
import ch.unisg.ems.eventprocessor.serialization.json.ProductionEventSerdes;
import ch.unisg.ems.eventprocessor.timestampExtractors.ProductionTimestampExtractor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;

import java.time.Duration;

class EmsTopology {
    private static final Double MAX_PRODUCTION_LOAD = 100.0;
    private static final Double MAX_CONSUMPTION_LOAD = 100.0;

    private static UnitConverter unitConverter = new UnitConverter();

    public static Topology build() {
        // Serde<EntityProductionEvent> fixationSerde = jsonSerde(Fixation.class);
        // the builder is used to construct the topology
        StreamsBuilder builder = new StreamsBuilder();

        System.out.println("access yolo stream");

        // start streaming production events using our custom value serdes.
        KStream<byte[], ProductionEvent> streamProduction =
                builder.stream("pv_production",
                        Consumed.with(Serdes.ByteArray(), new ProductionEventSerdes())
                                .withTimestampExtractor(new ProductionTimestampExtractor()));
        streamProduction.print(Printed.<byte[], ProductionEvent>toSysOut().withLabel("pv_production-event-stream"));

        // start streaming consumption events using our custom value serdes.
        KStream<byte[], ConsumptionEvent> streamConsumption =
                builder.stream("energy_consumption", Consumed.with(Serdes.ByteArray(), new ConsumptionEventSerdes()));
        streamConsumption.print(Printed.<byte[], ConsumptionEvent>toSysOut().withLabel("energy_consumption-event-stream"));

        /**
         * Consume global ktable to inititalize customer data
         */
        GlobalKTable<String, Customer> customerTable =
                builder.globalTable("customers", Consumed.with(Serdes.String(), JsonSerdes.Customer()));


        // Apply content filter to production events, Keep only relevant attributes
        KStream<byte[], EntityProductionEvent> contentFilteredProductionEvents =
                streamProduction.mapValues(
                        (event) -> {
                            EntityProductionEvent contentFilteredProductionEvent = new EntityProductionEvent();
                            contentFilteredProductionEvent.setPvId(event.getPvId());
                            contentFilteredProductionEvent.setLoad(event.getLoad());
                            contentFilteredProductionEvent.setTimestamp(event.getTimestamp());
                            contentFilteredProductionEvent.setUnitLoad(event.getUnitLoad());
                            return contentFilteredProductionEvent;
                        });

        // Apply content filter to production events, keep only relevant attributes
        KStream<byte[], EntityConsumptionEvent> contentFilteredConsumptionEvents =
                streamConsumption.mapValues(
                        (event) -> {
                            EntityConsumptionEvent contentFilteredConsumptionEvent = new EntityConsumptionEvent();
                            contentFilteredConsumptionEvent.setConsumerId(event.getConsumerId());
                            contentFilteredConsumptionEvent.setLoad(event.getLoad());
                            contentFilteredConsumptionEvent.setTimestamp(event.getTimestamp());
                            contentFilteredConsumptionEvent.setUnitLoad(event.getUnitLoad());
                            return contentFilteredConsumptionEvent;
                        });

        /*
         * Unit converter for production events
         * divide into 2 branches -> convert unit if needed -> merge branches
         */

        // match all events that have kW as unit
        Predicate<byte[], EntityProductionEvent> unitKW = (key, event) -> event.getUnitLoad().equals("kW");

        // match all other events
        Predicate<byte[], EntityProductionEvent> unitNotKW = (key, event) -> !event.getUnitLoad().equals("kW");

        // branch based on load unit
        KStream<byte[], EntityProductionEvent>[] branches = contentFilteredProductionEvents.branch(unitKW, unitNotKW);

        // load unit: kW
        KStream<byte[], EntityProductionEvent> kWStream = branches[0];
        kWStream.print(Printed.<byte[], EntityProductionEvent>toSysOut().withLabel("event-kW"));

        // load unit: not kW
        KStream<byte[], EntityProductionEvent> notKwStream = branches[1];
        notKwStream.print(Printed.<byte[], EntityProductionEvent>toSysOut().withLabel("events-not-kW"));

        // for events where the load unit is not kW convert the load to kW
        KStream<byte[], EntityProductionEvent> convertedStream =
                notKwStream.mapValues(
                        (event) -> unitConverter.convertToKW(event));

        // merge the two streams
        KStream<byte[], EntityProductionEvent> mergedProduction = kWStream.merge(convertedStream);

        /*
         * Filter events that contain not allowed measurements
         */
        // filter out events with a load greater than 100 kW (measurement error since the pv system is only 100 kW)
        KStream<byte[], EntityProductionEvent> filteredProduction =
                mergedProduction.filterNot(
                        (key, event) -> event.getLoad() > MAX_PRODUCTION_LOAD);

        // filter out events with a load greater than 100 kW (measurement error since the pv system is only 100 kW)
        KStream<byte[], EntityConsumptionEvent> filteredConsumption =
                contentFilteredConsumptionEvents.filterNot(
                        (key, event) -> event.getLoad() > MAX_CONSUMPTION_LOAD);

        /**
         * Join customer information to Production and Consumption streams
         */
        KeyValueMapper<byte[], EntityProductionEvent, String> keyMapper =
                (leftKey, entityProductionEvent) -> String.valueOf(entityProductionEvent.getPvId());

        // join the withPlayers stream to the product global ktable
        ValueJoiner<EntityProductionEvent, Customer, ProductionEventWithCustomer> customerJoiner =
                (entityProductionEvent, customer) -> new ProductionEventWithCustomer(entityProductionEvent, customer);
        KStream<byte[], ProductionEventWithCustomer> productionWithCustomer = filteredProduction.join(customerTable, keyMapper, customerJoiner);
        productionWithCustomer.print(Printed.<byte[], ProductionEventWithCustomer>toSysOut().withLabel("with-products"));

        /**
         * Stateful processing for joined production stream
         * group by customer and aggregate average load over tumbling window
         */
        // Window config for production events
        TimeWindows tumblingWindowProductionEvents =
                TimeWindows.of(Duration.ofSeconds(10)).grace(Duration.ofSeconds(1));


        // aggregation: average load, max load
        Initializer<ProductionAggregation> productionEventsInitializer = () -> new ProductionAggregation(0,0, 0);

        Aggregator<String, ProductionEventWithCustomer, ProductionAggregation> productionEventAggregator = (key, production, productionAggregation) -> {
            int newProductionEventCount = productionAggregation.getCount() + 1;
            double newMaxLoad = productionAggregation.getMaxLoad() >= production.getEntityProductionEvent().getLoad() ? productionAggregation.getMaxLoad() : production.getEntityProductionEvent().getLoad();
            double newAverageLoad = (productionAggregation.getAverageLoad() * (newProductionEventCount - 1) + production.getEntityProductionEvent().getLoad()) / newProductionEventCount;
            return new ProductionAggregation(newAverageLoad, newMaxLoad, newProductionEventCount);
        };


        // Group production events by customer id, Window by tumblingWindowFixations, Aggregate, Materialize, suppress
        KTable<Windowed<String>, ProductionAggregation> productionAggregationTable =
                productionWithCustomer
                        // group by AOI
                        .groupBy((key, value) -> value.getCustomer().getId(),
                                Grouped.<String, ProductionEventWithCustomer>with(Serdes.String(), JsonSerdes.ProductionEventWithCustomer()))
                        // windowing by config
                        .windowedBy(tumblingWindowProductionEvents)
                        // windowed aggregation
                        .aggregate(
                                productionEventsInitializer,
                                productionEventAggregator,
                                Materialized.<String, ProductionAggregation, WindowStore<Bytes, byte[]>>as("productionAggregations")
                                        .withKeySerde(Serdes.String())
                                        .withValueSerde(JsonSerdes.ProductionAggregation())
                        )
                        // suppress
                        .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded().shutDownWhenFull()));



        // send the merged streamProduction to the "pv_production_clean" topic
        /*filteredConsumption.to(
                "pv_production_clean",
                Produced.with(
                        Serdes.ByteArray(),
                        // registryless Avro Serde
                        AvroSerdes.get(EntityProductionEvent.class)));*/

        return builder.build();
    }
}
