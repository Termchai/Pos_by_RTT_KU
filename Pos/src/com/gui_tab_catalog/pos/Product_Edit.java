package com.gui_tab_catalog.pos;

import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.main_activity;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;

import Inventory.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * find product code to edit.
 * @author rtt team
 *
 */
public class Product_Edit extends Activity{

	public static StoreController sCT;
    private Product p; 
    private EditText productCode;
    private Button okButton;
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_edit_layout);
		
		// call Store Controller
		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		
		initWidget();
		addButton();
	}

	// add function on click.
	private void addButton() {
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String product_code = productCode.getText().toString();
				Intent intent = new Intent(v.getContext(), ProductItem_Edit.class);
				intent.putExtra("pc", product_code);
				startActivity(intent);
			}
		});
	}

	//view matching.
	private void initWidget() {
		productCode = (EditText)findViewById(R.id.inventory_editEditText);
		okButton = (Button)findViewById(R.id.inventory_edit_okButton);
	}
}
