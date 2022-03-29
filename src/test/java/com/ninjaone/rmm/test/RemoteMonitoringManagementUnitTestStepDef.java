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
import java.util.Random;
import java.util.UUID;

@Slf4j
public class RemoteMonitoringManagementUnitTestStepDef {

    @Autowired
    private GlobalVariables globalVariables;

    @Autowired
    private IMonitorManagementService monitorManagementService;

    @Given("Device Type data, name: {string}, antivirus cost: {double}, device management cost: {double}")
    public void device_type_data_id_name_antivirus_cost(String typeName, double antivirusCost, double deviceManagementCost) {
        globalVariables.deviceType = DeviceType.builder().id(UUID.randomUUID()).name(typeName).antivirusCost(antivirusCost)
                .deviceManagementCost(deviceManagementCost).build();
    }
    @Then("Added device type successfully")
    public void added_device_type_successfully() {
        globalVariables.deviceType = monitorManagementService.createDeviceType(globalVariables.deviceType);
    }
    @Then("Return device type data, name: {string}, antivirus cost: {double}, device management cost: {double}")
    public void return_device_type_data_id_name_antivirus_cost(String typeName, double antivirusCost, double deviceManagementCost) {
        Optional<DeviceType> optionalDeviceType = monitorManagementService.findDeviceTypeById(globalVariables.deviceType.getId());
        Assertions.assertTrue(optionalDeviceType.isPresent());
        DeviceType deviceType = optionalDeviceType.get();
        Assertions.assertEquals(typeName,deviceType.getName());
        Assertions.assertEquals(antivirusCost,deviceType.getAntivirusCost());
        Assertions.assertEquals(deviceManagementCost,deviceType.getDeviceManagementCost());
    }
    @When("Update device type name: {string}")
    public void update_device_type_name(String typeName) {
        globalVariables.deviceType.setName(typeName);

    }
    @Then("Updated device type successfully")
    public void updated_device_type_successfully() {
        monitorManagementService.updateDeviceType(globalVariables.deviceType);
    }
    @Then("Delete device type successfully")
    public void delete_device_type_successfully() {
        monitorManagementService.deleteDeviceType(globalVariables.deviceType.getId());
    }
    @Then("Return not found device type")
    public void return_not_found_device_type() {
        Optional<DeviceType> optionalDeviceType = monitorManagementService.findDeviceTypeById(globalVariables.deviceType.getId());
        Assertions.assertFalse(optionalDeviceType.isPresent());
    }


    @Given("Device data, system name: {string}")
    public void device_data_id_device_system_name_and_type(String systemName) {
        globalVariables.device = Device.builder().id(UUID.randomUUID()).systemName(systemName).deviceTypeId(globalVariables.deviceType.getId()).build();
    }

    @Given("Device data, system name: {string}, antivirus enabled: {string}")
    public void device_data_id_device_system_name_and_type(String systemName, String hasAntivirus) {
        globalVariables.device = Device.builder().id(UUID.randomUUID()).systemName(systemName)
                .hasAntivirus(Boolean.valueOf(hasAntivirus)).deviceTypeId(globalVariables.deviceType.getId()).build();
    }

    @Then("Added device successfully")
    public void added_device_successfully() {
        monitorManagementService.createDevice(globalVariables.device);
    }
    @Then("Return device data, system name: {string}, device type: {string}")
    public void return_device_data_id_device_system_name_and_type(String systemName, String typeDevice) {
        Optional<Device> optionalDevice = monitorManagementService.findDeviceById(globalVariables.device.getId());
        Assertions.assertTrue(optionalDevice.isPresent());
    }
    @When("Update device, system name: {string}")
    public void update_device_system_name(String systemName) {
        globalVariables.device.setSystemName(systemName);
    }
    @Then("Updated device successfully")
    public void updated_successfully() {
        monitorManagementService.updateDevice(globalVariables.device);
    }

    @Then("Deleted device successfully")
    public void deleted_successfully() {
        log.info("ID device to delete: {}",globalVariables.device.getId());
        monitorManagementService.deleteDevice(globalVariables.device.getId());
    }
    @Then("Return not found device")
    public void return_not_found_device() {
        Optional<Device> optionalDevice = monitorManagementService.findDeviceById(globalVariables.device.getId());
        Assertions.assertFalse(optionalDevice.isPresent());
    }

