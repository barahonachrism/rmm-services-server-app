package com.nubank.authorizer.test;

import com.nubank.authorizer.domain.entities.Device;
import com.nubank.authorizer.domain.entities.DeviceType;
import com.nubank.authorizer.domain.services.IMonitorManagementService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public class TypeDeviceOperationUnitTestStepDef {

    DeviceType deviceType;

    @Autowired
    private IMonitorManagementService monitorManagementService;


    @Given("Device Type data, ID: {string}, name: {string}, antivirus cost: {double}")
    public void device_type_data_id_name_antivirus_cost(String id, String typeName, double antivirusCost) {
        deviceType = DeviceType.builder().id(UUID.fromString(id)).name(typeName).antivirusCost(antivirusCost).build();
    }
    @Then("Added device type successfully")
    public void added_device_type_successfully() {
        monitorManagementService.createDeviceType(deviceType);
    }
    @Then("Return device type data, ID: {string}, name: {string}, antivirus cost: {double}")
    public void return_device_type_data_id_name_antivirus_cost(String id, String typeName, double antivirusCost) {
        Optional<DeviceType> optionalDeviceType = monitorManagementService.findDeviceTypeById(deviceType.getId());
        Assertions.assertTrue(optionalDeviceType.isPresent());
        DeviceType deviceType = optionalDeviceType.get();
        Assertions.assertEquals(id,deviceType.getId().toString());
        Assertions.assertEquals(typeName,deviceType.getName());
        Assertions.assertEquals(antivirusCost,deviceType.getAntivirusCost());
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
}
