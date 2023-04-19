package ch.unisg.ems.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private String offerId;

    private String clientEmail;

}