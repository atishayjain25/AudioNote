package com.android.audionote;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> names;
	private final ArrayList<String> counts;
 
	public MobileArrayAdapter(Context context, ArrayList<String> names, ArrayList<String> counts) {
		super(context, R.layout.audio_log, names);
		this.context = context;
		this.names = names;
		this.counts=counts;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.audio_log, parent, false);
		TextView textView_name = (TextView) rowView.findViewById(R.id.label);
		TextView textView_count= (TextView) rowView.findViewById(R.id.count);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView_name.setText(names.get(position));
		textView_count.setText(counts.get(position));
 
		imageView.setImageResource(R.drawable.default_picture);
 
		return rowView;
	}
}
