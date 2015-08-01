package com.loginext.shipme.presenter;

import java.util.List;

import com.loginext.shipme.model.Barcode;
import com.loginext.shipme.model.ConsignmentType;
import com.loginext.shipme.model.Destination;
import com.loginext.shipme.model.Driver;
import com.loginext.shipme.model.Origin;
import com.loginext.shipme.model.Vehicle;

public interface RequesterView {

  abstract public void onConsignmentTypeReceived(List<ConsignmentType> consignmentTypes);

  abstract public void onOriginReponse(List<Origin> origins);

  abstract public void onDestinationReceived(List<Destination> destinations);

  abstract public void onBarcodeReceived(List<Barcode> barcodes);

  abstract public void onVehicleReceived(List<Vehicle> vehicles);

  abstract public void onDriverReceived(List<Driver> drivers);
}
