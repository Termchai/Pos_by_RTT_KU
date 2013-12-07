package com.gui_report_sale.pos;

import java.util.ArrayList;

import com.rtt_ku.pos.R;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Sale_ReportYearly_ChooseDate extends Activity{
	
	private Button okButton,cancelButton;
	private Spinner yearSpn;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_choose_year);
		
		initWidget();
		addButton();
		addYear();
	}
	
	private void addYear(){
		ArrayList<String> mylistYear = new ArrayList<String>();
		for(int i= 1990; i < 2014; i++){
			mylistYear.add(i+"");
		}
		ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylistYear);
		myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearSpn.setAdapter(myAdapter);
		
	}
	private void addButton(){
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), Sale_Report_YearlyListView.class));
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
			
	}
	private void initWidget(){
		
		okButton = (Button) findViewById(R.id.report_yearly_okButton);
		cancelButton = (Button) findViewById(R.id.report_yearly_cancelButton);
		yearSpn = (Spinner) findViewById(R.id.report_yearlySpinner_year);
	}
}
