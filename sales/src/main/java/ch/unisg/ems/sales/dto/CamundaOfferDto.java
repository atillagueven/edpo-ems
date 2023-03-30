package ch.unisg.ems.sales.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CamundaOfferDto implements Serializable {

    private String correlationId;
    private OfferDto dto;

}
