package ch.unisg.ems.eventprocessor.serialization.json;

import ch.unisg.ems.eventprocessor.serialization.ProductionEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ProductionEventSerdes implements Serde<ProductionEvent> {

  @Override
  public Serializer<ProductionEvent> serializer() {
    return new ProductionEventSerializer();
  }

  @Override
  public Deserializer<ProductionEvent> deserializer() {
    return new ProductionEventDeserializer();
  }
}
