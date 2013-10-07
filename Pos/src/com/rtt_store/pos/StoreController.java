package com.rtt_store.pos;

import Inventory.AbstractInventory;
import Inventory.InventoryController;
import Inventory.Product;

import com.database.pos.Database;
import com.database.pos.DatabaseController;

public class StoreController {
	DatabaseController dbCT;
	InventoryController inCT;
	public StoreController(Database db) {
		dbCT = new DatabaseController(db);
		inCT = new InventoryController();
		updateInventory();
		inCT.printList();
		addQuantity("C008", 13);
		inCT.printList();
		
		
	}
	
	// add new Product to Database and InventoryAll
	public void addProduct(String Product_Code, String Name, int Quantity)
	{
		if (dbCT.isHasYet(Product_Code)) System.out.println("Product number " + Product_Code + " has already");
		else
		{
			dbCT.insertProduct(Product_Code, Name, Quantity);
			updateInventory();
		}
	}
	
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
	
	// update inventory
	public void updateInventory()
	{
		inCT.updateInventory(dbCT.getAllProduct());
	}
	
	// set product quantity by Product_Code
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
	// plus/minus product quantity by Product_Code and diff quantity
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
