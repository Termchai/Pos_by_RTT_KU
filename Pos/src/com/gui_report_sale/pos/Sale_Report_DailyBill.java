package com.gui_report_sale.pos;

import java.util.ArrayList;

import com.rtt_ku.pos.Add_Activity;
import com.rtt_ku.pos.R;
import com.salerecord.pos.DailyRecord;
import com.salerecord.pos.Record;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Sale_Report_DailyBill extends Activity{
	
	ListView listView;
	ArrayList<String> list;
	String date,timeStr,idBill;
	TextView time_textView;
	TextView date_textView;
	TextView totalPrice;
	TextView idBill_textView;
	int position;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_bill);
		

		listView = (ListView)findViewById(R.id.report_bill_listView);
		time_textView = (TextView)findViewById(R.id.sale_report_time);
		date_textView = (TextView)findViewById(R.id.sale_report_date);
		totalPrice = (TextView)findViewById(R.id.report_bill_totalprice);
		idBill_textView = (TextView)findViewById(R.id.sale_report_idBill);
		
		Intent intend = getIntent();
		date = intend.getExtras().getString("date");
		timeStr = intend.getExtras().getString("time");
		idBill = intend.getExtras().getString("idBill");
		position = Integer.parseInt(intend.getExtras().getString("position"));
		Time time = new Time();
		String[] tempDate = date.split("/");
		time.set(Integer.parseInt(tempDate[0]), Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[2]));
		Record r = (new DailyRecord(this, time)).SelectAllData().get(position);
		String[] temp = r.basket.split(":");
		list = new ArrayList<String>();
		time_textView.setText(timeStr);
		date_textView.setText(Integer.parseInt(tempDate[0])+"/"+ (Integer.parseInt(tempDate[1])+1)+"/"+Integer.parseInt(tempDate[2]));
		totalPrice.setText(r.getTotal()+"");
		idBill_textView.setText(idBill);
		for (String tempp : temp)
			list.add(tempp);
		listView.setAdapter(new DailyBillAdapter(list,this));
	}

	public View getView(){
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sale_report_list, null);
        return view;
	}
}
