package com.rtt_ku.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Inventory_Edit extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_edit_layout);
		
		Button okButton = (Button)findViewById(R.id.inventory_edit_okButton);
		Button cancelButton = (Button)findViewById(R.id.inventory_edit_cancelButton);
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText productCode = (EditText)findViewById(R.id.inventory_editEditText);
				String product_code = productCode.getText().toString();
				
				Intent intent = new Intent(v.getContext(), Inventory_editItem.class);
				intent.putExtra("pc", product_code);
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
}
