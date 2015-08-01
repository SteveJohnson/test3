package com.loginext.shipme.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class LogiNextAutoCompleteTextView extends AutoCompleteTextView {

  public LogiNextAutoCompleteTextView(Context context) {
    super(context);
  }

  public LogiNextAutoCompleteTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LogiNextAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public LogiNextAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override public boolean enoughToFilter() {
    return true;
  }

}
