package com.example.orange_app.notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

public class OreoAndAboveNotification extends ContextWrapper {

    private static final String ID = "some_id";
    private static final String NAME = "OrangeApp";

    private NotificationManager notificationManager;

    public OreoAndAboveNotification(Context base) {

        super(base);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            createChannel();

        }//if

    }//OreoAndAboveNotification()

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {

        NotificationChannel notificationChannel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getNotificationManager().createNotificationChannel(notificationChannel);

    }//createChannel

    public NotificationManager getNotificationManager(){

        if(notificationManager == null){

            notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        }//if

        return notificationManager;

    }//NotificationManager getNotificationManager

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getONotifications(String title, String body, PendingIntent pIntent, Uri soundUri, String icon){

        return new Notification.Builder(getApplicationContext(), ID)
                .setContentIntent(pIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(soundUri)
                .setAutoCancel(true)
                .setSmallIcon(Integer.parseInt(icon));

    }//Notification.Builder getONotifications

}//OreoAndAboveNotification
