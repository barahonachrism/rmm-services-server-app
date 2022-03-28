package com.ninjaone.rmm.domain.vo;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@QueryEntity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceVo {
    private UUID id;
    private UUID serviceId;
    private UUID customerId;
    private UUID deviceId;
    private String firstNameCustomer;
    private String lastNameCustomer;
    private String serviceName;
    private String systemName;
    private String deviceTypeName;
    private double costService;
}
