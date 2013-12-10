package com.gui_tab_inventory.pos;

import java.util.ArrayList;
import java.util.Date;

import com.database.pos.InventoryDatabase;
import com.database.pos.InventoryDatabaseReader;
import com.gui_report_inventory.pos.Inventory_Report;
import com.gui_tab_catalog.pos.ProductAdapter;
import com.gui_tab_catalog.pos.Tab_Product_Activity;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.Remove_Activity;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.SaleRecordDateDatabase;

import Inventory.Product;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * main of tab inventory.
 * 
 * @author rtt team
 * 
 */
public class Tab_Inventory_Activity extends Activity {

	// SQLiteDatabase db;
	InventoryDatabaseReader databaseReader;
	// list item
	private ArrayList<Product> productList = new ArrayList<Product>();
	public static StoreController sCT;

	private ArrayAdapter<String> listAdapter;
	private ListView list_item;
	private Button removeButton;
	private Button editButton;
	private EditText editText;
	private Button addButton;
	private Button reportButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_tab);

		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		Time now = new Time();
		now.setToNow();
		SaleRecordDateDatabase DbSr = new SaleRecordDateDatabase(this);
		DbSr.getWritableDatabase();
		sCT = new StoreController(myDb, DbSr);
		sCT.updateInventory();

		productList = sCT.getProductList();

		initWidget();

		// adapter of list item.
		System.out.println(productList);
		editText.setHint("search");

		addFindByPartial();
		addButton();
	}

	// add function on click.
	private void addButton() {
		reportButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(), Inventory_Report.class));
			}
		});

		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(), Inventory_Edit.class));
			}
		});

		// add function on click at remove button.
//		removeButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(Tab_Inventory_Activity.this,
//						Remove_Activity.class));
//			}
//		});

		list_item.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Tab_Inventory_Activity.this,
						Inventory_editItem.class);
				Product p = sCT.getProductList().get(position);
				intent.putExtra("pc", p.getProduct_Code());
				startActivity(intent);
			}
		});
	}

	// add find by partial on editText.
	private void addFindByPartial() {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				String text = editText.getText().toString();
				productList = sCT.getProductListByPartial(text);
				list_item.setAdapter(new InventoryAdapter(productList,
						Tab_Inventory_Activity.this));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});
	}

	// view matching.
	private void initWidget() {
		removeButton = (Button) findViewById(R.id.set_quantity_ok_button);
		editButton = (Button) findViewById(R.id.Inventort_Tab_Edit_Button);
		editText = (EditText) findViewById(R.id.inventory_editText);
		list_item = (ListView) findViewById(R.id.inventory_listView);
		reportButton = (Button) findViewById(R.id.Inventort_Tab_reportButton);
	}

	@Override
	public void onResume() {
		productList = sCT.getProductList();
		list_item.setAdapter(new InventoryAdapter(productList, this));
		super.onResume();
	}

	// send view to adapter.
	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.product_item, null);
		return view;
	}

}
