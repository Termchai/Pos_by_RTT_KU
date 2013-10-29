package com.rtt_ku.pos;

import java.util.ArrayList;

import com.database.pos.Database;
import com.database.pos.DatabaseReader;
import com.rtt_ku.pos.main_activity2.MyAdapter;
import com.rtt_ku.pos.main_activity2.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;

import Inventory.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Tab_Inventory_Activity extends Activity{

	private String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
			"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
			"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
	
	//	SQLiteDatabase db;
	DatabaseReader databaseReader;
	// list item
	ArrayList<Product> productList = new ArrayList<Product>();
    public static StoreController sCT;
	
	private ArrayAdapter<String> listAdapter;
	private ListView list_item;
	private Button removeButton;
	
	private Button addButton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_tab);

        Database myDb = new Database(this);
        myDb.getWritableDatabase();
        sCT = new StoreController(myDb);
        productList = sCT.getProductList();
		
		// view matching
		addButton = (Button) findViewById(R.id.button_add);
		removeButton = (Button) findViewById(R.id.set_quantity_ok_button);
        list_item = (ListView)findViewById(R.id.listView1);
        
        // adapter of list item.
		list_item.setAdapter(new MyAdapter());
		
		//add function on click at add button.
        addButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//setContentView(R.layout.add_layout);
				startActivity(new Intent(Tab_Inventory_Activity.this, Check_product_Activity.class));
			}
        	
        });
        
        // add function on click at remove button.
        removeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//setContentView(R.layout.remove_layout);
				startActivity(new Intent(Tab_Inventory_Activity.this, Remove_Activity.class));
			}
        });
		
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

