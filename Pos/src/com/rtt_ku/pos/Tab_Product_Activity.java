package com.rtt_ku.pos;

import java.util.ArrayList;

import com.database.pos.Database;
import com.database.pos.DatabaseReader;
import com.rtt_ku.pos.ProductAdapter.Holder;
import com.rtt_store.pos.StoreController;

import Inventory.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Tab_Product_Activity extends Activity{

	//	SQLiteDatabase db;
	DatabaseReader databaseReader;
	
	ArrayList<Product> productList = new ArrayList<Product>();
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_tab);
		
		 Database myDb = new Database(this);
	     myDb.getWritableDatabase();
	     StoreController sCT = new StoreController(myDb);
	     productList = sCT.getProductList();
	        
	     Button addButton = (Button)findViewById(R.id.product_addButton);
		ListView listview = (ListView)findViewById(R.id.product_listView);
		
		listview.setAdapter(new ProductAdapter(productList,this));
		
		listview.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Tab_Product_Activity.this,ProductItem_Info.class));
			}
        });
	}

	public View getView(){
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.product_item, null);
        return view;
	}
}
