package Sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Inventory.Product;
/**
 * Sale for record product in basket(before sale), quantity, price(can be overide from database)
 * @author RTT
 *
 */
public class Sale {
	HashMap<Product,Integer> map_Quan,map_Price;
	ArrayList<Product> list;
	
	public Sale() {
		map_Quan = new HashMap<Product,Integer>();
		list = new ArrayList<Product>();
		map_Price = new HashMap<Product, Integer>();
//		list.add(new Product("123", "test1", 4, 1, "", "", "", "", "", "", ""));
//		list.add(new Product("1234", "test2", 2, 10, "", "", "", "", "", "", ""));
	}
	/**
	 * add product to basket with original price(from database)
	 * @param product
	 * @param quantity
	 */
	public void addProduct(Product product, int quantity)
	{
		if (map_Quan.containsKey(product))
		{
			int quan = map_Quan.get(product) + quantity;
			map_Quan.remove(product);
			map_Quan.put(product,quan);
			map_Price.put(product, product.getPrice());
		}
		else
		{
			map_Quan.put(product, quantity);
			map_Price.put(product, product.getPrice());
			list.add(product);
		}
	}
	/**
	 * overide price
	 * @param product
	 * @param price
	 */
	public void setPrice (Product product, int price)
	{
		map_Price.put(product, price);
	}
	/**
	 * get total price of sale
	 * @return
	 */
	public double getTotalPrice()
	{
		double total = 0;
		for (int i=0; i<list.size(); i++)
		{
			Product p = list.get(i);
			total += (map_Price.get(p) * map_Quan.get(p)); 
		}
		return total;
	}
	public HashMap<Product,Integer> getMapQuan(){return map_Quan;}
	public ArrayList<Product> getList(){return list;}
	public HashMap<Product,Integer> getMapPrice(){return map_Price;}
}
