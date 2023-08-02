package com.example.applicationandroid.service;

import android.app.NotificationManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.applicationandroid.R;
import com.example.applicationandroid.helper.webservice.DeviceTokenAPI;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;

import java.util.UUID;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static String NOTIFICATION_CHANNEL_ID = "my_notification_channel";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
           showNotification(title,body);
        }
    }

    /**
     * fonction pour afficher la notification
     * @param title
     * @param message
     */
    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(UUID.randomUUID().hashCode(), builder.build());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        DeviceTokenAPI deviceTokenAPI = new DeviceTokenAPI(this);
        try {
            deviceTokenAPI.sendDeviceToken(token);
        } catch (JSONException e) {
        }
    }
}
