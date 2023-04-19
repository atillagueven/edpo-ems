package ch.unisg.ems.payment.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


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
    private Integer loadProfile;

    private String batterySize;

    private String correlationId;
    public Offer() {}

    // Needs refactor
    public Offer(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Offer offer = objectMapper.readValue(json, Offer.class);
        this.id = offer.id;
//        this.customerName = offer.customerName;
//        this.customerEmail = offer.customerEmail;
//        this.loadProfile = offer.loadProfile;
//        this.batterySize = offer.batterySize;
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

    public Integer getLoadProfile() {
        return loadProfile;
    }

    public void setLoadProfile(Integer loadProfile) {
        this.loadProfile = loadProfile;
    }

    public String getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(String batterySize) {
        this.batterySize = batterySize;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
