package com.database.pos;

import java.util.ArrayList;
import java.util.List;


import Inventory.Product;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "Product";
	private static final String PRODUCT_CODE = "Product_Code";

	public Database(Context context) {
		super(context, "mydatabase", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + DATABASE_NAME +

		"(ID INTEGER PRIMARY KEY," +

		" Product_Code TEXT(100)," +

		" Name TEXT(100)," +

		" Quantity Integer);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public long InsertData(String productCode, String name, int quantity) {
		try {
			SQLiteDatabase db;
			db = this.getWritableDatabase();

			ContentValues Val = new ContentValues();
			Val.put("Product_Code", productCode);
			Val.put("Name", name);
			Val.put("Quantity", quantity);

			long rows = db.insert(DATABASE_NAME, null, Val);

			db.close();

			return rows; // return rows inserted.

		} catch (Exception e) {
			return -1;
		}
	}

	public String[] SelectData(String Product_Code) {

		try {

			String arrData[] = null;

			SQLiteDatabase db;

			db = this.getReadableDatabase(); // Read Data

			Cursor cursor = db.query(DATABASE_NAME, new String[] { "*" },
					"Product_Code=?",
					new String[] { String.valueOf(Product_Code) }, null, null,
					null, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					arrData = new String[cursor.getColumnCount()];

					/*
					 * [0] ID
					 * [1] Product_Code
					 * [2] Name
					 * [3] Quantity
					 */
					arrData[0] = cursor.getString(0);
					arrData[1] = cursor.getString(1);
					arrData[2] = cursor.getString(2);
					arrData[3] = cursor.getString(3);
				}
			}
			cursor.close();
			db.close();
			return arrData;

		} catch (Exception e) {
			return null;
		}

	}
	



	public ArrayList<Product> SelectAllData() {
		// TODO Auto-generated method stub
		
		 try {
			 ArrayList<Product> MemberList = new ArrayList<Product>();
			 
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT  * FROM " + DATABASE_NAME;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
			 	    if (cursor.moveToFirst()) {
			 	        do {
			 	        	String product_code = cursor.getString(1);
			 	        	String name = cursor.getString(2);
			 	        	int quantity = Integer.parseInt(cursor.getString(3));
			 	        	Product cMember = new Product(product_code, name, quantity);
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
	
	// Update Data
	public long UpdateQuantity(String Product_Code,int Quantity) {
		// TODO Auto-generated method stub
		
		 try {
			
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data

	           ContentValues Val = new ContentValues();
	           Val.put("Quantity", Quantity);
	    
	           long rows = db.update(DATABASE_NAME, Val, " Product_Code = ?",
	                   new String[] { String.valueOf(Product_Code) });

			db.close();
			return rows; // return rows updated.

		 } catch (Exception e) {
		    return -1;
		 }

	}
	
	// Delete Data
	public long DeleteData(String Product_Code) {
		// TODO Auto-generated method stub
		
		 try {
			
			SQLiteDatabase db;
	    		db = this.getWritableDatabase(); // Write Data

				
			long rows =  db.delete(DATABASE_NAME, "Product_Code = ?",
		            new String[] { String.valueOf(Product_Code) });

			db.close();
			return rows; // return rows delete.

		 } catch (Exception e) {
		    return -1;
		 }

	}

	public String getDatabaseName() {
		return DATABASE_NAME;
	}
}
