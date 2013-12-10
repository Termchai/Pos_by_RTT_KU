package com.gui_tab_sale.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.database.pos.InventoryDatabase;
import com.database.pos.InventoryDatabaseReader;
import com.gui_report_sale.pos.Sale_ReportDaily_ChooseDate;
import com.gui_report_sale.pos.Sale_ReportMonthly_ChooseDate;
import com.gui_report_sale.pos.Sale_ReportYearly_ChooseDate;
import com.gui_tab_catalog.pos.ProductAdapter;
import com.gui_tab_catalog.pos.Tab_Product_Activity;
import com.rtt_ku.pos.R;
import com.rtt_ku.pos.R.id;
import com.rtt_ku.pos.R.layout;
import com.rtt_ku.pos.R.menu;
//import com.rtt_ku.pos.Tab_Inventory_Activity.MyAdapter;
//import com.rtt_ku.pos.Tab_Inventory_Activity.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;
import com.salerecord.pos.DailyRecord;
import com.salerecord.pos.SaleRecordDateDatabase;

import Inventory.Product;
import Sale.Sale;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * main of tab sale page.
 * 
 * @author rtt team
 * 
 */
public class Tab_Sale_Activity extends Activity {

	// SQLiteDatabase db;
	InventoryDatabaseReader databaseReader;
	// list item
	ArrayList<Product> productList = new ArrayList<Product>();
	public static StoreController sCT;
	private Context context = this;

	private ArrayAdapter<String> listAdapter;
	private ListView list_item;
	private ListView list_sale_item;
	private Sale basket;
	private SaleItemAdapter saleAdapter;
	private TextView total_text;
	private EditText cash;
	private Button ok_button, reset_button;
	private MyAdapter adapter;
	private InventoryDatabase myDb;
	private Button report_button;
	private EditText editText;

	private Dialog dialog;
	private Dialog reportDialog;

