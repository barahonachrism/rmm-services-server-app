package com.ninjaone.rmm.infrastructure.repositories;

import com.ninjaone.rmm.domain.entities.*;
import com.ninjaone.rmm.domain.exceptions.ServiceManagementException;
import com.ninjaone.rmm.domain.repositories.IMonitorManagementRepository;
import com.ninjaone.rmm.domain.vo.ServiceVo;
import com.ninjaone.rmm.domain.vo.QServiceVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MonitorManagementRepository implements IMonitorManagementRepository {
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    public MonitorManagementRepository(EntityManager entityManager, JPAQueryFactory queryFactory) {
        this.entityManager = entityManager;
        this.queryFactory = queryFactory;
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
        Query query = entityManager.createNamedQuery("device.deviceWithType");
        query.setParameter("deviceId",idDevice);
        return Optional.ofNullable((Device)query.getSingleResult());
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

    @Override
    public ServiceCatalog createServiceCatalog(ServiceCatalog serviceCatalog) {
        if(serviceCatalog.getId() == null){
            serviceCatalog.setId(UUID.randomUUID());
        }
        entityManager.persist(serviceCatalog);
        return serviceCatalog;
    }

    @Override
    public Optional<ServiceCatalog> findServiceCatalogById(UUID idServiceCatalog) {
        ServiceCatalog serviceCatalog = entityManager.find(ServiceCatalog.class, idServiceCatalog);
        return Optional.ofNullable(serviceCatalog);
    }

    @Override
    public void deleteServiceCatalog(UUID idServiceCatalog) {
        Optional<ServiceCatalog> optionalServiceCatalog = findServiceCatalogById(idServiceCatalog);
        if(!optionalServiceCatalog.isPresent()){
            throw new ServiceManagementException("Service catalog not found");
        }
        entityManager.remove(optionalServiceCatalog.get());
    }

    @Override
    public ServiceCatalog updateServiceCatalog(ServiceCatalog serviceCatalog) {
        return entityManager.merge(serviceCatalog);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if(customer.getId() == null){
            customer.setId(UUID.randomUUID());
        }
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public Optional<Customer> findCustomerById(UUID customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        return Optional.ofNullable(customer);
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        Optional<Customer> optionalCustomer = findCustomerById(customerId);
        if(!optionalCustomer.isPresent()){
            throw new ServiceManagementException("Customer not found");
        }
        entityManager.remove(optionalCustomer.get());
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public DeviceService createDeviceService(DeviceService deviceService) {
        QDeviceService qDeviceService = QDeviceService.deviceService;
        List<DeviceService> deviceServiceList = queryFactory.selectFrom(qDeviceService).fetch();
        Optional<Integer> optionalExistValue = Optional.ofNullable(queryFactory.select(Expressions.ONE)
                        .from(qDeviceService)
                .where(qDeviceService.device.id.eq(deviceService.getDevice().getId())
                        .and(qDeviceService.serviceCatalog.id.eq(deviceService.getServiceCatalog().getId()))
                        .and(qDeviceService.customer.id.eq(deviceService.getCustomer().getId()))
                )
                .fetchOne());

        if(optionalExistValue.isPresent()){
            throw new ServiceManagementException("Service already exists");
        }

        if(deviceService.getId() == null){
            deviceService.setId(UUID.randomUUID());
        }
        entityManager.persist(deviceService);
        return  deviceService;
    }

    @Override
    public void deleteDeviceService(UUID deviceServiceID) {
        Optional<DeviceService> optionalDeviceService = Optional.ofNullable(entityManager.find(DeviceService.class, deviceServiceID));
        if(!optionalDeviceService.isPresent()){
            throw new ServiceManagementException("Device service not found");
        }
        entityManager.remove(optionalDeviceService.get());
    }

    @Override
    public void deleteDeviceServiceByServiceId(UUID serviceID) {
        QDeviceService qDeviceService = QDeviceService.deviceService;
        queryFactory.delete(qDeviceService).where(qDeviceService.serviceCatalog.id.eq(serviceID)).execute();
    }


    @Override
    public List<ServiceVo> findServicesByCustomerId(UUID customerId) {
        QDeviceService qDeviceService = QDeviceService.deviceService;
        QDevice qDevice = QDevice.device;
        QDeviceType qDeviceType = QDeviceType.deviceType;
        QServiceCatalog qServiceCatalog = QServiceCatalog.serviceCatalog;
        QCustomer qCustomer = QCustomer.customer;
        QServiceVo qServiceVo = QServiceVo.serviceVo;

        return queryFactory.select(Projections.bean(
                        ServiceVo.class,
                        qDeviceService.id.as(qServiceVo.id),
                        qDeviceService.serviceCatalog.id.as(qServiceVo.serviceId),
                        qDeviceService.customer.id.as(qServiceVo.customerId),
                        qDeviceService.device.id.as((qServiceVo.deviceId)),
                        qDeviceService.customer.firstName.as(qServiceVo.firstNameCustomer),
                        qDeviceService.customer.lastName.as(qServiceVo.lastNameCustomer),
                        qDeviceService.serviceCatalog.name.as(qServiceVo.serviceName),
                        qDeviceService.device.systemName.as(qServiceVo.systemName),
                        qDeviceService.device.deviceType.name.as(qServiceVo.deviceTypeName),
                        qDeviceService.serviceCatalog.cost.as(qServiceVo.costService)
                )).from(qDeviceService).innerJoin(qDeviceService.device,qDevice)
                .innerJoin(qDevice.deviceType,qDeviceType)
                .innerJoin(qDeviceService.serviceCatalog,qServiceCatalog)
                .innerJoin(qDeviceService.customer, qCustomer)
                .where(qCustomer.id.eq(customerId)).fetch();
    }

    public List<Device> findDeviceWithServicesByCustomerID(UUID customerId){
       Query query = entityManager.createQuery("select distinct d from Device d " +
               "inner join fetch d.deviceType dt" +
               "inner join fetch d.deviceServices ds " +
               "inner join fetch ds.serviceCatalog s " +
               "where ds.customer.id =:customerId");
       query.setParameter("customerId",customerId);
       return query.getResultList();
    }


}
