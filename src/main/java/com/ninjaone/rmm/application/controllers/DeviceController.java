package com.ninjaone.rmm.application.controllers;

import com.ninjaone.rmm.domain.entities.Device;
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
public class DeviceController {

    @Autowired
    private IMonitorManagementService monitorManagementService;

    @GetMapping(value = "/private/devices")
    public ResponseEntity<GetDeviceResponse> getDevices(@RequestParam String customerId) {
        List<DeviceVo> devices = monitorManagementService.findDevicesByCustomerId(UUID.fromString(customerId));
        GetDeviceResponse deviceResponse = new GetDeviceResponse();
        deviceResponse.setDevices(devices);
        return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/private/devices")
    public ResponseEntity<ChangeDeviceResponse> addDevices(@RequestBody ChangeDeviceRequest deviceRequest){
        DeviceVo deviceVo = deviceRequest.getDevice();
        Device device = Device.builder()
                .id(deviceVo.getId())
                .deviceTypeId(deviceVo.getDeviceTypeId())
                .systemName(deviceVo.getSystemName())
                .hasAntivirus(deviceVo.isHasAntivirus())
                .build();
        device = monitorManagementService.createDeviceToCustomer(device,deviceRequest.getCustomerId());
        ChangeDeviceResponse addDeviceResponse = new ChangeDeviceResponse();
        deviceVo.setId(device.getId());
        addDeviceResponse.setDevice(deviceVo);
        return new ResponseEntity<>(addDeviceResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/private/devices")
    public ResponseEntity<ChangeDeviceResponse> updateDevices(@RequestBody ChangeDeviceRequest deviceRequest){
        DeviceVo deviceVo = deviceRequest.getDevice();
        Device device = Device.builder()
                .id(deviceVo.getId())
                .deviceTypeId(deviceVo.getDeviceTypeId())
                .systemName(deviceVo.getSystemName())
                .hasAntivirus(deviceVo.isHasAntivirus())
                .build();
        device = monitorManagementService.updateDeviceToCustomer(device,deviceRequest.getCustomerId());
        ChangeDeviceResponse changeDeviceResponse = new ChangeDeviceResponse();
        deviceVo.setId(device.getId());
        changeDeviceResponse.setDevice(deviceVo);
        return new ResponseEntity<>(changeDeviceResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/private/devices")
    public ResponseEntity<String> deleteDevices(@RequestBody DeleteDeviceRequest deviceRequest){
        monitorManagementService.deleteDeviceToCustomer(deviceRequest.getDeviceId(),deviceRequest.getCustomerId());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
