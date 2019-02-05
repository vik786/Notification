package com.example.vikas.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button notificationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationButton=(Button)findViewById(R.id.notificationButton);
        createNotificationChannel();
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerNotification();
            }
        });
    }

    public void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel(getString(R.string.NEWS_CHANNEL_ID), getString(R.string.CHANNEL_NEWS), NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(getString(R.string.CHANNEL_DESCRIPTION));
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void triggerNotification()
    {
        Intent intent=new Intent(this,activity_Notification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,getString(R.string.NEWS_CHANNEL_ID))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle("Notification")
                .setContentText("My Name is Khan But I am Not a Terrorist")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("My Name is Khan But I am Not a Terrorist"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setChannelId(getString(R.string.NEWS_CHANNEL_ID))
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getResources().getInteger(R.integer.notificationId),builder.build());
    }

}
