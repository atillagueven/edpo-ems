package ch.unisg.ems.inventory.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;


public class AppointmentReply {

    @Getter
    @Setter
    private String offerId;

    @Getter
    @Setter
    private String appointmentDate;

    public AppointmentReply() {}

    // Needs refactor
    public AppointmentReply(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AppointmentReply appointmentReply = objectMapper.readValue(json, AppointmentReply.class);
        this.offerId = appointmentReply.offerId;
        this.appointmentDate = appointmentReply.appointmentDate;
    }
}
