package com.loginext.shipme.presenter;

import java.util.List;

public interface ResponseListener {

  public void onConsignmentTypeResponse(List<String> lookupCds);

  public void onOriginReponse(List<String> origins);

  public void onDestinationResponse(List<String> destinations);

  public void onBarcodeResponse(List<String> barcodes, List<String> deviceIds);

  public void onVehicleResponse(List<String> capacitiesInVolume, List<String> deviceIds, List<String> vehicleNumbers, List<String> vehicleTypes);

  public void onDriverResponse(List<String> driverNames, List<String> phoneNumbers);
}
