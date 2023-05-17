package ch.unisg.ems.eventprocessor.timestampExtractors;
import ch.unisg.ems.eventprocessor.model.EntityConsumptionEvent;
import ch.unisg.ems.eventprocessor.model.EntityProductionEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

public class ConsumptionTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
        EntityConsumptionEvent entityConsumptionEvent = (EntityConsumptionEvent) record.value();
        return entityConsumptionEvent.getTimestamp();
    }
}