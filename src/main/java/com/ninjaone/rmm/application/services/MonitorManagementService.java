package com.ninjaone.rmm.application.services;

import com.ninjaone.rmm.domain.common.Parameters;
import com.ninjaone.rmm.domain.entities.*;
import com.ninjaone.rmm.domain.exceptions.ServiceManagementException;
import com.ninjaone.rmm.domain.repositories.IMonitorManagementRepository;
import com.ninjaone.rmm.domain.vo.DeviceVo;
import com.ninjaone.rmm.domain.vo.ServiceVo;
import com.ninjaone.rmm.domain.services.IMonitorManagementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(readOnly = false)
    @Override
    public Device createDeviceToCustomer(Device device, UUID customerId){
        Optional<ServiceCatalog> optionalServiceCatalog = monitorManagementRepository.findServiceCatalogByName(Parameters.MONITORING_SERVICE_NAME);
        ServiceCatalog serviceCatalog;
        if(optionalServiceCatalog.isPresent()){
            serviceCatalog = optionalServiceCatalog.get();
        }
        else{
            Optional<DeviceType> optionalDeviceType = findDeviceTypeById(device.getDeviceTypeId());

            if(optionalDeviceType.isPresent()){
                serviceCatalog = ServiceCatalog.builder().id(UUID.randomUUID())
                        .name(Parameters.MONITORING_SERVICE_NAME)
                        .cost(optionalDeviceType.get().getDeviceManagementCost()).build();
                monitorManagementRepository.createServiceCatalog(serviceCatalog);
            }
            else{
                throw new ServiceManagementException("Device type "+ device.getDeviceTypeId()+ " not found");
            }
        }

        Device createdDevice = monitorManagementRepository.createDevice(device);
        DeviceService deviceService = DeviceService.builder().id(UUID.randomUUID())
                        .serviceCatalogId(serviceCatalog.getId())
                                .deviceId(device.getId())
                                        .customerId(customerId).build();
        monitorManagementRepository.createDeviceService(deviceService);
        return createdDevice;
    }

    @Override
    public Optional<Device> findDeviceById(UUID idDevice) {
        return monitorManagementRepository.findDeviceById(idDevice);
    }

    @Override
    public List<DeviceVo> findDevicesByCustomerId(UUID customerId){
        return monitorManagementRepository.findDevicesByCustomerId(customerId);
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
    public Device updateDeviceToCustomer(Device device,UUID customerId){
        Optional<Device> deviceToUpdate = monitorManagementRepository.findDeviceByIdAndCustomerId(device.getId(),customerId);
        if(deviceToUpdate.isPresent()){
            return updateDevice(device);
        }
        else{
            throw new ServiceManagementException("Device not found");
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteDeviceToCustomer(UUID deviceId,UUID customerId){
        Optional<Device> deviceToUpdate = monitorManagementRepository.findDeviceByIdAndCustomerId(deviceId,customerId);
        if(deviceToUpdate.isPresent()){
            monitorManagementRepository.deleteDeviceServiceByDeviceId(deviceId);
            deleteDevice(deviceId);
        }
        else{
            throw new ServiceManagementException("Device not found");
        }
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

    @Transactional(readOnly = false)
    @Override
    public ServiceCatalog createServiceCatalog(ServiceCatalog serviceCatalog) {
        return monitorManagementRepository.createServiceCatalog(serviceCatalog);
    }

    @Override
    public Optional<ServiceCatalog> findServiceCatalogById(UUID idServiceCatalog) {
        return monitorManagementRepository.findServiceCatalogById(idServiceCatalog);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteServiceCatalog(UUID idServiceCatalog) {
        monitorManagementRepository.deleteDeviceServiceByServiceId(idServiceCatalog);
        monitorManagementRepository.deleteServiceCatalog(idServiceCatalog);
    }

    @Transactional(readOnly = false)
    @Override
    public ServiceCatalog updateServiceCatalog(ServiceCatalog serviceCatalog) {
        return monitorManagementRepository.updateServiceCatalog(serviceCatalog);
    }

    @Transactional(readOnly = false)
    @Override
    public DeviceService createDeviceService(DeviceService deviceService) {
        return monitorManagementRepository.createDeviceService(deviceService);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteDeviceService(UUID deviceServiceId) {
        monitorManagementRepository.deleteDeviceService(deviceServiceId);
    }

    @Transactional(readOnly = false)
    @Override
    public Customer createCustomer(Customer customer) {
        return monitorManagementRepository.createCustomer(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(UUID customerId) {
        return monitorManagementRepository.findCustomerById(customerId);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteCustomer(UUID customerId) {
        monitorManagementRepository.deleteCustomer(customerId);
    }

    @Transactional(readOnly = false)
    @Override
    public Customer updateCustomer(Customer customer) {
        return monitorManagementRepository.updateCustomer(customer);
    }

    @Override
    public List<ServiceVo> findServicesByCustomerId(UUID customerId) {
        return monitorManagementRepository.findServicesByCustomerId(customerId);
    }

    @Override
    public double calculateMontlyCostByCustomerId(UUID customerId) {
        return monitorManagementRepository.findDeviceWithServicesByCustomerID(customerId);
    }
}
