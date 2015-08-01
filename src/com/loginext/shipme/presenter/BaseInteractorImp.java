package com.loginext.shipme.presenter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.loginext.shipme.ShipmentApplication;
import com.loginext.shipme.common.AppConstants;
import com.loginext.shipme.common.LoginextJsonObjectRequest;
import com.loginext.shipme.common.VolleyHelper;
import com.loginext.shipme.model.Barcode;
import com.loginext.shipme.model.ConsignmentDetails;
import com.loginext.shipme.model.ConsignmentType;
import com.loginext.shipme.model.Destination;
import com.loginext.shipme.model.Driver;
import com.loginext.shipme.model.Origin;
import com.loginext.shipme.model.Vehicle;

public class BaseInteractorImp implements BaseInteractor, AppConstants{
  private Context context;
  private ResponseListener responseListener;

  private Listener<JSONObject> consignmentTypeListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        if(response != null) {
          JSONArray array = response.optJSONArray("lookup");

          if(array != null) {
            List<ConsignmentType> consignmentTypes = new ArrayList<ConsignmentType>();

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                ConsignmentType consignmentType = new ConsignmentType();

                consignmentType.setAssignDeliveryBoyFlag(object.optString("assignDeliveryBoyFlag"));
                consignmentType.setCreatedByUserId(object.optString("createdByUserId"));
                consignmentType.setId(object.optString("id"));
                consignmentType.setUpdatedByUserId(object.optString("updatedByUserId"));
                consignmentType.setLookupCd(object.optString("lookupCd"));
                consignmentType.setLookupDesc(object.optString("lookupDesc"));
                consignmentType.setLookupId(object.optString("lookupId"));
                consignmentType.setLookupType(object.optString("lookupType"));

                consignmentTypes.add(consignmentType);
              }
            }

