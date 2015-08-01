package com.loginext.shipme.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loginext.shipme.R;
import com.loginext.shipme.common.AppConstants;
import com.loginext.shipme.common.LogiNextAutoCompleteTextView;
import com.loginext.shipme.model.Barcode;
import com.loginext.shipme.model.ConsignmentDetails;
import com.loginext.shipme.model.ConsignmentType;
import com.loginext.shipme.model.Destination;
import com.loginext.shipme.model.Driver;
import com.loginext.shipme.model.Origin;
import com.loginext.shipme.model.Vehicle;
import com.loginext.shipme.picker.SlideDateTimeListener;
import com.loginext.shipme.picker.SlideDateTimePicker;
import com.loginext.shipme.presenter.BasePresenter;
import com.loginext.shipme.presenter.BasePresenterImp;
import com.loginext.shipme.presenter.RequesterView;

public class FrontPageFragment extends Fragment implements RequesterView, OnClickListener {
  private BasePresenter basePresenter;

  /**
   * View Elements
   */
  private EditText mLRNumber;
  private LogiNextAutoCompleteTextView mOrigin;
  private LogiNextAutoCompleteTextView mDestination;
  private LogiNextAutoCompleteTextView mConsignmentType;
  private LogiNextAutoCompleteTextView mVehicleNumber;
  private LogiNextAutoCompleteTextView mBarCode;
  private LogiNextAutoCompleteTextView mDriverName;
  private EditText mDriverPhoneNumber;
  private EditText mVehicleType;
  private EditText mVehicleCapacity;
  @SuppressWarnings("unused") private EditText mWeight;
  @SuppressWarnings("unused") private EditText mTripId;
  private EditText mTripDate;
  @SuppressWarnings("unused") private EditText mVolume;
  private EditText mNumberOfItems;
  private EditText mChallanNumber;
  private EditText mSealNumber;
  @SuppressWarnings("unused") private EditText mBaName;
  private EditText mVehicleReportingTime;
  private Button mSubmit;
  private SlideDateTimePicker.Builder mSlideDateTimePickerBuilder;
  private SlideDateTimePicker mSlideDateTimePicker;

  /**
   * Variables
   */
  private long simID;
  private String modeOfTransport = "ROAD";
  private double originDestinationLatitude;
  private double originDestinationLongitude;
  private String clientShipmentID = "";
  private double originLatitude;
  private double originLongitude;
  private long clientId = 12;
  private long branchId;
  private long vehicleId;
  private long driverId;
  private String tripDate;
  private long createByUserId = 12;
  private long updatedByUserId = 12;

  /**
   * Lists
   */
  @SuppressWarnings("unused") private List<ConsignmentType> consignmentTypes;
  private List<Origin> origins;
  private List<Destination> destinations;
  private List<Barcode> barcodes;
  private List<Vehicle> vehicles;
  private List<Driver> drivers;

