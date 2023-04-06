package ch.unisg.ems.sales.domain;

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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER  )
    protected Customer customer = new Customer();

    private String customerEmail;
    private String customerName;
    private String message;

    public Offer() {}

    // Needs refactor
    public Offer(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Offer offer = objectMapper.readValue(json, Offer.class);
        this.id = UUID.randomUUID().toString();
        this.customer = new Customer();
        this.customer.setName(offer.customerName);
        this.customer.setEmail(offer.customerEmail);
    }

    public String getId() {
        return id;
    }

    @JsonProperty("offerId")
    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
