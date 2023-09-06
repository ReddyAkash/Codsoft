package com.example.swe2008;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("1","notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notificationButton(View view){

        final String CHANNEL_ID="channel1";
        NotificationCompat.Builder  builder= new NotificationCompat.Builder(this,CHANNEL_ID).setSmallIcon(R.drawable.baseline_notifications_active_24);
        NotificationManager notificationManager=getSystemService(NotificationManager.class);
        notificationManager.notify(1,builder.build());


    }
}