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

/**
 * Bill of sale report in monthly.
 * @author rtt team
 * 
 */
public class Sale_Report_MonthlyBill extends Activity {

	private ListView listView;
	private ArrayList<String> list;
	private String month, year, timeStr, idBill;
	private TextView time_textView;
	private TextView date_textView;
	private TextView totalPrice;
	private TextView idBill_textview;
	private int position;
	private Intent intend;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_bill);

		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getReadableDatabase();
		SaleRecordDateDatabase DbSr = new SaleRecordDateDatabase(this);
		DbSr.getReadableDatabase();
		StoreController sCT = new StoreController(myDb, DbSr);

		initWidget();

		intend = getIntent();

		getInformation();

		ArrayList<Wan> wans = sCT.getWans();
		final ArrayList<Record> list2 = new ArrayList<Record>();
		for (int i = 0; i < wans.size(); i++) {
			Wan tempWan = wans.get(i);
			if (tempWan.getMonth().equals(month)
					&& tempWan.getYear().equals(year)) {
				Time time = new Time();
				time.set(Integer.parseInt(tempWan.getDay()),
						Integer.parseInt(tempWan.getMonth()),
						Integer.parseInt(tempWan.getYear()));
				list2.addAll(new DailyRecord(this, time).SelectAllData());
			}
		}

		Record r = list2.get(position);
		String[] temp = r.basket.split(":");
		list = new ArrayList<String>();
		time_textView.setText(timeStr);
		idBill_textview.setText(idBill);

		date_textView.setText(r.day + "/" + (Integer.parseInt(r.month) + 1)
				+ "/" + Integer.parseInt(r.year));
		totalPrice.setText(r.getTotal() + "");
		for (String tempp : temp)
			list.add(tempp);
		listView.setAdapter(new MonthlyBillAdapter(list, this));
	}

	// get date from another class.
	private void getInformation() {
		month = intend.getExtras().getString("month");
		year = intend.getExtras().getString("year");
		idBill = intend.getExtras().getString("idBill");
		position = Integer.parseInt(intend.getExtras().getString("position"));
		timeStr = intend.getExtras().getString("time");
	}

	// view matching.
	private void initWidget() {
		listView = (ListView) findViewById(R.id.report_bill_listView);
		time_textView = (TextView) findViewById(R.id.sale_report_time);
		date_textView = (TextView) findViewById(R.id.sale_report_date);
		totalPrice = (TextView) findViewById(R.id.report_bill_totalprice);
		idBill_textview = (TextView) findViewById(R.id.sale_report_idBill);
	}

	// send view of line item to adapter.
	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.sale_report_list, null);
		return view;
	}
}
