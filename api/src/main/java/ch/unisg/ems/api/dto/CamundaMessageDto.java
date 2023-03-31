package ch.unisg.ems.api.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CamundaMessageDto implements Serializable {
    private String correlationId;
    private String requester;
    // private MessageProcessDto dto;
}
