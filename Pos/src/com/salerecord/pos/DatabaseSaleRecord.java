package com.salerecord.pos;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseSaleRecord extends SQLiteOpenHelper {
	
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "history";
 
    // Table Name
    private static final String TABLE_SALE = "sales";

	public DatabaseSaleRecord(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	    db.execSQL("CREATE TABLE " + TABLE_SALE + 
		          "(ID INTEGER PRIMARY KEY," +
		          " day TEXT(100)," +
		          " month TEXT(100)," +
		          " year TEXT(100));");
	   
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	// Insert Data
	public long InsertData(String day, String month, String year) {
		 try {
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data
			
	    	   ContentValues Val = new ContentValues();
	    	   Val.put("day", day); 
	    	   Val.put("month", month);
	    	   Val.put("year", year);
	   
			long rows = db.insert(TABLE_SALE, null, Val);

			db.close();
			return rows; // return rows inserted.
	          
		 } catch (Exception e) {
		    return -1;
		 }
	}

	
	public ArrayList<Wan> SelectAllData() {
		// TODO Auto-generated method stub
		
		 try {
			 ArrayList<Wan> MemberList = new ArrayList<Wan>();
			 
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT  * FROM " + TABLE_SALE;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
			 	    if (cursor.moveToFirst()) {
			 	        do {
			 	        	Wan cMember = new Wan();
			 	        	cMember.setDay(cursor.getShort(1));
			 	        	cMember.setMonth(cursor.getShort(2));
			 	        	cMember.setYear(cursor.getShort(3));
			 	        	MemberList.add(cMember);
			 	        } while (cursor.moveToNext());
			 	    }
			 	}
			 	cursor.close();
				db.close();
				return MemberList;
				
		 } catch (Exception e) {
		    return null;
		 }

	}
}