  private SlideDateTimeListener tripDateListener = new SlideDateTimeListener() {
    @Override public void onDateTimeSet(Date date) {
      try {
        mTripDate.setText(date.toString());

        tripDate = parseDate(date);
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  };

  private SlideDateTimeListener vehicleReportingTimeListener = new SlideDateTimeListener() {
    @Override public void onDateTimeSet(Date date) {
      try { 
        mVehicleReportingTime.setText(date.toString());
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  };

  private OnItemClickListener originItemClickListener = new OnItemClickListener() {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      try {
        Origin origin = origins.get(position);
        originLatitude = Double.parseDouble(origin.getLatitude());
        originLongitude = Double.parseDouble(origin.getLongitude());
        branchId = Long.parseLong(origin.getClientBranchId());
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  };

  private OnItemClickListener destinationItemClickListener = new OnItemClickListener() {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      try {
        Destination destination = destinations.get(position);
        originDestinationLatitude = Double.parseDouble(destination.getLatitude());
        originDestinationLongitude = Double.parseDouble(destination.getLongitude());
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  };

  private OnItemClickListener consignmentTypeItemClickListener = new OnItemClickListener() {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
  };

  private OnItemClickListener vehicleNumberItemClickListener = new OnItemClickListener() {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      try {
        Vehicle vehicle = vehicles.get(position);
        mVehicleType.setText(vehicle.getVehicleType());
        mVehicleCapacity.setText(vehicle.getCapacityInUnits());
        String barcode = findBarCode(vehicle.getDeviceId());
        mBarCode.setText(barcode);
        simID = Long.parseLong(vehicle.getSimId());
        vehicleId = Long.parseLong(vehicle.getVehicleId());
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  };

  private OnItemClickListener driverNameItemClickListener = new OnItemClickListener() {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      try {
        Driver driver = drivers.get(position);
        mDriverPhoneNumber.setText(driver.getPhoneNumber());
        driverId = Long.parseLong(driver.getDriverId());
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  };

  public FrontPageFragment() {

  }

  private String parseDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT_1, Locale.US);
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    return sdf.format(date);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    basePresenter = BasePresenterImp.getInstance();
    basePresenter.setObserver(this);
    mSlideDateTimePickerBuilder = new SlideDateTimePicker.Builder(getActivity().getSupportFragmentManager());
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_front_page, container, false);
    findViewByIds(rootView);
    setValues();
    return rootView;
  }

  @SuppressLint("CutPasteId")
  private void findViewByIds(View view) {
    mLRNumber = (EditText) view.findViewById(R.id.lr_number);

    mOrigin = (LogiNextAutoCompleteTextView) view.findViewById(R.id.origin);
    mOrigin.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));

    mOrigin.setOnClickListener(this);
    mOrigin.setOnItemClickListener(originItemClickListener);

    mDestination = (LogiNextAutoCompleteTextView) view.findViewById(R.id.destination);
    mDestination.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mDestination.setOnClickListener(this);
    mDestination.setOnItemClickListener(destinationItemClickListener);

    mConsignmentType = (LogiNextAutoCompleteTextView) view.findViewById(R.id.consignment_type);
    mConsignmentType.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mConsignmentType.setOnClickListener(this);
    mConsignmentType.setOnItemClickListener(consignmentTypeItemClickListener);

    mVehicleNumber = (LogiNextAutoCompleteTextView) view.findViewById(R.id.vehicle_number);
    mVehicleNumber.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mVehicleNumber.setOnClickListener(this);
    mVehicleNumber.setOnItemClickListener(vehicleNumberItemClickListener);

    mBarCode = (LogiNextAutoCompleteTextView) view.findViewById(R.id.barcode);
    mBarCode.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    /*mBarCode.setOnClickListener(this);*/
    disableEditText(mBarCode);

    mDriverName = (LogiNextAutoCompleteTextView) view.findViewById(R.id.driver_name);
    mDriverName.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mDriverName.setOnClickListener(this);
    mDriverName.setOnItemClickListener(driverNameItemClickListener);

    mDriverPhoneNumber = (EditText) view.findViewById(R.id.driver_phone_number);
    disableEditText(mDriverPhoneNumber);

    mVehicleType = (EditText) view.findViewById(R.id.vehicle_type);
    disableEditText(mVehicleType);

    mVehicleCapacity = (EditText) view.findViewById(R.id.vehicle_capacity);
    disableEditText(mVehicleCapacity);

    mWeight = (EditText) view.findViewById(R.id.weight);
    mTripId = (EditText) view.findViewById(R.id.trip_id);

    mTripDate = (EditText) view.findViewById(R.id.trip_date);
    mTripDate.setOnClickListener(this);

    mNumberOfItems = (EditText) view.findViewById(R.id.number_of_items);
    mChallanNumber = (EditText) view.findViewById(R.id.challan_number);
    mSealNumber = (EditText) view.findViewById(R.id.seal_number);
    mBaName = (EditText) view.findViewById(R.id.ba_name);

    mVehicleReportingTime = (EditText) view.findViewById(R.id.vehicle_reporting_time);
    mVehicleReportingTime.setOnClickListener(this);

    mSubmit = (Button) view.findViewById(R.id.submit);
    mSubmit.setOnClickListener(this);
  }

  private void setValues() {
    basePresenter.fetchOrigin();
    basePresenter.fetchDestination();
    basePresenter.fetchBarcode();
    basePresenter.fetchVehicle();
    basePresenter.fetchDriver();
    basePresenter.fetchConsignmentType();
  }

  @Override public void onConsignmentTypeReceived(List<ConsignmentType> consignmentTypes) {
    this.consignmentTypes = consignmentTypes;

    List<String> lookupCds = new ArrayList<String>();

    for(ConsignmentType consignmentType : consignmentTypes) {
      String lookupCd = consignmentType.getLookupCd();
      if(!lookupCds.contains(lookupCd)) {
        lookupCds.add(lookupCd);  
      }
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, lookupCds);
    mConsignmentType.setAdapter(adapter);
  }

  @Override public void onOriginReponse(List<Origin> origins) {
    this.origins = origins;

    List<String> originStrings = new ArrayList<String>();

    for(Origin origin : origins) {
      String originString = origin.getDescription();

      if(!originStrings.contains(originString)) {
        originStrings.add(originString);
      }
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, originStrings);
    mOrigin.setAdapter(adapter);
  }

  @Override public void onDestinationReceived(List<Destination> destinations) {
    this.destinations = destinations;

    List<String> destinationStrings = new ArrayList<String>();

    for(Destination destination : destinations) {
      String destinationString = destination.getClientNodeName();

      if(!destinationStrings.contains(destinationString)) {
        destinationStrings.add(destinationString);
      }
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, destinationStrings);
    mDestination.setAdapter(adapter);
  }

  @Override public void onBarcodeReceived(List<Barcode> barcodes) {
    this.barcodes = barcodes;

    List<String> barcodeStrings = new ArrayList<String>();
    List<String> deviceIdStrings = new ArrayList<String>();

    for(Barcode barcode : barcodes) {
      String barcodeString = barcode.getBarcode();

      if(!barcodeStrings.contains(barcodeString)) {
        barcodeStrings.add(barcodeString);
      }

      String deviceIdString = barcode.getDeviceId();

      if(!deviceIdStrings.contains(deviceIdString)) {
        deviceIdStrings.add(deviceIdString);
      }
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, barcodeStrings);
    mBarCode.setAdapter(adapter);
  }

  @Override public void onVehicleReceived(List<Vehicle> vehicles) {
    this.vehicles = vehicles;

    List<String> vehicleNumberStrings = new ArrayList<String>();

    for(Vehicle vehicle : vehicles) {
      String vehicleNumberString = vehicle.getVehicleNumber();

      if(!vehicleNumberStrings.contains(vehicleNumberString)) {
        vehicleNumberStrings.add(vehicleNumberString);
      }
    }

    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, vehicleNumberStrings);
    mVehicleNumber.setAdapter(adapter1);
  }

  @Override public void onDriverReceived(List<Driver> drivers) {
    this.drivers = drivers;

    List<String> driverNames = new ArrayList<String>();

    for(Driver driver : drivers) {
      String driverName = driver.getDriverName();

      if(!driverNames.contains(driverName)) {
        driverNames.add(driverName);
      }
    }


    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, driverNames);
    mDriverName.setAdapter(adapter);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
    case R.id.origin:
      mOrigin.showDropDown();
      break;
    case R.id.destination:
      mDestination.showDropDown();
      break;
      /*case R.id.barcode:
      mBarCode.showDropDown();
      break;*/
    case R.id.vehicle_number:
      mVehicleNumber.showDropDown();
      break;
    case R.id.driver_name:
      mDriverName.showDropDown();
      break;
    case R.id.consignment_type:
      mConsignmentType.showDropDown();
      break;
    case R.id.trip_date :
      mSlideDateTimePickerBuilder.setInitialDate(new Date());
      mSlideDateTimePickerBuilder.setListener(tripDateListener);
      mSlideDateTimePicker = mSlideDateTimePickerBuilder.build();
      mSlideDateTimePicker.show();
      break;
    case R.id.vehicle_reporting_time:
      mSlideDateTimePickerBuilder.setInitialDate(new Date());
      mSlideDateTimePickerBuilder.setListener(vehicleReportingTimeListener);
      mSlideDateTimePicker = mSlideDateTimePickerBuilder.build();
      mSlideDateTimePicker.show();
      break;
    case R.id.submit:
      try {
        ConsignmentDetails consignmentDetails = new ConsignmentDetails();
        consignmentDetails.setSimId(simID);
        consignmentDetails.setOriginAddr(mOrigin.getText().toString());
        consignmentDetails.setDestinationAddr(mDestination.getText().toString());
        consignmentDetails.setShipmentTypeCd(mConsignmentType.getText().toString());
        consignmentDetails.setModeOfTransport(modeOfTransport);
        consignmentDetails.setDeviceBarCode(mBarCode.getText().toString());
        consignmentDetails.setVehicleNumber(mVehicleNumber.getText().toString());
        consignmentDetails.setOriginDestinationLatitude(originDestinationLatitude);
        consignmentDetails.setOriginDestinationLongitude(originDestinationLongitude);
        consignmentDetails.setClientShipmentId(clientShipmentID);
        consignmentDetails.setOriginLatitude(originLatitude);
        consignmentDetails.setOriginLongitude(originLongitude);
        consignmentDetails.setClientId(clientId);
        consignmentDetails.setClientBranchId(branchId);
        consignmentDetails.setDestinationClientNodeId(0);
        consignmentDetails.setNumberOfItems(Long.parseLong(mNumberOfItems.getText().toString()));
        consignmentDetails.setChallanNumber(mChallanNumber.getText().toString());
        consignmentDetails.setSealNumber(mSealNumber.getText().toString());
        consignmentDetails.setVehicleId(vehicleId);
        consignmentDetails.setDriverId(driverId);
        consignmentDetails.setTripDate(tripDate);
        consignmentDetails.setLrNumber(mLRNumber.getText().toString());
        consignmentDetails.setCreatedByUserId(createByUserId);
        consignmentDetails.setUpdateByUserId(updatedByUserId);

        basePresenter.createConsignment(consignmentDetails);
      } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(getActivity(), "Failed to create a consignment", Toast.LENGTH_SHORT).show();
      }

      break;
    default:
      //DO Nothing
      break;
    }
  }

  private void disableEditText(EditText editText) {
    editText.setFocusable(false);
    editText.setEnabled(false);
    editText.setCursorVisible(false);
    editText.setKeyListener(null);
    editText.setBackgroundColor(Color.TRANSPARENT); 
  }

  private String findBarCode(String deviceId) {
    for(Barcode barcode : barcodes) {
      if(!barcode.getDeviceId().equalsIgnoreCase(deviceId)) {
        continue;
      }

      return barcode.getDeviceId();
    }

    return "";
  }

}