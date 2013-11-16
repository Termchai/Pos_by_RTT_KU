package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.database.pos.Database;
import com.database.pos.DatabaseReader;
//import com.rtt_ku.pos.Tab_Inventory_Activity.MyAdapter;
//import com.rtt_ku.pos.Tab_Inventory_Activity.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;

import Inventory.Product;
import Sale.Basket;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Tab_Sale_Activity extends Activity{

	//	SQLiteDatabase db;
	DatabaseReader databaseReader;
	// list item
	ArrayList<Product> productList = new ArrayList<Product>();
    public static StoreController sCT;
	
	private ArrayAdapter<String> listAdapter;
	private ListView list_item;
	private ListView list_sale_item;
	private Basket basket;
	private SaleItemAdapter saleAdapter;
	private TextView total_text;
	private EditText cash;
	private Button ok_button;
	private Button reset_button;
	private SaleAdapter adapter;
	private Database myDb;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_tab);
		
        myDb = new Database(this);
        myDb.getWritableDatabase();
        sCT = new StoreController(myDb);
        productList = sCT.getProductList();
        basket = sCT.newBasket();
		
		// view matching
		list_item = (ListView)findViewById(R.id.listView2);
		list_sale_item = (ListView)findViewById(R.id.listView1);
		total_text = (TextView)findViewById(R.id.textView1);
		cash = (EditText)findViewById(R.id.set_quantity_editText);
		ok_button = (Button)findViewById(R.id.inventoryItem_add_button);
		reset_button =(Button)findViewById(R.id.inventoryItem_edit_button);
		
		// adapter of list item.
		adapter = new SaleAdapter(productList, this);
		saleAdapter = new SaleItemAdapter();
		
		list_item.setAdapter(adapter);
		list_sale_item.setAdapter(saleAdapter);
		 
		ok_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try
				{
				double cashh = Double.parseDouble(cash.getText().toString());
				double change = cashh-basket.getTotalPrice();
				Toast.makeText(Tab_Sale_Activity.this,change+"", Toast.LENGTH_SHORT).show();
				if(cashh >= basket.getTotalPrice()){
					


					
					final AlertDialog.Builder dialog_change = new AlertDialog.Builder(Tab_Sale_Activity.this);
				
					dialog_change.setTitle("Change");
					dialog_change.setMessage("Total price = " + basket.getTotalPrice() + "\nCash = " + cash.getText().toString() + "\nChange = " + change);
						dialog_change.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							sCT.confirmSale(basket);
							sCT.setDB(myDb);
							sCT.updateInventory();
							productList = sCT.getProductList();
							adapter.notifyDataSetChanged();
							
							basket = sCT.newBasket();
							saleAdapter.notifyDataSetChanged();
							total_text.setText("0.0");
							cash.setText("");

						}
					}).show();

				}
				
				else 
				{
					final AlertDialog.Builder dialog_not = new AlertDialog.Builder(Tab_Sale_Activity.this);
					
					dialog_not.setTitle("Warning!!!");
					dialog_not.setMessage("Not enough money");
						dialog_not.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
						}
					}).show();
				}
				
				
			}catch(Exception e){ 
				final AlertDialog.Builder dialog_Limit = new AlertDialog.Builder(Tab_Sale_Activity.this);
				
				dialog_Limit.setTitle("Warning!!!");
				dialog_Limit.setMessage("Please enter amount of cash");
					dialog_Limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
					}
				}).show();
			
			
			
		}
			}
		});
		
		reset_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				basket = sCT.newBasket();
				saleAdapter.notifyDataSetChanged();
				total_text.setText("0.0");
				cash.setText("");
			}
		});
		
		list_item.setOnItemClickListener(new OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position,
	                long id) {
	        	
	        	Product temp = sCT.getProduct(productList.get(position).getProduct_Code());
	        	if(basket.getMap().containsKey(temp))
	        	{
	        	if (basket.getMap().get(temp)+1 <= temp.getQuantity())
		        {
		        	basket.addProduct(temp, 1);	
//		        	Toast.makeText(Tab_Sale_Activity.this,temp.getProduct_Code(), Toast.LENGTH_SHORT).show();
	//	        	startActivity(new Intent(Tab_Sale_Activity.this,Tab_Sale_Activity.class));
		        	saleAdapter.notifyDataSetChanged();
		        	total_text.setText(basket.getTotalPrice()+"");
	        	
	        	}
	        	else
	        	{
	        		final AlertDialog.Builder dialog_Limit = new AlertDialog.Builder(Tab_Sale_Activity.this);
					
					dialog_Limit.setTitle("Warning!!!");
					dialog_Limit.setMessage("Not enough item");
						dialog_Limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
						}
					}).show();
	        	}
	        }
	        	else if (temp.getQuantity()!=0)
	        	{
	        		
		        	basket.addProduct(temp, 1);	
//		        	Toast.makeText(Tab_Sale_Activity.this,temp.getProduct_Code() + "fuck", Toast.LENGTH_SHORT).show();
	//	        	startActivity(new Intent(Tab_Sale_Activity.this,Tab_Sale_Activity.class));
		        	saleAdapter.notifyDataSetChanged();
		        	total_text.setText(basket.getTotalPrice()+"");
	        	}
	        	else 
	        	{
	        		final AlertDialog.Builder dialog_Limit = new AlertDialog.Builder(Tab_Sale_Activity.this);
					
					dialog_Limit.setTitle("Warning!!!");
					dialog_Limit.setMessage("Not enough item");
						dialog_Limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
						}
					}).show();
	        	}
	        }
	    });
	}
	
	public void update(){
		list_sale_item.setAdapter(new SaleItemAdapter());
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.pos__ui, menu);
	        return true;
	    }

