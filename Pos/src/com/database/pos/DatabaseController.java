package com.database.pos;

import java.util.ArrayList;

import Inventory.Product;

public class DatabaseController {
	Database db;
	DatabaseReader dbR;
	public DatabaseController(Database db) {
		this.db=db;
		dbR = new DatabaseReader(db);
	}
	
	public ArrayList<Product> getAllProduct()
	{
		return dbR.getData();
	}
	
	public boolean isHasYet(String Product_Code)
	{
		if (db.SelectData(Product_Code) == null) return false;
		return true;
	}
	
	public void insertProduct(String Product_Code, String Name, int Quantity, int price)
	{
		db.InsertData(Product_Code, Name, Quantity, price);
	}
	
	public void removeProduct(String Product_Code)
	{
		db.DeleteData(Product_Code);
	}
	
	public boolean setQuantityProduct(String Product_Code, int Quantity)
	{
		if (isHasYet(Product_Code))
		{
			db.UpdateQuantity(Product_Code, Quantity);
			return true;
		}
		return false;
	}
	
	/*
	 * return 
	 * >= 0 is new quantity
	 * -1 is new quantity < 0
	 * -2 is product code not found
	 */
	public int addQuantity(String Product_Code, int diff)
	{
		if (isHasYet(Product_Code))
		{
			int quantity = (Integer.parseInt(db.SelectData(Product_Code)[3]));
			if (quantity+diff >= 0) 
			{
				db.UpdateQuantity(Product_Code, quantity+diff);
				return (Integer.parseInt(db.SelectData(Product_Code)[3]));
			}
			
			return -1;
		}
		return -2;
	}
	
	
	
}
