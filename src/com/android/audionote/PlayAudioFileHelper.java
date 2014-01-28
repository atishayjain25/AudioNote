package com.android.audionote;

import java.io.File;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

 class PlayAudioFileHelper {
	
	public static void PlayAudio(Context context, String audioPath){
		
			   Intent intent = new Intent();  
			   intent.setAction(android.content.Intent.ACTION_VIEW);  
			   File file = new File(audioPath.toString());  
			   intent.setDataAndType(Uri.fromFile(file), "audio/*");  
			   context.startActivity(intent);
	}

}
