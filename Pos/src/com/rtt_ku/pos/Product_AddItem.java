package com.rtt_ku.pos;
import com.database.pos.Database;
import com.rtt_ku.pos.R;
import com.rtt_store.pos.StoreController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Product_AddItem extends Activity {
	
	private Button okButton,cancelButton ;
	private EditText checkEditText;
	StoreController sCT;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_addbutton);	
		
		Database myDb = new Database(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		
		okButton = (Button)findViewById(R.id.product_add_okButton);
		cancelButton  = (Button)findViewById(R.id.product_add_cancelButton);
		checkEditText = (EditText)findViewById(R.id.product_findId_editText);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String product_code = checkEditText.getText().toString();
				
				if(product_code.equals(""))
				{
					Toast.makeText(v.getContext(),"please enter product code", Toast.LENGTH_SHORT).show();

				}
				else if(!sCT.isHasYet(product_code))
				{
					// not has
//					Toast.makeText(v.getContext(),"Put your fucking hand up", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(v.getContext(), Product_AddItemDes.class);
					intent.putExtra("product_code", product_code);
					startActivity(intent);
				}
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(),main_activity.class));
			}
		});
	}
}
