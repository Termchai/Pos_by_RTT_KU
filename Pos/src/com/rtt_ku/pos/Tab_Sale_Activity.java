package com.rtt_ku.pos;

import java.util.ArrayList;

import com.database.pos.DatabaseReader;
import com.rtt_ku.pos.Tab_Inventory_Activity.MyAdapter;
import com.rtt_ku.pos.Tab_Inventory_Activity.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;

import Inventory.Product;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Tab_Sale_Activity extends Activity{

	//	SQLiteDatabase db;
	DatabaseReader databaseReader;
	// list item
	ArrayList<Product> productList = new ArrayList<Product>();
    public static StoreController sCT;
	
	private ArrayAdapter<String> listAdapter;
	private ListView list_item;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_tab);
		
		// view matching
		list_item = (ListView)findViewById(R.id.listView2);
		        
		// adapter of list item.
		list_item.setAdapter(new MyAdapter());
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
}
