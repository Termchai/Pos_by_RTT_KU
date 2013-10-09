package com.rtt_store.pos;

import java.util.ArrayList;

import Inventory.AbstractInventory;
import Inventory.InventoryController;
import Inventory.Product;

import com.database.pos.Database;
import com.database.pos.DatabaseController;
/**
 * have all Controller (now have inventory and database)
 * have relation with GUI and can manage everything in store
 * @author Termchai
 */
public class StoreController {
	DatabaseController dbCT;
	InventoryController inCT;
	public StoreController(Database db) {
		dbCT = new DatabaseController(db);
		inCT = new InventoryController();
		updateInventory();
		inCT.printList();
		inCT.printList();
		
		
	}
	
	// add new Product to Database and InventoryAll
	/*
	 * return -1 is product has already
	 * 		  1 is add product suscess
	 */
	/**
	 * add new product in database and update inventory
	 * @param Product_Code
	 * @param Name
	 * @param Quantity
	 * @param Price
	 * @return 1 if success, -1 if this product code has already
	 */
	public int addProduct(String Product_Code, String Name, int Quantity, int Price)
	{
		if (dbCT.isHasYet(Product_Code)) return -1;
			dbCT.insertProduct(Product_Code, Name, Quantity, Price);
			updateInventory();
			return 1;
	}
	
	/**
	 * remove product by product code
	 * @param Product_Code
	 * @return true if remove complete, false if can't find that product
	 */
	public boolean removeProduct(String Product_Code)
	{
		if (dbCT.isHasYet(Product_Code))
		{
			dbCT.removeProduct(Product_Code);
			updateInventory();
			System.out.println("remove complete");
			return true;
		}
		System.out.println("don't has product : " + Product_Code);
		return false;
	}
	

	/**
	 * update inventory
	 */
	public void updateInventory()
	{
		inCT.updateInventory(dbCT.getAllProduct());
	}
	
	/**
	 * get all product 
	 * @return arraylist <product>
	 */
	public ArrayList<Product> getProductList()
	{
		return inCT.getProductList();
	}
	
	/**
	 * set amount quantity of product
	 * @param Product_Code
	 * @param Quantity
	 * @return true if success, false if can't find product
	 */
	public boolean setQuantity(String Product_Code, int Quantity)
	{
		if(dbCT.setQuantityProduct(Product_Code, Quantity)) 
		{
			System.out.println("Product Code : " + Product_Code + " has " + Quantity + " ea");
			updateInventory();
			return true;
		}
		System.out.println("Product Code : " + Product_Code + " don't has yet");
		return false;
	}

/**
 * add different quantity of product
 * @param Product_Code
 * @param diff different of product that will be add/remove
 */
	public void addQuantity(String Product_Code, int diff)
	{
		int newQuantity = dbCT.addQuantity(Product_Code, diff);
		if (newQuantity >= 0) {
			updateInventory();
			System.out.println(Product_Code + " Quantity is " + newQuantity);
		}
		else if (newQuantity == -1)
			System.out.println("new Quantity < 0");
		else if (newQuantity == -2)
			System.out.println(Product_Code + " not found");
	}
}
