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
import android.widget.TextView;
import android.widget.Toast;

public class Inventory_editItem extends Activity{
    public static StoreController sCT;
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_edit_item);
		
		// call Store Controller
		Database myDb = new Database(this);
	    myDb.getWritableDatabase();
	    sCT = new StoreController(myDb);
	    
	    // get product
		Intent intend = getIntent();
		final String product_code = intend.getExtras().getString("pc");
		Product p = sCT.getProduct(product_code);
		
		// find view
		final EditText quantityTextEdit = (EditText) findViewById(R.id.inventoryEdit_quantity_edittext);
		TextView product_code_textview = (TextView)findViewById(R.id.inventoryEdit_productcode_textview);
		TextView name_textview = (TextView)findViewById(R.id.inventoryEdit_name_textview);
		TextView price_code_textview = (TextView)findViewById(R.id.inventoryEdit_price_textview);
		TextView old_quantity_textview = (TextView)findViewById(R.id.inventoryEdit_oldquantity_textview);
		Button okButton = (Button)findViewById(R.id.inventoryEdit_ok_button);
		Button cancelButton = (Button)findViewById(R.id.inventoryEdit_cancel_button);
		
		// set view
		product_code_textview.setText(p.getProduct_Code());
		name_textview.setText(p.getName());
		price_code_textview.setText(p.getPrice()+"");
		old_quantity_textview.setText(p.getQuantity()+"");
		
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				sCT.setQuantity(product_code,Integer.parseInt(quantityTextEdit.getText().toString()));
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
