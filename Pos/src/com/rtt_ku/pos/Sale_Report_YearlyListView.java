package com.rtt_ku.pos;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class Sale_Report_YearlyListView extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_daily_listview);
	}

	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.sale_report_daily_lineitem, null);
		return view;

	}
}
