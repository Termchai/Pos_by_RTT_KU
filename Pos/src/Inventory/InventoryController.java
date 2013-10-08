package Inventory;

import java.util.ArrayList;

public class InventoryController {
	InventoryAll inAll;
	public InventoryController() {
		inAll = new InventoryAll();
	}
	public void updateInventory(ArrayList<Product> productList)
	{
		inAll.updateInventory(productList);
	}
	public void printList()
	{
		inAll.printList();
	}
	public Product getProduct (String Product_Code)
	{
		return inAll.getProduct(Product_Code);
	}
	public ArrayList<Product> getProductList() {
		
		return inAll.getProductList();
	}
}
