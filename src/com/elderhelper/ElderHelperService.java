package com.elderhelper;

import com.elderhelper.utils.notification.NotificationHelper;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ElderHelperService extends Service {

  @Override
  public IBinder onBind(Intent arg0) {
    return null;
  }
  
  @Override
  public int onStartCommand (Intent intent, int flags, int startId) {
    return START_STICKY;
  }
  
  @Override
  public void onCreate() {
    NotificationHelper helper = new NotificationHelper(this);
    //helper.set("ElderHelper", "Launching elder helper", Constants.NOTIFICATION_ID, R.drawable.elderhelper);
    helper.set("ElderHelper", "Launching elder helper", ElderhelperActivity.class, Constants.NOTIFICATION_ID, R.drawable.elderhelper);
    Toast.makeText(this,"Service created", Toast.LENGTH_LONG).show();
  }
  
  @Override
  public void onDestroy() {
    super.onDestroy();
    NotificationHelper helper = new NotificationHelper(this);
    helper.remove(Constants.NOTIFICATION_ID);
    Toast.makeText(this,"Service stopped", Toast.LENGTH_LONG).show();

  }

}
