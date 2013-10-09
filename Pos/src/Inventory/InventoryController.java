package Inventory;

import java.util.ArrayList;
/**
 * control inventory
 * @author Termchai
 *
 */
public class InventoryController {
	/**
	 * inventory of all item
	 */
	InventoryAll inAll;

	
	public InventoryController() {
		inAll = new InventoryAll();
	}
	/**
	 * update all product in database to inventory
	 * @param productList
	 */
	public void updateInventory(ArrayList<Product> productList)
	{
		inAll.updateInventory(productList);
	}
	/**
	 * printline to Logcat all product (For test by dev)
	 */
	public void printList()
	{
		inAll.printList();
	}
	/**
	 * get product object by product_code
	 * @param Product_Code
	 * @return object product
	 */
	public Product getProduct (String Product_Code)
	{
		return inAll.getProduct(Product_Code);
	}
	/**
	 * get all product
	 * @return arraylist<product>
	 */
	public ArrayList<Product> getProductList() {
		
		return inAll.getProductList();
	}
}
