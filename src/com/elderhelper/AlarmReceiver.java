package com.elderhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;

public class AlarmReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    /*
    Intent newIntent = new Intent(context,ElderhelperActivity.class);
    newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(newIntent);
    */
    
    /*
    Intent serviceIntent = new Intent();
    serviceIntent.setAction(Constants.SERVICE_ACTION);
    context.startService(intent);
    */
  }
}
