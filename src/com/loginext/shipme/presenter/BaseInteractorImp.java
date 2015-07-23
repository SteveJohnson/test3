package com.loginext.shipme.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.loginext.shipme.ShipmentApplication;
import com.loginext.shipme.common.AppConstants;
import com.loginext.shipme.common.VolleyHelper;

public class BaseInteractorImp implements BaseInteractor, ErrorListener, AppConstants{
  private Context context;
  private ResponseListener responseListener;

  private Listener<JSONObject> consignmentTypeListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        List<String> lookupCds = new ArrayList<String>();

        if(response != null) {
          JSONArray array = response.optJSONArray("lookup");
          if(array != null) {

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                lookupCds.add(object.optString("lookupCd"));
              }
            }
          }
        }

        responseListener.onConsignmentTypeResponse(lookupCds);
      }
    }
  };

  private Listener<JSONObject> originListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        List<String> origins = new ArrayList<String>();

        if(response != null) {
          JSONArray array = response.optJSONArray("clientBranch");
          if(array != null) {

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                origins.add(object.optString("description"));
              }
            }
          }
        }

        responseListener.onOriginReponse(origins);
      }
    }
  };

  private Listener<JSONObject> destinationListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        List<String> destinations = new ArrayList<String>();

        if(response != null) {
          JSONArray array = response.optJSONArray("clientNode");
          if(array != null) {

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                destinations.add(object.optString("clientNodeName"));
              }
            }
          }
        }

        responseListener.onDestinationResponse(destinations);
      }
    }
  };

  private Listener<JSONObject> barcodeListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        List<String> barcodes = new ArrayList<String>();
        List<String> deviceIds = new ArrayList<String>();

        if(response != null) {
          JSONArray array = response.optJSONArray("orderDeviceMap");
          if(array != null) {

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                barcodes.add(object.optString("barcode"));
                deviceIds.add(object.optString("deviceId"));
              }
            }
          }
        }

        responseListener.onBarcodeResponse(barcodes, deviceIds);
      }
    }
  };

  private Listener<JSONObject> vehicleListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        List<String> capacitiesInVolume = new ArrayList<String>();
        List<String> deviceIds = new ArrayList<String>();
        List<String> vehicleNumbers = new ArrayList<String>();
        List<String> vehicleTypes = new ArrayList<String>();

        if(response != null) {
          JSONArray array = response.optJSONArray("vehicleMaster");
          if(array != null) {

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                capacitiesInVolume.add(object.optString("capacityInVolume"));
                deviceIds.add(object.optString("deviceId"));
                vehicleNumbers.add(object.optString("vehicleNumber"));
                vehicleTypes.add(object.optString("vehicleType"));
              }
            }
          }
        }

        responseListener.onVehicleResponse(capacitiesInVolume, deviceIds, vehicleNumbers, vehicleTypes);
      }
    }
  };

  private Listener<JSONObject> driverListener = new Listener<JSONObject>() {

    @Override public void onResponse(JSONObject response) {
      if(responseListener != null) {
        List<String> driverNames = new ArrayList<String>();
        List<String> phoneNumbers = new ArrayList<String>();

        if(response != null) {
          JSONArray array = response.optJSONArray("driverMaster");
          if(array != null) {

            for(int i = 0; i < array.length(); i++) {
              JSONObject object = array.optJSONObject(i);

              if(object != null) {
                driverNames.add(object.optString("driverName"));
                phoneNumbers.add(object.optString("phoneNumber"));
              }
            }
          }
        }

        responseListener.onDriverResponse(driverNames, phoneNumbers);
      }
    }
  };

  public BaseInteractorImp(ResponseListener responseListener) {
    context = ShipmentApplication.getInstance().getApplicationContext();
    this.responseListener = responseListener;
  }

  private void makeDataRequest(String url, final Map<String, String> params, Listener<JSONObject> listener) {
    JsonObjectRequest request = new JsonObjectRequest(Method.POST, url, null, listener, this)
    {

      @Override public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        return headers;
      }

      @Override protected Map<String, String> getParams() {
        return params;
      }

    };

    VolleyHelper.getInstance(context).getRequestQueue().add(request);
  }

  @Override public void fetchConsignmentType() {
    Map<String, String> params = new HashMap<String, String>();
    params.clear();
    makeDataRequest(CONSIGNMENT_TYPE_URL, params, consignmentTypeListener);
  }

  @Override public void fetchOrigin() {
    Map<String, String> params = new HashMap<String, String>();
    params.clear();
    params.put(CLIENT_ID, "23");
    makeDataRequest(ORIGIN_URL, params, originListener);
  }

  @Override public void fetchDestination() {
    Map<String, String> params = new HashMap<String, String>();
    params.clear();
    params.put(CLIENT_ID, "23");
    makeDataRequest(DESTINATION_URL, params, destinationListener);
  }

  @Override public void fetchBarcode() {
    Map<String, String> params = new HashMap<String, String>();
    params.clear();
    params.put(CLIENT_ID, "23");
    makeDataRequest(BARCODE_URL, params, barcodeListener);
  }

  @Override public void fetchVehicle() {
    Map<String, String> params = new HashMap<String, String>();
    params.clear();
    params.put(CLIENT_ID, "23");
    makeDataRequest(VEHICLE_URL, params, vehicleListener);
  }

  @Override public void fetchDriver() {
    Map<String, String> params = new HashMap<String, String>();
    params.clear();
    params.put(CLIENT_ID, "23");
    makeDataRequest(DRIVER_URL, params, driverListener);
  }

  @Override public void onErrorResponse(VolleyError error) {

  }

}
