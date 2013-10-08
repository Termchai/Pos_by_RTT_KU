package Inventory;

import com.database.pos.Database;
import com.rtt_ku.pos.main_activity;

public class Product {
		String product_code, name;
		int quantity;
		int price;
		public Product(String Product_Code, String Name, int Quantity, int Price)
		{
			this.product_code = Product_Code;
			this.name = Name;
			this.quantity = Quantity;
			this.price = Price;
		}
		public String getProduct_Code() {return product_code;}
		public String getName() {return name;}
		public int getQuantity() {return quantity;}
		public int getPrice() {return price;}
		public String toString()
		{
			return product_code + " " + name + " " + quantity + " " + price;
			
		}
	}


