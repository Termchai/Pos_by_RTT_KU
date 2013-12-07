package com.gui_report_sale.pos;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.DailyRecord;
import com.salerecord.pos.Record;
import com.salerecord.pos.SaleRecordDateDatabase;
import com.salerecord.pos.Wan;

public class Sale_Report_MonthlyBill extends Activity{
	
	ListView listView;
	ArrayList<String> list;
	String month,year,timeStr,idBill;
	TextView time_textView;
	TextView date_textView;
	TextView totalPrice;
	TextView idBill_textview;
	int position;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_bill);
		
		InventoryDatabase myDb = new InventoryDatabase(this);
        myDb.getReadableDatabase();
        SaleRecordDateDatabase DbSr = new SaleRecordDateDatabase(this);
        DbSr.getReadableDatabase();
		StoreController sCT = new StoreController(myDb,DbSr);

		listView = (ListView)findViewById(R.id.report_bill_listView);
		time_textView = (TextView)findViewById(R.id.sale_report_time);
		date_textView = (TextView)findViewById(R.id.sale_report_date);
		totalPrice = (TextView)findViewById(R.id.report_bill_totalprice);
		idBill_textview = (TextView)findViewById(R.id.sale_report_idBill);
		
		Intent intend = getIntent();
		month = intend.getExtras().getString("month");
		year = intend.getExtras().getString("year");
		idBill = intend.getExtras().getString("idBill");
		position = Integer.parseInt(intend.getExtras().getString("position"));
		timeStr = intend.getExtras().getString("time");
		
		ArrayList<Wan> wans = sCT.getWans();
		final ArrayList<Record> list2 = new ArrayList<Record>();
		for (int i=0; i<wans.size(); i++)
		{
			Wan tempWan = wans.get(i);
			if (tempWan.getMonth().equals(month) && tempWan.getYear().equals(year))
			{
				Time time = new Time();
				time.set(Integer.parseInt(tempWan.getDay()), Integer.parseInt(tempWan.getMonth()), Integer.parseInt(tempWan.getYear()));
				list2.addAll(new DailyRecord(this, time).SelectAllData());
			}
		}
		
		Record r = list2.get(position);
		String[] temp = r.basket.split(":");
		list = new ArrayList<String>();
		time_textView.setText(timeStr);
		idBill_textview.setText(idBill);
		
		
		
		date_textView.setText(r.day+"/"+ (Integer.parseInt(r.month)+1)+"/"+Integer.parseInt(r.year));
		totalPrice.setText(r.getTotal()+"");
		for (String tempp : temp)
			list.add(tempp);
		listView.setAdapter(new MonthlyBillAdapter(list,this));
	}

	public View getView(){
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sale_report_list, null);
        return view;
	}
}
