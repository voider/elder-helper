package com.elderhelper.utils.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Helps to manage notifications  
 */
public class NotificationHelper {
  private Context context;
  private NotificationManager notificationMgr;
  

  /**
   * 
   * @param context
   */
  public NotificationHelper(Context context) {
    this.context = context;
  }
  
  protected void setupNotificationMgr() {
    if (notificationMgr == null) {
      notificationMgr = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
     
    }
  }
  
  
  /**
   * Sets a notification
   * @param contentTitle notification content title
   * @param contentText notification content text
   * @param notoficationIntent intent to be used when notification is pressed
   * @param notficationId ID of notification
   * @param flags for notification
   * @param defaults for notification

   * @return notification ID
   */
  public int set(String contentTitle, String contentText, Intent notificationIntent, int notificationId, int icon, int flags, int defaults) {
    setupNotificationMgr();

    // prevent from create a new activity if there is existing activity in the stack
    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent intent = 
    PendingIntent.getActivity(context, 0, 
    notificationIntent, 0);
    
    //Notification.Builder builder = new Notification.Builder(context);
    //Works only at API level 11 and above - you can use the NotificationBuilder at utils if you need to create a notification for previous levelo
    //Notification notification = builder.setDefaults(defaults).setContentIntent(intent).setContentText(contentText).setContentTitle(contentTitle).setWhen(System.currentTimeMillis()).getNotification();
    Notification notification = new Notification(icon, "ElderHelper", System.currentTimeMillis());
    notification.setLatestEventInfo(context, contentTitle, contentText, intent);
    notificationMgr.notify(notificationId, notification);
    return notificationId;
  }

  /**
   * Sets a notification
   * @param contentTitle notification content title
   * @param contentText notification content text
   * @param notoficationIntent intent to be used when notification is pressed
   * @param notficationId ID of notification

   * @return notification ID
   */
  public int set(String contentTitle, String contentText, Intent notificationIntent, int notificationId, int icon) {
    return set(contentTitle,contentText,notificationIntent,notificationId,icon,0,0);
  }
  
  /**
   * Sets a notification
   * @param contentTitle title of notification
   * @param contentText text of notification
   * @param ActivityClass activity to be invoked when the notification is used
   * @param notificationId ID of notification
   * @param flags for notification
   * @param defaults for notification
   * @return
   */
  public int set(String contentTitle, String contentText, Class<? extends Activity> activityClass, int notificationId, int icon, int flags, int defaults) {
    Intent notificIntent = new Intent(context,activityClass);
    return set(contentTitle,contentText,notificIntent,notificationId, icon, flags,defaults);
  }
  
  /**
   * Sets a notification
   * @param contentTitle title of notification
   * @param contentText text of notification
   * @param ActivityClass activity to be invoked when the notification is used
   * @param notificationId ID of notification
   * @return
   */
  public int set(String contentTitle, String contentText, Class<? extends Activity> activityClass, int notificationId, int icon) {
    return set(contentTitle,contentText,activityClass,notificationId, icon,0,0);
  }
  
  /**
   * Removes the notification
   * @param notificationId ID of notification to be removed
   */
  public void remove(int notificationId) {
    notificationMgr.cancel(notificationId);
  }
  
}
