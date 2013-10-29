package Sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Inventory.Product;

public class Basket {
	HashMap<Product,Integer> map;
	ArrayList<Product> list;
	
	public Basket() {
		map = new HashMap<Product,Integer>();
		list = new ArrayList<Product>();
//		list.add(new Product("123", "test1", 4, 1, "", "", "", "", "", "", ""));
//		list.add(new Product("1234", "test2", 2, 10, "", "", "", "", "", "", ""));
	}
	
	public void addProduct(Product product, int quantity)
	{
		if (map.containsKey(product))
		{
			int quan = map.get(product) + quantity;
			map.remove(product);
			map.put(product,quan);
			
		}
		else
		{
			map.put(product, quantity);
			list.add(product);
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
	public HashMap<Product,Integer> getMap(){return map;}
	public ArrayList<Product> getList(){return list;}
}
