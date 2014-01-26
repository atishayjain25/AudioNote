package com.android.audionote;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public class NotificationHelper {
	
	public static void DisplayNotification(Context context, String msg)
	{
		Notification.Builder builder = new Notification.Builder(context);
		
		builder.setContentTitle("Audio note saved")
		.setContentText(msg)
		.setSmallIcon(R.drawable.application_icon);
		
		NotificationManager notificationManager = 
				  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(0, builder.getNotification()); 
	}
}
