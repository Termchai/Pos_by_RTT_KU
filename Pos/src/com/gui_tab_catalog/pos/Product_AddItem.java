package com.gui_tab_catalog.pos;
import com.database.pos.InventoryDatabase;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.main_activity;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * add item in product catalog.
 * @author rtt team
 *
 */
public class Product_AddItem extends Activity {
	
	private Button okButton,cancelButton ;
	private EditText checkEditText;
	StoreController sCT;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_addbutton);	
		
		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		
		iniwidget();
		addButton();
		
	}
	
	// add function on click.
	private void addButton() {
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
	}

	// view matching.
	private void iniwidget() {
		okButton = (Button)findViewById(R.id.product_add_okButton);
		checkEditText = (EditText)findViewById(R.id.product_findId_editText);
	}

	@Override
	public void onResume(){
		checkEditText.setText("");
		super.onResume();
	}
}
