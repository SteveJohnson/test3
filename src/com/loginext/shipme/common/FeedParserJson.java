package com.loginext.shipme.common;

import android.app.Notification;

import com.google.gson.Gson;

public class FeedParserJson {
  private static final String TAG = FeedParserJson.class.getSimpleName();

  public static Notification parseNotificationApiResponse(String notificationApiResponse){
    Gson gson = new Gson();
    Notification notification = gson.fromJson(notificationApiResponse, Notification.class);
    return notification;
  }
}
