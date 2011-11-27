package com.elderhelper;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OnBootBroadCastReceiver extends BroadcastReceiver {

  private static final int REQUEST_ID = 221; // This value is not really used

  @Override
  public void onReceive(Context context, Intent arg1) {
    try {
      Toast.makeText(context, "Setting alarm", Toast.LENGTH_SHORT).show();
      AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
      Intent intent = new Intent(context,AlarmReceiver.class);
      PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.MINUTE,1);
      long wakeupTime = calendar.getTimeInMillis();
      mgr.set(AlarmManager.RTC_WAKEUP,wakeupTime,pendingIntent);
    } catch (Exception ex) {
      Log.e("elder helper ",ex.getMessage(),ex);
    }
 }}
