package com.rtt_ku.pos;

import java.util.ArrayList;

import com.rtt_ku.pos.R;
import com.salerecord.pos.DatabaseSaleRecord;
import com.salerecord.pos.Profile;
import com.salerecord.pos.SaleRecordController;
import com.salerecord.pos.Wan;

import Inventory.Product;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class SaleReportAdapter extends BaseAdapter{

	private ArrayList<Profile> list;
	private Sale_Report sr;
	private DatabaseSaleRecord dbSr;
	private ArrayList<String> record;
	
	public SaleReportAdapter(ArrayList<Profile> profiles,Sale_Report sr){
		this.list = profiles;
		this.sr = sr;
		
	}

	private Holder holder;
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view == null){
			view = sr.getView();
			holder = new Holder();
			
			holder.productCode = (TextView) view.findViewById(R.id.sale_report_productTextView);
			holder.quantity = (TextView) view.findViewById(R.id.sale_report_quantityTextView);
			holder.price = (TextView) view.findViewById(R.id.sale_report_priceTextView);
			holder.time = (TextView) view.findViewById(R.id.sale_report_timeTextView);
			holder.date = (TextView) view.findViewById(R.id.sale_report_dateTextView);
			view.setTag(holder);
		}
		else{
			holder = (Holder) view.getTag();
		}
		
		//set text here
		Profile p = list.get(arg0);
		int hour = Integer.parseInt(p.hour);
//		if (hour > 12) hour = hour - 12;
//		else hour = hour + 12;
		String min = p .min;
		if (Integer.parseInt(min) < 10) min = "0"+min;
		holder.date.setText(p.day + "/" + (p.month+1) + "/" + p.year);
		holder.time.setText(hour + ":" + min);
		holder.productCode.setText(p.product_code);
		holder.price.setText(p.quantity);
		holder.quantity.setText(p.price);
		
		
		return view;
		
	}
	
	class Holder{
		
		public TextView productCode;
		public TextView quantity;
		public TextView price;
		public TextView time;
		public TextView date;
	}

}
