package ch.unisg.ems.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferPaymentDto {

    private String invoiceId;

}
