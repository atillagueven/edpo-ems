package ch.unisg.ems.inventory.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplementaryMaterialsReceivedDto {

    @Getter
    @Setter
    private String offerId;

    @Getter
    @Setter
    private String complementaryMaterialsConfirmed;


    // Needs refactor
    public ComplementaryMaterialsReceivedDto(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ComplementaryMaterialsReceivedDto partsReceivedDto = objectMapper.readValue(json, ComplementaryMaterialsReceivedDto.class);
        this.offerId = partsReceivedDto.offerId;
        this.complementaryMaterialsConfirmed = partsReceivedDto.complementaryMaterialsConfirmed.toString();
    }
}