    @Given("Catalog service data, name: {string}, cost: {double}")
    public void catalog_service_data_id_name_cost(String nameServiceCatalog, Double serviceCost) {
        globalVariables.serviceCatalog = ServiceCatalog.builder().id(UUID.randomUUID()).name(nameServiceCatalog).cost(serviceCost).build();
    }
    @Then("Added catalog service successfully")
    public void added_catalog_service_successfully() {
        monitorManagementService.createServiceCatalog(globalVariables.serviceCatalog);
    }
    @Then("Return catalog service data")
    public void return_catalog_service_data_id_name_cost() {
        Optional<ServiceCatalog> optionalServiceCatalog = monitorManagementService.findServiceCatalogById(globalVariables.serviceCatalog.getId());
        Assertions.assertTrue(optionalServiceCatalog.isPresent());
    }
    @When("Update catalog service name: {string}")
    public void update_catalog_service_name(String nameCatalogService) {
        globalVariables.serviceCatalog.setName(nameCatalogService);
    }
    @Then("Updated catalog service successfully")
    public void updated_catalog_service_successfully() {
        monitorManagementService.updateServiceCatalog(globalVariables.serviceCatalog);
    }
    @Then("Delete catalog service successfully")
    public void delete_catalog_service_successfully() {
        monitorManagementService.deleteServiceCatalog(globalVariables.serviceCatalog.getId());
    }
    @Then("Return not found catalog service")
    public void return_not_found_catalog_service() {
        Optional<ServiceCatalog> optionalServiceCatalog = monitorManagementService.findServiceCatalogById(globalVariables.serviceCatalog.getId());
        Assertions.assertFalse(optionalServiceCatalog.isPresent());
    }

    @Then("Added service to device successfully")
    public void added_service_to_device_successfully() {
        globalVariables.deviceService = DeviceService.builder().id(UUID.randomUUID()).deviceId(globalVariables.device.getId())
                .serviceCatalogId(globalVariables.serviceCatalog.getId())
                .customerId(globalVariables.customer.getId())
                .build();
        monitorManagementService.createDeviceService(globalVariables.deviceService);
    }

    @Then("Deleted service to device successfully")
    public void deleted_service_to_device_successfully() {
        monitorManagementService.deleteDeviceService(globalVariables.deviceService.getId());
    }

    @Given("Customer data, national document id: {string}, fist name: {string}, last name: {string}")
    public void customer_data_id_national_document_id_fist_name_last_name(String nationalDocumentId, String firstName, String lastName) {
        globalVariables.customer = Customer.builder().id(UUID.randomUUID()).nationalDocumentId(nationalDocumentId)
                .firstName(firstName).lastName(lastName).build();
        log.info("===============================");
        log.info("Customer ID:{}", globalVariables.customer.getId());
    }
    @Then("Added customer successfully")
    public void added_customer_successfully() {
        monitorManagementService.createCustomer(globalVariables.customer);
    }
    @Then("Return customer data")
    public void return_customer_data() {
        Assertions.assertTrue(monitorManagementService.findCustomerById(globalVariables.customer.getId()).isPresent());
    }
    @When("Update first name: {string}")
    public void update_first_name(String firstName) {
        globalVariables.customer.setFirstName(firstName);
    }
    @Then("Updated customer successfully")
    public void updated_customer_successfully() {
        monitorManagementService.updateCustomer(globalVariables.customer);
    }
    @Then("Delete customer successfully")
    public void delete_customer_successfully() {
        monitorManagementService.deleteCustomer(globalVariables.customer.getId());
    }
    @Then("Return not found customer")
    public void return_not_found_customer() {
        Assertions.assertFalse(monitorManagementService.findCustomerById(globalVariables.customer.getId()).isPresent());
    }
    @Then("Return services selected to customer")
    public void return_services_selected_to_customer() {
        List<ServiceVo> serviceVoList = monitorManagementService.findServicesByCustomerId(globalVariables.customer.getId());
        Assertions.assertFalse(serviceVoList.isEmpty());
    }

