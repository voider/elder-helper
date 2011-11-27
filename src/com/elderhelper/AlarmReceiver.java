package com.elderhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    Intent newIntent = new Intent(context,ElderhelperActivity.class);
    newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(newIntent);
  }
}
