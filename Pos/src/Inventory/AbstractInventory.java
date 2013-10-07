package Inventory;

import java.util.ArrayList;

public abstract class AbstractInventory {
	ArrayList<Product> productList;
	public AbstractInventory() {
		
	}
	
	// update Inventory
	public void updateInventory(ArrayList<Product> productList)
	{
		this.productList = productList;
	}
	
	
	
	// get Object Product by Product code
	// if not have, return null
	public Product getProduct(String Product_Code)
	{
		for (int i=0; i<productList.size(); i++)
		{
			Product product = productList.get(i);
			if (product.getProduct_Code().equals(Product_Code)) return product;
		}
		return null;
	}
	
	// Syso all product at LogCat
	public void printList()
	{
		System.out.println("*** Print Product List Start ***");
        for (int i=0; i<productList.size(); i++)
        {
        	Product product = productList.get(i);
        	System.out.println(product.toString());
        }
		System.out.println("*** Print Product List End ***");
	}

}
