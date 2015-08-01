package com.loginext.shipme.presenter;

import java.util.List;

import com.loginext.shipme.model.Barcode;
import com.loginext.shipme.model.ConsignmentType;
import com.loginext.shipme.model.Destination;
import com.loginext.shipme.model.Driver;
import com.loginext.shipme.model.Origin;
import com.loginext.shipme.model.Vehicle;

public interface ResponseListener {

  abstract public void onConsignmentTypeResponse(List<ConsignmentType> consignmentTypes);

  abstract public void onOriginReponse(List<Origin> origins);

  abstract public void onDestinationResponse(List<Destination> destinations);

  abstract public void onBarcodeResponse(List<Barcode> barcodes);

  abstract public void onVehicleResponse(List<Vehicle> vehicles);

  abstract public void onDriverResponse(List<Driver> drivers);

}
