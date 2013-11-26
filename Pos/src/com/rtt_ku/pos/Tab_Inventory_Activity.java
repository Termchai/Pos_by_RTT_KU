package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.Date;

import com.database.pos.Database;
import com.database.pos.DatabaseReader;
import com.rtt_ku.pos.main_activity2.MyAdapter;
import com.rtt_ku.pos.main_activity2.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.DatabaseSaleRecord;

import Inventory.Product;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

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
	private Button editButton;
	
	private Button addButton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_tab);

        Database myDb = new Database(this);
        myDb.getWritableDatabase();
        Time now = new Time();
        now.setToNow();
        DatabaseSaleRecord DbSr = new DatabaseSaleRecord(this);
        DbSr.getWritableDatabase();
        sCT = new StoreController(myDb,DbSr);
        
        System.out.println(DbSr.SelectAllData());
        
        
        productList = sCT.getProductList();
		
		// view matching
		addButton = (Button) findViewById(R.id.button_add);
		removeButton = (Button) findViewById(R.id.set_quantity_ok_button);
		editButton = (Button) findViewById(R.id.Inventort_Tab_Edit_Button);
        list_item = (ListView)findViewById(R.id.inventory_listView);

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
		
		editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(), Inventory_Edit.class));
			}
		});
		
		
        // add function on click at remove button.
        removeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Tab_Inventory_Activity.this, Remove_Activity.class));
			}
        });
        
        list_item.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Tab_Inventory_Activity.this, Inventory_editItem.class);
				Product p = sCT.getProductList().get(position);
				intent.putExtra("pc",p.getProduct_Code()) ;
				startActivity(intent);
			}
        });
		
	}
	public View getView () {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.large_item_layout, null);
        return view;
	}
		
}

