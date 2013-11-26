package com.inventory_record.pos;


import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.util.Log;

public class InventoryRecord extends SQLiteOpenHelper {
	
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "inventory_record";
 
    // Table Name
    private static final String TABLE_MEMBER = "In_Re";

	public InventoryRecord(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	    db.execSQL("CREATE TABLE " + TABLE_MEMBER + 
		          "(MemberID INTEGER PRIMARY KEY," +
		          " Day TEXT(100)," +
		          " Month TEXT(100)," +
		          " Year TEXT(100)," +
		          " Hour TEXT(100)," +
		          " Min TEXT(100)," +
		          " Product_Code TEXT(100)," +
		          " Cost INTEGER," +
		          " Quantity INTEGER);");
	   
	    Log.d("CREATE TABLE","Create Table Successfully.");
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	// Insert Data
	public long InsertData(String product_code, int quantity, int cost ) {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		 try {
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data

			
	    	   ContentValues Val = new ContentValues();
	    	   Val.put("Day", time.monthDay); 
	    	   Val.put("Month", time.month); 
	    	   Val.put("Year", time.year); 
	    	   Val.put("Hour", time.hour); 
	    	   Val.put("Min", time.minute); 

	    	   Val.put("Product_Code", product_code);
	    	   Val.put("Cost", cost);
	    	   Val.put("Quantity", quantity);
	   
			long rows = db.insert(TABLE_MEMBER, null, Val);

			db.close();
			return rows; // return rows inserted.
	          
		 } catch (Exception e) {
		    return -1;
		 }

	}

}