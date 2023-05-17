package ch.unisg.ems.eventprocessor.serialization;

import com.google.gson.annotations.SerializedName;

public class ConsumptionEvent {
    @SerializedName("load")
    double load;
    @SerializedName("unit_load")
    String unitLoad;
    @SerializedName("consumer_id")
    String consumerId;
    @SerializedName("timestamp")
    Long timestamp;

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public String getUnitLoad() {
        return unitLoad;
    }

    public void setUnitLoad(String unitLoad) {
        this.unitLoad = unitLoad;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
