package com.gui_report_sale.pos;

import java.util.ArrayList;

import com.database.pos.InventoryDatabase;
import com.gui_tab_catalog.pos.ProductItem_Info;
import com.gui_tab_catalog.pos.Tab_Product_Activity;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.DailyRecord;
import com.salerecord.pos.SaleRecordDateDatabase;
import com.salerecord.pos.Record;

import Inventory.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Daily report at sale report page.
 * 
 * @author rtt team
 * 
 */
public class Sale_Report_DailyListView extends Activity {
	public static ListView listView;
	public Sale_Report_DailyListView sr;
	private Intent intend;

	private int day;
	private int month;
	private int year;
	private ArrayList<Record> list;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_daily_listview);

		sr = this;

		initWidget();

		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getReadableDatabase();
		SaleRecordDateDatabase DbSr = new SaleRecordDateDatabase(this);
		DbSr.getReadableDatabase();
		StoreController sCT = new StoreController(myDb, DbSr);

		intend = getIntent();
		getInformation();

		Time time = new Time();
		time.set(day, month, year);
		list = new DailyRecord(this, time).SelectAllData();

		listView.setAdapter(new Sale_Report_DailyListViewAdapter(list, this,
				time));

		addButton();
	}

	/** add function onclick */
	private void addButton() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(view.getContext(),
						Sale_Report_DailyBill.class);
				Record r = list.get(position);
				// Product p = sCT.getProductList().get(position);
				intent.putExtra("date", day + "/" + month + "/" + year);
				intent.putExtra("time", r.hour + ":" + r.min);
				intent.putExtra("idBill", r.id);
				intent.putExtra("position", position + "");

				startActivity(intent);
			}
		});
	}

	/** get date from another class */
	private void getInformation() {
		day = Integer.parseInt(intend.getExtras().getString("day"));
		month = Integer.parseInt(intend.getExtras().getString("month")) - 1;
		year = Integer.parseInt(intend.getExtras().getString("year"));
	}

	/** view matching */
	private void initWidget() {
		listView = (ListView) findViewById(R.id.sale_report_daily_listView);
	}

	/** send view of line item to adapter */
	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.sale_report_daily_lineitem, null);
		return view;

	}
}
