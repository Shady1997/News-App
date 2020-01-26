package com.graduationproject.egyptnews.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.sessionmanager.SessionManager;
import com.graduationproject.egyptnews.views.activities.EntryActivity;
import com.graduationproject.egyptnews.views.activities.EnvironmentalNewsActivity;
import com.graduationproject.egyptnews.views.activities.MainNewsActivity;

public class NotificationGenerator extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent intent1=new Intent(context, MainNewsActivity.class);
        Intent intent2=new Intent(context, EnvironmentalNewsActivity.class);

        Intent intent3=new Intent(context, EntryActivity.class);
        Intent intent4=new Intent(context, EntryActivity.class);

        SessionManager sessionManager=new SessionManager(context);
        if(sessionManager.checkUserSession()) {
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 101, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context)
                    .setContentIntent(pendingIntent1)
            .setLights(Color.BLUE, 500, 500);
            long[] pattern1 = {500,500,500,500,500,500,500,500,500};
            builder1.setVibrate(pattern1);
            builder1.setStyle(new NotificationCompat.InboxStyle());
            builder1.setContentTitle("General News In Egypt ")
                    .setContentText("the is new general news in egypt")
                    .setSmallIcon(R.drawable.ic_gnews)
                    .setSound( Uri.parse("android.resource://"+ context.getPackageName() + "/" + R.raw.notification))
                    .setAutoCancel(true);

            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(context)
                    .setContentIntent(pendingIntent2)
                    .setLights(Color.BLUE, 500, 500);
            long[] pattern2 = {500,500,500,500,500,500,500,500,500};
            builder2.setVibrate(pattern2);
            builder2.setStyle(new NotificationCompat.InboxStyle());
            builder2.setContentTitle("Environmental News In Egypt ")
                    .setContentText("the is new environmental news in egypt")
                    .setSmallIcon(R.drawable.ic_enews)
                    .setSound( Uri.parse("android.resource://"+ context.getPackageName() + "/" + R.raw.notification))
                    .setAutoCancel(true);

            notificationManager.notify(100, builder1.build());
            notificationManager.notify(101, builder2.build());
        }
        else{
            intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 100, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntent4 = PendingIntent.getActivity(context, 101, intent4, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder3 = new NotificationCompat.Builder(context)
                    .setContentIntent(pendingIntent3)
                    .setLights(Color.BLUE, 500, 500);
            long[] pattern3 = {500,500,500,500,500,500,500,500,500};
            builder3.setVibrate(pattern3);
            builder3.setStyle(new NotificationCompat.InboxStyle());
            builder3.setContentTitle("General News In Egypt ")
                    .setContentText("the is new general news in egypt")
                    .setSmallIcon(R.drawable.ic_gnews)
                    .setSound( Uri.parse("android.resource://"+ context.getPackageName() + "/" + R.raw.notification))
                    .setAutoCancel(true);

            NotificationCompat.Builder builder4 = new NotificationCompat.Builder(context)
                    .setContentIntent(pendingIntent4)
                    .setLights(Color.BLUE, 500, 500);
            long[] pattern4 = {500,500,500,500,500,500,500,500,500};
            builder4.setVibrate(pattern4);
            builder4.setStyle(new NotificationCompat.InboxStyle());
            builder4.setContentTitle("Environmental News In Egypt ")
                    .setContentText("the is new environmental news in egypt")
                    .setSmallIcon(R.drawable.ic_enews)
                    .setSound( Uri.parse("android.resource://"+ context.getPackageName() + "/" + R.raw.notification))
                    .setAutoCancel(true);

            notificationManager.notify(100, builder3.build());
            notificationManager.notify(101, builder4.build());
        }
    }
}
