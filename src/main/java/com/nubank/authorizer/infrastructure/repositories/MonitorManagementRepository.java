package com.nubank.authorizer.infrastructure.repositories;

import com.nubank.authorizer.domain.entities.Device;
import com.nubank.authorizer.domain.entities.DeviceType;
import com.nubank.authorizer.domain.exceptions.ServiceManagementException;
import com.nubank.authorizer.domain.repositories.IMonitorManagementRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MonitorManagementRepository implements IMonitorManagementRepository {

    private EntityManager entityManager;
    public MonitorManagementRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Device createDevice(Device device) {
        if(device.getId() == null){
            device.setId(UUID.randomUUID());
        }
        entityManager.persist(device);
        return device;
    }

    @Override
    public Optional<Device> findDeviceById(UUID idDevice) {
        return Optional.of(entityManager.find(Device.class,idDevice));
    }

    @Override
    public void deleteDevice(UUID idDevice) {
        Optional<Device> optionalDevice = findDeviceById(idDevice);
        if(!optionalDevice.isPresent()){
            throw new ServiceManagementException("Device not found");
        }
        entityManager.remove(optionalDevice.get());

    }

    @Override
    public Device updateDevice(Device device) {
        return entityManager.merge(device);
    }

    @Override
    public DeviceType createDeviceType(DeviceType deviceType) {
        if(deviceType.getId() == null){
            deviceType.setId(UUID.randomUUID());
        }
        entityManager.persist(deviceType);
        return deviceType;
    }

    @Override
    public Optional<DeviceType> findDeviceTypeById(UUID idDeviceType) {
        DeviceType deviceType = entityManager.find(DeviceType.class, idDeviceType);
        return Optional.ofNullable(deviceType);
    }

    @Override
    public void deleteDeviceType(UUID idDeviceType) {
        Optional<DeviceType> optionalDeviceType = findDeviceTypeById(idDeviceType);
        if(!optionalDeviceType.isPresent()){
            throw new ServiceManagementException("Device type not found");
        }
        entityManager.remove(optionalDeviceType.get());
    }

    @Override
    public DeviceType updateDeviceType(DeviceType deviceType) {
        return entityManager.merge(deviceType);
    }
}
