package com.nubank.authorizer.domain.services;

import com.nubank.authorizer.domain.entities.Device;
import com.nubank.authorizer.domain.entities.DeviceType;

import java.util.Optional;
import java.util.UUID;

public interface IMonitorManagementService {
    Device createDevice(Device device);
    Optional<Device> findDeviceById(UUID idDevice);
    void deleteDevice(UUID idDevice);
    Device updateDevice(Device device);

    DeviceType createDeviceType(DeviceType deviceType);
    Optional<DeviceType> findDeviceTypeById(UUID idDeviceType);
    void deleteDeviceType(UUID idDeviceType);
    DeviceType updateDeviceType(DeviceType deviceType);
}
