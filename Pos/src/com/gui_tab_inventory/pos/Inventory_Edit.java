package com.gui_tab_inventory.pos;

import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.main_activity;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * find product code to edit information from inventory tab.
 * @author rtt team
 *
 */
public class Inventory_Edit extends Activity{
	StoreController sCT;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_edit_layout);
		

		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		
		Button okButton = (Button)findViewById(R.id.inventory_edit_okButton);
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try
				{
					// TODO Auto-generated method stub
					EditText productCode = (EditText)findViewById(R.id.inventory_editEditText);
					String product_code = productCode.getText().toString();
					if (sCT.isHasYet(product_code))
					{
						Intent intent = new Intent(v.getContext(), Inventory_editItem.class);
						intent.putExtra("pc", product_code);
						startActivity(intent);
					}
					else
					{
						final AlertDialog.Builder dialog_not = new AlertDialog.Builder(Inventory_Edit.this);
						
						dialog_not.setTitle("Warning!!!");
						dialog_not.setMessage("Product <" + product_code + "> dose not exist");
							dialog_not.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								
							}
						}).show();
					}
				}catch(Exception e) {}
			}
		});
		
	}
}
