package com.ninjaone.rmm.test;

import com.ninjaone.rmm.domain.entities.Device;
import com.ninjaone.rmm.domain.entities.DeviceService;
import com.ninjaone.rmm.domain.vo.*;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class DeviceControllerFunctionalTestStepDef {
    @Autowired
    private TestRestTemplate restTemplate;
    private TokenResponse tokenResponse = null;
    @Autowired
    private GlobalVariables globalVariables;

    public TokenResponse getToken(){
        if(tokenResponse ==null){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            TokenRequest tokenRequest = TokenRequest.builder().clientId("pJTTsL8rQ124e8qBdRtDen1spPGPaMiH")
                    .clientSecret("OPaf0QoWVWaHxIX_S1CZ3KEI5OUqolUsNzeM1at1hXPSI0cmasx__bQtdxgvr__1")
                    .audience("https://ninjaone-rmm/api")
                    .grantType("client_credentials").build();

            HttpEntity<TokenRequest> requestEntity = new HttpEntity<>(tokenRequest, headers);

            ResponseEntity<TokenResponse> response = restTemplate.postForEntity("https://dev-vph4ynx3.us.auth0.com/oauth/token", requestEntity, TokenResponse.class);
            tokenResponse = response.getBody();
        }
        return tokenResponse;
    }

    @Then("Get all devices for customer")
    public void get_all_devices_for_customer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken().getAccessToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/private/devices");
        builder.queryParam("customerId",globalVariables.customer.getId());

        ResponseEntity<GetDeviceResponse> deviceResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,entity, GetDeviceResponse.class);
        Assertions.assertFalse(deviceResponse.getBody().getDevices().isEmpty());

    }

    @Then("Add device for customer")
    public void add_device_for_customer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken().getAccessToken());

        ChangeDeviceRequest changeDeviceRequest = new ChangeDeviceRequest();
        changeDeviceRequest.setDevice(DeviceVo.builder().systemName("Windows 1")
                .deviceTypeId(globalVariables.deviceType.getId()).build());
        changeDeviceRequest.setCustomerId(globalVariables.customer.getId());

        HttpEntity<ChangeDeviceRequest> entity = new HttpEntity<>(changeDeviceRequest,headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/private/devices");

        ResponseEntity<ChangeDeviceResponse> deviceResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,entity, ChangeDeviceResponse.class);
        Assertions.assertNotNull(deviceResponse.getBody().getDevice().getId());
        DeviceVo deviceVoResponse = deviceResponse.getBody().getDevice();
        globalVariables.device = Device.builder().systemName(deviceVoResponse.getSystemName())
                .id(deviceVoResponse.getId())
                .deviceTypeId(deviceVoResponse.getDeviceTypeId()).build();
    }

    @Then("Update device for customer")
    public void update_device_for_customer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken().getAccessToken());

        ChangeDeviceRequest changeDeviceRequest = new ChangeDeviceRequest();
        changeDeviceRequest.setDevice(DeviceVo.builder().systemName("Windows 2").id(globalVariables.device.getId())
                .deviceTypeId(globalVariables.deviceType.getId()).build());
        changeDeviceRequest.setCustomerId(globalVariables.customer.getId());

        HttpEntity<ChangeDeviceRequest> entity = new HttpEntity<>(changeDeviceRequest,headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/private/devices");

        ResponseEntity<ChangeDeviceResponse> deviceResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT,entity, ChangeDeviceResponse.class);
        Assertions.assertNotNull(deviceResponse.getBody().getDevice().getId());
        DeviceVo deviceVoResponse = deviceResponse.getBody().getDevice();
        globalVariables.device = Device.builder().systemName(deviceVoResponse.getSystemName())
                .id(deviceVoResponse.getId())
                .deviceTypeId(deviceVoResponse.getDeviceTypeId()).build();
    }
    @Then("Delete device for customer")
    public void delete_device_for_customer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken().getAccessToken());

        DeleteDeviceRequest deleteDeviceRequest = new DeleteDeviceRequest();
        deleteDeviceRequest.setDeviceId(globalVariables.device.getId());
        deleteDeviceRequest.setCustomerId(globalVariables.customer.getId());

        HttpEntity<DeleteDeviceRequest> entity = new HttpEntity<>(deleteDeviceRequest,headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/private/devices");

        ResponseEntity<String> deviceResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE,entity, String.class);
        Assertions.assertEquals("OK", deviceResponse.getBody());
    }

    @Then("Added integrity service to device successfully")
    public void added_integrity_service_to_device_successfully() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken().getAccessToken());

        AddServiceRequest addServiceRequest = AddServiceRequest.builder()
                .deviceId(globalVariables.device.getId())
                .serviceCatalogId(globalVariables.serviceCatalog.getId())
                .customerId(globalVariables.customer.getId())
                .build();


        HttpEntity<AddServiceRequest> entity = new HttpEntity<>(addServiceRequest,headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/private/services");

        ResponseEntity<AddServiceResponse> serviceResponseResponseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,entity, AddServiceResponse.class);
        Assertions.assertNotNull(serviceResponseResponseEntity.getBody().getDeviceServiceId());
        AddServiceResponse addServiceResponse = serviceResponseResponseEntity.getBody();
        globalVariables.deviceService = DeviceService.builder()
                .id(addServiceResponse.getDeviceServiceId())
                .deviceId(addServiceResponse.getDeviceId())
                .customerId(addServiceResponse.getCustomerId())
                .serviceCatalogId(addServiceResponse.getServiceCatalogId())
                .build();
    }

    @Then("Deleted integrity service to device successfully")
    public void deleted_integrity_service_to_device_successfully() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken().getAccessToken());

        DeleteServiceRequest serviceRequest = DeleteServiceRequest.builder()
                .deviceServiceId(globalVariables.deviceService.getId())
                .build();


        HttpEntity<DeleteServiceRequest> entity = new HttpEntity<>(serviceRequest,headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/private/services");

        ResponseEntity<String> serviceResponseResponseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE,entity, String.class);
        Assertions.assertEquals("OK", serviceResponseResponseEntity.getBody());
    }

    @Then("Return services selected to customer as functional test")
    public void return_services_selected_to_customer_as_functional_test() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken().getAccessToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/private/services");
        builder.queryParam("customerId",globalVariables.customer.getId());

        ResponseEntity<GetServicesResponse> serviceResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,entity, GetServicesResponse.class);
        Assertions.assertFalse(serviceResponse.getBody().getServices().isEmpty());
    }

    @Then("Then Monthly cost is {double} as functional test")
    public void then_monthly_cost_is_as_functional_test(Double monthlyCostEstimated) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getToken().getAccessToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/private/services/monthly-cost");
        builder.queryParam("customerId",globalVariables.customer.getId());

        ResponseEntity<GetMonthlyCostServicesResponse> serviceResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,entity, GetMonthlyCostServicesResponse.class);
        Assertions.assertEquals(monthlyCostEstimated,serviceResponse.getBody().getMonthlyCost());
    }
}
