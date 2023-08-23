package com.example.codsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



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

    public void notificationButton(View view) {
        final String CHANNEL_ID = "1";
        Bitmap largeicon = BitmapFactory.decodeResource(getResources(), R.drawable.akash);

        // Replace "YOUTUBE_VIDEO_URL" with the actual YouTube video URL
        String youtubeVideoUrl = "https://youtu.be/I2SERbDDNbo";

        Intent playIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeVideoUrl));
        PendingIntent playContentIntent = PendingIntent.getActivity(
                getApplicationContext(), 0, playIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent cancelIntent = new Intent(getApplicationContext(), video.class);
        PendingIntent cancelContentIntent = PendingIntent.getActivity(
                getApplicationContext(), 0, cancelIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Akash codes")
                .setContentText("New Video out, take a look")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle("Android Studio Notification Tutorial")
                        .bigText("Today let's discuss the custom notification generator using Java."))
                .setLargeIcon(largeicon)
                .addAction(R.mipmap.ic_launcher, "Play", playContentIntent)
                .addAction(R.mipmap.ic_launcher, "Cancel", cancelContentIntent)
                .setColor(Color.RED)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(1, builder.build());
    }

}