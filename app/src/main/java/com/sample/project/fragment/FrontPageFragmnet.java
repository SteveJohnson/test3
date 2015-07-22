package com.sample.project.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.project.R;

public class FrontPageFragmnet extends Fragment {

    public FrontPageFragmnet() {
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_front_page, container, false);
        return rootView;
    }
}