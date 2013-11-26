package Inventory;

import com.database.pos.Database;
import com.rtt_ku.pos.main_activity2;

public class Product {
	
	/*
	 * [0] ID
	 * [1] Product_Code
	 * [2] Name
	 * [3] Quantity
	 * [4] Price
	 * [5] Type
	 * [6] Date
	 * [7] Barcode
	 * [8] Picture
	 * [9] Last Edit
	 * [10] Status
	 * [11] Stage
	 */
	
	
		String product_code, name, type, date, barcode, picture, lastedit, status, stage;
		int quantity,cost,price;
		public Product(String productCode, String name, int quantity, int price, String type, String date,
				String barcode, String picture, String lastedit, String status, String stage,int cost)
		{
			this.product_code = productCode;
			this.name = name;
			this.quantity = quantity;
			this.price = price;
			this.type = type;
			this.date = date;
			this.barcode = barcode;
			this.picture = picture;
			this.lastedit = lastedit;
			this.status = status;
			this.stage = stage;
			this.cost = cost;
		}
		public String getProduct_Code() {return product_code;}
		public String getName() {return name;}
		public int getQuantity() {return quantity;}
		public int getPrice() {return price;}
		public int getCost() {return price;}
		public String getType() {return type;}
		public String getBarcode() {return barcode;}
		public String toString()
		{
			return product_code + " " + name + " " + quantity + " " + price + type + date + barcode + picture + lastedit + status +stage;
			
		}
	}


