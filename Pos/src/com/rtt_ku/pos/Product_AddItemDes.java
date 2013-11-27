package com.rtt_ku.pos;
import com.rtt_ku.pos.R;

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
	EditText id;
	EditText name;
	EditText type;
	EditText price;
	EditText barcode;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_add_item);
		
		okButton = (Button)findViewById(R.id.product_addDes_okButton);
		cancelButton = (Button)findViewById(R.id.product_addDes_cancelButton);
		id = (EditText)findViewById(R.id.product_add_productEditText);
		name = (EditText)findViewById(R.id.product_add_nameEditText);
		type = (EditText)findViewById(R.id.product_add_typeEditText);
		price = (EditText)findViewById(R.id.product_add_priceEditText);
		barcode = (EditText)findViewById(R.id.product_add_barcodeEditText);
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				id.setEnabled(false);
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
	
}