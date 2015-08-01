package com.loginext.shipme.common;

public interface AppConstants {
  public static final String CONSIGNMENT_TYPE_URL = "http://tlm.cloudapp.net:8080/LogiNextService/LookupController/retrieveLookupForShipment";
  public static final String ORIGIN_URL = "http://tlm.cloudapp.net:8080/LogiNextService/ClientBranchController/retrieveClientBranchHubJSON";
  public static final String DESTINATION_URL = "http://tlm.cloudapp.net:8080/LogiNextService/ClientNodeController/retrieveClientNodeByJSON";
  public static final String BARCODE_URL = "http://tlm.cloudapp.net:8080/LogiNextService/OrderDeviceMapController/retrieveOrderDeviceMapJSON";
  public static final String VEHICLE_URL = "http://tlm.cloudapp.net:8080/LogiNextService/VehicleMasterController/retrieveVehicleForCreateShipmentJSON";
  public static final String DRIVER_URL = "http://tlm.cloudapp.net:8080/LogiNextService/DriverMasterController/retrieveNotAssignedDriversTripJSON";

  public static final String CLIENT_ID = "clientId";

  public static final String SIMId = "SIMId";
  public static final String originAddr = "originAddr";
  public static final String destinationAddr = "destinationAddr";
  public static final String shipmentTypeCd = "shipmentTypeCd";
  public static final String modeOfTransport = "modeOfTransport";
  public static final String deviceBarcode = "deviceBarcode";
  public static final String vehicleNum = "vehicleNum";
  public static final String origDestLatitude = "origDestLatitude";
  public static final String origDestLongitude = "origDestLongitude";
  public static final String clientShipmentId = "clientShipmentId";
  public static final String originLatitude = "originLatitude";
  public static final String originLongitude = "originLongitude";
  public static final String clientId = "clientId";
  public static final String clientBranchId = "clientBranchId";
  public static final String destClientNodeId = "destClientNodeId";
  public static final String numberOfItems = "numberOfItems";
  public static final String challanNumber = "challanNumber";
  public static final String sealNumber = "sealNumber";
  public static final String vehicleNumber = "vehicleNumber";
  public static final String vehicleId = "vehicleId";
  public static final String driverId = "driverId";
  public static final String tripDate = "tripDate";
  public static final String LRNumber = "LRNumber";
  public static final String createdByUserId = "createdByUserId";
  public static final String updatedByUserId = "updatedByUserId";
}
