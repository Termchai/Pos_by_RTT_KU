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
import android.widget.TextView;

/**
 * Information of item.
 * @author rtt team
 *
 */
public class ProductItem_Info extends Activity {

    public static StoreController sCT;
    private Product p; 
    private Button editButton;
    private TextView id;
	private TextView name;
	private TextView type;
	private TextView price;
	private TextView barcode;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_iteminfo);
		
		editButton = (Button)findViewById(R.id.productItem_edit_button);
		
		// call Store Controller
		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
			
		Intent intent = getIntent();
		String product_code = intent.getExtras().getString("pc");
		p = sCT.getProduct(product_code);
				
		initWidget();
		setTextView(p);
		addButton();
		
	}

	// add function on click.
	private void addButton() {	
		editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), ProductItem_Edit.class);
				intent.putExtra("pc", p.getProduct_Code());
				startActivity(intent);
			}
		});
	}

	// view matching.
	private void initWidget() {
		id = (TextView)findViewById(R.id.product_itemId);
		name = (TextView)findViewById(R.id.product_item_name);
		type = (TextView)findViewById(R.id.product_item_type);
		price = (TextView)findViewById(R.id.product_itemPrice);
		barcode = (TextView)findViewById(R.id.product_itemBarcode);
	}

	// set text of item information.
	private void setTextView(Product p) {
		id.setText(p.getProduct_Code());
		name.setText(p.getName());
		type.setText(p.getType());
		price.setText(p.getPrice()+"");
		barcode.setText(p.getBarcode());
	}
}
