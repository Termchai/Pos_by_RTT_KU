package com.salerecord.pos;



import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.util.Log;
/**
 * DailyRecord is Database for record sale each day
 * 1 day it mean 1 Table
 * @author RTT
 *
 */
public class DailyRecord extends SQLiteOpenHelper {
	
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static String DATABASE_NAME;
    
    private static String TABLE_NAME;
    private Time time;
 

    /**
     * get database from time (upon day, month, year)
     * @param context
     * @param time for get database table same date of time
     */
	public DailyRecord(Context context, Time time) {
		super(context, ""+time.monthDay+"_"+(time.month)+"_"+time.year, null, DATABASE_VERSION);
		DATABASE_NAME = ""+time.monthDay+"_"+(time.month)+"_"+time.year;
		TABLE_NAME = "date"+DATABASE_NAME;
		this.time = time;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	    db.execSQL("CREATE TABLE " + TABLE_NAME + 
		          "(ID INTEGER PRIMARY KEY," +
		          " hour TEXT(100)," +
		          " min TEXT(100)," +
		          " basket TEXT(100));");
	   
	}
	public Time getTIme(){return time;}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * record sale to database
	 * call 1 time it mean 1 bill
	 * @param hour
	 * @param min
	 * @param basket list of product in sale
	 * @return
	 */
	public long InsertData(String hour, String min, String basket) {
		 try {
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data
			
	    	   ContentValues Val = new ContentValues();
	    	   Val.put("hour", hour); 
	    	   Val.put("min", min);
	    	   Val.put("basket", basket);
	   
			long rows = db.insert(TABLE_NAME, null, Val);

			db.close();
			return rows; // return rows inserted.
	          
		 } catch (Exception e) {
		    return -1;
		 }
	}

	
	public ArrayList<Record> SelectAllData() {
		// TODO Auto-generated method stub
		
		 try {
			 ArrayList<Record> MemberList = new ArrayList<Record>();
			 
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT  * FROM " + TABLE_NAME;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
			 	    if (cursor.moveToFirst()) {
			 	        do {
			 	        	Record cMember = new Record();
			 	        	
			 	        	String temp = cursor.getString(0);
			 	        	while(temp.length() < 3)
			 	        		temp = 0 + temp;
			 	        	String tempDay = time.monthDay+"";
			 	        	while (tempDay.length() < 2)
			 	        		tempDay = 0 + tempDay;
			 	        	String tempMonth = time.month+1+"";
			 	        	while(tempMonth.length() < 2)
			 	        		tempMonth = 0 + tempMonth;
			 	        	cMember.id = tempDay+tempMonth+time.year+temp;
			 	        	cMember.hour = cursor.getString(1);
			 	        	cMember.min = cursor.getString(2);
			 	        	cMember.basket = cursor.getString(3);
			 	        	cMember.day = time.monthDay+"";
			 	        	cMember.month = time.month+"";
			 	        	cMember.year = time.year+"";
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
	
	
	public double getTotal()
	{
		double sum = 0;
		ArrayList<Record> list = SelectAllData();
		for (int i=0; i<list.size(); i++)
		{
			Record r = list.get(i);
			sum += r.getTotal();
		}
		return sum;
	}

}