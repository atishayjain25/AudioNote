package com.android.audionote;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
//import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
//import android.util.Log;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView;

public class AudioNote_main_activity extends ListActivity {

	static final String[] MOBILE_OS = 
          new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};
	static final String[] COUNT = new String[] {"10", "199","200", "15"};
	public static final String TAG = "Audio Note";
	MobileArrayAdapter mobileArrayAdapter;//=new MobileArrayAdapter(this,MOBILE_OS,COUNT);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "First Run Activity Started");
		Intent i = new Intent(this, ListenerService.class);
		this.startService(i);
		mobileArrayAdapter=new MobileArrayAdapter(this,MOBILE_OS,COUNT);
		setListAdapter(mobileArrayAdapter);
		handleIntent(getIntent());
	}
	
	@Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }
	
	private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //doMySearch(query);
            //use the query to search your data somehow
            AudioNote_main_activity.this.mobileArrayAdapter.getFilter().filter(query);
        }
    }
	
	private void doMySearch(String query) {
		// TODO Auto-generated method stub
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.audio_note_main_activity, menu);
	 // Associate searchable configuration with the SearchView
	    SearchManager searchManager =
	           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView =
	            (SearchView) menu.findItem(R.id.search).getActionView();
	    searchView.setSearchableInfo(
	            searchManager.getSearchableInfo(getComponentName()));

	    return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		Intent i = new Intent(getApplicationContext(), IndividualPersonLog.class);
      // sending data to new activity
      i.putExtra("product", selectedValue);
      System.out.println("*************"+selectedValue);
      startActivity(i);

	}

}

