package ch.unisg.ems.sales.flow.payload;

import lombok.Getter;
import lombok.Setter;

public class SendOfferToClientCommandPayload {

    @Getter
    @Setter
    private String offerId;

    @Getter
    @Setter
    private String clientEmail;

    @Getter
    @Setter
    private String clientName;

    @Getter
    @Setter
    private String offerMessage;

}
