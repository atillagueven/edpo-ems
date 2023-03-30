package ch.unisg.ems.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto implements Serializable {

    private String offerId;

    private String customerId;

    private Boolean accepted;
}
