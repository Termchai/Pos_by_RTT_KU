package com.gui_tab_catalog.pos;

import java.util.ArrayList;

import com.database.pos.InventoryDatabase;
import com.database.pos.InventoryDatabaseReader;
import com.rtt_ku.pos.Add_Activity;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.Remove_Activity;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;

import Inventory.Product;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * main page of product catalog page.
 * 
 * @author rtt team
 * 
 */
public class Tab_Product_Activity extends Activity {

	// SQLiteDatabase db;
	private InventoryDatabaseReader databaseReader;

	private StoreController sCT;

	private ArrayList<Product> productList = new ArrayList<Product>();
	private Button addButton;
	private Button removeButton;
	private Button editButton;
	private EditText editText;
	private ListView listview;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_tab);

		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		productList = sCT.getProductList();

		iniWidget();
		editText.setHint("search");
		addButton();
		addFindByPartial();
	}

	// add find by partial on editText.
	private void addFindByPartial() {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String text = editText.getText().toString();
				productList = sCT.getProductListByPartial(text);
				listview.setAdapter(new ProductAdapter(productList,
						Tab_Product_Activity.this));

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {}

		});
	}

	// add function on click.
	private void addButton() {
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Tab_Product_Activity.this,
						ProductItem_Info.class);
				Product p = sCT.getProductList().get(position);
				intent.putExtra("pc", p.getProduct_Code());
				startActivity(intent);
			}
		});

		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), Product_AddItem.class));
			}
		});

		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), Product_Edit.class));
			}
		});

		removeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), Remove_Activity.class));
			}
		});
	}

	// view matching.
	private void iniWidget() {
		addButton = (Button) findViewById(R.id.product_addButton);
		removeButton = (Button) findViewById(R.id.product_removeButton);
		editButton = (Button) findViewById(R.id.product_editButton);
		editText = (EditText) findViewById(R.id.product_editText);
		listview = (ListView) findViewById(R.id.product_listView);
	}

	@Override
	public void onResume() {
		productList = sCT.getProductList();
		listview.setAdapter(new ProductAdapter(productList, this));
		super.onResume();
	}

	// send view of line item to adapter.
	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.large_item_layout, null);
		return view;
	}
}
