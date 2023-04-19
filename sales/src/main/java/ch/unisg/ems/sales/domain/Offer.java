package ch.unisg.ems.sales.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

@Entity(name="OfferEntity")
public class Offer {

    @Id
    protected String id;

    private String customerEmail;
    private String customerName;
    private Integer loadProfileConsumption;
    private Integer loadProfileProduction;

    private String batterySize;

    public Offer() {}

    // Needs refactor
    public Offer(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Offer offer = objectMapper.readValue(json, Offer.class);
        this.id = UUID.randomUUID().toString();
        this.customerName = offer.customerName;
        this.customerEmail = offer.customerEmail;
        this.loadProfileConsumption = offer.loadProfileConsumption;
        this.loadProfileProduction = offer.loadProfileProduction;
        this.batterySize = offer.batterySize;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("offerId")
    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getLoadProfileConsumption() {
        return loadProfileConsumption;
    }

    public void setLoadProfileConsumption(Integer loadProfile) {
        this.loadProfileConsumption = loadProfile;
    }

    public Integer getLoadProfileProduction() {
        return loadProfileProduction;
    }

    public void setLoadProfileProduction(Integer loadProfileProduction) {
        this.loadProfileProduction = loadProfileProduction;
    }

    public String getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(String batterySize) {
        this.batterySize = batterySize;
    }
}
