package com.android.audionote;

import java.util.ArrayList;

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

		textView = (TextView) convertView.findViewById(R.id.audio_file_name);
		textView.setText(child.get(4*(childPosition) + 3));
		
		textView = (TextView) convertView.findViewById(R.id.audio_file_size);
		textView.setText(child.get(4*(childPosition) + 2));
		
		textView = (TextView) convertView.findViewById(R.id.audio_start_time);
		textView.setText(child.get(4*(childPosition)));
		
		textView = (TextView) convertView.findViewById(R.id.audio_end_time);
		textView.setText(child.get(4*(childPosition) + 1));
		
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

		((TextView) convertView.findViewById(R.id.textView1)).setText(parentItems.get(groupPosition*3 + 2));
		//((CheckedTextView) convertView).setChecked(isExpanded);

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