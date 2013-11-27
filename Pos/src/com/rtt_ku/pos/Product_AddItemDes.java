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
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_add_item);
		
		Button okButton = (Button)findViewById(R.id.product_addDes_okButton);
		Button cancelButton = (Button)findViewById(R.id.product_addDes_cancelButton);
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText id = (EditText)findViewById(R.id.product_add_productEditText);
				EditText name = (EditText)findViewById(R.id.product_add_nameEditText);
				EditText type = (EditText)findViewById(R.id.product_add_typeEditText);
				EditText price = (EditText)findViewById(R.id.product_add_priceEditText);
				EditText barcode = (EditText)findViewById(R.id.product_add_barcodeEditText);
				
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