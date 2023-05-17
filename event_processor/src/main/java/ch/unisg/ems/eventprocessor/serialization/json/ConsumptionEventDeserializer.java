package ch.unisg.ems.eventprocessor.serialization.json;

import ch.unisg.ems.eventprocessor.serialization.ConsumptionEvent;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class ConsumptionEventDeserializer implements Deserializer<ConsumptionEvent> {
    private Gson gson =
            new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    @Override
    public ConsumptionEvent deserialize(String topic, byte[] bytes) {
        if (bytes == null) return null;
        return gson.fromJson(new String(bytes, StandardCharsets.UTF_8), ConsumptionEvent.class);
    }
}
