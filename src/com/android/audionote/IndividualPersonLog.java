package com.android.audionote;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
 
public class IndividualPersonLog extends ListActivity{

	static final String[] Individual_lists=new String[] {"1000","2000","3000"};
    static final String[] Dates=new String[] {"Dec 25, 2013", "Jan 1, 2014", "Jan 26, 2014"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.single_item_list_view);
         
        /*TextView txtProduct = (TextView) findViewById(R.id.product_label);
         
        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("product");
        // displaying selected product name
        Log.i("MyList", "Passed value" + product);
        txtProduct.setText(product);*/
        setListAdapter(new MobileArrayAdapter_IndividualPerson(this,Individual_lists,Dates));
        ActionBar ab = getActionBar();
        ab.setTitle("My Title");
        ab.setSubtitle("sub-title");
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.audio_note_main_activity, menu);
		return true;
	}
}
