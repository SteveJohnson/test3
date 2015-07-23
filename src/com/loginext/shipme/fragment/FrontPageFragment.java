package com.loginext.shipme.fragment;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.loginext.shipme.R;
import com.loginext.shipme.model.RequesterView;
import com.loginext.shipme.presenter.BasePresenter;
import com.loginext.shipme.presenter.BasePresenterImp;

public class FrontPageFragment extends Fragment implements RequesterView, OnClickListener{
  private BasePresenter basePresenter;
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
  private EditText mWeight;
  private EditText mTripId;
  private EditText mTripDate;
  private EditText mVolume;
  private EditText mNumberOfItems;
  private EditText mChallanNumber;
  private EditText mSealNumber;

  public FrontPageFragment() {
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    basePresenter = BasePresenterImp.getInstance();
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
    mOrigin.setOnClickListener(this);

    mDestination = (AutoCompleteTextView) view.findViewById(R.id.destination);
    mDestination.setOnClickListener(this);

    mConsignmentType = (AutoCompleteTextView) view.findViewById(R.id.consignment_type);
    mConsignmentType.setOnClickListener(this);

    mVehicleNumber = (AutoCompleteTextView) view.findViewById(R.id.vehicle_number);
    mVehicleNumber.setOnClickListener(this);

    mBarCode = (AutoCompleteTextView) view.findViewById(R.id.barcode);
    mBarCode.setOnClickListener(this);

    mDriverName = (AutoCompleteTextView) view.findViewById(R.id.driver_name);
    mDriverName.setOnClickListener(this);

    mDriverPhoneNumber = (EditText) view.findViewById(R.id.driver_phone_number);
    mVehicleType = (EditText) view.findViewById(R.id.vehicle_type);
    mVehicleCapacity = (EditText) view.findViewById(R.id.vehicle_capacity);
    mWeight = (EditText) view.findViewById(R.id.weight);
    mTripId = (EditText) view.findViewById(R.id.trip_id);
    mTripDate = (EditText) view.findViewById(R.id.trip_date);
    mVolume = (EditText) view.findViewById(R.id.number_of_items);
    mChallanNumber = (EditText) view.findViewById(R.id.challan_number);
    mSealNumber = (EditText) view.findViewById(R.id.seal_number);
  }

  private void setValues() {

  }

  @Override public void onConsignmentTypeReceived(List<String> lookupCds) {

  }

  @Override public void onOriginReponse(List<String> origins) {
    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, origins);
    mOrigin.setAdapter(adapter);
  }

  @Override public void onDestinationReceived(List<String> destinations) {
    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, destinations);
    mDestination.setAdapter(adapter);
  }

  @Override public void onBarcodeReceived(List<String> barcodes, List<String> deviceIds) {
    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, barcodes);
    mBarCode.setAdapter(adapter);
  }

  @Override public void onVehicleReceived(List<String> capacitiesInVolume, List<String> deviceIds, List<String> vehicleNumbers, List<String> vehicleTypes) {
    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, vehicleNumbers);
    mVehicleNumber.setAdapter(adapter1);
  }

  @Override public void onDriverReceived(List<String> driverNames, List<String> phoneNumbers) {
    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, driverNames);
    mVehicleNumber.setAdapter(adapter);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
    case R.id.origin:
      basePresenter.fetchOrigin();
      break;
    case R.id.destination:
      basePresenter.fetchDestination();
      break;
    case R.id.barcode:
      basePresenter.fetchBarcode();
      break;
    case R.id.vehicle_number:
      basePresenter.fetchVehicle();
      break;
    case R.id.driver_name:
      basePresenter.fetchDriver();
      break;
    case R.id.consignment_type:
      basePresenter.fetchConsignmentType();
      break;
    default:
      //DO Nothing
      break;
    }
  }


}