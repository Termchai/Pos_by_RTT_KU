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


public class Product_AddItemDes extends Activity{
	
	Button okButton;
	Button cancelButton;
	Button scanButton;
	EditText id,name,type,price,barcode;
	StoreController sCT;

	Intent intent;
	Bundle b;
	String product_code;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_add_item);

		InventoryDatabase myDb = new InventoryDatabase(this);
		myDb.getWritableDatabase();
		sCT = new StoreController(myDb);
		
		intent = getIntent();
		b = intent.getExtras();
		product_code = (String)b.get("product_code");
		
		okButton = (Button)findViewById(R.id.product_addDes_okButton);
		scanButton = (Button)findViewById(R.id.product_addDes_scanButton);
		id = (EditText)findViewById(R.id.product_add_productEditText);
		name = (EditText)findViewById(R.id.product_add_nameEditText);
		type = (EditText)findViewById(R.id.product_add_typeEditText);
		price = (EditText)findViewById(R.id.product_add_priceEditText);
		barcode = (EditText)findViewById(R.id.product_add_barcodeEditText);
		
		id.setText(product_code);
		id.setEnabled(false);
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
				String name_text = name.getText().toString();
				String type_text = type.getText().toString();
				int price_text = Integer.parseInt(price.getText().toString());
				String barcode_text = barcode.getText().toString();
				sCT.addProduct(product_code, name_text, 0, price_text, type_text, "", barcode_text, "", "", "", "", 0);
				onBackPressed();
			}
		});
		
		
		scanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
                    //กำหนด intent ในการเรียกใช้ Barcode Scanner
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    //ส่ง Mode ในการ Scan ให้กับ โปรแกรม Barcode Scanner
                    intent.putExtra("SCAN_MODE", "BAR_CODE_MODE");
                    //เริ่ม Activity จาก intent ที่กำหนด โดยกำหนด requestCode เป็น 0
                    startActivityForResult(intent, 0);
                } catch (Exception e) {
                    // TODO: handle exception
                    //ถ้าไม่ได้ลงโปรแกรม Barcode Scanner ไว้จะแสดงข้อความ Please Install Barcode Scanner
                    Toast.makeText(getBaseContext(),"Please Install Barcode Scanner",Toast.LENGTH_SHORT).show();
                }
 
            }
        });
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        if (requestCode == 0) //ทำการตรวจสอบว่า requestCode ตรงกับที่ Barcode Scanner คืนค่ามาหรือไม่
        {
            if (resultCode == RESULT_OK) //ถ้า Barcode Scanner ทำงานสมบูรณ์
            {
                    //รับข้อมูลจาก Barcode Scanner ที่ได้จากการสแกน
                    String contents = intent.getStringExtra("SCAN_RESULT");
                    //รับรูปแบบจาก Barcode Scanner ที่ได้จากการสแกน ว่าเป็นชนิดใด
                    String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                    Toast.makeText(getBaseContext(),contents,Toast.LENGTH_SHORT).show();
                    barcode.setText(contents);
            }
        }
    }
	
	@Override
	public void onBackPressed(){
		finish();
		super.onBackPressed();
	}
	
	
}