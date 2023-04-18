package ch.unisg.ems.sales.flow.payload;

public class ClientAnswerReceivedPayload {

    private String refId;

    public String getRefId() {
        return refId;
    }

    public ClientAnswerReceivedPayload setRefId(String refId) {
        this.refId = refId;
        return this;
    }
}
