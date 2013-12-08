package com.gui_tab_inventory.pos;

import java.util.ArrayList;

import com.inventory_record.pos.InventoryRecord;
import com.rtt_ku.pos.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class Inventory_Report extends Activity{

	ListView listView;
	EditText editText;
	ArrayList<InventoryRecord> list;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_report_layout);
		
		listView = (ListView)findViewById(R.id.inventory_report_listView);
		editText = (EditText)findViewById(R.id.inventory_report_editText);
		
		list = new ArrayList<InventoryRecord>();
		listView.setAdapter(new Inventory_ReportAdapter(list,this));
		
		editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String text = editText.getText().toString();
				listView.setAdapter(new Inventory_ReportAdapter(list,Inventory_Report.this));
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
	}
	
	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.inventory_report_lineitem, null);
        return view;
	}
}
