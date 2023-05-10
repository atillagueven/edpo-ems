package ch.unisg.ems.eventprocessor;

import ch.unisg.ems.eventprocessor.loadprofile.UnitConverter;
import ch.unisg.ems.eventprocessor.model.EntityProductionEvent;
import ch.unisg.ems.eventprocessor.serialization.ProductionEvent;
import ch.unisg.ems.eventprocessor.serialization.json.ProductionEventSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import com.mitchseymour.kafka.serialization.avro.AvroSerdes;

import java.util.Arrays;
import java.util.List;

class EmsTopology {
  private static final List<String> currencies = Arrays.asList("bitcoin", "ethereum");

  private static UnitConverter unitConverter = new UnitConverter();

  public static Topology build() {
    // the builder is used to construct the topology
    StreamsBuilder builder = new StreamsBuilder();

    // start streaming production events using our custom value serdes.
    KStream<byte[], ProductionEvent> stream =
            builder.stream("pv_production", Consumed.with(Serdes.ByteArray(), new ProductionEventSerdes()));
    stream.print(Printed.<byte[], ProductionEvent>toSysOut().withLabel("pv_production-event-stream"));

    // Apply content filter to production events // Keep only relevant attributes
    KStream<byte[], EntityProductionEvent> contentFilteredProductionEvents =
            stream.mapValues(
                    (event) -> {
                      EntityProductionEvent contentFilteredProductionEvent = new EntityProductionEvent();
                      contentFilteredProductionEvent.setPvId(event.getPvId());
                      contentFilteredProductionEvent.setLoad(event.getLoad());
                      contentFilteredProductionEvent.setTimestamp(event.getTimestamp());
                      contentFilteredProductionEvent.setUnitLoad(event.getUnitLoad());
                      return contentFilteredProductionEvent;
                    });

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
      KStream<byte[], EntityProductionEvent> merged = kWStream.merge(convertedStream);

      // filter out events with a load greater than 100 kW (measurement error since the pv system is only 100 kW)
      KStream<byte[], EntityProductionEvent> filtered =
              merged.filterNot(
                      (key, event) -> event.getLoad() > 100.0);

    // send the merged stream to the "pv_production_clean" topic
    filtered.to(
            "pv_production_clean",
            Produced.with(
                    Serdes.ByteArray(),
                    // registryless Avro Serde
                    AvroSerdes.get(EntityProductionEvent.class)));


    return builder.build();
  }
}
