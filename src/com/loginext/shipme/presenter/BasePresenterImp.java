package com.loginext.shipme.presenter;

import java.util.List;

import com.loginext.shipme.model.RequesterView;

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

  @Override public void onConsignmentTypeResponse(List<String> lookupCds) {
    if(requesterView != null) {
      requesterView.onConsignmentTypeReceived(lookupCds);
    }
  }

  @Override public void onOriginReponse(List<String> origins) {
    if(requesterView != null) {
      requesterView.onOriginReponse(origins);
    }
  }

  @Override public void onDestinationResponse(List<String> destinations) {
    if(requesterView != null) {
      requesterView.onDestinationReceived(destinations);
    }
  }

  @Override public void onBarcodeResponse(List<String> barcodes, List<String> deviceIds) {
    if(requesterView != null) {
      requesterView.onBarcodeReceived(barcodes, deviceIds);
    }
  }

  @Override public void onVehicleResponse(List<String> capacitiesInVolume, List<String> deviceIds, List<String> vehicleNumbers, List<String> vehicleTypes) {
    if(requesterView != null) {
      requesterView.onVehicleReceived(capacitiesInVolume, deviceIds, vehicleNumbers, vehicleTypes);
    }
  }

  @Override public void onDriverResponse(List<String> driverNames, List<String> phoneNumbers) {
    if(requesterView != null) {
      requesterView.onDriverReceived(driverNames, phoneNumbers);
    }
  }

  public void setObserver(RequesterView requesterView) {
    this.requesterView = requesterView;
  }

}
