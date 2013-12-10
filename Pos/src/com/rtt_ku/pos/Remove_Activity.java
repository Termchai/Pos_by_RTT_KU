package com.rtt_ku.pos;

import com.database.pos.InventoryDatabase;
import com.gui_tab_inventory.pos.Inventory_editItem;
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
 * activity on remove button. 
 * @author Suttanan
 *
 */
public class Remove_Activity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.remove_layout);
	    
	    // view matching
	    Button okButton = (Button)findViewById(R.id.OK_button);
		
        InventoryDatabase myDb = new InventoryDatabase(this);
        myDb.getWritableDatabase();
		final StoreController sCT = new StoreController(myDb);
		
		// add function on click at OK button.
		okButton.setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(final View v) {
				
				// TODO Auto-generated method stub
				EditText pc = (EditText)findViewById(R.id.pc_text);
				final String product_code = pc.getText().toString();
				if (!sCT.isHasYet(product_code))
				{
					final AlertDialog.Builder dialog_not = new AlertDialog.Builder(
							Remove_Activity.this);

					dialog_not.setTitle("Warning!!!");
					dialog_not.setMessage("Product Code <" + product_code + "> dosen't exist");
					dialog_not.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									
								}
							}).show();
				}
				else
				{
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Remove_Activity.this);

				alertDialogBuilder.setTitle("Remove product");
				alertDialogBuilder
						.setMessage(
								"Do you sure to remove product <"
										+ product_code + ">")
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										sCT.removeProduct(product_code);
										startActivity(new Intent(
												v.getContext(),
												main_activity.class));
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				
				}
				
				
				
//				boolean check = sCT.removeProduct(product_code);
//				String output = "";
				
				
//				if (check) output = "<" + product_code  + "> have been removed";
//				else output = "can't find <" +product_code + "> in inventory";
//		        Toast.makeText(Remove_Activity.this,output,
//		        		Toast.LENGTH_LONG).show();
				
				
//		        if (check) {
//					setContentView(R.layout.activity_main);
//					startActivity(new Intent(Remove_Activity.this, main_activity.class));
//		        }

			}
			
		});
		
	}
}
