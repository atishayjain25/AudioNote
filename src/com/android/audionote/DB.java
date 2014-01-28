package com.android.audionote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * Owner : Mayank Agarwal (maagarwa)
 * */

public class DB {
	
	public static final String DB_NAME = "AudioNote"; //$NON-NLS-1$

	 public static final int DB_VERSION = 1;
	 
	 private static final String table_contactInfo 	= "ContactInfo";
	 private static final String contact_Id 		= "ContactId";
	 private static final String contact_name 		= "Name";
	 private static final String contact_phone 		= "PhoneNo";
	 private static final String contact_NoOfAudios = "NoOfAudios";
	 
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
			 "("+ contact_Id +" integer Primary Key , " +
			 contact_name + " varchar(25), " +
			 contact_phone +" varchar(15) not null," +
			 		contact_NoOfAudios +" integer);";
	 
	 private static final String CR_Table_CallInfo =
			 "Create Table " + table_CallInfo +
			 "("+ call_id +" integer Primary Key autoincrement, " +
			 call_starttime +" varchar(25), " +
			 call_endtime +" varchar(25), " +
			 call_Ref_contact_id + " integer," +
			 "FOREIGN KEY (" + call_Ref_contact_id + ") REFERENCES "+ table_contactInfo +"(" +contact_Id +"));";
	 
	 private static final String CR_Table_AudioInfo =
			 "Create table " + table_AudioInfo +
			 "(" + Audio_id + " varchar(25) PRIMARY KEY, " +
			 Audio_size + " varchar(15) not null, " +
			 Audio_starttime + " varchar(25) not null, " +
			 Audio_endtime + " varchar(25) not null, " +
			 Audio_Ref_call_id + " integer, " +
			 "FOREIGN KEY (" + Audio_Ref_call_id + ") REFERENCES " + table_CallInfo +"(" + call_id +"));";
	 
	 
	 private class DBHelper extends SQLiteOpenHelper{
		 DBHelper(Context cxt)
		 {
			 super(cxt, DB_NAME, null, DB_VERSION);
			 Log.d ("test", "hit");
		 }
		 
		 @Override
		 public void onCreate(SQLiteDatabase db)
		 {
			 Log.d("test", CR_Table_ContactInfo);
			 Log.d("test", CR_Table_CallInfo);
			 Log.d("test", CR_Table_AudioInfo);

			 db.execSQL(CR_Table_ContactInfo);
			 db.execSQL(CR_Table_CallInfo);
			 db.execSQL(CR_Table_AudioInfo);
		 }
		 
		 @Override
		 public void onUpgrade(SQLiteDatabase db, int OLD_VERSION, int NEW_VERSION)
		 {
			 //TODO:
		 }
	 }
	 private DBHelper dbHelper;
	 public DB (Context context){
		 dbHelper = new DBHelper(context);
	 }
	 
	 /**
	     * open the db
	     * @return this
	     * @throws SQLException
	     * return type: SQLiteDatabase
	     */
	    public SQLiteDatabase open() throws SQLException 
	    {
	        return dbHelper.getWritableDatabase();
	    }

	    /**
	     * close the db 
	     * return type: void
	     */
	    public void close() 
	    {
	    	dbHelper.close();
	    }
	    
	    public int insertAudioSnippet(String[] contact, int callId, ContentValues audioSnippet)
	    {
	    	SQLiteDatabase db = open();
	    	// contact array contains three string i.e., contactId, number,name
	    	Log.d("test", "Call Id = " +callId);
	    	if (callId == 0)
	    	{
	    		// check weather contact already exist else insert
	    		if (contact[0] == null)
	    		{
	    			contact[0] = contact[1];
	    		}
	    		int contactId = Integer.parseInt(contact[0]);
	    		String findContact = "SELECT * FROM " + table_contactInfo + " where " + contact_Id +" = '"+contact[0]+"'";
	    		Log.d("test", findContact);
	    		Cursor c = db.rawQuery(findContact, null);
	            if (c == null ) {
	                // insert the contact in ContactInfo
	            	String insertContact = "Insert into " + table_contactInfo + "("+contactId+",'"+contact[2]+"','"+contact[1]+"',1);";
	            	Log.d("test",insertContact);
	            	db.rawQuery(insertContact, null);
	            }
	            else
	            {
	            	//TODO Update the NoOf Audios to +1
	            	String updateNoOfAudios = "Update " + table_contactInfo + " SET " + contact_NoOfAudios + " = " + 
	            									contact_NoOfAudios + " + 1 Where " + contact_Id + " = "+contactId;
	            	db.rawQuery(updateNoOfAudios, null);
	            }
	    		// insert the call and return the callId
	            ContentValues values = new ContentValues();
	            values.put(call_Ref_contact_id, contactId);
	            callId = (int) db.insert(table_CallInfo, null, values);
	    	}
	    	
	    	audioSnippet.put(Audio_Ref_call_id, callId);
	    	db.insert(table_AudioInfo, null, audioSnippet);
	    	close();
	    	
	    	return callId;
	    }
}

