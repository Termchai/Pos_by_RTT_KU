package com.gui_tab_inventory.pos;

import java.util.ArrayList;

import Inventory.Product;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gui_tab_catalog.pos.Tab_Product_Activity;
import com.inventory_record.pos.InventoryRecord;
import com.rtt_ku.pos.R;

public class Inventory_ReportAdapter extends BaseAdapter{

	private Holder holder;
	private ArrayList<InventoryRecord> list;
	private Inventory_Report ac;
	
	
	public Inventory_ReportAdapter(ArrayList<InventoryRecord> list,Inventory_Report ac){
		this.list = list;
		this.ac = ac;
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
			view = ac.getView();
			holder = new Holder();
			
			holder.date = (TextView) view.findViewById(R.id.inventory_report_dateTextView);
			holder.time = (TextView) view.findViewById(R.id.inventory_report_timeTextView); 
			holder.product = (TextView) view.findViewById(R.id.inventory_report_productTextView);
			holder.quantity = (TextView) view.findViewById(R.id.inventory_report_quantityTextView);
			holder.cost = (TextView) view.findViewById(R.id.inventory_report_costTextView);
			view.setTag(holder);
		}
		else {
			holder = (Holder) view.getTag();
		}
//		Product p = list.get(position);
//		String product_name = p.getName();
//		String product_code = p.getProduct_Code();
//		int product_quantity = p.getQuantity();
//		int product_price = p.getPrice();
//		holder.product_code.setText("<"+product_code+">");
//		holder.title.setText(list.get(position).getName());
//		holder.quantity.setText(product_quantity +  " item(s)");
//		holder.price.setText(product_price+"");
		return view;
	}
	
	// hold text view in each list of item. 
	class Holder{
		public TextView date;
		public TextView time;
		public TextView product;
		public TextView quantity;
		public TextView cost;
	}

}
