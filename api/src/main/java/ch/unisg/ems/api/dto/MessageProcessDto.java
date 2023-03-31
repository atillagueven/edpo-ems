package ch.unisg.ems.api.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageProcessDto implements Serializable {

    private String requester;
}
