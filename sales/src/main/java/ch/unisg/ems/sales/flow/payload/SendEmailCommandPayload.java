package ch.unisg.ems.sales.flow.payload;

import lombok.Getter;
import lombok.Setter;

public class SendEmailCommandPayload {
    @Getter
    @Setter
    private String clientEmail;

    @Getter
    @Setter
    private String emailContent;

}
