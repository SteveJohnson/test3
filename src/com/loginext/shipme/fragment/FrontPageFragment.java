package com.loginext.shipme.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loginext.shipme.R;
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

public class FrontPageFragment extends Fragment implements RequesterView, OnClickListener, OnItemSelectedListener{
  private BasePresenter basePresenter;

  /**
   * View Elements
   */
  private EditText mLRNumber;
  private AutoCompleteTextView mOrigin;
  private AutoCompleteTextView mDestination;
  private AutoCompleteTextView mConsignmentType;
  private AutoCompleteTextView mVehicleNumber;
  private AutoCompleteTextView mBarCode;
  private AutoCompleteTextView mDriverName;
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
  private String modeOfTransport;
  private double originDestinationLatitude;
  private double originDestinationLongitude;
  private String clientShipmentID;
  private double originLatitude;
  private double originLongitude;
  private long clientId;
  private long branchId;
  private long vehicleId;
  private long driverId;
  private String tripDate;
  private long createByUserId;
  private long updatedByUserId;

  private SlideDateTimeListener listener = new SlideDateTimeListener() {

    @Override public void onDateTimeSet(Date date) {

    }

    @Override public void onDateTimeCancel() {

    };

  };

  public FrontPageFragment() {

  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    basePresenter = BasePresenterImp.getInstance();
    basePresenter.setObserver(this);
    mSlideDateTimePickerBuilder = new SlideDateTimePicker.Builder(getActivity().getSupportFragmentManager());
    mSlideDateTimePickerBuilder.setListener(listener);
    mSlideDateTimePickerBuilder.setInitialDate(new Date());
    mSlideDateTimePicker = mSlideDateTimePickerBuilder.build();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_front_page, container, false);
    findViewByIds(rootView);
    setValues();
    return rootView;
  }

  private void findViewByIds(View view) {
    mLRNumber = (EditText) view.findViewById(R.id.lr_number);

    mOrigin = (AutoCompleteTextView) view.findViewById(R.id.origin);
    mOrigin.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mOrigin.setOnClickListener(this);

    mDestination = (AutoCompleteTextView) view.findViewById(R.id.destination);
    mDestination.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mDestination.setOnClickListener(this);
    mDestination.setOnItemSelectedListener(this);

    mConsignmentType = (AutoCompleteTextView) view.findViewById(R.id.consignment_type);
    mConsignmentType.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mConsignmentType.setOnClickListener(this);

    mVehicleNumber = (AutoCompleteTextView) view.findViewById(R.id.vehicle_number);
    mVehicleNumber.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mVehicleNumber.setOnClickListener(this);

    mBarCode = (AutoCompleteTextView) view.findViewById(R.id.barcode);
    mBarCode.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mBarCode.setOnClickListener(this);

    mDriverName = (AutoCompleteTextView) view.findViewById(R.id.driver_name);
    mDriverName.setDropDownBackgroundDrawable(new ColorDrawable(getActivity().getApplicationContext().getResources().getColor(R.color.purple_900_combination_2)));
    mDriverName.setOnClickListener(this);

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

    mVolume = (EditText) view.findViewById(R.id.number_of_items);
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
    case R.id.barcode:
      mBarCode.showDropDown();
      break;
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
      mSlideDateTimePicker.show();
      break;
    case R.id.vehicle_reporting_time:
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
        consignmentDetails.setNumberOfItems(Integer.parseInt(mNumberOfItems.getText().toString()));
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

  @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    Toast.makeText(getActivity(), "Item Selected at position " + position, Toast.LENGTH_SHORT).show();
  }

  @Override public void onNothingSelected(AdapterView<?> parent) {

  }

}