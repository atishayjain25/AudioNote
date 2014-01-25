package com.android.audionote.controller;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ListenerService extends Service implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	public static final String TAG = "Audio Note";
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "Listener Service started");
		//get the sensor service
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    //get the accelerometer sensor
	    mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	    return Service.START_NOT_STICKY;
	  }


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float x = event.values[0];
	    float y = event.values[1];
	   	float z = event.values[2];
	   	String info = "Accelerometer :" + x + ":  " + y + ": " + z;
	   	Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
	}

}
