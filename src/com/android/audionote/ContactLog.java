package com.android.audionote;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

public class ContactLog extends ExpandableListActivity{

	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<Object> childItems = new ArrayList<Object>();

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// this is not really  necessary as ExpandableListActivity contains an ExpandableList
		//setContentView(R.layout.main);

		Intent i = getIntent();
        // getting attached intent data
        String contactId = i.getStringExtra("contactId");
        Log.d("Contact View", "Contact Id: "+ contactId);
        int cId = Integer.parseInt(contactId);
		ExpandableListView expandableList = getExpandableListView(); // you can use (ExpandableListView) findViewById(R.id.list)

		expandableList.setDividerHeight(2);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);

		setGroupParents();
		setChildData();

		DB db = new DB(this);
		ArrayList<Object> result = db.getCallandAudioDetails(cId);
		ArrayList<Object>audioLogs = (ArrayList<Object>)result.get(1);
		ContactViewAdapter adapter = new ContactViewAdapter(this, (ArrayList<String>)result.get(0), (ArrayList<Object>)result.get(1));

		adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
		expandableList.setAdapter(adapter);
		expandableList.setOnChildClickListener(this);
		ActionBar ab = getActionBar();
        ab.setTitle("Name");
        ab.setSubtitle("Contact No");
	}

	public void setGroupParents() {
		parentItems.add("Android");
		parentItems.add("Core Java");
		parentItems.add("Desktop Java");
		parentItems.add("Enterprise Java");
	}

	public void setChildData() {

		// Android
		ArrayList<String> child = new ArrayList<String>();
		child.add("Core");
		child.add("Games");
		childItems.add(child);

		// Core Java
		child = new ArrayList<String>();
		child.add("Apache");
		child.add("Applet");
		child.add("AspectJ");
		child.add("Beans");
		child.add("Crypto");
		childItems.add(child);

		// Desktop Java
		child = new ArrayList<String>();
		child.add("Accessibility");
		child.add("AWT");
		child.add("ImageIO");
		child.add("Print");
		childItems.add(child);

		// Enterprise Java
		child = new ArrayList<String>();
		child.add("EJB3");
		child.add("GWT");
		child.add("Hibernate");
		child.add("JSP");
		childItems.add(child);
	}

}