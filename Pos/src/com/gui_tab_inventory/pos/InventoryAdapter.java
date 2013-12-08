package com.gui_tab_inventory.pos;

import java.util.ArrayList;

import Inventory.Product;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rtt_ku.pos.R;
import com.rtt_ku.pos.R.id;

public class InventoryAdapter extends BaseAdapter{
	
	private Holder holder;
	private ArrayList<Product> list;
	private Tab_Inventory_Activity pa;
	
	public InventoryAdapter(ArrayList<Product> list,Tab_Inventory_Activity pa){
		this.list = list;
		this.pa = pa;
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
			view = pa.getView();
			holder = new Holder();
	
			holder.title = (TextView) view.findViewById(R.id.product_itemName);
			holder.quantity = (TextView) view.findViewById(R.id.product_itemQuantity); 
			view.setTag(holder);
		}
		else {
			holder = (Holder) view.getTag();
		}
		Product p = list.get(position);
		String product_name = p.getName();
		String product_code = p.getProduct_Code();
		int product_quantity = p.getQuantity();
		holder.title.setText(list.get(position).getName() + " <" + product_code + "> ");
		holder.quantity.setText(product_quantity +  " item(s)");
		return view;
	}
	
	// hold text view in each list of item. 
	class Holder{
		
		public TextView title;
		public TextView quantity;
	}
}
