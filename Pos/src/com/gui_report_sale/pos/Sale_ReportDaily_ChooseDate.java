package com.gui_report_sale.pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.main_activity;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.SaleRecordDateDatabase;
import com.salerecord.pos.Wan;

import Inventory.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Sale_ReportDaily_ChooseDate extends Activity{

	
	private Spinner daySpn,monthSpn,yearSpn;
	private Button okButton,cancelButton;
	private StoreController sCT;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_choose_daily);
		
        InventoryDatabase myDb = new InventoryDatabase(this);
        myDb.getReadableDatabase();
        SaleRecordDateDatabase DbSr = new SaleRecordDateDatabase(this);
        DbSr.getReadableDatabase();
		sCT = new StoreController(myDb,DbSr);
		
		initWidget();
		addButton();
//		addDay();
//		addMonth();
		addYear();
		
		daySpn.setEnabled(false);
		monthSpn.setEnabled(false);
	}
	
	

	private void initWidget(){
		okButton = (Button)findViewById(R.id.report_daily_okButton);
		cancelButton = (Button)findViewById(R.id.report_daily_cancelButton);
		daySpn = (Spinner)findViewById(R.id.report_dailySpinner_day);
		monthSpn = (Spinner)findViewById(R.id.report_dailySpinner_month);
		yearSpn = (Spinner)findViewById(R.id.report_dailySpinner_year);
	}
	
	private void addButton(){
		okListener();
		cancelListener();
	}
	private void cancelListener() {
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(), main_activity.class));
			}
		});
		
	}
	
	
	
	

	private void okListener() {
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(v.getContext(), Sale_Report_DailyListView.class);
				intent.putExtra("day",String.valueOf(daySpn.getSelectedItem()));
				intent.putExtra("month",String.valueOf(monthSpn.getSelectedItem()));
				intent.putExtra("year",String.valueOf(yearSpn.getSelectedItem()));
				
				
				startActivity(intent);
				
				
//				Toast.makeText(v.getContext(),String.valueOf(daySpn.getSelectedItem())+" "+String.valueOf(monthSpn.getSelectedItem()), Toast.LENGTH_SHORT).show();
//				startActivity(new Intent(v.getContext(), Sale_Report_DailyListView.class));
			}
		});
		
	}

	private void addDay(String year,String month){
		ArrayList<Wan> wans = sCT.getWans();
		ArrayList<String> mylistDay = new ArrayList();
		Set<String> set = new HashSet<String>();
		for (int i=0; i<wans.size(); i++)
		{
			if (wans.get(i).getYear().equals(year) && wans.get(i).getMonth().equals(month))
			{
				String temp = wans.get(i).getDay();
				if (!set.contains(temp))
					mylistDay.add(temp);
				set.add(temp);
			}
		}
		
		
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylistDay);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		daySpn.setAdapter(myAdapter);
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
				daySpn.setEnabled(true);
				addDay(year,Integer.parseInt(String.valueOf((monthSpn.getSelectedItem())))-1+"");
				
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
}
