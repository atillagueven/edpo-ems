package ch.unisg.ems.inventory.flow;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

import java.util.HashMap;
import java.util.Map;

public class InventoryFlowContext {
    @Getter
    @Setter
    private String traceId;

    @Getter
    @Setter
    private String offerId;

    @Getter
    @Setter
    private String batterySize;

    @Getter
    @Setter
    private Boolean offerAccepted;

    @Getter
    @Setter
    private Boolean newOfferRequested;

    @Getter
    @Setter
    private Boolean reminderSent;

    @Getter
    @Setter
    private String offerMessage;

    @Getter
    @Setter
    private Boolean partsAvailable;

    public static InventoryFlowContext fromMap(Map<String, Object> values) {
        InventoryFlowContext context = new InventoryFlowContext();
        context.traceId = (String) values.get("traceId");
        context.offerId = (String) values.get("offerId");
        context.batterySize = values.get("batterySize").toString();
        context.partsAvailable = Boolean.parseBoolean(values.get("partsAvailable").toString());
        return context;
    }

    public Map<String, String> asMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("traceId", traceId);
        map.put("offerId", offerId);
        map.put("batterySize", batterySize.toString());
        map.put("partsAvailable", partsAvailable.toString());
        return map;
    }

    @Override
    public String toString() {
        return "InventoryFlowContext [traceId=" + traceId + ", offerId=" + offerId + "]";
    }
}
