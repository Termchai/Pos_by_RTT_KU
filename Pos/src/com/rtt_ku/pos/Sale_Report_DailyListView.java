package com.rtt_ku.pos;

import com.database.pos.Database;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.DatabaseSaleRecord;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class Sale_Report_DailyListView extends Activity {
	public static ListView listView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_report_daily_listview);
		listView = (ListView)findViewById(R.id.sale_report_listView);
		Database myDb = new Database(this);
        myDb.getReadableDatabase();
        DatabaseSaleRecord DbSr = new DatabaseSaleRecord(this);
        DbSr.getReadableDatabase();
		StoreController sCT = new StoreController(myDb,DbSr);

	}

	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.sale_report_daily_lineitem, null);
		return view;

	}
}
