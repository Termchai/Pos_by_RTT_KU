package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Sale_Activity extends Activity{
	ArrayAdapter<String> listAdapter;
	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.makesale_layout);
		
		Button cancel_button = (Button)findViewById(R.id.cancel_button);
//		ListView list_item = (ListView)findViewById(R.id.sale_button);
		
		ArrayList<String>planets = new ArrayList<String>();
		planets.addAll(Arrays.asList(FRUITS));
		listAdapter = new ArrayAdapter<String>(this, R.layout.makesale_layout, planets);
//		listView.setOnItemClickListener(new OnItemClickListener(){
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position,
//					long id) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//			}
//		});
		
		cancel_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_main);
				startActivity(new Intent(Sale_Activity.this, main_activity.class));
			}
			
		});
	}
}
