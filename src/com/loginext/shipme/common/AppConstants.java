package com.loginext.shipme.common;

public interface AppConstants {
  public static final String CONSIGNMENT_TYPE_URL = "http://tlm.cloudapp.net:8080/LogiNextService/LookupController/retrieveLookupForShipment";
  public static final String ORIGIN_URL = "http://tlm.cloudapp.net:8080/LogiNextService/ClientBranchController/retrieveClientBranchHubJSON";
  public static final String DESTINATION_URL = "http://tlm.cloudapp.net:8080/LogiNextService/ClientNodeController/retrieveClientNodeByJSON";
  public static final String BARCODE_URL = "http://tlm.cloudapp.net:8080/LogiNextService/OrderDeviceMapController/retrieveOrderDeviceMapJSON";
  public static final String VEHICLE_URL = "http://tlm.cloudapp.net:8080/LogiNextService/VehicleMasterController/retrieveVehicleForCreateShipmentJSON";
  public static final String DRIVER_URL = "http://tlm.cloudapp.net:8080/LogiNextService/DriverMasterController/retrieveNotAssignedDriversTripJSON";

  public static final String CLIENT_ID = "clientid";
}
