package com.android.audionote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MobileArrayAdapter_IndividualPerson extends ArrayAdapter<String> {
	private final Context context;
	private final String[] file_names;
	private final String[] file_date;
 
	public MobileArrayAdapter_IndividualPerson(Context context, String[] file_names, String[] file_date) {
		super(context, R.layout.individual_person_log, file_names);
		this.context = context;
		this.file_names = file_names;
		this.file_date=file_date;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.individual_person_log, parent, false);
		TextView textView_name = (TextView) rowView.findViewById(R.id.file_name);
		TextView textView_count= (TextView) rowView.findViewById(R.id.file_date);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo2);
		textView_name.setText(file_names[position]);
		textView_count.setText(file_date[position]);
 
		// Change icon based on name
		String s = file_names[position];
 
		System.out.println(s);
		imageView.setImageResource(R.drawable.default_picture);
 
		return rowView;
	}
}