//	    // inner class to adapter holder with listview.
//	    class MyAdapter extends BaseAdapter{
//			private Holder holder;
//
//			
//			@Override
//			public int getCount() {
//				// TODO Auto-generated method stub
//				return productList.size();
//			}
//
//			@Override
//			public Object getItem(int arg0) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public long getItemId(int arg0) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//			
//			public String getName(){
//				return holder.title.toString();
//			}
//			
//			public String getPrice(){
//				return holder.price.toString();
//			}
//			
//			public String getQuantity(){
//				return holder.quantity.toString();
//			}
//			
//			@Override
//			public View getView(int position, View view, ViewGroup parent) {
//				// TODO Auto-generated method stub
//				if(view == null){
//					view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout, null);
//					holder = new Holder();
//					
//					holder.title = (TextView) view.findViewById(R.id.text_item);
//					holder.quantity = (TextView) view.findViewById(R.id.text_quantity); 
//					holder.price = (TextView) view.findViewById(R.id.text_price);
//					view.setTag(holder);
//				}
//				else {
//					holder = (Holder) view.getTag();
//				}
//				
//				Product p = productList.get(position);
//				String product_name = p.getName();
//				String product_code = p.getProduct_Code();
//				int product_quantity = p.getQuantity();
//				int product_price = p.getPrice();
//				holder.title.setText(productList.get(position).getName() + " <" + product_code + "> ");
//				holder.quantity.setText(product_quantity +  " item(s)");
//				holder.price.setText(product_price+"");
//				return view;
//			}
//			
//			// hold text view in each list of item. 
//			class Holder{
//				
//				public TextView title;
//				public TextView quantity;
//				public TextView price;
//			}
//		}
	    
	    class SaleItemAdapter extends BaseAdapter{
			private Holder holder;

			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return basket.getList().size();
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View view, ViewGroup parent) {
				// TODO Auto-generated method stub
				if(view == null){
					view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.large_item_layout, null);
					holder = new Holder();
					
					holder.title = (TextView) view.findViewById(R.id.large_text_item);
					holder.quantity = (TextView) view.findViewById(R.id.large_text_quantity); 
					holder.price = (TextView) view.findViewById(R.id.large_text_price);
					view.setTag(holder);
				}
				else {
					holder = (Holder) view.getTag();
				}
				
				//
				ArrayList<Product> list = basket.getList();
				
				
				Product p = list.get(position);
				String product_name = p.getName();
				String product_code = p.getProduct_Code();
				int product_quantity = basket.getMap().get(p);
				int product_price = p.getPrice();
				holder.title.setText(product_name + " <" + product_code + "> ");
				holder.quantity.setText(product_quantity +  " item(s)");
				holder.price.setText(product_price+"");
				return view;
			}
			
			// hold text view in each list of item. 
			class Holder{
				
				public TextView title;
				public TextView quantity;
				public TextView price;
			}
		}
	    
	    public View getView () {
	        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout, null);
	        return view;
		}
}

