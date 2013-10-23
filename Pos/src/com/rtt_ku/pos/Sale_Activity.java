package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Sale_Activity extends Activity{
	
	ListView list_item;
	
//	ArrayList<String> data = new ArrayList<String>();
	
	private String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.makesale_layout);
		
		Button cancel_button = (Button)findViewById(R.id.cancel_button);
		list_item = (ListView)findViewById(R.id.listItem);
		
		list_item.setAdapter(new MyAdapter());
		
		
		
		cancel_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_main);
				startActivity(new Intent(Sale_Activity.this, main_activity2.class));
			}
			
		});
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
				view.setTag(holder);
			}
			else {
				holder = (Holder) view.getTag();
			}
			
			holder.title.setText(FRUITS[position]);
			
			return view;
		}
		class Holder{
			
			public TextView title;
			public TextView quantity;
		}
	}
}
