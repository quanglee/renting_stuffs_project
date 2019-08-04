package com.quangle.rentingutilities.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Build;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;

import androidx.core.app.NotificationCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private NotificationManager mNotificationManager;

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fcmToken", "empty");
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        System.out.println("NEW TOKEN GENERATED");
        System.out.println(s);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fcmToken", s).apply();
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Auth auth = MySharedPreferences.getAuth(this);
        if (auth != null) { // ONLY AUTHENTICATED USER RECEIVING THE NOTIFICATION FROM FIREBASE FCM
            System.out.println("RECEIVING NOTIFICATION FROM FMC");
            String CHANNEL_ID = "my_channel_01";// The id of the channel.
            CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.drawable.icon_notification)
                    .setTicker("Hearty365")
                    .setAutoCancel(true);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(mChannel);
            }

            notificationManager.notify(0, notificationBuilder.build());
        }
    }
}
