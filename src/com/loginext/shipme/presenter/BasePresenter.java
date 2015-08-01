package com.loginext.shipme.presenter;

import com.loginext.shipme.model.ConsignmentDetails;


public interface BasePresenter {

  abstract public void fetchConsignmentType();

  abstract public void fetchOrigin();

  abstract public void fetchDestination();

  abstract public void fetchBarcode();

  abstract public void fetchVehicle();

  abstract public void fetchDriver();

  abstract public void setObserver(RequesterView requesterView);

  abstract public void createConsignment(ConsignmentDetails consignmentDetails);

}
