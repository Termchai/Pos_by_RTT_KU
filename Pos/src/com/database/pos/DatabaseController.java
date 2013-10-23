package com.database.pos;

import java.util.ArrayList;

import Inventory.Product;
/**
 * control database
 * @author Termchai
 *
 */
public class DatabaseController {
	Database db;
	DatabaseReader dbR;
	public DatabaseController(Database db) {
		this.db=db;
		dbR = new DatabaseReader(db);
	}
	
	/**
	 * get all product in database 
	 * @return Product class with full information
	 */
	public ArrayList<Product> getAllProduct()
	{
		return dbR.getData();
	}
	
	/**
	 * check product is has already yet?
	 * @param Product_Code 
	 * @return true if has
	 */
	public boolean isHasYet(String Product_Code)
	{
		if (db.SelectData(Product_Code) == null) return false;
		return true;
	}
	
	/**
	 * add new product in database
	 * @param Product_Code
	 * @param Name
	 * @param Quantity
	 * @param price
	 */
	public void insertProduct(String productCode, String name, int quantity, int price, String type, String date,
			String barcode, String picture, String lastedit, String status, String stage)
	{
		db.InsertData(productCode, name, quantity, price,type,date,barcode,picture,lastedit,status,stage);
	}
	
	/**
	 * remove product in database
	 * @param Product_Code
	 * @return -1 if has that product code already
	 */
	public long removeProduct(String Product_Code)
	{
		return db.DeleteData(Product_Code);
	}
	
	/**
	 * set amount quantity of product
	 * @param Product_Code
	 * @param Quantity
	 * @return true if success, false if can't find that product
	 */
	public boolean setQuantityProduct(String Product_Code, int Quantity)
	{
		if (isHasYet(Product_Code))
		{
			db.UpdateQuantity(Product_Code, Quantity);
			return true;
		}
		return false;
	}
	
	/**
	 * add quantity of that product (can't minus so)
	 * @param Product_Code
	 * @param diff different of product that will be add/remove
	 * @return
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
