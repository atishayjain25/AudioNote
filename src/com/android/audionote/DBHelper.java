package com.android.audionote;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Owner : Mayank Agarwal (maagarwa)
 * */

public class DBHelper extends SQLiteOpenHelper {
	
	public static final String DB_NAME = "AudioNote"; //$NON-NLS-1$

	 public static final int DB_VERSION = 1;
	 
	 private static final String table_contactInfo 	= "ContactInfo";
	 private static final String contact_Id 		= "ContactId";
	 private static final String contact_name 		= "Name";
	 private static final String contact_phone 		= "PhoneNo";
	 
	 private static final String table_CallInfo 	= "CallInfo";
	 private static final String call_id 			= "Callid";
	 private static final String call_starttime 	= "StartTime";
	 private static final String call_endtime 		= "EndTime";
	 private static final String call_Ref_contact_id= "NameId";
	 
	 private static final String table_AudioInfo	= "AudioInfo";
	 private static final String Audio_id 			= "AudioName";
	 private static final String Audio_size			= "AudioSize";
	 private static final String Audio_starttime 	= "StartTime";
	 private static final String Audio_endtime 		= "EndTime";
	 private static final String Audio_Ref_call_id 	= "Callid";

	 private static final String CR_Table_ContactInfo = 
			 "Create table " + table_contactInfo +
			 "("+ contact_Id +" integer Primary Key autoincrement, " +
			 contact_name + " varchar(25) not null, " +
			 		contact_phone +" varchar(15) not null);";
	 
	 private static final String CR_Table_CallInfo =
			 "Create Table " + table_CallInfo +
			 "("+ call_id +" integer Primary Key autoincrement, " +
			 call_starttime +" datetime not null, " +
			 call_endtime +" datetime not null, " +
			 call_Ref_contact_id + " integer," +
			 "FOREIGN KEY (" + call_Ref_contact_id + ") REFERENCES "+ table_contactInfo +"(" +contact_Id +"));";
	 
	 private static final String CR_Table_AudioInfo =
			 "Create table " + table_AudioInfo +
			 "(" + Audio_id + " varchar(25) PRIMARY KEY, " +
			 Audio_size + " integer not null, " +
			 Audio_starttime + " datetime not null, " +
			 Audio_endtime + " datetime not null, " +
			 Audio_Ref_call_id + " integer, " +
			 "FOREIGN KEY (" + Audio_Ref_call_id + ") REFERENCES " + table_CallInfo +"(" + call_id +"));";
	 
	 
	 DBHelper(Context cxt)
	 {
		 super(cxt, DB_NAME, null, DB_VERSION);
	 }
	 
	 @Override
	 public void onCreate(SQLiteDatabase db)
	 {
		 db.execSQL(CR_Table_ContactInfo);
		 db.execSQL(CR_Table_CallInfo);
		 db.execSQL(CR_Table_AudioInfo);
	 }
	 
	 @Override
	 public void onUpgrade(SQLiteDatabase db, int OLD_VERSION, int NEW_VERSION)
	 {
		 //TODO:
	 }
	 
	 /**
	     * open the db
	     * @return this
	     * @throws SQLException
	     * return type: SQLiteDatabase
	     */
	    public SQLiteDatabase open() throws SQLException 
	    {
	        return this.getWritableDatabase();
	    }

	    /**
	     * close the db 
	     * return type: void
	     */
	    public void close() 
	    {
	        this.close();
	    }	    
}

