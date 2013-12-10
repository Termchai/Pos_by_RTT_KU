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

/**
 * Set each bill at sale report on daily page.
 * @author rtt team
 *
 */
public class Sale_Report_DailyBill extends Activity{
	
	private ListView listView;
	private ArrayList<String> list;
	private String date,timeStr,idBill;
	private TextView time_textView;
	private TextView date_textView;
	private TextView totalPrice;
	private TextView idBill_textView;
	private int position;
	private Intent intend;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_bill);
		
		initWidget();
		
		intend = getIntent();
		getInformation();
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

	/**
	 * get data from another class.
	 */
	private void getInformation() {
		date = intend.getExtras().getString("date");
		timeStr = intend.getExtras().getString("time");
		idBill = intend.getExtras().getString("idBill");
		position = Integer.parseInt(intend.getExtras().getString("position"));
	}

	/**
	 * view matching
	 */
	private void initWidget() {
		listView = (ListView)findViewById(R.id.report_bill_listView);
		time_textView = (TextView)findViewById(R.id.sale_report_time);
		date_textView = (TextView)findViewById(R.id.sale_report_date);
		totalPrice = (TextView)findViewById(R.id.report_bill_totalprice);
		idBill_textView = (TextView)findViewById(R.id.sale_report_idBill);
	}

	/**
	 * send view to adapter.
	 * @return view of line item.
	 */
	public View getView(){
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sale_report_list, null);
        return view;
	}
}
