package ch.unisg.ems.api.dto;

import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequestDto implements Serializable {

    private String offerId;

    @Getter
    private String requester;

    @Getter
    private String email;

    @Getter
    private String message;

}
