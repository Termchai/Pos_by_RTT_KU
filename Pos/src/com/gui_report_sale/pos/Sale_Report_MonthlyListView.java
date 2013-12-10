package com.gui_report_sale.pos;

import java.util.ArrayList;

import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.DailyRecord;
import com.salerecord.pos.Record;
import com.salerecord.pos.SaleRecordDateDatabase;
import com.salerecord.pos.Wan;

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
 * sale report in monthly.
 * @author rtt team
 *
 */
public class Sale_Report_MonthlyListView extends Activity {

	public static ListView listView;
	public Sale_Report_MonthlyListView sr;
	private Intent intend;
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
		StoreController sCT = new StoreController(myDb,DbSr);
		
		
		intend = getIntent();
		getInformation();
		
		ArrayList<Wan> wans = sCT.getWans();
		list = new ArrayList<Record>();
		for (int i=0; i<wans.size(); i++)
		{
			Wan tempWan = wans.get(i);
			if (Integer.parseInt(tempWan.getMonth()) == month && Integer.parseInt(tempWan.getYear()) == year)
			{
				Time time = new Time();
				time.set(Integer.parseInt(tempWan.getDay()), Integer.parseInt(tempWan.getMonth()), Integer.parseInt(tempWan.getYear()));
				list.addAll(new DailyRecord(this, time).SelectAllData());
			}
		}

		listView.setAdapter(new Sale_Report_MonthlyListViewAdapter(list, this));
		
		addButton();
	}

	// view matching.
	private void initWidget() {
		listView = (ListView)findViewById(R.id.sale_report_daily_listView);
	}

	// add function on click.
	private void addButton(){
		listView.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(view.getContext(), Sale_Report_MonthlyBill.class);
				Record r = list.get(position);
//				Product p = sCT.getProductList().get(position);
				intent.putExtra("month",r.month+"");
				intent.putExtra("year", r.year+"");
				intent.putExtra("time", r.hour + ":" + r.min);
				intent.putExtra("idBill", r.id);
				intent.putExtra("position", position+"");
				
				startActivity(intent);
			}
        });
	}
	
	// get data from another class.
	private void getInformation() {
		month = Integer.parseInt(intend.getExtras().getString("month"))-1;
		year = Integer.parseInt(intend.getExtras().getString("year"));
	}

	// send view to adapter.
	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.sale_report_daily_lineitem, null);
		return view;

	}
}

