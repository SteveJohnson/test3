package com.loginext.shipme.presenter;

public interface BaseInteractor {

  public abstract void fetchConsignmentType();

  public abstract void fetchOrigin();

  public abstract void fetchDestination();

  public abstract void fetchBarcode();

  public abstract void fetchVehicle();

  public abstract void fetchDriver();

}