            responseListener.onConsignmentTypeResponse(consignmentTypes);   
          }
        }
      }
    }
  };

  private Listener<JSONObject> originListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        if(response != null) {
          JSONArray array = response.optJSONArray("clientBranch");

          if(array != null) {
            List<Origin> origins = new ArrayList<Origin>();

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                Origin origin = new Origin();

                origin.setAssignDeliveryBoyFlag(object.optString("assignDeliveryBoyFlag"));
                origin.setCreatedByUserId(object.optString("createdByUserId"));
                origin.setId(object.optString("id"));
                origin.setUpdatedByUserId(object.optString("updatedByUserId"));
                origin.setClientBranchId(object.optString("clientBranchId"));
                origin.setClientId(object.optString("clientId"));
                origin.setDescription(object.optString("description"));
                origin.setLatitude(object.optString("latitude"));
                origin.setLongitude(object.optString("longitude"));
                origin.setName(object.optString("name"));

                origins.add(origin);
              }
            }

            responseListener.onOriginReponse(origins);
          }
        }
      }
    }
  };

  private Listener<JSONObject> destinationListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        if(response != null) {
          JSONArray array = response.optJSONArray("clientNode");

          if(array != null) {
            List<Destination> destinations = new ArrayList<Destination>();

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                Destination destination = new Destination();

                destination.setAssignDeliveryBoyFlag(object.optString("assignDeliveryBoyFlag"));
                destination.setCreatedByUserId(object.optString("createdByUserId"));
                destination.setCreatedOnDt(object.optString("createdOnDt"));
                destination.setId(object.optString("id"));
                destination.setIsDeleteFl(object.optString("isDeleteFl"));
                destination.setUpdatedByUserId(object.optString("updatedByUserId"));
                destination.setUpdatedOnDt(object.optString("updatedOnDt"));
                destination.setClientNodeName(object.optString("clientNodeName"));
                destination.setClientNodeRank(object.optString("clientNodeRank"));
                destination.setClientnodeId(object.optString("clientnodeId"));
                destination.setClientnodecd(object.optString("clientnodecd"));
                destination.setDeliverycutoffinhrs(object.optString("deliverycutoffinhrs"));
                destination.setLatitude(object.optString("latitude"));
                destination.setLongitude(object.optString("longitude"));

                destinations.add(destination);
              }
            }

            responseListener.onDestinationResponse(destinations);
          }
        }
      }
    }
  };

  private Listener<JSONObject> barcodeListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        if(response != null) {
          List<Barcode> barcodes = new ArrayList<Barcode>();

          JSONArray array = response.optJSONArray("orderDeviceMap");
          if(array != null) {

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                Barcode barcode = new Barcode();

                barcode.setAssignDeliveryBoyFlag(object.optString("assignDeliveryBoyFlag"));
                barcode.setCreatedByUserId(object.optString("createdByUserId"));
                barcode.setCreatedOnDt(object.optString("createdOnDt"));
                barcode.setId(object.optString("id"));
                barcode.setUpdatedByUserId(object.optString("updatedByUserId"));
                barcode.setBarcode(object.optString("barcode"));
                barcode.setCostOfUse(object.optString("costOfUse"));
                barcode.setDeviceId(object.optString("deviceId"));
                barcode.setExtraField3(object.optString("extraField3"));
                barcode.setExtraField3(object.optString("extraField4"));
                barcode.setInvoiceNumber(object.optString("invoiceNumber"));
                barcode.setLookupDesc(object.optString("lookupDesc"));
                barcode.setNoOfReturnAlerts(object.optString("noOfReturnAlerts"));
                barcode.setOrderDeviceMapId(object.optString("orderDeviceMapId"));
                barcode.setOrderId(object.optString("orderId"));
                barcode.setOrderNumber(object.optString("orderNumber"));
                barcode.setPhoneNumber(object.optString("phoneNumber"));
                barcode.setStatusCd(object.optString("statusCd"));

                barcodes.add(barcode);
              }
            }

            responseListener.onBarcodeResponse(barcodes);
          }
        }
      }
    }
  };

  private Listener<JSONObject> vehicleListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        if(response != null) {
          JSONArray array = response.optJSONArray("vehicleMaster");

          if(array != null) {
            List<Vehicle> vehicles = new ArrayList<Vehicle>();

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                Vehicle vehicle = new Vehicle();

                vehicle.setAssignDeliveryBoyFlag(object.optString("assignDeliveryBoyFlag"));
                vehicle.setCreatedByUserId(object.optString("createdByUserId"));
                vehicle.setId(object.optString("id"));
                vehicle.setUpdatedByUserId(object.optString("updatedByUserId"));
                vehicle.setCapacityInUnits(object.optString("capacityInUnits"));
                vehicle.setCapacityInVolume(object.optString("capacityInVolume"));
                vehicle.setClientId(object.optString("clientId"));
                vehicle.setDeviceId(object.optString("deviceId"));
                vehicle.setSimId(object.optString("simId"));
                vehicle.setVehicleId(object.optString("vehicleId"));
                vehicle.setVehicleName(object.optString("vehicleName"));
                vehicle.setVehicleNumber(object.optString("vehicleNumber"));
                vehicle.setVehicleType(object.optString("vehicleType"));

                vehicles.add(vehicle);
              }
            }

            responseListener.onVehicleResponse(vehicles);
          } else {
            JSONObject object = response.optJSONObject("driverMaster");
            List<Vehicle> vehicles = new ArrayList<Vehicle>();

            if(object != null) {
              Vehicle vehicle = new Vehicle();

              vehicle.setAssignDeliveryBoyFlag(object.optString("assignDeliveryBoyFlag"));
              vehicle.setCreatedByUserId(object.optString("createdByUserId"));
              vehicle.setId(object.optString("id"));
              vehicle.setUpdatedByUserId(object.optString("updatedByUserId"));
              vehicle.setCapacityInUnits(object.optString("capacityInUnits"));
              vehicle.setCapacityInVolume(object.optString("capacityInVolume"));
              vehicle.setClientId(object.optString("clientId"));
              vehicle.setDeviceId(object.optString("deviceId"));
              vehicle.setSimId(object.optString("simId"));
              vehicle.setVehicleId(object.optString("vehicleId"));
              vehicle.setVehicleName(object.optString("vehicleName"));
              vehicle.setVehicleNumber(object.optString("vehicleNumber"));
              vehicle.setVehicleType(object.optString("vehicleType"));

              vehicles.add(vehicle);
            }

            responseListener.onVehicleResponse(vehicles);
          }
        }
      }
    }
  };

  private Listener<JSONObject> driverListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        if(response != null) {
          JSONArray array = response.optJSONArray("driverMaster");

          if(array != null) {
            List<Driver> drivers = new ArrayList<Driver>();

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                Driver driver = new Driver();

                driver.setAssignDeliveryBoyFlag(object.optString("assignDeliveryBoyFlag"));
                driver.setCreatedByUserId(object.optString("createdByUserId"));
                driver.setDriverId(object.optString("id"));
                driver.setUpdatedByUserId(object.optString("updatedByUserId"));
                driver.setClientId(object.optString("clientId"));
                driver.setDriverId(object.optString("driverId"));
                driver.setDriverName(object.optString("driverName"));
                driver.setPhoneNumber(object.optString("phoneNumber"));

                drivers.add(driver);
              }
            }

            responseListener.onDriverResponse(drivers);

          } 
        }
      }
    }
  };

  private Listener<JSONObject> createConsignmentListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        if(response != null) {

        }
      }
    }
  };

  private ErrorListener createConsignmentError = new ErrorListener() {
    @Override public void onErrorResponse(VolleyError error) {
      try {
        showMessagePerStatus(error.networkResponse.statusCode);

      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  };

  private ErrorListener genericErrorListener = new ErrorListener() {
    @Override public void onErrorResponse(VolleyError error) {
      Log.d(BaseInteractorImp.class.getSimpleName(), "Error in retreival = " + error);
    }
  };

  public BaseInteractorImp(ResponseListener responseListener) {
    context = ShipmentApplication.getInstance().getApplicationContext();
    this.responseListener = responseListener;
  }

  private void makeDataRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener, JSONObject jsonData) {
    LoginextJsonObjectRequest request = new LoginextJsonObjectRequest(Method.POST, url, jsonData, listener, errorListener);
    VolleyHelper.getInstance(context).getRequestQueue().add(request);
  }

  @Override public void fetchConsignmentType() {
    JSONObject jsonData = new JSONObject();
    makeDataRequest(CONSIGNMENT_TYPE_URL, consignmentTypeListener, null, jsonData);
  }

  @Override public void fetchOrigin() {
    JSONObject jsonData = new JSONObject();
    try {
      jsonData.putOpt(CLIENT_ID, "23");
    } catch (JSONException e) {
      e.printStackTrace();
    }

    makeDataRequest(ORIGIN_URL, originListener, genericErrorListener, jsonData);
  }

  @Override public void fetchDestination() {
    JSONObject jsonData = new JSONObject();
    try {
      jsonData.putOpt(CLIENT_ID, "23");
    } catch (JSONException e) {
      e.printStackTrace();
    }

    makeDataRequest(DESTINATION_URL, destinationListener, genericErrorListener, jsonData);
  }

  @Override public void fetchBarcode() {
    JSONObject jsonData = new JSONObject();
    makeDataRequest(BARCODE_URL, barcodeListener, genericErrorListener, jsonData);
  }

  @Override public void fetchVehicle() {
    JSONObject jsonData = new JSONObject();
    try {
      jsonData.putOpt(CLIENT_ID, "23");
    } catch (JSONException e) {
      e.printStackTrace();
    }

    makeDataRequest(VEHICLE_URL, vehicleListener, genericErrorListener, jsonData);
  }

  @Override public void fetchDriver() {
    JSONObject jsonData = new JSONObject();
    try {
      jsonData.putOpt(CLIENT_ID, "23");
    } catch (JSONException e) {
      e.printStackTrace();
    }

    makeDataRequest(DRIVER_URL, driverListener, genericErrorListener, jsonData);
  }

  private void showMessagePerStatus(int statusCode) {
    switch (statusCode) {
    case 200: 
      Toast.makeText(context, "Consignment created successfully.", Toast.LENGTH_SHORT).show();
      break;
    case 400:
      Toast.makeText(context, "Unable to create shipment.", Toast.LENGTH_SHORT).show();
      break;
    case 401:
      Toast.makeText(context, "Failed to create consignment.", Toast.LENGTH_SHORT).show();
      break;
    case 402:
      Toast.makeText(context, "Shipment created successfuly. Failed to create via points.", Toast.LENGTH_SHORT).show();
      break;
    case 403:
      Toast.makeText(context, "Shipment created successfuly.Failed to create trip.", Toast.LENGTH_SHORT).show();
      break;
    case 404:
      Toast.makeText(context, "Error occurred in creating consignment.", Toast.LENGTH_SHORT).show();
      break;
    case 405:
      Toast.makeText(context, "Shipment created successfuly. Route configuration cound not be found.", Toast.LENGTH_SHORT).show();
      break;
    case 406:
      Toast.makeText(context, "Shipment created successfuly. Failed to create trip. Route configuration cound not be found.", Toast.LENGTH_SHORT).show();
      break;
    case 407:
      Toast.makeText(context, "LR Number already exists.", Toast.LENGTH_SHORT).show();
      break;
    default:
      break;
    }
  }

  @Override public void createConsignment(ConsignmentDetails consignmentDetails) {
    JSONObject jsonData = new JSONObject();
    try {
      jsonData.putOpt(SIMId, consignmentDetails.getSimId());
      jsonData.putOpt(originAddr, consignmentDetails.getOriginAddr());
      jsonData.putOpt(destinationAddr, consignmentDetails.getDestinationAddr());
      jsonData.putOpt(shipmentTypeCd, consignmentDetails.getShipmentTypeCd());
      jsonData.putOpt(modeOfTransport, consignmentDetails.getModeOfTransport());
      jsonData.putOpt(deviceBarcode, consignmentDetails.getDeviceBarCode());
      jsonData.putOpt(vehicleNum, consignmentDetails.getVehicleNumber());
      jsonData.putOpt(origDestLatitude, consignmentDetails.getOriginDestinationLatitude());
      jsonData.putOpt(origDestLongitude, consignmentDetails.getOriginDestinationLongitude());
      jsonData.putOpt(clientShipmentId, consignmentDetails.getClientShipmentId());
      jsonData.putOpt(originLatitude, consignmentDetails.getOriginLatitude());
      jsonData.putOpt(originLongitude, consignmentDetails.getOriginLongitude());
      jsonData.putOpt(clientId, consignmentDetails.getClientId());
      jsonData.putOpt(clientBranchId, consignmentDetails.getClientBranchId());
      jsonData.putOpt(destClientNodeId, consignmentDetails.getDestinationClientNodeId());
      jsonData.putOpt(numberOfItems, consignmentDetails.getNumberOfItems());
      jsonData.putOpt(challanNumber, consignmentDetails.getChallanNumber());
      jsonData.putOpt(sealNumber, consignmentDetails.getSealNumber());
      jsonData.putOpt(vehicleNumber, consignmentDetails.getVehicleNumber());
      jsonData.putOpt(vehicleId, consignmentDetails.getVehicleId());
      jsonData.putOpt(driverId, consignmentDetails.getDriverId());
      jsonData.putOpt(tripDate, consignmentDetails.getTripDate());
      jsonData.putOpt(LRNumber, consignmentDetails.getLrNumber());
      jsonData.putOpt(createdByUserId, consignmentDetails.getCreatedByUserId());
      jsonData.putOpt(updatedByUserId, consignmentDetails.getUpdateByUserId());

    } catch (JSONException e) {
      e.printStackTrace();
    }

    makeDataRequest(BARCODE_URL, createConsignmentListener, createConsignmentError, jsonData);
  }

}
