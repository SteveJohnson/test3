package com.sample.project.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.sample.project.R;

public class FrontPageFragment extends Fragment {
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

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_front_page, container, false);
        findViewByIds(rootView);
        setValues();
        return rootView;
    }

    private void findViewByIds(View view) {
        mLRNumber = (EditText) view.findViewById(R.id.lr_number);
        mOrigin = (AutoCompleteTextView) view.findViewById(R.id.origin);
        mDestination = (AutoCompleteTextView) view.findViewById(R.id.destination);
        mConsignmentType = (AutoCompleteTextView) view.findViewById(R.id.consignment_type);
        mVehicleNumber = (AutoCompleteTextView) view.findViewById(R.id.vehicle_number);
        mBarCode = (AutoCompleteTextView) view.findViewById(R.id.barcode);
        mDriverName = (AutoCompleteTextView) view.findViewById(R.id.driver_name);
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


}