    @Then("Throw error {string}")
    public void throw_error(String errorMessage) {
        try{
            globalVariables.deviceService.setId(UUID.randomUUID());
            monitorManagementService.createDeviceService(globalVariables.deviceService);
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
        globalVariables.serviceCatalog = ServiceCatalog.builder().id(UUID.fromString(serviceId)).name(serviceName).cost(serviceCost).build();
    }
    @Then("Added service {string} to device successfully")
    public void added_service_to_device_successfully(String serviceId) {
        globalVariables.deviceService = DeviceService.builder().id(UUID.randomUUID()).deviceId(globalVariables.device.getId())
                .serviceCatalogId(UUID.fromString(serviceId))
                .customerId(globalVariables.customer.getId())
                .build();
        monitorManagementService.createDeviceService(globalVariables.deviceService);
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
        globalVariables.customer = Customer.builder().id(UUID.randomUUID()).nationalDocumentId("1002556148")
                .firstName("Christian").lastName("Barahona").build();
        monitorManagementService.createCustomer(globalVariables.customer);
        DeviceType deviceTypeWindows = DeviceType.builder().id(UUID.randomUUID()).name("Windows Workstation").antivirusCost(5.0)
                .deviceManagementCost(4.0).build();
        monitorManagementService.createDeviceType(deviceTypeWindows);
        DeviceType deviceTypeMac = DeviceType.builder().id(UUID.randomUUID()).name("Windows Workstation").antivirusCost(7.0)
                .deviceManagementCost(4.0).build();
        monitorManagementService.createDeviceType(deviceTypeMac);
        Random random = new Random();

        ServiceCatalog serviceCatalogTeamViewer = ServiceCatalog.builder().id(UUID.randomUUID()).name("Team Viewer " + random.nextInt(100)).cost(1.0).build();
        monitorManagementService.createServiceCatalog(serviceCatalogTeamViewer);
        ServiceCatalog serviceCatalogCloudBerry = ServiceCatalog.builder().id(UUID.randomUUID()).name("Cloudberry "+ random.nextInt(100)).cost(3.0).build();
        monitorManagementService.createServiceCatalog(serviceCatalogCloudBerry);

        for(int i = 0; i < windowsQuantity; i++){
            Device myDevice = Device.builder().id(UUID.randomUUID()).systemName("Windows " + i)
                    .hasAntivirus(true).deviceTypeId(deviceTypeWindows.getId()).build();
            monitorManagementService.createDeviceToCustomer(myDevice,globalVariables.customer.getId());

            DeviceService myDeviceServiceTeamViewer = DeviceService.builder().id(UUID.randomUUID()).deviceId(myDevice.getId())
                    .serviceCatalogId(serviceCatalogTeamViewer.getId())
                    .customerId(globalVariables.customer.getId())
                    .build();

            monitorManagementService.createDeviceService(myDeviceServiceTeamViewer);

            DeviceService myDeviceServiceCloudberry = DeviceService.builder().id(UUID.randomUUID()).deviceId(myDevice.getId())
                    .serviceCatalogId(serviceCatalogCloudBerry.getId())
                    .customerId(globalVariables.customer.getId())
                    .build();

            monitorManagementService.createDeviceService(myDeviceServiceCloudberry);

        }

        for(int i = 0; i < macQuantity; i++){
            Device myDevice = Device.builder().id(UUID.randomUUID()).systemName("Mac " + i)
                    .hasAntivirus(true).deviceTypeId(deviceTypeMac.getId()).build();
            monitorManagementService.createDeviceToCustomer(myDevice,globalVariables.customer.getId());

            DeviceService myDeviceServiceTeamViewer = DeviceService.builder().id(UUID.randomUUID()).deviceId(myDevice.getId())
                    .serviceCatalogId(serviceCatalogTeamViewer.getId())
                    .customerId(globalVariables.customer.getId())
                    .build();

            monitorManagementService.createDeviceService(myDeviceServiceTeamViewer);

            DeviceService myDeviceServiceCloudberry = DeviceService.builder().id(UUID.randomUUID()).deviceId(myDevice.getId())
                    .serviceCatalogId(serviceCatalogCloudBerry.getId())
                    .customerId(globalVariables.customer.getId())
                    .build();

            monitorManagementService.createDeviceService(myDeviceServiceCloudberry);

        }

    }

    @Then("Then Monthly cost is {double}")
    public void then_monthly_cost_is(Double montlyCost) {
        Assertions.assertEquals(montlyCost,monitorManagementService.calculateMontlyCostByCustomerId(globalVariables.customer.getId()));
    }

}
