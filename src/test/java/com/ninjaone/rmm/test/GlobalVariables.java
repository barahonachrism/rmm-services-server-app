package com.ninjaone.rmm.test;

import com.ninjaone.rmm.domain.entities.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class GlobalVariables {
    DeviceType deviceType;
    DeviceService deviceService;
    Customer customer;
    ServiceCatalog serviceCatalog;
    Device device;
}
