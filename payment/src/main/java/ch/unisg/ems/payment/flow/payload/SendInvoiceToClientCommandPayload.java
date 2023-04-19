package ch.unisg.ems.payment.flow.payload;

import lombok.Getter;
import lombok.Setter;

public class SendInvoiceToClientCommandPayload {

    @Getter
    @Setter
    private String offerId;

    @Getter
    @Setter
    private String invoiceId;

    @Getter
    @Setter
    private String clientEmail;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String amount;

}
