package com.database.pos;

import java.util.ArrayList;
import java.util.List;


import Inventory.Product;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * read database(inventory) and create object product
 * @author Termchai
 *
 */
public class DatabaseReader {
	SQLiteDatabase db;
	Database myDb;
	public DatabaseReader(Database dbTemp)
	{
		this.myDb = dbTemp;
	}
	/**
	 * 
	 * @return arraylist of object product
	 */
	public ArrayList<Product> getData()
	{
		ArrayList<Product> list;
		db = myDb.getReadableDatabase();
		list = myDb.SelectAllData();
		return list;
	}
	
}
