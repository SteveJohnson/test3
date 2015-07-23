package com.loginext.shipme.model;

import java.util.List;

public interface RequesterView {

  public void onConsignmentTypeReceived(List<String> lookupCds);

  public void onOriginReponse(List<String> origins);

  public void onDestinationReceived(List<String> destinations);

  public void onBarcodeReceived(List<String> barcodes, List<String> deviceIds);

  public void onVehicleReceived(List<String> capacitiesInVolume, List<String> deviceIds, List<String> vehicleNumbers, List<String> vehicleTypes);

  public void onDriverReceived(List<String> driverNames, List<String> phoneNumbers);
}
