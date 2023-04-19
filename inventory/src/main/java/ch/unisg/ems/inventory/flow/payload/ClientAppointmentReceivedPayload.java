package ch.unisg.ems.inventory.flow.payload;

import lombok.Getter;
import lombok.Setter;

public class ClientAppointmentReceivedPayload {

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
    private String emsSystemRecommendation;

    @Getter
    @Setter
    private String appointmentDate;

}
