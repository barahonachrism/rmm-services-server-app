package com.nubank.authorizer.test;

import com.nubank.authorizer.domain.entities.Device;
import com.nubank.authorizer.domain.services.IMonitorManagementService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class RemoteMonitoringManagementUnitTestStepDef {
    private Device device;
    @Autowired
    private IMonitorManagementService monitorManagementService;

    @Given("Device data, ID device: {string}, system name: {string}, and type: {string}")
    public void device_data_id_device_system_name_and_type(String idDevice, String systemName, String typeDevice) {
        UUID id = UUID.fromString(idDevice);
        device = Device.builder().id(id).systemName(systemName).type(typeDevice).build();
    }

    @Then("Added device successfully")
    public void added_device_successfully() {
        monitorManagementService.createDevice(device);
    }

    @Then("Return device data ID device: {string}, system name: {string}, and type: {string}")
    public void return_device_data_id_device_system_name_and_type(String idDevice, String systemName, String typeDevice) {
        Optional<Device> optionalDevice = monitorManagementService.findDeviceById(device.getId());
        if(optionalDevice.isPresent()){
            device = optionalDevice.get();
            Assertions.assertEquals(device.getId(),idDevice);
            Assertions.assertEquals(device.getSystemName(), systemName);
            Assertions.assertEquals(device.getType(),typeDevice);
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
}
