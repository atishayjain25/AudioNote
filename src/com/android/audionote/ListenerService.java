package com.android.audionote;

import java.util.ArrayList;
import java.util.List;


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


	private class DataPoint {
        public float x, y, z;
        public long atTimeMilliseconds;
        public DataPoint(float x, float y, float z, long atTimeMilliseconds) {
                this.x = x;
                this.y = y;
                this.z = z;
                this.atTimeMilliseconds = atTimeMilliseconds;
        }
}

	private List<DataPoint> dataPoints = new ArrayList<DataPoint>();
	
	private static final int SHAKE_CHECK_THRESHOLD = 200;
	private boolean IsFileRecording = false;
	
	/**
	 * After we detect a shake, we ignore any events for a bit of time. We don't want two shakes to close together.
	 */
	private static final int IGNORE_EVENTS_AFTER_SHAKE = 1000;
	private long lastUpdate;
	private long lastShake = 0;
	
	private float last_x = 0, last_y=0, last_z=0; 
	
	@Override
	public void onSensorChanged(SensorEvent event) {
	        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
	
	                long curTime = System.currentTimeMillis();
	                // if a shake in last X seconds ignore.
	                if (lastShake != 0 && (curTime - lastShake) < IGNORE_EVENTS_AFTER_SHAKE) return;
	
	                float x = event.values[0];
	                float y = event.values[1];
	                float z = event.values[2];
	                if (last_x != 0 && last_y != 0 && last_z != 0 && (last_x != x || last_y != y || last_z != z)) {
	                        DataPoint dp = new DataPoint(last_x-x, last_y-y, last_z-z, curTime);
	                        //Log.i("XYZ",Float.toString(dp.x)+"   "+Float.toString(dp.y)+"   "+Float.toString(dp.z)+"   ");
	                        dataPoints.add(dp);
	
	                        if ((curTime - lastUpdate) > SHAKE_CHECK_THRESHOLD) {
	                                lastUpdate = curTime;
	                                checkForShake();
	                        }
	                }
	                last_x = x;
	                last_y = y;
	                last_z = z;
	        }                
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	private static final long KEEP_DATA_POINTS_FOR = 1500;
	private static final long MINIMUM_EACH_DIRECTION = 7;
	private static final float POSITIVE_COUNTER_THRESHHOLD = (float) 2.0;
	private static final float NEGATIVE_COUNTER_THRESHHOLD = (float) -2.0;
	
	
	public void checkForShake() {
        long curTime = System.currentTimeMillis();
        long cutOffTime = curTime - KEEP_DATA_POINTS_FOR;
        while(dataPoints.size() > 0 && dataPoints.get(0).atTimeMilliseconds < cutOffTime) dataPoints.remove(0);

        int x_pos =0, x_neg=0, x_dir = 0, y_pos=0, y_neg=0, y_dir=0, z_pos=0, z_neg = 0, z_dir = 0;
        for(DataPoint dp: dataPoints){
                if (dp.x > POSITIVE_COUNTER_THRESHHOLD && x_dir < 1) {
                        ++x_pos;
                        x_dir = 1;
                }
                if (dp.x < NEGATIVE_COUNTER_THRESHHOLD && x_dir > -1) {
                        ++x_neg;
                        x_dir = -1;
                }
                if (dp.y > POSITIVE_COUNTER_THRESHHOLD && y_dir < 1) {
                        ++y_pos;
                        y_dir = 1;
                }
                if (dp.y < NEGATIVE_COUNTER_THRESHHOLD && y_dir > -1) {
                        ++y_neg;
                        y_dir = -1;
                }
                if (dp.z > POSITIVE_COUNTER_THRESHHOLD && z_dir < 1) {
                        ++z_pos;
                        z_dir = 1;
                }
                if (dp.z < NEGATIVE_COUNTER_THRESHHOLD && z_dir > -1) {
                        ++z_neg;                        
                        z_dir = -1;
                }
        }
        //Log.i(TAG,Integer.toString(x_pos)+" - "+Integer.toString(x_neg)+"  "+Integer.toString(y_pos)+" - "+Integer.toString(y_neg)+"  "+Integer.toString(z_pos)+" - "+Integer.toString(z_neg));

        //if ((x_pos >= MINIMUM_EACH_DIRECTION && x_neg >= MINIMUM_EACH_DIRECTION) || 
                        //(y_pos >= MINIMUM_EACH_DIRECTION && y_neg >= MINIMUM_EACH_DIRECTION) || 
                        //(z_pos >= MINIMUM_EACH_DIRECTION && z_neg >= MINIMUM_EACH_DIRECTION) ) { 
        if(x_pos + x_neg + y_pos + y_neg + z_pos + z_neg >= MINIMUM_EACH_DIRECTION) {
                lastShake = System.currentTimeMillis();
                last_x = 0; last_y=0; last_z=0;
                dataPoints.clear();
                Toast.makeText(getApplicationContext(), "Shake Detected", Toast.LENGTH_LONG).show();
                IsFileRecording = !IsFileRecording;
                if(!IsFileRecording)
                {
                	NotificationHelper.DisplayNotification(this, "abcd.mp3", "10 Kb");
                }
                return;
        }
	
	}
}