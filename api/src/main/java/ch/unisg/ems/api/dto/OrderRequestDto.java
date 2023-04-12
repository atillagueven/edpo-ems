package ch.unisg.ems.api.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto implements Serializable {

    @Getter
    private String appointmentDate;

}
