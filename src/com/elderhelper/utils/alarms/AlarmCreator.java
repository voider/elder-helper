package com.elderhelper.utils.alarms;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Creates an Alarm to trigger an alarm
 * receiver after a given amount of time
 * @author voider
 *
 */
public class AlarmCreator {
  
  private static final int REQUEST_ID = 221; // This value is not really used
  
  private Context context;
  

  /**
   * CTOR that receives the context that is used to create
   * the alarm
   * @param context
   */
  public AlarmCreator(Context context) {
     this.context = context;
  }
  
  /**
   * Creates an alarm for a given alarm receiver class
   * @param calendarTimeUnit one of the time unit fields of Calendar (i.e - MINUTES)
   * @param number amount of the given time unit
   * @param alarmReceiverClass broadcast receiver to receive the alarm
   */
  public void scheduleInFuture(int calendarTimeUnit, int number, Class<? extends BroadcastReceiver> alarmReceiverClass) {
    Calendar cal = Calendar.getInstance();
    cal.add(calendarTimeUnit,number);
    long timeInMilis = cal.getTimeInMillis();
    scheduleInFuture(timeInMilis,alarmReceiverClass);
  }
  
  /**
   * Creates an alarm for a given alarm receiver class
   * @param minutes time in the future (minutes) the alarm will be received
   * @param alarmReceiverClass broadcast receiver to receive the alarm
   */
  public void scheduleInFutureMinutes(int minutes,Class<? extends BroadcastReceiver> alarmReceiverClass) {
    scheduleInFuture(Calendar.MINUTE, alarmReceiverClass);
  }

  /**
   * Creates an alarm for a given alarm receiver class
   * @param timeInMilis time in future (mili seconds) the alarm will be received
   * @param alarmReceiverClass broadcast receiver to receive the alarm
   */
  public void scheduleInFuture(long timeInMilis, Class<? extends BroadcastReceiver> alarmReceiverClass)  {
    AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context,alarmReceiverClass);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    mgr.set(AlarmManager.RTC_WAKEUP,timeInMilis,pendingIntent);
  }
}
