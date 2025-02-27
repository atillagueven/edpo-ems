package ch.unisg.ems.payment.flow;

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
    private String invoiceId;

    @Getter
    @Setter
    private String clientEmail;

    @Getter
    @Setter
    private float amount;


    public static OfferFlowContext fromMap(Map<String, Object> values) {
        OfferFlowContext context = new OfferFlowContext();
        context.traceId = (String) values.get("traceId");
        context.offerId = (String) values.get("offerId");
        context.invoiceId = (String) values.get("invoiceId");
        context.clientEmail = (String) values.get("clientEmail");
        context.amount = Float.parseFloat((String) values.get("amount"));
        return context;
    }

    public Map<String, String> asMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("traceId", traceId);
        map.put("offerId", offerId);
        map.put("invoiceId", invoiceId);
        map.put("clientEmail", clientEmail);
        map.put("amount", String.valueOf(amount));
        return map;
    }

    @Override
    public String toString() {
        return "OrderFlowContext [traceId=" + traceId + ", offerId=" + offerId + "]";
    }
}
