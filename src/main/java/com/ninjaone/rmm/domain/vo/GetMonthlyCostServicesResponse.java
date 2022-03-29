package com.ninjaone.rmm.domain.vo;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetMonthlyCostServicesResponse {
    private UUID customerId;
    private double monthlyCost;
}
