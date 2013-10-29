package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.database.pos.Database;
import com.database.pos.DatabaseReader;
import com.rtt_ku.pos.Tab_Inventory_Activity.MyAdapter;
import com.rtt_ku.pos.Tab_Inventory_Activity.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;

import Inventory.Product;
import Sale.Basket;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
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

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_tab);
		
        Database myDb = new Database(this);
        myDb.getWritableDatabase();
        sCT = new StoreController(myDb);
        productList = sCT.getProductList();
        basket = new Basket();
		
		// view matching
		list_item = (ListView)findViewById(R.id.listView2);
		list_sale_item = (ListView)findViewById(R.id.listView1);
		total_text = (TextView)findViewById(R.id.textView1);
		
		// adapter of list item.
		MyAdapter adapter = new MyAdapter();
		saleAdapter = new SaleItemAdapter();
		
		list_item.setAdapter(adapter);
		list_sale_item.setAdapter(saleAdapter);
		 
		list_item.setOnItemClickListener(new OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position,
	                long id) {
	            // TODO Auto-generated method stub
//	        	Object o = list_item.getItemAtPosition(position);
//	        	 MyAdapter str = (MyAdapter)o;
//	        	 
//	        	Toast.makeText(Tab_Sale_Activity.this,productList.get(position).getName(), Toast.LENGTH_SHORT).show();
	        	
	        	Product temp = sCT.getProduct(productList.get(position).getProduct_Code());
	        	basket.addProduct(temp, 1);
	        	Toast.makeText(Tab_Sale_Activity.this,temp.getProduct_Code() + "fuck", Toast.LENGTH_SHORT).show();
//	        	startActivity(new Intent(Tab_Sale_Activity.this,Tab_Sale_Activity.class));
	        	saleAdapter.notifyDataSetChanged();
	        	total_text.setText(basket.getTotalPrice()+"");
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

	    // inner class to adapter holder with listview.
	    class MyAdapter extends BaseAdapter{
			private Holder holder;

			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return productList.size();
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
			
			public String getName(){
				return holder.title.toString();
			}
			
			public String getPrice(){
				return holder.price.toString();
			}
			
			public String getQuantity(){
				return holder.quantity.toString();
			}
			
			@Override
			public View getView(int position, View view, ViewGroup parent) {
				// TODO Auto-generated method stub
				if(view == null){
					view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout, null);
					holder = new Holder();
					
					holder.title = (TextView) view.findViewById(R.id.text_item);
					holder.quantity = (TextView) view.findViewById(R.id.text_quantity); 
					holder.price = (TextView) view.findViewById(R.id.text_price);
					view.setTag(holder);
				}
				else {
					holder = (Holder) view.getTag();
				}
				Product p = productList.get(position);
				String product_name = p.getName();
				String product_code = p.getProduct_Code();
				int product_quantity = p.getQuantity();
				int product_price = p.getPrice();
				holder.title.setText(productList.get(position).getName() + " <" + product_code + "> ");
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
					view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout, null);
					holder = new Holder();
					
					holder.title = (TextView) view.findViewById(R.id.text_item);
					holder.quantity = (TextView) view.findViewById(R.id.text_quantity); 
					holder.price = (TextView) view.findViewById(R.id.text_price);
					view.setTag(holder);
				}
				else {
					holder = (Holder) view.getTag();
				}
				
				//
				ArrayList<Product> list = basket.getList();
				
				Toast.makeText(Tab_Sale_Activity.this,"fuuuuu", Toast.LENGTH_SHORT).show();
				
				
				
				
				
				
				Product p = list.get(position);
				String product_name = p.getName();
				String product_code = p.getProduct_Code();
				int product_quantity = basket.getMap().get(p);
				int product_price = p.getPrice();
				holder.title.setText(productList.get(position).getName() + " <" + product_code + "> ");
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
}
