package com.gui_report_sale.pos;

import java.util.ArrayList;

import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.R.id;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.Record;

import Inventory.Product;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * adapter of listview in sale report on daily page.
 * @author rtt team
 *
 */
public class DailyBillAdapter extends BaseAdapter{

	private Holder holder;
	private ArrayList<String> list;
	private Sale_Report_DailyBill b;
	
	
	public DailyBillAdapter(ArrayList<String> list,Sale_Report_DailyBill b){
		this.list = list;
		this.b = b;
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
			view = b.getView();
			holder = new Holder();
			
			holder.product = (TextView) view.findViewById(R.id.sale_report_productTextView);
			holder.quantity = (TextView) view.findViewById(R.id.sale_report_quantityTextView); 
			holder.price = (TextView) view.findViewById(R.id.sale_report_priceTextView);
			view.setTag(holder);
		}
		else {
			holder = (Holder) view.getTag();
		}
		System.out.println(list);
		String[]temp = list.get(position).split(" ");
		System.out.println(temp.length);
		System.out.println(temp[0]);
		System.out.println(temp[1]);
		System.out.println(holder);
		System.out.println(holder.product);
		holder.product.setText("<"+temp[0]+">");
		holder.quantity.setText(temp[1]+"");
		holder.price.setText(temp[2]+"");
//		holder.subTotal.setText((Integer.parseInt(temp[1]) * Integer.parseInt(temp[2]))+"");
		
		
		return view;
	}
	
	// hold text view in each list of item. 
	class Holder{
		public TextView product;
		public TextView quantity;
		public TextView price;
	}
}

