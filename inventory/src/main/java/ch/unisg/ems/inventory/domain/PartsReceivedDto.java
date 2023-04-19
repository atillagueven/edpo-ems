package ch.unisg.ems.inventory.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartsReceivedDto {

    @Getter
    @Setter
    private String offerId;

    @Getter
    @Setter
    private String partsConfirmed;


    // Needs refactor
    public PartsReceivedDto(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PartsReceivedDto partsReceivedDto = objectMapper.readValue(json, PartsReceivedDto.class);
        this.offerId = partsReceivedDto.offerId;
        this.partsConfirmed = partsReceivedDto.partsConfirmed.toString();
    }
}
