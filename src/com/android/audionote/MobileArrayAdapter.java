package com.android.audionote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] names;
	private final String[] counts;
 
	public MobileArrayAdapter(Context context, String[] names, String[] counts) {
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
		textView_name.setText(names[position]);
		textView_count.setText(counts[position]);
 
		// Change icon based on name
		String s = names[position];
 
		System.out.println(s);
		imageView.setImageResource(R.drawable.default_picture);
 
		return rowView;
	}
}
