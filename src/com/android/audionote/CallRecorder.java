package com.android.audionote;

import android.content.Context;
import android.media.AudioManager;

public class CallRecorder {
	
	public static void StartRecording(Context context)
	{
		
	}
	
	public static boolean IsCallActive(Context context){
		   AudioManager manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		   if(manager.getMode()==AudioManager.MODE_IN_CALL &&
				   manager.getMode()!= AudioManager.MODE_RINGTONE){
		         return true;
		   }
		   else{
		       return false;
		   }
		}
}
