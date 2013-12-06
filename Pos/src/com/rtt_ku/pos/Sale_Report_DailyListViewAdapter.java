package com.rtt_ku.pos;

import java.util.ArrayList;

import Inventory.Product;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rtt_ku.pos.InventoryAdapter.Holder;
import com.salerecord.pos.Record;

public class Sale_Report_DailyListViewAdapter extends BaseAdapter{

	private Holder holder;
	private ArrayList<Record> list;
	private Sale_Report_DailyListView sRD;
	private Time time;
	
	
	public Sale_Report_DailyListViewAdapter(ArrayList<Record> list,Sale_Report_DailyListView sRD,Time time){
		this.list = list;
		this.sRD = sRD;
		this.time = time;
	}
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
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view == null){
			view = sRD.getView();
			holder = new Holder();
			
			holder.date = (TextView) view.findViewById(R.id.sale_report_daily_lineitem_date);
			holder.timee = (TextView) view.findViewById(R.id.sale_report_daily_lineitem_timee);
			holder.total = (TextView) view.findViewById(R.id.sale_report_daily_lineitem_totalprice);

			view.setTag(holder);
		}
		else {
			holder = (Holder) view.getTag();
		}
		Record r = list.get(position);
		System.out.println(position);
		String date = time.monthDay + "/" + time.month + "/" + time.year;
		String timee = r.hour + ":" + r.min;
		String total = r.getTotal()+"";
		holder.date.setText(date);
		holder.timee.setText(timee);
		holder.total.setText(total);
//		Product p = list.get(position);
//		String product_name = p.getName();
//		String product_code = p.getProduct_Code();
//		int product_quantity = p.getQuantity();
//		int product_price = p.getPrice();
//		holder.title.setText(list.get(position).getName() + " <" + product_code + "> ");
//		holder.quantity.setText(product_quantity +  " item(s)");
//		holder.price.setText(product_price+"");
		return view;
	}
	
	// hold text view in each list of item. 
	class Holder{
		public TextView total,date,timee;
	}
}
