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
import android.widget.EditText;
import android.widget.Toast;

public class Inventory_editItem extends Activity{
    public static StoreController sCT;
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_edit_item);
		
		Database myDb = new Database(this);
	    myDb.getWritableDatabase();
	    sCT = new StoreController(myDb);
		Intent intend = getIntent();
		String product_code = intend.getExtras().getString("pc");
		Product p = sCT.getProduct(product_code);
		Toast.makeText(Inventory_editItem.this,p.getProduct_Code(), Toast.LENGTH_SHORT).show();
		EditText quantityTextEdit = (EditText) findViewById(R.id.inventoryEdit_quantity_edittext);
		Button okButton = (Button)findViewById(R.id.inventoryEdit_ok_button);
		Button cancelButton = (Button)findViewById(R.id.inventoryEdit_cancel_button);
		
		
		quantityTextEdit.setText((p.getQuantity()+""));
		
		
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				startActivity(new Intent(v.getContext(), main_activity.class));
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
