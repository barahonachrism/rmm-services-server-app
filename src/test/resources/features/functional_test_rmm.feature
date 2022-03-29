Feature: Functional test for Ninja Remote Monitoring Management
  Scenario: Customer should be able to get, add, update, or delete
    Given Customer with 2 Windows, 3 Mac with  Antivirus, Cloudberry and TeamViewer
    Then Get all devices for customer
    And Device Type data, name: "Windows Server", antivirus cost: 5.0, device management cost: 4.0
    Then Added device type successfully
    Then Add device for customer
    Then Update device for customer
    Then Delete device for customer
  Scenario: Customer should be able to add or delete available services to his/her account
    Given Customer data, national document id: "1002556148", fist name: "Christian", last name: "Barahona"
    Then Added customer successfully
    Given Device Type data, name: "Windows Server", antivirus cost: 5.0, device management cost: 4.0
    And Device data, system name: "WinSer01"
    And Catalog service data, name: "Cloudberry backup 4", cost: 3.0
    Then Added device type successfully
    Then Added device successfully
    Then Added catalog service successfully
    Then Added integrity service to device successfully
    Then Deleted integrity service to device successfully
  Scenario: Customer can see all services selected using as functional test
    Given Customer data, national document id: "1002556148", fist name: "Christian", last name: "Barahona"
    Then Added customer successfully
    And Device Type data, name: "Windows Server", antivirus cost: 5.0, device management cost: 4.0
    Then Added device type successfully
    And Device data, system name: "WinSer01"
    Then Added device successfully
    And Catalog service data, name: "Cloudberry backup 5", cost: 3.0
    Then Added catalog service successfully
    Then Added service to device successfully
    And Catalog service data, name: "PSA", cost: 2.0
    Then Added catalog service successfully
    Then Added service to device successfully
    And Catalog service data, name: "Cloudberry backup 6", cost: 1.0
    Then Added catalog service successfully
    Then Added service to device successfully
    Then Return services selected to customer as functional test

  Scenario: Customer needs to calculate the total monthly cost of the deal depending on
  selected services and the number of devices in database as functional test.
    Given Customer with 2 Windows, 3 Mac with  Antivirus, Cloudberry and TeamViewer
    Then Then Monthly cost is 71.0 as functional test
