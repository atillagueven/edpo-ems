package ch.unisg.ems.eventprocessor.timestampExtractors;
import ch.unisg.ems.eventprocessor.model.EntityProductionEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.Instant;
public class ProductionTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
        EntityProductionEvent entityProductionEvent = (EntityProductionEvent) record.value();
        return entityProductionEvent.getTimestamp();
    }
}