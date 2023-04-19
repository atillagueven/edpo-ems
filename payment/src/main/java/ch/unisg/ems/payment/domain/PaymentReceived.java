package ch.unisg.ems.payment.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;


public class PaymentReceived {

    @Getter
    @Setter
    private String invoiceId;


    public PaymentReceived() {}

    // Needs refactor
    public PaymentReceived(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentReceived paymentReceived = objectMapper.readValue(json, PaymentReceived.class);
        this.invoiceId = paymentReceived.invoiceId;
    }
}
