package com.loginext.shipme.presenter;

import java.util.List;

import com.loginext.shipme.model.Barcode;
import com.loginext.shipme.model.ConsignmentDetails;
import com.loginext.shipme.model.ConsignmentType;
import com.loginext.shipme.model.Destination;
import com.loginext.shipme.model.Driver;
import com.loginext.shipme.model.Origin;
import com.loginext.shipme.model.Vehicle;

public class BasePresenterImp implements BasePresenter, ResponseListener{
  private static BasePresenterImp instance;
  private BaseInteractor baseInteractor;
  private RequesterView requesterView;

  private BasePresenterImp() {
    baseInteractor = new BaseInteractorImp(this);
  }

  public static BasePresenterImp getInstance() {
    if(instance == null) {
      instance = new BasePresenterImp();
    }

    return instance;
  }

  public void fetchConsignmentType() {
    baseInteractor.fetchConsignmentType();
  }

  public void fetchOrigin() {
    baseInteractor.fetchOrigin();
  }

  public void fetchDestination() {
    baseInteractor.fetchDestination();
  }

  public void fetchBarcode() {
    baseInteractor.fetchBarcode();
  }

  public void fetchVehicle() {
    baseInteractor.fetchVehicle();
  }

  public void fetchDriver() {
    baseInteractor.fetchDriver();
  }

  @Override public void createConsignment(ConsignmentDetails consignmentDetails) {
    baseInteractor.createConsignment(consignmentDetails);
  }

  @Override public void onConsignmentTypeResponse(List<ConsignmentType> consignmentTypes) {
    if(requesterView != null) {
      requesterView.onConsignmentTypeReceived(consignmentTypes);
    }
  }

  @Override public void setObserver(RequesterView requesterView) {
    this.requesterView = requesterView;
  }

  @Override public void onOriginReponse(List<Origin> origins) {
    if(requesterView != null) {
      requesterView.onOriginReponse(origins);
    } 
  }

  @Override public void onDestinationResponse(List<Destination> destinations) {
    if(requesterView != null) {
      requesterView.onDestinationReceived(destinations);
    }
  }

  @Override public void onBarcodeResponse(List<Barcode> barcodes) {
    if(requesterView != null) {
      requesterView.onBarcodeReceived(barcodes);
    }
  }

  @Override public void onVehicleResponse(List<Vehicle> vehicles) {
    if(requesterView != null) {
      requesterView.onVehicleReceived(vehicles);
    }
  }

  @Override public void onDriverResponse(List<Driver> drivers) {
    if(requesterView != null) {
      requesterView.onDriverReceived(drivers);
    } 
  }

}
