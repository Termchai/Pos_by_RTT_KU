package com.gui_tab_catalog.pos;
import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.main_activity;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Product_AddItemDes extends Activity{
	
	Button okButton;
	Button cancelButton;
	EditText id,name,type,price,barcode;
	StoreController sCT;

	Intent intent;
	Bundle b;
	String product_code;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_add_item);

		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		
		intent = getIntent();
		b = intent.getExtras();
		product_code = (String)b.get("product_code");
		
		okButton = (Button)findViewById(R.id.product_addDes_okButton);
		cancelButton = (Button)findViewById(R.id.product_addDes_cancelButton);
		id = (EditText)findViewById(R.id.product_add_productEditText);
		name = (EditText)findViewById(R.id.product_add_nameEditText);
		type = (EditText)findViewById(R.id.product_add_typeEditText);
		price = (EditText)findViewById(R.id.product_add_priceEditText);
		barcode = (EditText)findViewById(R.id.product_add_barcodeEditText);
		
		id.setText(product_code);
		id.setEnabled(false);
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
				String name_text = name.getText().toString();
				String type_text = type.getText().toString();
				int price_text = Integer.parseInt(price.getText().toString());
				String barcode_text = barcode.getText().toString();
				sCT.addProduct(product_code, name_text, 0, price_text, type_text, "", barcode_text, "", "", "", "", 0);
				onBackPressed();
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
	
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(this, main_activity.class);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
	
	
}