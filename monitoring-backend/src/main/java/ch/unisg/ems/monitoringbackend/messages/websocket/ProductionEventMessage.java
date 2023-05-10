package ch.unisg.ems.monitoringbackend.messages.websocket;

public class ProductionEventMessage {

    private int reactive;

    private int load;

    private String unit_load;

    private String timestamp;

    private String unit_reactive;

    public ProductionEventMessage() {
    }

    public ProductionEventMessage(int reactive, int load, String unit_load, String timestamp, String unit_reactive) {
        this.reactive = reactive;
        this.load = load;
        this.unit_load = unit_load;
        this.timestamp = timestamp;
        this.unit_reactive = unit_reactive;
    }

    public int getReactive() {
        return reactive;
    }

    public void setReactive(int reactive) {
        this.reactive = reactive;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String getUnit_load() {
        return unit_load;
    }

    public void setUnit_load(String unit_load) {
        this.unit_load = unit_load;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUnit_reactive() {
        return unit_reactive;
    }

    public void setUnit_reactive(String unit_reactive) {
        this.unit_reactive = unit_reactive;
    }

}
