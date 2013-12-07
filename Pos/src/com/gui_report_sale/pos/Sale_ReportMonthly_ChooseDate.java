package com.gui_report_sale.pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.SaleRecordDateDatabase;
import com.salerecord.pos.Wan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Sale_ReportMonthly_ChooseDate extends Activity{

	private Button okButton,cancelButton;
	private Spinner monthSpn,yearSpn;
	private StoreController sCT;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_choose_monthly);
		
        InventoryDatabase myDb = new InventoryDatabase(this);
        myDb.getReadableDatabase();
        SaleRecordDateDatabase DbSr = new SaleRecordDateDatabase(this);
        DbSr.getReadableDatabase();
		sCT = new StoreController(myDb,DbSr);
		
		
		initWidget();
		addButton();
		addYear();
		monthSpn.setEnabled(false);

	}
	
	private void addButton(){
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(v.getContext(), Sale_Report_MonthlyListView.class);
				intent.putExtra("month",Integer.parseInt(String.valueOf(monthSpn.getSelectedItem()))+"");
				intent.putExtra("year",String.valueOf(yearSpn.getSelectedItem()));
				
				
				startActivity(intent);
				
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	private void addMonth(final String year){
		
		ArrayList<Wan> wans = sCT.getWans();
		ArrayList<String> mylistMonth = new ArrayList();
		Set<String> set = new HashSet<String>();
		for (int i=0; i<wans.size(); i++)
		{
			if (wans.get(i).getYear().equals(year))
			{
				String temp = Integer.parseInt(wans.get(i).getMonth())+1+"";
				if (!set.contains(temp))
					mylistMonth.add(temp);
				set.add(temp);
			}
		}
		
		
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylistMonth);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpn.setAdapter(myAdapter);
		monthSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void addYear() {
		ArrayList<Wan> wans = sCT.getWans();
		ArrayList<String> mylistDay = new ArrayList();
		Set<String> set = new HashSet<String>();
		for (int i=0; i<wans.size(); i++)
		{
			String temp = wans.get(i).getYear();
			if (!set.contains(temp))
				mylistDay.add(temp);
			set.add(temp);
		}
		
	
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylistDay);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearSpn.setAdapter(myAdapter);
		yearSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				monthSpn.setEnabled(true);
				addMonth(String.valueOf(yearSpn.getSelectedItem()));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void initWidget(){
		okButton = (Button) findViewById(R.id.report_monthly_okButton);
		cancelButton =(Button) findViewById(R.id.report_monthly_cancelButton);
		monthSpn = (Spinner) findViewById(R.id.report_monthlySpinner_month);
		yearSpn = (Spinner) findViewById(R.id.report_monthlySpinner_year);
	}
}