	private Button reportDaily;
	private Button reportWeekly;
	private Button reportYear;
	private Button reportBack;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_tab);

		myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		SaleRecordDateDatabase DbSr = new SaleRecordDateDatabase(this);
		DbSr.getWritableDatabase();
		sCT = new StoreController(myDb, DbSr);
		productList = sCT.getProductList();
		basket = sCT.newBasket();

		initWidget();

		editText.setHint("search");
		cash.setHint("input cash");

		// adapter of list item.
		saleAdapter = new SaleItemAdapter();

		list_sale_item.setAdapter(saleAdapter);

		addButton();

		addFindByPartial();
		
	}

	// add function on click.
	private void addButton() {
		addOkButton();
		addResetButton();
		addReportButton();
		addOnClickListItem();
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
				adapter = new MyAdapter();
				list_item.setAdapter(adapter);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});
	}

	// add function on click in each item.
	private void addOnClickListItem() {

		list_item.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				dialog = new Dialog(context);
				dialog.setContentView(R.layout.sale_dialog);

				final Product product = productList.get(position);
				dialog.setTitle(product.getName());

				TextView name = (TextView) dialog
						.findViewById(R.id.sale_dialogtextView);
				final EditText quantity = (EditText) dialog
						.findViewById(R.id.saleDialog_editText);
				Button dialogOkButton = (Button) dialog
						.findViewById(R.id.saleDialog_okButton);
				Button dialogEditButton = (Button) dialog
						.findViewById(R.id.saleDialog_editButton);

				name.setText(product.getName());
				quantity.setText("1");

				dialogOkButton.setOnClickListener(new OnClickListener() {
					// input only quantity
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try {
							int quan = Integer.parseInt(quantity.getText()
									.toString());
							if (quan > 0) {
								System.out.println("Testttttttttttttttttttt");
								basket.addProduct(product, quan);
								saleAdapter.notifyDataSetChanged();
								total_text.setText(basket.getTotalPrice() + "");
								editText.setText("");
							}
						} catch (Exception e) {

						}
						dialog.dismiss();

					}
				});

				dialogEditButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						final Dialog dialogDiscount = new Dialog(context);
						dialogDiscount
								.setContentView(R.layout.sale_dialog_discount);

						dialogDiscount.setTitle(product.getName());

						TextView nameItem = (TextView) dialogDiscount
								.findViewById(R.id.sale_dialog_nameTextView);
						final EditText quan = (EditText) dialogDiscount
								.findViewById(R.id.sale_dialog_quantityEditText);
						final EditText price = (EditText) dialogDiscount
								.findViewById(R.id.sale_dialog_priceEditText);
						Button okButton = (Button) dialogDiscount
								.findViewById(R.id.sale_dialog_okButton);
						quan.setText("1");
						price.setText(product.getPrice() + "");
						nameItem.setText(product.getName());
						// edit price,quantity
						okButton.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub

								try {
									int tempQuan = Integer.parseInt(quan
											.getText().toString());

									basket.addProduct(product, tempQuan);
									basket.setPrice(product, Integer
											.parseInt(price.getText()
													.toString()));

									saleAdapter.notifyDataSetChanged();
									total_text.setText(basket.getTotalPrice()
											+ "");
									editText.setText("");
								} catch (Exception e) {

								}
								dialog.dismiss();
								dialogDiscount.dismiss();
							}
						});

						dialogDiscount.show();
					}
				});
				dialog.show();
			}
		});
	}

	// add function on click at report button.
	private void addReportButton() {
		report_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reportDialog = new Dialog(context);
				reportDialog.setContentView(R.layout.sale_report);

				reportDialog.setTitle("Report");

				reportDaily = (Button) reportDialog
						.findViewById(R.id.sale_repot_dailybutton);
				reportWeekly = (Button) reportDialog
						.findViewById(R.id.sale_report_monthlyButton);
				reportYear = (Button) reportDialog
						.findViewById(R.id.sale_report_yearButton);

				setReportAction();
				reportDialog.show();
			}
		});
	}

	// add function on click at reset button.
	private void addResetButton() {
		reset_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				basket = sCT.newBasket();
				saleAdapter.notifyDataSetChanged();
				total_text.setText("0.0");
				cash.setText("");
			}
		});
	}

	// add function on click at oj button.
	private void addOkButton() {
		ok_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					double cashh = Double
							.parseDouble(cash.getText().toString());
					double change = cashh - basket.getTotalPrice();
					Toast.makeText(Tab_Sale_Activity.this, change + "",
							Toast.LENGTH_SHORT).show();

					if (basket.getList().size() == 0) {
						final AlertDialog.Builder dialog_not = new AlertDialog.Builder(
								Tab_Sale_Activity.this);

						dialog_not.setTitle("Warning!!!");
						dialog_not.setMessage("Plese insert product to basket");
						dialog_not.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
									}
								}).show();
					}

					else if (cashh >= basket.getTotalPrice()) {

						final AlertDialog.Builder dialog_change = new AlertDialog.Builder(
								Tab_Sale_Activity.this);

						dialog_change.setTitle("Change");
						dialog_change.setMessage("Total price = "
								+ basket.getTotalPrice() + "\nCash = "
								+ cash.getText().toString() + "\nChange = "
								+ change);
						dialog_change.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
										Time now = new Time();
										now.setToNow();
										DailyRecord dr = new DailyRecord(
												Tab_Sale_Activity.this, now);
										sCT.confirmSale(basket, dr);
										sCT.setDB(myDb);
										sCT.updateInventory();
										productList = sCT.getProductList();
										adapter.notifyDataSetChanged();

										basket = sCT.newBasket();
										saleAdapter.notifyDataSetChanged();
										total_text.setText("0.0");
										cash.setText("");

									}
								}).show();

					}

					else {
						final AlertDialog.Builder dialog_not = new AlertDialog.Builder(
								Tab_Sale_Activity.this);

						dialog_not.setTitle("Warning!!!");
						dialog_not.setMessage("Not enough money");
						dialog_not.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
									}
								}).show();
					}

				} catch (Exception e) {
					final AlertDialog.Builder dialog_Limit = new AlertDialog.Builder(
							Tab_Sale_Activity.this);

					dialog_Limit.setTitle("Warning!!!");
					dialog_Limit.setMessage("Please enter amount of cash");
					dialog_Limit.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
								}
							}).show();

				}
			}
		});
	}

	// view matching
	private void initWidget() {

		list_item = (ListView) findViewById(R.id.listView2);
		list_sale_item = (ListView) findViewById(R.id.inventory_listView);
		total_text = (TextView) findViewById(R.id.sale_report_productTextView);
		cash = (EditText) findViewById(R.id.set_quantity_editText);
		ok_button = (Button) findViewById(R.id.productItem_add_button);
		reset_button = (Button) findViewById(R.id.productItem_edit_button);
		report_button = (Button) findViewById(R.id.sale_report_button);
		editText = (EditText) findViewById(R.id.sale_editText);
	}

	// update list item
	public void update() {
		list_sale_item.setAdapter(new SaleItemAdapter());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pos__ui, menu);
		return true;
	}

	// inner class to adapter holder with listview.
	class MyAdapter extends BaseAdapter {
		private Holder holder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return productList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		public String getName() {
			return holder.title.toString();
		}

		public String getPrice() {
			return holder.price.toString();
		}

		public String getQuantity() {
			return holder.quantity.toString();
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (view == null) {
				view = LayoutInflater.from(getApplicationContext()).inflate(
						R.layout.item_layout, null);
				holder = new Holder();

				holder.title = (TextView) view.findViewById(R.id.text_item);
				holder.quantity = (TextView) view
						.findViewById(R.id.text_quantity);
				holder.price = (TextView) view.findViewById(R.id.text_price);
				view.setTag(holder);
			} else {
				holder = (Holder) view.getTag();
			}

			Product p = productList.get(position);
			String product_name = p.getName();
			String product_code = p.getProduct_Code();
			int product_quantity = p.getQuantity();
			int product_price = p.getPrice();
			holder.title.setText(productList.get(position).getName() + " <"
					+ product_code + "> ");
			holder.quantity.setText(product_quantity + " item(s)");
			holder.price.setText(product_price + "");
			return view;
		}

		// hold text view in each list of item.
		class Holder {

			public TextView title;
			public TextView quantity;
			public TextView price;
		}
	}

	class SaleItemAdapter extends BaseAdapter {
		private Holder holder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return basket.getList().size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (view == null) {
				view = LayoutInflater.from(getApplicationContext()).inflate(
						R.layout.large_item_layout, null);
				holder = new Holder();

				holder.code = (TextView) view
						.findViewById(R.id.large_item_layout_product_code);
				holder.title = (TextView) view
						.findViewById(R.id.large_text_item);
				holder.quantity = (TextView) view
						.findViewById(R.id.large_text_quantity);
				holder.price = (TextView) view
						.findViewById(R.id.large_text_price);
				view.setTag(holder);
			} else {
				holder = (Holder) view.getTag();
			}

			//
			ArrayList<Product> list = basket.getList();

			Product p = list.get(position);
			String product_name = p.getName();
			String product_code = p.getProduct_Code();
			int product_quantity = basket.getMapQuan().get(p);
			int product_price = basket.getMapPrice().get(p);
			holder.code.setText(" <" + product_code + "> ");
			holder.title.setText(product_name);
			holder.quantity.setText(product_quantity + " item(s)");
			holder.price.setText(product_price + "");
			return view;
		}

		// hold text view in each list of item.
		class Holder {

			public TextView code;
			public TextView title;
			public TextView quantity;
			public TextView price;
		}
	}

	public View getView() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.item_layout, null);
		return view;
	}

	// set action on each button in dialog box from report button.
	public void setReportAction() {
		reportDaily.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(),
						Sale_ReportDaily_ChooseDate.class));
			}
		});

		reportWeekly.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(),
						Sale_ReportMonthly_ChooseDate.class));
			}
		});

		reportYear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(v.getContext(),
						Sale_ReportYearly_ChooseDate.class));
			}
		});
	}

	@Override
	public void onResume() {
		productList = sCT.getProductList();
		adapter = new MyAdapter();
		list_item.setAdapter(adapter);
		super.onResume();
	}
}
