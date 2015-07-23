package com.loginext.shipme;

import android.app.Application;

public class ShipmentApplication extends Application {
  private static ShipmentApplication instance;

  public static ShipmentApplication getInstance() {
    return instance;
  }

  @Override public void onCreate() {
    super.onCreate();

    instance = this;

  }

}