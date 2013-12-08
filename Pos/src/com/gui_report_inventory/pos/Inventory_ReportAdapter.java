package com.gui_report_inventory.pos;

import java.util.ArrayList;

import Inventory.Product;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gui_tab_catalog.pos.Tab_Product_Activity;
import com.inventory_record.pos.InventoryInput;
import com.inventory_record.pos.InventoryRecord;
import com.rtt_ku.pos.R;

public class Inventory_ReportAdapter extends BaseAdapter{

	private Holder holder;
	private ArrayList<InventoryInput> list;
	private Inventory_Report ir;
	
	
	public Inventory_ReportAdapter(ArrayList<InventoryInput> list,Inventory_Report ir){
		this.list = list;
		this.ir = ir;
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
			view = ir.getView();
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
		
		InventoryInput input = list.get(position);
//		System.out.println(list);
//		System.out.println(input);
//		System.out.println("testttttttttttttttttttttttttttttttttttttt");
		holder.date.setText(input.day + "/" + input.month + "/" + input.year);
		holder.time.setText(input.hour+":"+input.min);
		holder.product.setText(input.product_code);
		holder.quantity.setText(input.quantity);
		holder.cost.setText(input.cost);
		
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
