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
		double total = 0;
		for (int i=0; i<list.size(); i++)
		{
			Product p = list.get(i);
			total += (p.getPrice() * map.get(p)); 
		}
		return total;
	}
	public HashMap<Product,Integer> getMap(){return map;}
	public ArrayList<Product> getList(){return list;}
}
