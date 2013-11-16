package com.rtt_ku.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Inventory_Info extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_iteminfo);
		
		Button addButton = (Button)findViewById(R.id.inventoryItem_add_button);
		Button editButton = (Button)findViewById(R.id.inventoryItem_edit_button);
		Button cancelButton = (Button)findViewById(R.id.inventoryItem_cancel_button);
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Inventory_Info.this, Tab_Inventory_Activity.class));
			}
		});
	}
}
