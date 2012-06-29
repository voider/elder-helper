package com.elderhelper;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.elderhelper.utils.notification.NotificationHelper;
import com.elderhelper.utils.sound.MediaPlayerPool;

public class ElderhelperActivity extends Activity {
  
    //private MediaPlayer mediaPlayer = null;
    private MediaPlayerPool mediaPlayerPool = null;
    private File musicDir = null;
    /** Called when the activity is first created. */
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        //mediaPlayer = new MediaPlayer();
          
        musicDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
          musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
          File soundFilesDirectory = new File(musicDir,"elderhelper");
          Log.i("music",musicDir.getAbsolutePath());
          mediaPlayerPool = new MediaPlayerPool(soundFilesDirectory.getAbsolutePath(),"mp3",MediaPlayerPool.LoadMethod.SYNC, "takeyourpills", "of", "monday", "morning");
          try {
            mediaPlayerPool.load();
          } catch (IOException ex) {
            Log.e("elder helper", "Error in loading sound files. Exception is: " + ex.getMessage());
          }
        } else {
          Log.e("elder helper", "The sound files of the application must exist in your device music directory. Make sure the directory can be read by the application. ");
        }
        mediaPlayerPool.play();
        */
        
        //mediaPlayer.set
        setContentView(R.layout.main);
        Button approveButton = (Button)findViewById(R.id.approveButton);
        approveButton.setOnClickListener(new OnClickListener() {
          
          
          @Override
          public void onClick(View view) {
           
            //mediaPlayer.reset();
            try {
              
              NotificationHelper helper = new NotificationHelper(getApplicationContext());
              helper.set("ElderHelper", "Took medicine for Monday morning", ElderhelperActivity.class, Constants.NOTIFICATION_ID, R.drawable.elderhelper);
              ElderhelperActivity.this.finish();
              
              //NotificationHelper helper = new NotificationHelper(getApplicationContext());
              //helper.set("ElderHelper", "Launching elder helper", ElderhelperActivity.class, Constants.NOTIFICATION_ID, R.drawable.elderhelper);
              
              
              //mediaPlayer.setDataSource(songFile.getAbsolutePath());
              //mediaPlayer.prepare();
              //mediaPlayer.start();
              /*
              while (mediaPlayer.isPlaying());
              mediaPlayer.reset();
              songFile = new File(musicDir,"song2.mp3");
              mediaPlayer.setDataSource(songFile.getAbsolutePath());
              mediaPlayer.prepare();
              mediaPlayer.start();
              while (mediaPlayer.isPlaying());
              mediaPlayer.release();
              
              */
             // ElderhelperActivity.this.finish();
         
            } catch (IllegalArgumentException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } catch (IllegalStateException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } /*catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }*/
            /*
            
            new Thread(new Runnable() {
              
              @Override
              public void run() {
                Mail m = new Mail("user@gmail.com", "******"); 
                String[] toArr = {"to@gmail.com"}; 
                m.setTo(toArr); 
                m.setFrom("from@gmail.com"); 
                m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device."); 
                m.setBody("Email body."); 
           
                try { 
                  //m.addAttachment("/sdcard/filelocation"); 
                  //Context  context = ElderhelperActivity.this;
                  if(m.send()) { 
                    Log.i("mail","sent ok");
                  } else {
                    Log.i("mail","not sent ok");
                  } 
                } catch(Exception e) { 
                  //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
                  Log.e("MailApp", "Could not send email", e); 
                } 
                
              }
            }).start();*/
          }

          private void traverse(File f) {
            File[] files = f.listFiles();
            if (files == null) {
              return;
            }
            for (File currentFile:files) {
              
              if (currentFile.isDirectory()) {
                  traverse(currentFile);
              } else {
                if (currentFile.getAbsolutePath().endsWith("mp3")) {
                  Log.i("filesz",currentFile.getAbsolutePath());
                }
              }
             
            }
            
            // TODO Auto-generated method stub
            
          } 
        });
    }
}