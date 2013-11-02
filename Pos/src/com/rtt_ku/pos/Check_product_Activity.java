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
import android.widget.TextView;
import android.widget.Toast;

public class Check_product_Activity extends Activity {

	StoreController sCT;

	TextView addTextView;
	EditText editText;
	Button setQuantityButton;
	String product_code;
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_id_layout);

		Database myDb = new Database(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		;

		// view matching
		Button okButton = (Button) findViewById(R.id.button1);
		Button cancelButton = (Button) findViewById(R.id.button2);
		addTextView = (TextView)findViewById(R.id.set_quantity_textView1);
		editText = (EditText) findViewById(R.id.set_quantity_editText);
		setQuantityButton = (Button) findViewById(R.id.set_quantity_ok_button);
		
		editText.setVisibility(View.INVISIBLE);
		addTextView.setVisibility(View.INVISIBLE);
		setQuantityButton.setVisibility(View.INVISIBLE);
		
		// add function on click at OK button.
		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				EditText pc = (EditText) findViewById(R.id.pc_text);
				
				product_code = pc.getText().toString();
				if(product_code.equals(""))
				{
					Toast.makeText(Check_product_Activity.this,"please enter product code", Toast.LENGTH_SHORT).show();

				}
				else if(!sCT.isHasYet(product_code))
				{
					// not has
//					Toast.makeText(Check_product_Activity.this,"Put your fucking hand up", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(Check_product_Activity.this, Add_Activity.class);
					intent.putExtra("pc", product_code);
					startActivity(intent);
				}
				else
				{
					// has
					Toast.makeText(Check_product_Activity.this,"This product id has already", Toast.LENGTH_SHORT).show();
					pc.setEnabled(false);
					editText.setVisibility(View.VISIBLE);
					addTextView.setVisibility(View.VISIBLE);
					setQuantityButton.setVisibility(View.VISIBLE);
					
					
					
					setQuantityButton.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							//setContentView(R.layout.activity_main);
							try
							{
							String quantity = editText.getText().toString();
							sCT.addQuantity(product_code, Integer.parseInt(quantity));
							startActivity(new Intent(Check_product_Activity.this,main_activity.class));
							}catch(Exception e) 
							{
								final AlertDialog.Builder dialog_Limit = new AlertDialog.Builder(Check_product_Activity.this);
								
								dialog_Limit.setTitle("Warning!!!");
								dialog_Limit.setMessage("You have to input quantity in numbers");
									dialog_Limit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
									@Override
									public void onClick(DialogInterface arg0, int arg1) {
										// TODO Auto-generated method stub
									}
								}).show();
							}
						}
						
					});
					
				}
			}

		});

		// add function on click at cancel button.
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// setContentView(R.layout.activity_main);
				startActivity(new Intent(Check_product_Activity.this,
						main_activity.class));
			}

		});
	}
}
