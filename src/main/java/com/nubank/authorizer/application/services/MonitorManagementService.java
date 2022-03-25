package com.nubank.authorizer.application.services;

import com.nubank.authorizer.domain.entities.Device;
import com.nubank.authorizer.domain.entities.DeviceType;
import com.nubank.authorizer.domain.repositories.IMonitorManagementRepository;
import com.nubank.authorizer.domain.services.IMonitorManagementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class MonitorManagementService implements IMonitorManagementService {

    IMonitorManagementRepository monitorManagementRepository;

    public MonitorManagementService(IMonitorManagementRepository monitorManagementRepository){
        this.monitorManagementRepository = monitorManagementRepository;
    }

    @Transactional(readOnly = false)
    @Override
    public Device createDevice(Device device) {
        return monitorManagementRepository.createDevice(device);
    }

    @Override
    public Optional<Device> findDeviceById(UUID idDevice) {
        return Optional.empty();
    }
    @Transactional(readOnly = false)
    @Override
    public void deleteDevice(UUID idDevice) {
        monitorManagementRepository.deleteDevice(idDevice);
    }

    @Transactional(readOnly = false)
    @Override
    public Device updateDevice(Device device) {
        return monitorManagementRepository.updateDevice(device);
    }

    @Transactional(readOnly = false)
    @Override
    public DeviceType createDeviceType(DeviceType deviceType) {
        return monitorManagementRepository.createDeviceType(deviceType);
    }

    @Override
    public Optional<DeviceType> findDeviceTypeById(UUID idDeviceType) {
        return monitorManagementRepository.findDeviceTypeById(idDeviceType);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteDeviceType(UUID idDeviceType) {
        monitorManagementRepository.deleteDeviceType(idDeviceType);
    }

    @Transactional(readOnly = false)
    @Override
    public DeviceType updateDeviceType(DeviceType deviceType) {
        return monitorManagementRepository.updateDeviceType(deviceType);
    }
}
