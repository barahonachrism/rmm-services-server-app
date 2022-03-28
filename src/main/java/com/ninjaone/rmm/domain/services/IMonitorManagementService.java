package com.ninjaone.rmm.domain.services;

import com.ninjaone.rmm.domain.entities.*;
import com.ninjaone.rmm.domain.vo.ServiceVo;

import java.util.List;
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

    ServiceCatalog createServiceCatalog(ServiceCatalog serviceCatalog);
    Optional<ServiceCatalog> findServiceCatalogById(UUID idServiceCatalog);
    void deleteServiceCatalog(UUID idServiceCatalog);
    ServiceCatalog updateServiceCatalog(ServiceCatalog serviceCatalog);

    DeviceService createDeviceService(DeviceService deviceService);
    void deleteDeviceService(UUID deviceServiceId);

    Customer createCustomer(Customer customer);
    Optional<Customer> findCustomerById(UUID customerId);
    void deleteCustomer(UUID customerId);
    Customer updateCustomer(Customer customer);
    List<ServiceVo> findServicesByCustomerId(UUID customerId);

    double calculateMontlyCostByCustomerId(UUID customerId);
}
