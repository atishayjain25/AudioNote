package com.android.audionote;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public class NotificationHelper {
	
	public static void DisplayNotification(Context context, String fileName, String fileSize)
	{
		Notification.Builder builder = new Notification.Builder(context);
		
		builder.setDefaults(Notification.DEFAULT_SOUND)
		.setContentTitle("Audio note saved")
		.setContentText(fileName)
		.setContentInfo(fileSize)
		.setSmallIcon(R.drawable.application_icon);
		
		NotificationManager notificationManager = 
				  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(0, builder.getNotification()); 
	}
}
