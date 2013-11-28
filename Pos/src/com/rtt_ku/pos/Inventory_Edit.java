package com.rtt_ku.pos;

import com.database.pos.Database;
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

public class Inventory_Edit extends Activity{
	StoreController sCT;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_edit_layout);
		

		Database myDb = new Database(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		
		Button okButton = (Button)findViewById(R.id.inventory_edit_okButton);
		Button cancelButton = (Button)findViewById(R.id.inventory_edit_cancelButton);
		
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
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), main_activity.class));
			}
		});
	}
}
