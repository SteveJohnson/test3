package com.loginext.shipme.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.loginext.shipme.R;
import com.loginext.shipme.fragment.FrontPageFragment;


public class FrontPageActivity extends FragmentActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_front_page);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, new FrontPageFragment()).commit();
    }
  }
}
