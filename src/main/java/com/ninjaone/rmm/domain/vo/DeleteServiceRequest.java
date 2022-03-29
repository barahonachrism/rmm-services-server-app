package com.ninjaone.rmm.domain.vo;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DeleteServiceRequest {
    private UUID deviceServiceId;
}
