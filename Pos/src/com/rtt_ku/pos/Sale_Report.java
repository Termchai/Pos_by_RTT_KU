package com.rtt_ku.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Sale_Report extends Activity{
	
	private ListView listView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_layout);
		
		//view matching
		Button cancelButton = (Button)findViewById(R.id.sale_report_cancelbutton);
		listView = (ListView)findViewById(R.id.sale_report_listView);
		
		//set adapter here
//		listView.setAdapter(new SaleReportAdapter(list, this));
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), main_activity.class));
			}
		});
	}
	
	public View getView () {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sale_report_list, null);
        return view;
	}

}
