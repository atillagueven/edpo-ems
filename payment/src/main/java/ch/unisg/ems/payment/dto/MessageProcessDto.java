package ch.unisg.ems.payment.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageProcessDto implements Serializable {

    private String requester;
    private Double amount;
    private Boolean preApproved;
    private Boolean processed;
}
