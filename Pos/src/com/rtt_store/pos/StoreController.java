package com.rtt_store.pos;

import java.util.ArrayList;
import java.util.Iterator;

import Inventory.AbstractInventory;
import Inventory.InventoryController;
import Inventory.Product;
import Sale.Sale;
import Sale.SaleFactory;

import android.text.format.Time;

import com.database.pos.InventoryDatabase;
import com.database.pos.InventoryDatabaseController;
import com.salerecord.pos.DailyRecord;
import com.salerecord.pos.SaleRecordDateDatabase;
import com.salerecord.pos.SaleRecordController;
import com.salerecord.pos.Wan;
/**
 * have all Controller 
 * have relation with GUI and can manage everything in store
 * @author Termchai
 */
public class StoreController {
	InventoryDatabaseController dbCT;
	InventoryController inCT;
	SaleFactory saleCT;
	SaleRecordController srCT;
	public StoreController(InventoryDatabase db) {
		dbCT = new InventoryDatabaseController(db);
		inCT = new InventoryController();
		saleCT = new SaleFactory();
		updateInventory();
//		inCT.printList();
		
		
	}
	
	public StoreController(InventoryDatabase db, SaleRecordDateDatabase dbSr) {
		dbCT = new InventoryDatabaseController(db);
		srCT = new SaleRecordController(dbSr);
		inCT = new InventoryController();
		saleCT = new SaleFactory();
		updateInventory();
	}
	
	
	public void setDB(InventoryDatabase db)
	{
		this.dbCT = new InventoryDatabaseController(db);
	}
	
	
	public Sale newBasket()
	{
		return saleCT.createBasket();
	}

	
	/**
	 * add new product in database and update inventory
	 * @param Product_Code
	 * @param Name
	 * @param Quantity
	 * @param Price
	 * @return 1 if success, -1 if this product code has already
	 */
	public int addProduct(String productCode, String name, int quantity, int price, String type, String date,
			String barcode, String picture, String lastedit, String status, String stage,int cost)
	{
		if (dbCT.isHasYet(productCode)) return -1;
			dbCT.insertProduct(productCode, name, quantity, price,type,date,barcode,picture,lastedit,status,stage,cost);
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
	 * get all product which have name or product_code contain keyword
	 * @param keyword
	 * @return
	 */
	public ArrayList<Product> getProductListByPartial(String keyword)
	{
		ArrayList<Product>list = getProductList();
		ArrayList<Product>ans = new ArrayList<Product>();
		Iterator<Product> it = list.iterator();
		keyword = keyword.toLowerCase();
		while(it.hasNext())
		{
			Product p = it.next();
			if (p.getName().toLowerCase().contains(keyword) || p.getProduct_Code().toLowerCase().contains(keyword))
			{
				ans.add(p);
			}
		}
		return ans;
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
//		if (newQuantity >= 0) {
			updateInventory();
			System.out.println(Product_Code + " Quantity is " + newQuantity);
//		}
//		else if (newQuantity == -1)
//			System.out.println("new Quantity < 0");
//		else if (newQuantity == -2)
//			System.out.println(Product_Code + " not found");
	}
	/**
	 * check this product_code has already in InventoryDatabase?
	 * @param product_code
	 * @return
	 */
	public boolean isHasYet(String product_code)
	{
		return dbCT.isHasYet(product_code);
	}
	/**
	 * get object Product by product_code
	 * @param product_code
	 * @return
	 */
	public Product getProduct(String product_code)
	{
		return inCT.getProduct(product_code);
	}
	
	/**
	 * make sale complete
	 * @param basket
	 * @param dr
	 */
	public void confirmSale(Sale basket,DailyRecord dr)
	{
		dbCT.confirmSale(basket);
		srCT.insertDay(dr.getTIme());
		srCT.confirmSale(basket,dr);

	}
	
	/**
	 * get day which have at leaste 1 sale
	 * @return
	 */
	public ArrayList<Wan> getWans()
	{
		return srCT.getListSaleRecord();
	}
	
	/**
	 * update description of product
	 * @param Product_Code which product should be update
	 * @param name
	 * @param type
	 * @param price
	 * @param barcode
	 */
	public void setDescription(String Product_Code,String name, String type, int price, String barcode)
	{
		dbCT.setDescription(Product_Code, name, type, price, barcode);
	}
	
}
