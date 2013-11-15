package com.rtt_ku.pos;

import java.util.ArrayList;

import com.rtt_ku.pos.InventoryAdapter.Holder;

import Inventory.Product;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SaleAdapter extends BaseAdapter{
	private Holder holder;
	private ArrayList<Product> list;
	private Tab_Sale_Activity sa;
	
	public SaleAdapter(ArrayList<Product> list, Tab_Sale_Activity sa){
		this.list = list;
		this.sa = sa;
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
			view = sa.getView();
			holder = new Holder();
			
			holder.title = (TextView) view.findViewById(R.id.text_item);
			holder.quantity = (TextView) view.findViewById(R.id.text_quantity); 
			holder.price = (TextView) view.findViewById(R.id.text_price);
			view.setTag(holder);
		}
		else {
			holder = (Holder) view.getTag();
		}
		Product p = list.get(position);
		String product_name = p.getName();
		String product_code = p.getProduct_Code();
		int product_quantity = p.getQuantity();
		int product_price = p.getPrice();
		holder.title.setText(list.get(position).getName() + " <" + product_code + "> ");
		holder.quantity.setText(product_quantity +  " item(s)");
		holder.price.setText(product_price+"");
		return view;
	}
	
	// hold text view in each list of item. 
	class Holder{
		
		public TextView title;
		public TextView quantity;
		public TextView price;
	}
}
