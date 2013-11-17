package com.rtt_ku.pos;

import java.util.ArrayList;

import com.rtt_ku.pos.R;

import Inventory.Product;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class SaleReportAdapter extends BaseAdapter{

	private ArrayList<?> list;
	private Sale_Report sr;
	
	public SaleReportAdapter(ArrayList<?> list,Sale_Report sr){
		this.list = list;
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
