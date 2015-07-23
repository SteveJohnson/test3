package com.loginext.shipme.activity;

import android.app.Activity;
import android.os.Bundle;

import com.loginext.shipme.R;
import com.loginext.shipme.fragment.FrontPageFragment;


public class FrontPageActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_front_page);
    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction().add(R.id.container, new FrontPageFragment()).commit();
    }
  }
}
