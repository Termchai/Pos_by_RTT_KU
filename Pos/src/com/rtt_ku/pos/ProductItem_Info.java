package com.rtt_ku.pos;

import com.database.pos.Database;
import com.rtt_store.pos.StoreController;

import Inventory.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProductItem_Info extends Activity {

    public static StoreController sCT;
    private Product p; 
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_iteminfo);
		
		Button cancelButton = (Button)findViewById(R.id.productItem_cancel_button);
		Button editButton = (Button)findViewById(R.id.productItem_edit_button);
		
		// call Store Controller
		Database myDb = new Database(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
			
		Intent intent = getIntent();
		String product_code = intent.getExtras().getString("pc");
		p = sCT.getProduct(product_code);
				
				
		// view matching
		TextView id = (TextView)findViewById(R.id.product_itemId);
		TextView name = (TextView)findViewById(R.id.product_item_name);
		TextView type = (TextView)findViewById(R.id.product_item_type);
		TextView price = (TextView)findViewById(R.id.product_itemPrice);
		TextView barcode = (TextView)findViewById(R.id.product_itemBarcode);

				
		setTextView(id,name,type,price,barcode,p);
			
		editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), ProductItem_Edit.class);
				intent.putExtra("pc", p.getProduct_Code());
				startActivity(intent);
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(v.getContext(), main_activity.class));
			}
		});
	}

	private void setTextView(TextView id, TextView name, TextView type,TextView price,TextView barcode, Product p) {
		id.setText(p.getProduct_Code());
		name.setText(p.getName());
		type.setText(p.getType());
		price.setText(p.getPrice()+"");
		barcode.setText(p.getBarcode());
	}
}
