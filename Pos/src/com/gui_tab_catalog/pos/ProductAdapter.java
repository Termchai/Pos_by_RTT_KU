package com.gui_tab_catalog.pos;

import java.util.ArrayList;

import com.rtt_ku.pos.R;
import com.rtt_ku.pos.R.id;

import Inventory.Product;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * adapter of listview in product catalog page.
 * @author rtt team
 *
 */
public class ProductAdapter extends BaseAdapter{

	private Holder holder;
	private ArrayList<Product> list;
	private Tab_Product_Activity ac;
	
	
	public ProductAdapter(ArrayList<Product> list,Tab_Product_Activity ac){
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
			
			holder.title = (TextView) view.findViewById(R.id.large_text_item);
			holder.quantity = (TextView) view.findViewById(R.id.large_text_quantity); 
			holder.price = (TextView) view.findViewById(R.id.large_text_price);
			holder.product_code = (TextView) view.findViewById(R.id.large_item_layout_product_code);
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
		holder.product_code.setText("<"+product_code+">");
		holder.title.setText(list.get(position).getName());
		holder.quantity.setText(product_quantity+"");
		holder.price.setText(product_price+"");
		return view;
	}
	
	// hold text view in each list of item. 
	class Holder{
		public TextView title;
		public TextView quantity;
		public TextView price;
		public TextView product_code;
	}
}

