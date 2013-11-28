package com.rtt_ku.pos;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Sale_ReportDaily_ChooseDate extends Activity{

	
	private Spinner daySpn,monthSpn,yearSpn;
	private Button okButton,cancelButton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_choose_daily);
		
		initWidget();
		addDay();
		addMonth();
		
	}
	
	private void initWidget(){
		okButton = (Button)findViewById(R.id.report_daily_okButton);
		cancelButton = (Button)findViewById(R.id.report_daily_cancelButton);
		daySpn = (Spinner)findViewById(R.id.report_dailySpinner_day);
		monthSpn = (Spinner)findViewById(R.id.report_dailySpinner_month);
		yearSpn = (Spinner)findViewById(R.id.report_dailySpinner_year);
	}
	
	private void addDay(){
		ArrayList<String> mylistDay = new ArrayList<String>();
		for(int i = 1; i <= 31; i++){
			mylistDay.add(i+"");
		}
		
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylistDay);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		daySpn.setAdapter(myAdapter);
	}
	
	private void addMonth(){
		ArrayList<String> mylistMonth = new ArrayList<String>();
		for(int i = 1; i<=12; i++){
			mylistMonth.add(i+"");
		}
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylistMonth);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpn.setAdapter(myAdapter);
	}
}
