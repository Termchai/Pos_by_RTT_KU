package com.rtt_ku.pos;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Sale_ReportMonthly_ChooseDate extends Activity{

	private Button okButton,cancelButton;
	private Spinner monthSpn,yearSpn;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_choose_monthly);
		
		initWidget();
		addButton();
		addDate();
	}
	
	private void addButton(){
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), Sale_Report_MonthlyListView.class));
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void addDate(){
		addMonth();
		addYear();
	}
	
	private void addMonth(){
		ArrayList<String> mylistMonth = new ArrayList<String>();
		for(int i= 1; i <=12; i++){
			mylistMonth.add(i+"");
		}
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylistMonth);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpn.setAdapter(myAdapter);
	}
	
	private void addYear(){
		ArrayList<String> mylistYear = new ArrayList<String>();
		for(int i=1990; i < 2014; i++){
			mylistYear.add(i+"");
		}
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylistYear);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearSpn.setAdapter(myAdapter);
	}
	
	private void initWidget(){
		okButton = (Button) findViewById(R.id.report_monthly_okButton);
		cancelButton =(Button) findViewById(R.id.report_monthly_cancelButton);
		monthSpn = (Spinner) findViewById(R.id.report_monthlySpinner_month);
		yearSpn = (Spinner) findViewById(R.id.report_monthlySpinner_year);
	}
}
