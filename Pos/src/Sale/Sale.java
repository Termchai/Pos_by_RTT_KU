package Sale;

import Inventory.Product;

public class Sale {
	private Basket basket;
	private double total;
	public Sale() {
		basket = new Basket();
	}
	
	public void addProduct(Product product, int quantity)
	{
		basket.addProduct(product, quantity);
		total = basket.getTotalPrice();
	}
	
	public double getTotal()
	{
		return total;
	}
	
	public Basket getBasket()
	{
		return basket;
	}
}
