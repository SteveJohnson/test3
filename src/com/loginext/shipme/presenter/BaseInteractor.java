package com.loginext.shipme.presenter;

import com.loginext.shipme.model.ConsignmentDetails;

public interface BaseInteractor {

  public abstract void fetchConsignmentType();

  public abstract void fetchOrigin();

  public abstract void fetchDestination();

  public abstract void fetchBarcode();

  public abstract void fetchVehicle();

  public abstract void fetchDriver();

  abstract public void createConsignment(ConsignmentDetails consignmentDetails);

}
