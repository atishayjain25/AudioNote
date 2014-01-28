package com.android.audionote;


import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class FirstRun extends Activity {

	public static final String TAG = "Audio Note";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_run);
		Log.i(TAG, "First Run Activity Started");
		Intent i = new Intent(this, ListenerService.class);
		this.startService(i);
		
		findViewById(R.id.continue_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), AudioNote_main_activity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getApplicationContext().startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_run, menu);
		return true;
	}

}
