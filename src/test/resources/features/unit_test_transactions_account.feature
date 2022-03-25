Feature: Unit tests collection to for Remote Monitoring and Management (RMM) company that provides services that monitor
  and manage devices
  Scenario: Admin system should be able to get, add, update or delete type of devices
    Given Device Type data, ID: "100c8afa-7920-4f04-94b5-5a4ba34298a6", name: "Windows Workstation", antivirus cost: 5.0
    Then Added device type successfully
    Then Return device type data, ID: "100c8afa-7920-4f04-94b5-5a4ba34298a6", name: "Windows Workstation", antivirus cost: 5.0
    When Update device type name: "Windows PC"
    Then Updated device type successfully
    Then Delete device type successfully
    Then Return not found device type

  Scenario: Customer should be able to get, add, update or delete devices. Implement endpoints
  to provide these operations
    Given Device data, ID device: "400c8afa-7920-4f04-94b6-5a4ba34298a6", system name: "WorkWin1", and type: "Windows Workstation"
    Then Added device successfully
    Then Return device data ID device: "400c8afa-7920-4f04-94b6-5a4ba34298a6", system name: "WorkWin1", and type: "Windows Workstation"
    When Update device, system name: "WorkWin001"
    Then Updated device successfully
    Then Deleted device successfully
    Then Return not found device
