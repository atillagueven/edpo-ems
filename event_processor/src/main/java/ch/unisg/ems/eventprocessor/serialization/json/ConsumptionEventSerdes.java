package ch.unisg.ems.eventprocessor.serialization.json;

import ch.unisg.ems.eventprocessor.serialization.ConsumptionEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ConsumptionEventSerdes implements Serde<ConsumptionEvent> {

    @Override
    public Serializer<ConsumptionEvent> serializer() {
        return new ConsumptionEventSerializer();
    }

    @Override
    public Deserializer<ConsumptionEvent> deserializer() {
        return new ConsumptionEventDeserializer();
    }
}
