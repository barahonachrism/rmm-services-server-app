package com.ninjaone.rmm.application.controllers;

import com.ninjaone.rmm.domain.entities.DeviceService;
import com.ninjaone.rmm.domain.services.IMonitorManagementService;
import com.ninjaone.rmm.domain.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ServiceController {
    @Autowired
    private IMonitorManagementService monitorManagementService;

    @PostMapping(value = "/private/services")
    public ResponseEntity<AddServiceResponse> addService(@RequestBody AddServiceRequest serviceRequest) {
        DeviceService deviceService = monitorManagementService.createDeviceService(DeviceService.builder()
                        .id(UUID.randomUUID())
                        .serviceCatalogId(serviceRequest.getServiceCatalogId())
                        .customerId(serviceRequest.getCustomerId())
                        .deviceId(serviceRequest.getDeviceId())
                .build());
        AddServiceResponse serviceResponse = AddServiceResponse.builder()
                .deviceServiceId(deviceService.getId())
                .serviceCatalogId(deviceService.getServiceCatalogId())
                .customerId(deviceService.getCustomerId())
                .deviceId(deviceService.getDeviceId())
                .build();

        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/private/services")
    public ResponseEntity<String> deleteService(@RequestBody DeleteServiceRequest serviceRequest) {
        monitorManagementService.deleteDeviceService(serviceRequest.getDeviceServiceId());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    @GetMapping(value = "/private/services")
    public ResponseEntity<GetServicesResponse> getServices(@RequestParam String customerId) {
        List<ServiceVo> serviceVoList = monitorManagementService.findServicesByCustomerId(UUID.fromString(customerId));
        GetServicesResponse deviceResponse = GetServicesResponse.builder()
                .services(serviceVoList).build();
        return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/private/services/monthly-cost")
    public ResponseEntity<GetMonthlyCostServicesResponse> getMonthlyCostServices(@RequestParam String customerId) {
        UUID customerUUID = UUID.fromString(customerId);
        GetMonthlyCostServicesResponse response = GetMonthlyCostServicesResponse.builder()
                .customerId(customerUUID)
                .monthlyCost(monitorManagementService.calculateMontlyCostByCustomerId(customerUUID)).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
