Feature: Unit tests collection to for Remote Monitoring and Management (RMM) company that provides services that monitor
  and manage devices
  Scenario: Admin system should be able to get, add, update or delete type of devices
    Given Device Type data, name: "Windows Workstation", antivirus cost: 5.0, device management cost: 4.0
    Then Added device type successfully
    Then Return device type data, name: "Windows Workstation", antivirus cost: 5.0, device management cost: 4.0
    When Update device type name: "Windows PC"
    Then Updated device type successfully
    Then Delete device type successfully
    Then Return not found device type
  Scenario: Admin system should be able to get, add, update or delete catalog services
    Given Catalog service data, name: "Cloudberry backup", cost: 3.0
    Then Added catalog service successfully
    Then Return catalog service data
    When Update catalog service name: "Cloudberry data backup"
    Then Updated catalog service successfully
    Then Delete catalog service successfully
    Then Return not found catalog service
  Scenario: Admin system should be able to get, add, update or delete Customers
    Given Customer data, national document id: "1002556148", fist name: "Christian", last name: "Barahona"
    Then Added customer successfully
    Then Return customer data
    When Update first name: "Christian Marcel"
    Then Updated customer successfully
    Then Delete customer successfully
    Then Return not found customer
  Scenario: Customer should be able to get, add, update or delete devices. Implement endpoints
  to provide these operations
    Given Device Type data, name: "Mac", antivirus cost: 7.0, device management cost: 4.0
    And Device data, system name: "MacBookPro1"
    Then Added device type successfully
    Then Added device successfully
    Then Return device data, system name: "MacBookPro1", device type: "Mac"
    When Update device, system name: "WorkWin001"
    Then Updated device successfully
    Then Deleted device successfully
    Then Return not found device

  Scenario: Customer should be able to add or delete available services to his/her account. Also,
  customer can see all services selected. Implement endpoints to provide these
  operations. To add the same service more than once is not allowed.
    Given Customer data, national document id: "1002556148", fist name: "Christian", last name: "Barahona"
    Then Added customer successfully
    Given Device Type data, name: "Windows Server", antivirus cost: 5.0, device management cost: 4.0
    And Device data, system name: "WinSer01"
    And Catalog service data, name: "Cloudberry backup", cost: 3.0
    Then Added device type successfully
    Then Added device successfully
    Then Added catalog service successfully
    Then Added service to device successfully
    Then Deleted service to device successfully
  Scenario: Customer can see all services selected
    Given Customer data, national document id: "1002556148", fist name: "Christian", last name: "Barahona"
    Then Added customer successfully
    And Device Type data, name: "Windows Server", antivirus cost: 5.0, device management cost: 4.0
    Then Added device type successfully
    And Device data, system name: "WinSer01"
    Then Added device successfully
    And Catalog service data, name: "Cloudberry backup", cost: 3.0
    Then Added catalog service successfully
    Then Added service to device successfully
    And Catalog service data, name: "PSA", cost: 2.0
    Then Added catalog service successfully
    Then Added service to device successfully
    And Catalog service data, name: "Cloudberry backup", cost: 1.0
    Then Added catalog service successfully
    Then Added service to device successfully
    Then Throw error "Service already exists"
    Then Return services selected to customer
  Scenario: Customer needs to calculate the total monthly cost of the deal depending on
  selected services and the number of devices in database.
    Given Customer with 2 Windows, 3 Mac with  Antivirus, Cloudberry and TeamViewer
    Then Then Monthly cost is 71.0
    Given Customer with 2 Windows, 4 Mac with  Antivirus, Cloudberry and TeamViewer
    Then Then Monthly cost is 86.0