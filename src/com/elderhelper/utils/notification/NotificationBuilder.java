package com.elderhelper.utils.notification;

import android.app.Notification;
import android.net.Uri;

/**
 * Builder for notification objects. 
 * This class van be used to create notification objects
 * and conveniently set its attributes using method chaining
 *
 */
public class NotificationBuilder {
  
  private int flags = 0;
  private boolean flagsSet = false;
  
  private int defaults = 0;
  private boolean defaultsSet = false;
  
  private int ledOnMs;
  private boolean ledOnMsSet = false;
  
  private int ledOffMs;
  private boolean ledOffMsSet = false;

  private int ledArgb;
  private boolean  ledArgbSet = false;
  
  private Uri sound;
  private boolean soundSet = false;

  private int icon;
  private String tickerText;
  private long when;
  
  public NotificationBuilder(int icon, String tickerText , long when) {
    this.icon = icon;
    this.tickerText = tickerText;
    this.when = when;
    
  }
  
  public NotificationBuilder defaults(int ...defaults) {
    for (int d:defaults) {
      this.defaults = this.defaults | d;
    }
    this.defaultsSet = true;
    return this;
  }
  
  public NotificationBuilder flags(int... flags) {
    for (int flag:flags) {
      this.flags = this.flags | flag;
    }
    this.flagsSet = true;
    return this;
  }
  
  public NotificationBuilder ledOnMs(int ledOnMs) {
    this.ledOnMs = ledOnMs;
    this.ledOnMsSet = true;
    return this;
  }
  
  public NotificationBuilder ledOffMs(int ledOffMs) {
    this.ledOffMs = ledOffMs;
    this.ledOffMsSet = true;
    return this;
  }
  
  public NotificationBuilder ledArgb(int ledArgb) {
    this.ledArgb = ledArgb;
    this.ledArgbSet = true;
    return this;
  }
  
  public NotificationBuilder sound(Uri sound) {
    this.sound = sound;
    this.soundSet = true;
    return this;
  }
  
  public Notification build() {
    Notification notification = new Notification(this.icon,this.tickerText,this.when);
    if (soundSet) {
      notification.sound = sound;
    }
    if (flagsSet) {
      notification.flags |= flags;
    }
    
    if (defaultsSet) {
      notification.defaults |= defaults;
    }
    
    if (ledOnMsSet) {
      notification.ledOnMS = ledOnMs;
    }
    
    if (ledOffMsSet) {
      notification.ledOffMS = ledOffMs;
    }
    
    if (ledArgbSet) {
      notification.ledARGB = ledArgb;
    }
    return notification;
  }

}
