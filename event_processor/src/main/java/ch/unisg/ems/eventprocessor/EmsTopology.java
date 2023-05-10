package ch.unisg.ems.eventprocessor;

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

  public static Topology build() {
    // the builder is used to construct the topology
    StreamsBuilder builder = new StreamsBuilder();

    // start streaming gazes using our custom value serdes.
    KStream<byte[], ProductionEvent> stream =
            builder.stream("pv_production", Consumed.with(Serdes.ByteArray(), new ProductionEventSerdes()));
    stream.print(Printed.<byte[], ProductionEvent>toSysOut().withLabel("pv_production-event-stream"));

    // Apply content filter to production events // Keep only relevant attributes
    KStream<byte[], EntityProductionEvent> contentFilteredProductionEvents =
            stream.mapValues(
                    (event) -> {
                      EntityProductionEvent contentFilteredProductionEvent = new EntityProductionEvent();
                      contentFilteredProductionEvent.setTimestamp(event.getTimestamp());
                      // contentFilteredProductionEvent.setId(event.getId());
                      contentFilteredProductionEvent.setUnitLoad(event.getUnitLoad());
                      return contentFilteredProductionEvent;
                    });

    KStream<byte[], EntityProductionEvent> filtered =
          contentFilteredProductionEvents.filterNot(
                  (key, event) -> event.getLoad() > 1000);

      // match all tweets that specify English as the source language
      Predicate<byte[], EntityProductionEvent> eventsWithUnitKWH = (key, event) -> event.getUnitLoad().equals("kW");

      // match all other tweets
      Predicate<byte[], EntityProductionEvent> eventsWithOtherUnit = (key, event) -> !event.getUnitLoad().equals("kW");

      // branch based on tweet language
      KStream<byte[], EntityProductionEvent>[] branches = filtered.branch(eventsWithUnitKWH, eventsWithOtherUnit);

      // English tweets
      KStream<byte[], EntityProductionEvent> kWStream = branches[0];
      kWStream.print(Printed.<byte[], EntityProductionEvent>toSysOut().withLabel("event-kW"));

      // non-English tweets
      KStream<byte[], EntityProductionEvent> nonKwStream = branches[1];
      nonKwStream.print(Printed.<byte[], EntityProductionEvent>toSysOut().withLabel("events-non-kW"));

      // for non-English tweets, translate the tweet text first.
      KStream<byte[], EntityProductionEvent> convertedStream =
              nonKwStream.mapValues(
                      (event) -> {
                          return event;
                          //return languageClient.translate(tweet, "en");
                      });

      // merge the two streams
      KStream<byte[], EntityProductionEvent> merged = kWStream.merge(convertedStream);


    merged.to(
            "pv_production_clean",
            Produced.with(
                    Serdes.ByteArray(),
                    // registryless Avro Serde
                    AvroSerdes.get(EntityProductionEvent.class)));


    return builder.build();
  }
}
