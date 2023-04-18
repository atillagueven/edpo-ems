package ch.unisg.ems.sales.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.util.UUID;


public class OfferReply {

    @Getter
    @Setter
    private String offerId;

    @Getter
    @Setter
    private Boolean offerAccepted;

    @Getter
    @Setter
    private Boolean newOfferRequested;

    public OfferReply() {}

    // Needs refactor
    public OfferReply(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OfferReply offerReply = objectMapper.readValue(json, OfferReply.class);
        this.offerId = offerReply.offerId;
        this.offerAccepted = offerReply.offerAccepted;
        this.newOfferRequested = offerReply.newOfferRequested;
    }
}
