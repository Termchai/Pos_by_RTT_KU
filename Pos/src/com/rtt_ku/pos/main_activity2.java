package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.database.pos.Database;
import com.database.pos.DatabaseController;
import com.database.pos.DatabaseReader;
import com.rtt_ku.pos.Sale_Activity.MyAdapter;
import com.rtt_ku.pos.Sale_Activity.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.DatabaseSaleRecord;

import Inventory.InventoryAll;
import Inventory.Product;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

/**
 * activity on main layout.
 * @author rtt
 *
 */
public class main_activity2 extends Activity {

//	SQLiteDatabase db;
	DatabaseReader databaseReader;
	// list item
	ArrayList<Product> productList = new ArrayList<Product>();
    public static StoreController sCT;
	
	ArrayAdapter<String> listAdapter;
	ListView list_item;
	
	private String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
			"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
			"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        Database myDb = new Database(this);
        myDb.getWritableDatabase();
        sCT = new StoreController(myDb);
        productList = sCT.getProductList();
        
        System.out.println(productList + "testttttttttt");
        
        // view matching.
        Button addButton = (Button) findViewById(R.id.add_button);
        Button removeButton = (Button) findViewById(R.id.remove_button);
        Button makeSaleButton = (Button) findViewById(R.id.sale_button);
        list_item = (ListView)findViewById(R.id.listItem);
        
        // adapter of list item.
		list_item.setAdapter(new MyAdapter());
		
        // add function on click at add button.
        addButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//setContentView(R.layout.add_layout);
				startActivity(new Intent(main_activity2.this, Add_Activity.class));
			}
        	
        });
        
        // add function on click at remove button.
        removeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//setContentView(R.layout.remove_layout);
				startActivity(new Intent(main_activity2.this, Remove_Activity.class));
			}
        });
        
//        makeSaleButton.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				setContentView(R.layout.makesale_layout);
//				startActivity(new Intent(main_activity.this, Sale_Activity.class));
//			}
        	
//        });
//        Toast.makeText(main_activity.this,"Fuck You All",
//        		Toast.LENGTH_LONG).show();

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
			holder.product_code = product_code;
			holder.title.setText(productList.get(position).getName() + " <" + product_code + "> ");
			holder.quantity.setText(product_quantity +  " item(s)");
			holder.price.setText(product_price+"");
			return view;
		}
		
		// hold text view in each list of item. 
		class Holder{
			public String product_code;
			public TextView title;
			public TextView quantity;
			public TextView price;
		}
	}
}
