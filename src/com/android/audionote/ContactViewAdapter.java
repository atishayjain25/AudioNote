package com.android.audionote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ContactViewAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<String> parentItems, child;
	private Context context;

	public ContactViewAdapter(Context context, ArrayList<String> parents, ArrayList<Object> childern) {
		this.context = context;
		this.parentItems = parents;
		this.childtems = childern;
	}

	public void setInflater(LayoutInflater inflater, Activity activity) {
		this.inflater = inflater;
		this.activity = activity;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		child = (ArrayList<String>) childtems.get(groupPosition);

		TextView textView = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.audioview, null);
		}
		
		String startDateString;
		String durationString;
		try
		{
			Date startDate = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").parse(child.get(4*(childPosition)));
			Date endDate = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").parse(child.get(4*(childPosition) + 1));
			
			startDateString = new SimpleDateFormat("dd-MMM hh-mm a").format(startDate);
			long duration = ((endDate.getTime() - startDate.getTime())/1000);
			durationString = duration + " sec";
		}
		catch(Exception ex)
		{
			startDateString = child.get(4*(childPosition));
			durationString = child.get(4*(childPosition) + 1);
		}

		textView = (TextView) convertView.findViewById(R.id.audio_file_name);
		textView.setText(child.get(4*(childPosition) + 3));
		
		textView = (TextView) convertView.findViewById(R.id.audio_file_size);
		textView.setText(child.get(4*(childPosition) + 2));
		
		textView = (TextView) convertView.findViewById(R.id.audio_start_time);
		textView.setText(startDateString);
		
		textView = (TextView) convertView.findViewById(R.id.audio_end_time);
		textView.setText(durationString);
		
		ImageButton imageButton = (ImageButton) convertView.findViewById((R.id.play_button));
		
		imageButton.setOnClickListener(new OnClickListener() {
			
			String fullFilePath = Environment.getExternalStorageDirectory() + "/AudioNote/" + child.get(4*(childPosition) + 3);
			@Override
			public void onClick(View v) {
				PlayAudioFileHelper.PlayAudio(context, fullFilePath);
				
			}
		});

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Toast.makeText(activity, child.get(childPosition),
						Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.contactview, null);
		}

		((TextView) convertView.findViewById(R.id.textView1)).setText(parentItems.get(groupPosition*3));
		
		((TextView) convertView.findViewById(R.id.call_duration)).setText(parentItems.get(groupPosition*3 + 1));
		
		((TextView) convertView.findViewById(R.id.audio_snippet_count)).setText(parentItems.get(groupPosition*3 + 2));

		return convertView;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		Log.d("ContactViewAdapter", "groupPos" + groupPosition + " childSize :" + ((ArrayList<String>) childtems.get(groupPosition)).size());
		return ((ArrayList<String>) childtems.get(groupPosition)).size()/4;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return parentItems.size()/3;
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}