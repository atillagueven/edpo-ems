package ch.unisg.ems.eventprocessor;

import ch.unisg.ems.eventprocessor.serialization.ProductionEvent;
import ch.unisg.ems.eventprocessor.serialization.json.ProductionEventSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.List;

class EmsTopology {
  private static final List<String> currencies = Arrays.asList("bitcoin", "ethereum");

  public static Topology build() {
    // the builder is used to construct the topology
    StreamsBuilder builder = new StreamsBuilder();

    // start streaming gazes using our custom value serdes.
    KStream<byte[], ProductionEvent> stream =
            builder.stream("pv_production", Consumed.with(Serdes.ByteArray(), new ProductionEventSerdes()));
    stream.print(Printed.<byte[], ProductionEvent>toSysOut().withLabel("pv_production-event-stream"));

    // Apply content filter to production events // Keep only relevant attributes
    KStream<byte[], ProductionEvent> contentFilteredProductionEvents =
            stream.mapValues(
                    (event) -> {
                      ProductionEvent contentFilteredProductionEvent = new ProductionEvent();
                      contentFilteredProductionEvent.setTimestamp(event.getTimestamp());
                      contentFilteredProductionEvent.setId(event.getId());
                      contentFilteredProductionEvent.setLoad(event.getLoad());
                      return contentFilteredProductionEvent;
                    });

    // TODO fix error with AvroSerdes
 /*   contentFilteredProductionEvents.to(
            "pv_production_cleaned",
            Produced.with(
                    Serdes.ByteArray(),
                    // registryless Avro Serde
                    com.mitchseymour.kafka.serialization.avro.AvroSerdes.get(ProductionEvent.class)));
*/
    return builder.build();
  }
}
