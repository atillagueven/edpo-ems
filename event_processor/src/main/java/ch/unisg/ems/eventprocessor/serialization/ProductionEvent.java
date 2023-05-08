package ch.unisg.ems.eventprocessor.serialization;

import com.google.gson.annotations.SerializedName;

public class ProductionEvent {
    @SerializedName("Id")
    String id;
    @SerializedName("reactive")
    boolean reactive;
    @SerializedName("load")
    int load;
    @SerializedName("unit_load")
    String unitLoad;
    @SerializedName("timestamp")
    String timestamp;
    @SerializedName("unit_reactive")
    String unitReactive;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isReactive() {
        return reactive;
    }

    public void setReactive(boolean reactive) {
        this.reactive = reactive;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String getUnitLoad() {
        return unitLoad;
    }

    public void setUnitLoad(String unitLoad) {
        this.unitLoad = unitLoad;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUnitReactive() {
        return unitReactive;
    }

    public void setUnitReactive(String unitReactive) {
        this.unitReactive = unitReactive;
    }

}
