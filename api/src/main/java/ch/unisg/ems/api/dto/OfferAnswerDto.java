package ch.unisg.ems.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferAnswerDto {

    private String offerId;

    @Getter
    private Boolean offerAccepted;

    @Getter
    private Boolean newOfferRequested;
}
