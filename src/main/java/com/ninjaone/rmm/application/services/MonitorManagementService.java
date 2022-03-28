package com.ninjaone.rmm.application.services;

import com.ninjaone.rmm.domain.entities.*;
import com.ninjaone.rmm.domain.repositories.IMonitorManagementRepository;
import com.ninjaone.rmm.domain.vo.ServiceVo;
import com.ninjaone.rmm.domain.services.IMonitorManagementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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
        //Get all services enabled to customer
        List<Device> deviceList = monitorManagementRepository.findDeviceWithServicesByCustomerID(customerId);
        double costPerDevice = deviceList.stream().map(device ->
                device.isHasAntivirus()?
                device.getDeviceType().getAntivirusCost() +
                device.getDeviceType().getDeviceManagementCost() : 0.0
        ).reduce(0.0,(previosCost, currentCost) ->
             (previosCost + currentCost)
        );
        AtomicReference<Double> costPerService = new AtomicReference<>(0.0);
        deviceList.stream().forEach(device -> {
            device.getDeviceServices().forEach( deviceService -> {
                costPerService.set(costPerService.get() + deviceService.getServiceCatalog().getCost());
            });
        });

        return costPerService.get() + costPerDevice;
    }
}
