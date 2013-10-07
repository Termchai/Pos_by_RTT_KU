package Inventory;

import com.database.pos.Database;
import com.rtt_ku.pos.main_activity;

public class Product {
		String product_code, name;
		int quantity;
		public Product(String Product_Code, String Name, int Quantity)
		{
			this.product_code = Product_Code;
			this.name = Name;
			this.quantity = Quantity;
		}
		public String getProduct_Code() {return product_code;}
		public String getName() {return name;}
		public int getQuantity() {return quantity;}
		public String toString()
		{
			return product_code + " " + name + " " + quantity;
			
		}
	}


