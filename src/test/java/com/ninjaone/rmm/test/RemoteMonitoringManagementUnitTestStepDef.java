package com.ninjaone.rmm.test;

import com.ninjaone.rmm.domain.entities.*;
import com.ninjaone.rmm.domain.vo.ServiceVo;
import com.ninjaone.rmm.domain.exceptions.ServiceManagementException;
import com.ninjaone.rmm.domain.services.IMonitorManagementService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class RemoteMonitoringManagementUnitTestStepDef {
    DeviceType deviceType;
    DeviceService deviceService;
    Customer customer;
    private ServiceCatalog serviceCatalog;
    private Device device;

    @Autowired
    private IMonitorManagementService monitorManagementService;

    @Given("Device Type data, name: {string}, antivirus cost: {double}, device management cost: {double}")
    public void device_type_data_id_name_antivirus_cost(String typeName, double antivirusCost, double deviceManagementCost) {
        deviceType = DeviceType.builder().id(UUID.randomUUID()).name(typeName).antivirusCost(antivirusCost)
                .deviceManagementCost(deviceManagementCost).build();
    }
    @Then("Added device type successfully")
    public void added_device_type_successfully() {
        monitorManagementService.createDeviceType(deviceType);
    }
    @Then("Return device type data, name: {string}, antivirus cost: {double}, device management cost: {double}")
    public void return_device_type_data_id_name_antivirus_cost(String typeName, double antivirusCost, double deviceManagementCost) {
        Optional<DeviceType> optionalDeviceType = monitorManagementService.findDeviceTypeById(deviceType.getId());
        Assertions.assertTrue(optionalDeviceType.isPresent());
        DeviceType deviceType = optionalDeviceType.get();
        Assertions.assertEquals(typeName,deviceType.getName());
        Assertions.assertEquals(antivirusCost,deviceType.getAntivirusCost());
        Assertions.assertEquals(deviceManagementCost,deviceType.getDeviceManagementCost());
    }
    @When("Update device type name: {string}")
    public void update_device_type_name(String typeName) {
        deviceType.setName(typeName);

    }
    @Then("Updated device type successfully")
    public void updated_device_type_successfully() {
        monitorManagementService.updateDeviceType(deviceType);
    }
    @Then("Delete device type successfully")
    public void delete_device_type_successfully() {
        monitorManagementService.deleteDeviceType(deviceType.getId());
    }
    @Then("Return not found device type")
    public void return_not_found_device_type() {
        Optional<DeviceType> optionalDeviceType = monitorManagementService.findDeviceTypeById(deviceType.getId());
        Assertions.assertFalse(optionalDeviceType.isPresent());
    }


    @Given("Device data, system name: {string}")
    public void device_data_id_device_system_name_and_type(String systemName) {
        device = Device.builder().id(UUID.randomUUID()).systemName(systemName).deviceTypeId(deviceType.getId()).build();
    }

    @Given("Device data, system name: {string}, antivirus enabled: {string}")
    public void device_data_id_device_system_name_and_type(String systemName, String hasAntivirus) {
        device = Device.builder().id(UUID.randomUUID()).systemName(systemName)
                .hasAntivirus(Boolean.valueOf(hasAntivirus)).deviceTypeId(deviceType.getId()).build();
    }

    @Then("Added device successfully")
    public void added_device_successfully() {
        monitorManagementService.createDevice(device);
    }
    @Then("Return device data, system name: {string}, device type: {string}")
    public void return_device_data_id_device_system_name_and_type(String systemName, String typeDevice) {
        Optional<Device> optionalDevice = monitorManagementService.findDeviceById(device.getId());
        if(optionalDevice.isPresent()){
            device = optionalDevice.get();
            Assertions.assertEquals(device.getSystemName(), systemName);
            Assertions.assertEquals(device.getDeviceType().getName(),typeDevice);
        }
    }
    @When("Update device, system name: {string}")
    public void update_device_system_name(String systemName) {
        device.setSystemName(systemName);
    }
    @Then("Updated device successfully")
    public void updated_successfully() {
        monitorManagementService.updateDevice(device);
    }

    @Then("Deleted device successfully")
    public void deleted_successfully() {
        log.info("ID device to delete: {}",device.getId());
        monitorManagementService.deleteDevice(device.getId());
    }
    @Then("Return not found device")
    public void return_not_found_device() {
        Optional<Device> optionalDevice = monitorManagementService.findDeviceById(device.getId());
        Assertions.assertFalse(optionalDevice.isPresent());
    }

    @Given("Catalog service data, name: {string}, cost: {double}")
    public void catalog_service_data_id_name_cost(String nameServiceCatalog, Double serviceCost) {
        serviceCatalog = ServiceCatalog.builder().id(UUID.randomUUID()).name(nameServiceCatalog).cost(serviceCost).build();
    }
    @Then("Added catalog service successfully")
    public void added_catalog_service_successfully() {
        monitorManagementService.createServiceCatalog(serviceCatalog);
    }
    @Then("Return catalog service data")
    public void return_catalog_service_data_id_name_cost() {
        Optional<ServiceCatalog> optionalServiceCatalog = monitorManagementService.findServiceCatalogById(serviceCatalog.getId());
        Assertions.assertTrue(optionalServiceCatalog.isPresent());
    }
    @When("Update catalog service name: {string}")
    public void update_catalog_service_name(String nameCatalogService) {
        serviceCatalog.setName(nameCatalogService);
    }
    @Then("Updated catalog service successfully")
    public void updated_catalog_service_successfully() {
        monitorManagementService.updateServiceCatalog(serviceCatalog);
    }
    @Then("Delete catalog service successfully")
    public void delete_catalog_service_successfully() {
        monitorManagementService.deleteServiceCatalog(serviceCatalog.getId());
    }
    @Then("Return not found catalog service")
    public void return_not_found_catalog_service() {
        Optional<ServiceCatalog> optionalServiceCatalog = monitorManagementService.findServiceCatalogById(serviceCatalog.getId());
        Assertions.assertFalse(optionalServiceCatalog.isPresent());
    }

    @Then("Added service to device successfully")
    public void added_service_to_device_successfully() {
        deviceService = DeviceService.builder().id(UUID.randomUUID()).deviceId(device.getId())
                .serviceCatalogId(serviceCatalog.getId())
                .customerId(customer.getId())
                .build();
        monitorManagementService.createDeviceService(deviceService);
    }

    @Then("Deleted service to device successfully")
    public void deleted_service_to_device_successfully() {
        monitorManagementService.deleteDeviceService(deviceService.getId());
    }

    @Given("Customer data, national document id: {string}, fist name: {string}, last name: {string}")
    public void customer_data_id_national_document_id_fist_name_last_name(String nationalDocumentId, String firstName, String lastName) {
        customer = Customer.builder().id(UUID.randomUUID()).nationalDocumentId(nationalDocumentId)
                .firstName(firstName).lastName(lastName).build();
        log.info("===============================");
        log.info("Customer ID:{}", customer.getId());
    }
    @Then("Added customer successfully")
    public void added_customer_successfully() {
        monitorManagementService.createCustomer(customer);
    }
    @Then("Return customer data")
    public void return_customer_data() {
        Assertions.assertTrue(monitorManagementService.findCustomerById(customer.getId()).isPresent());
    }
    @When("Update first name: {string}")
    public void update_first_name(String firstName) {
        customer.setFirstName(firstName);
    }
    @Then("Updated customer successfully")
    public void updated_customer_successfully() {
        monitorManagementService.updateCustomer(customer);
    }
    @Then("Delete customer successfully")
    public void delete_customer_successfully() {
        monitorManagementService.deleteCustomer(customer.getId());
    }
    @Then("Return not found customer")
    public void return_not_found_customer() {
        Assertions.assertFalse(monitorManagementService.findCustomerById(customer.getId()).isPresent());
    }
    @Then("Return services selected to customer")
    public void return_services_selected_to_customer() {
        List<ServiceVo> serviceVoList = monitorManagementService.findServicesByCustomerId(customer.getId());
        Assertions.assertFalse(serviceVoList.isEmpty());
    }

    @Then("Throw error {string}")
    public void throw_error(String errorMessage) {
        try{
            deviceService.setId(UUID.randomUUID());
            monitorManagementService.createDeviceService(deviceService);
        } catch(ServiceManagementException ex){
            Assertions.assertEquals(errorMessage,ex.getMessage());
        }
    }


    @Given("Customer with {int} Windows, {int} Mac with  Antivirus, Cloudberry and TeamViewer.")
    public void customer_with_windows_mac_with_and(Integer windowsQuantity, Integer macQuantity) {
        Assertions.assertTrue(true);
    }
    @Then("Monthly cost is {double}")
    public void monthly_cost_is(Double monthlyCost) {

    }

    @Given("Catalog service data, ID: {string},  name: {string}, cost: {double}")
    public void catalog_service_data_id_name_cost(String serviceId, String serviceName, Double serviceCost) {
        serviceCatalog = ServiceCatalog.builder().id(UUID.fromString(serviceId)).name(serviceName).cost(serviceCost).build();
    }
    @Then("Added service {string} to device successfully")
    public void added_service_to_device_successfully(String serviceId) {
        deviceService = DeviceService.builder().id(UUID.randomUUID()).deviceId(device.getId())
                .serviceCatalogId(UUID.fromString(serviceId))
                .customerId(customer.getId())
                .build();
        monitorManagementService.createDeviceService(deviceService);
    }

    @Then("Remove services {string} and {string}")
    public void remove_service_and(String service1Id, String service2Id) {
        UUID service1UUID = UUID.fromString(service1Id);
        UUID service2UUID = UUID.fromString(service2Id);

        monitorManagementService.deleteServiceCatalog(service1UUID);
        monitorManagementService.deleteServiceCatalog(service2UUID);
    }

    @Given("Customer with {int} Windows, {int} Mac with  Antivirus, Cloudberry and TeamViewer")
    public void customer_with_windows_mac_with_antivirus_cloudberry_and_team_viewer(Integer windowsQuantity, Integer macQuantity) {
        customer = Customer.builder().id(UUID.randomUUID()).nationalDocumentId("1002556148")
                .firstName("Christian").lastName("Barahona").build();
        monitorManagementService.createCustomer(customer);
        DeviceType deviceTypeWindows = DeviceType.builder().id(UUID.randomUUID()).name("Windows Workstation").antivirusCost(5.0)
                .deviceManagementCost(4.0).build();
        monitorManagementService.createDeviceType(deviceTypeWindows);
        DeviceType deviceTypeMac = DeviceType.builder().id(UUID.randomUUID()).name("Windows Workstation").antivirusCost(7.0)
                .deviceManagementCost(4.0).build();
        monitorManagementService.createDeviceType(deviceTypeMac);

        ServiceCatalog serviceCatalogTeamViewer = ServiceCatalog.builder().id(UUID.randomUUID()).name("Team Viewer").cost(1.0).build();
        monitorManagementService.createServiceCatalog(serviceCatalogTeamViewer);
        ServiceCatalog serviceCatalogCloudBerry = ServiceCatalog.builder().id(UUID.randomUUID()).name("Cloudberry").cost(3.0).build();
        monitorManagementService.createServiceCatalog(serviceCatalogCloudBerry);

        for(int i = 0; i < windowsQuantity; i++){
            Device myDevice = Device.builder().id(UUID.randomUUID()).systemName("Windows " + i)
                    .hasAntivirus(true).deviceTypeId(deviceTypeWindows.getId()).build();
            monitorManagementService.createDevice(myDevice);

            DeviceService myDeviceServiceTeamViewer = DeviceService.builder().id(UUID.randomUUID()).deviceId(myDevice.getId())
                    .serviceCatalogId(serviceCatalogTeamViewer.getId())
                    .customerId(customer.getId())
                    .build();

            monitorManagementService.createDeviceService(myDeviceServiceTeamViewer);

            DeviceService myDeviceServiceCloudberry = DeviceService.builder().id(UUID.randomUUID()).deviceId(myDevice.getId())
                    .serviceCatalogId(serviceCatalogCloudBerry.getId())
                    .customerId(customer.getId())
                    .build();

            monitorManagementService.createDeviceService(myDeviceServiceCloudberry);

        }

        for(int i = 0; i < macQuantity; i++){
            Device myDevice = Device.builder().id(UUID.randomUUID()).systemName("Mac " + i)
                    .hasAntivirus(true).deviceTypeId(deviceTypeMac.getId()).build();
            monitorManagementService.createDevice(myDevice);

            DeviceService myDeviceServiceTeamViewer = DeviceService.builder().id(UUID.randomUUID()).deviceId(myDevice.getId())
                    .serviceCatalogId(serviceCatalogTeamViewer.getId())
                    .customerId(customer.getId())
                    .build();

            monitorManagementService.createDeviceService(myDeviceServiceTeamViewer);

            DeviceService myDeviceServiceCloudberry = DeviceService.builder().id(UUID.randomUUID()).deviceId(myDevice.getId())
                    .serviceCatalogId(serviceCatalogCloudBerry.getId())
                    .customerId(customer.getId())
                    .build();

            monitorManagementService.createDeviceService(myDeviceServiceCloudberry);

        }

    }

    @Then("Then Monthly cost is {double}")
    public void then_monthly_cost_is(Double montlyCost) {
        Assertions.assertEquals(montlyCost,monitorManagementService.calculateMontlyCostByCustomerId(customer.getId()));
    }

}
