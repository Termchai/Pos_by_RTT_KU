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


public class ProductItem_Edit extends Activity{

    public static StoreController sCT;
    private Button okButton;
    private Button cancelButton; 
    private EditText id,name,type,price,barcode;
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_edit_item);
		
		// call Store Controller
		Database myDb = new Database(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		
		Intent intend = getIntent();
		final String product_code = intend.getExtras().getString("pc");
		final Product p = sCT.getProduct(product_code);
		
		okButton = (Button)findViewById(R.id.product_info_okButton);
		cancelButton = (Button)findViewById(R.id.product_info_cancelButton);
		id = (EditText)findViewById(R.id.product_id_editText);
		name = (EditText)findViewById(R.id.product_name_editText);
		type = (EditText)findViewById(R.id.product_type_editText);
		price = (EditText)findViewById(R.id.product_price_editText);
		barcode = (EditText)findViewById(R.id.product_barcode_editText);
		
		setEditText(p);
			
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), ProductItem_Info.class);
				intent.putExtra("pc", p.getProduct_Code());
				startActivity(intent);
			}
		});
	}
	
	private void setEditText(Product p){
		id.setText(p.getProduct_Code());
		id.setEnabled(false);
		name.setText(p.getName());
//		cost.setText(p.getCost());
		price.setText(p.getPrice()+"");
		type.setText(p.getType());
		barcode.setText(p.getBarcode());
	}
}