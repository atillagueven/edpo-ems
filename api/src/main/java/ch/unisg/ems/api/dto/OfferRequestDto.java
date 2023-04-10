package ch.unisg.ems.api.dto;

import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequestDto implements Serializable {

    @Getter
    private String customerName;

    @Getter
    private String customerEmail;

    @Getter
    private Integer loadProfile;

}
