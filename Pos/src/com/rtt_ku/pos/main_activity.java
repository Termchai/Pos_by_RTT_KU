package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.List;

import com.database.pos.Database;
import com.database.pos.DatabaseController;
import com.database.pos.DatabaseReader;
import com.rtt_ku.pos.Sale_Activity.MyAdapter;
import com.rtt_ku.pos.Sale_Activity.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;

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


public class main_activity extends Activity {

//	SQLiteDatabase db;
	DatabaseReader databaseReader;
	ArrayList<Product> productList = new ArrayList<Product>();
	
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
        StoreController sCT = new StoreController(myDb);
        
        list_item = (ListView)findViewById(R.id.listItem);
		list_item.setAdapter(new MyAdapter());
		
        Button addButton = (Button) findViewById(R.id.add_button);
        Button removeButton = (Button) findViewById(R.id.remove_button);
        Button makeSaleButton = (Button) findViewById(R.id.sale_button);
        
        addButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.add_layout);
				startActivity(new Intent(main_activity.this, Add_Activity.class));
			}
        	
        });
        
        removeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.remove_layout);
				startActivity(new Intent(main_activity.this, Remove_Activity.class));
			}
        });
        
        makeSaleButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setContentView(R.layout.makesale_layout);
				startActivity(new Intent(main_activity.this, Sale_Activity.class));
			}
        	
        });
        Toast.makeText(main_activity.this,"Fuck You All",
        		Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pos__ui, menu);
        return true;
    }

    class MyAdapter extends BaseAdapter{
		private Holder holder;
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return FRUITS.length;
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
				view.setTag(holder);
			}
			else {
				holder = (Holder) view.getTag();
			}
			
			holder.title.setText(FRUITS[position]);
			holder.quantity.setText("kuyy");
			return view;
		}
		class Holder{
			
			public TextView title;
			public TextView quantity;
		}
	}
}
