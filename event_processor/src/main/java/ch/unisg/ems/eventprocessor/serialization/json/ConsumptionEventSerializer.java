package ch.unisg.ems.eventprocessor.serialization.json;

import ch.unisg.ems.eventprocessor.serialization.ConsumptionEvent;
import ch.unisg.ems.eventprocessor.serialization.ProductionEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

class ConsumptionEventSerializer implements Serializer<ConsumptionEvent> {
    private Gson gson = new Gson();

    @Override
    public byte[] serialize(String topic, ConsumptionEvent consumptionEvent) {
        if (consumptionEvent == null) return null;
        return gson.toJson(consumptionEvent).getBytes(StandardCharsets.UTF_8);
    }
}
