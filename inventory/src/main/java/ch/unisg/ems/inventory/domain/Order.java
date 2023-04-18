package ch.unisg.ems.inventory.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.IOException;
import java.util.UUID;

@Entity(name="OrderEntity")
public class Order {

    @Id
    protected String id;

    private String customerEmail;
    private String customerName;
    private Integer loadProfile;

    public Order() {}

    // Needs refactor
    public Order(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(json, Order.class);
        this.id = UUID.randomUUID().toString();
        this.customerName = order.customerName;
        this.customerEmail = order.customerEmail;
        this.loadProfile = order.loadProfile;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("orderId")
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
}
