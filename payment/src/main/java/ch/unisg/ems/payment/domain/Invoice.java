package ch.unisg.ems.payment.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.IOException;
import java.util.UUID;
@Entity(name="InvoiceEntity")
public class Invoice {

    @Id
    protected String id;

    private String offerId;

    private Boolean isPaid;

    private float amount;

    private String clientEmail;

    public Invoice() {}

    // Needs refactor
    public Invoice(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Invoice invoice = objectMapper.readValue(json, Invoice.class);
        this.id = UUID.randomUUID().toString();
        this.offerId = invoice.offerId;
        this.isPaid = false;
        this.clientEmail = invoice.clientEmail;
        this.amount = caluclateInvoiceAmount();
    }

    public Invoice(String offerId, Boolean isPaid, String clientEmail) {
        this.offerId = offerId;
        this.isPaid = isPaid;
        this.id = UUID.randomUUID().toString();
        this.amount = caluclateInvoiceAmount();
        this.clientEmail = clientEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    private Float caluclateInvoiceAmount () {
        return (float) Math.floor(Math.random() *(50000 - 10000 + 1) + 10000);
    }
}
