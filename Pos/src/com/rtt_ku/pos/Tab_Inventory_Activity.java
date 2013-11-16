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
		list_item.setAdapter(new InventoryAdapter(productList,this));
		
		//add function on click at add button.
//		Intent intent = new Intent(Tab_Inventory_Activity.this, Check_product_Activity.class);
//		addButton.setOnClickListener(new OnClickChangeView(Tab_Inventory_Activity.this, intent));
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), Check_product_Activity.class));
			}
		});
		
 
        // add function on click at remove button.
        removeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Tab_Inventory_Activity.this, Remove_Activity.class));
			}
        });
		
	}
	public View getView () {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.large_item_layout, null);
        return view;
	}
		
}

