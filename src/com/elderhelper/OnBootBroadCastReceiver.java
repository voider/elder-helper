package com.elderhelper;

import java.util.Calendar;

import com.elderhelper.utils.AlarmCreator;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OnBootBroadCastReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    try {
      new AlarmCreator(context).scheduleInFuture(Calendar.MINUTE, 5,AlarmReceiver.class);
    } catch (Exception ex) {
      Log.e("elder helper ",ex.getMessage(),ex);
    }
 }}
