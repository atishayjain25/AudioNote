package com.android.audionote;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
//import android.app.Activity;
//import android.util.Log;
//import android.widget.AdapterView;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;

public class AudioNote_main_activity extends ListActivity {

	// [Mayank]  and number of audio snippet
	static final String[] MOBILE_OS = 
          new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};
	static final String[] COUNT = new String[] {"10", "199","200", "15"};
	public static final String TAG = "Audio Note";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "First Run Activity Started");
		Intent i = new Intent(this, ListenerService.class);
		this.startService(i);
		setListAdapter(new MobileArrayAdapter(this, MOBILE_OS, COUNT));
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		//Intent i = new Intent(getApplicationContext(), IndividualPersonLog.class);
      // sending data to new activity
      //i.putExtra("product", selectedValue);
      //System.out.println("*************"+selectedValue);
		Intent i = new Intent(getApplicationContext(), ContactLog.class);
      startActivity(i);

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.audio_note_main_activity, menu);
		return true;
	}

}

