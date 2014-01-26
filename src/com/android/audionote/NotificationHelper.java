package com.android.audionote;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

public class NotificationHelper {
	
	public static void DisplayNotification(Context context, String fileName, String fileSize)
	{
		Notification.Builder builder = new Notification.Builder(context);
		
		builder.setDefaults(Notification.DEFAULT_SOUND)
		.setContentTitle("Audio note saved")
		.setContentText(fileName)
		.setContentInfo(fileSize)
		.setSmallIcon(R.drawable.application_icon);
		
		Intent clickIntent = new Intent(context, AudioNote_main_activity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(AudioNote_main_activity.class);
		stackBuilder.addNextIntent(clickIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		
		builder.setContentIntent(resultPendingIntent);
		
		NotificationManager notificationManager = 
				  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(0, builder.getNotification()); 
	}
}
