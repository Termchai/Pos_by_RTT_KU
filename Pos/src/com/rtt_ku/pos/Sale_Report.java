package com.rtt_ku.pos;

import java.util.ArrayList;

import com.database.pos.Database;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.DailyRecord;
import com.salerecord.pos.DatabaseSaleRecord;
import com.salerecord.pos.Profile;
import com.salerecord.pos.Record;
import com.salerecord.pos.Wan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
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
        Database myDb = new Database(this);
        myDb.getReadableDatabase();
        DatabaseSaleRecord DbSr = new DatabaseSaleRecord(this);
        DbSr.getReadableDatabase();
		StoreController sCT = new StoreController(myDb,DbSr);
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		ArrayList<Wan> wans = sCT.getWans();
		for (int i=0; i<wans.size(); i++)
		{
			Wan wan = wans.get(i);
			Time time = new Time();
			time.set(Integer.parseInt(wan.getDay()), Integer.parseInt(wan.getMonth()), Integer.parseInt(wan.getYear()));
			DailyRecord dr = new DailyRecord(this, time);
			ArrayList<Record> listR = dr.SelectAllData();
			for (int j=0; j<listR.size(); j++)
			{
				Record r = listR.get(j);
				String[] temp = r.basket.split(":");
				for (int k=0; k<temp.length; k++)
				{
					String[] tempp = temp[k].split(" ");
					Profile p = new Profile(time.monthDay,time.month,time.year,r.hour,r.min);
					p.product_code = tempp[0];
					p.quantity = tempp[1];
					p.price = tempp[2];
					profiles.add(p);
				}
				
				

			}
			
		}
		
		
		
		listView.setAdapter(new SaleReportAdapter(profiles, this));
		
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
