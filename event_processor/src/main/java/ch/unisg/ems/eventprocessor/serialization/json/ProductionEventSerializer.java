package ch.unisg.ems.eventprocessor.serialization.json;

import ch.unisg.ems.eventprocessor.serialization.ProductionEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

class ProductionEventSerializer implements Serializer<ProductionEvent> {
  private Gson gson = new Gson();

  @Override
  public byte[] serialize(String topic, ProductionEvent productionEvent) {
    if (productionEvent == null) return null;
    return gson.toJson(productionEvent).getBytes(StandardCharsets.UTF_8);
  }
}
