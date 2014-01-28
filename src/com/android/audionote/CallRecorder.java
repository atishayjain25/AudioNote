package com.android.audionote;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class CallRecorder {
	
	public static MediaRecorder recorder;
    public static File audiofile;
	
	public static void StartRecording(Context context)
	{
		String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
        File sampleDir = new File(Environment.getExternalStorageDirectory(), "/AudioNote");
        if (!sampleDir.exists()) {
            sampleDir.mkdirs();
        }
        String file_name = "AudioNote";
        try {
            audiofile = File.createTempFile(file_name, ".3gp", sampleDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        recorder = new MediaRecorder();
//      recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);

        recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(audiofile.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("Error is happened here in Prepare Method1");
        } catch (IOException e) { 
            e.printStackTrace();
            System.out.println("Error is happened here in Prepare Method2");
        }
        try{
        recorder.start();
        }catch(IllegalStateException e){
        	e.printStackTrace();
            //Here it is thorowing illegal State exception
            System.out.println("Error is happened here in Start Method");
        }
	}
	
	public static File StopRecording()
	{
		recorder.stop();
		recorder.release();
		Log.i("Recorder", audiofile.length()/1024 + " KB");
		return audiofile;
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
