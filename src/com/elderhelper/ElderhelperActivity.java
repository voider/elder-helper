package com.elderhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ElderhelperActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button approveButton = (Button)findViewById(R.id.approveButton);
        approveButton.setOnClickListener(new OnClickListener() {
          
          @Override
          public void onClick(View view) { 
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
            }).start();
          } 
        });
    }
}