package Sale;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Inventory.Product;

public class Basket {
	Map<Product,Integer> map;
	
	public Basket() {
		map = new HashMap<Product,Integer>();
	}
	
	public void addProduct(Product product, int quantity)
	{
		if (map.containsKey(product))
		{
			map.put(product, map.get(product) + quantity);
		}
		else
		{
			map.put(product, quantity);
		}
	}
	
	public double getTotalPrice()
	{
		Set<Product> set = map.keySet();
		Iterator<Product> it = set.iterator();
		double total = 0;
		while(it.hasNext())
		{
			Product product = it.next();
			total += map.get(product) + product.getPrice();
		}
		return total;
	}
}
