package com.gui_report_sale.pos;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gui_report_sale.pos.MonthlyBillAdapter.Holder;
import com.rtt_ku.pos.R;

/**
 * adapter of each bill in each year 
 * @author Administrator
 *
 */
public class YearlyBillAdapter extends BaseAdapter{

	private Holder holder;
	private ArrayList<String> list;
	private Sale_Report_YearlyBill b;
	
	
	public YearlyBillAdapter(ArrayList<String> list,Sale_Report_YearlyBill b){
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
		holder.quantity.setText(temp[2]+"");
		holder.price.setText(temp[1]+"");
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
