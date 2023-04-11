package ch.unisg.ems.sales.flow;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class OfferFlowContext {
    @Getter
    @Setter
    private String traceId;

    @Getter
    @Setter
    private String offerId;

    @Getter
    @Setter
    private Integer loadProfile;

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

    public static OfferFlowContext fromMap(Map<String, Object> values) {
        OfferFlowContext context = new OfferFlowContext();
        context.traceId = (String) values.get("traceId");
        context.offerId = (String) values.get("offerId");
        context.offerMessage = (String) values.get("offerMessage");
        context.loadProfile = Integer.parseInt(values.get("loadProfile").toString());
        context.offerAccepted = Boolean.parseBoolean(values.get("offerAccepted").toString());
        context.newOfferRequested = Boolean.parseBoolean(values.get("newOfferRequested").toString());
        context.reminderSent = Boolean.parseBoolean(values.get("reminderSent").toString());
        return context;
    }

    public Map<String, String> asMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("traceId", traceId);
        map.put("offerId", offerId);
        map.put("offerMessage", offerMessage);
        map.put("loadProfile", loadProfile.toString());
        map.put("offerAccepted", offerAccepted.toString());
        map.put("newOfferRequested", newOfferRequested.toString());
        map.put("reminderSent", reminderSent.toString());

        return map;
    }

    @Override
    public String toString() {
        return "OrderFlowContext [traceId=" + traceId + ", offerId=" + offerId + "]";
    }
}
