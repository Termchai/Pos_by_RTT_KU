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
    private Button okButton,cancelButton,scanButton;
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
		scanButton = (Button)findViewById(R.id.product_info_scanButton);
		id = (EditText)findViewById(R.id.product_id_editText);
		name = (EditText)findViewById(R.id.product_name_editText);
		type = (EditText)findViewById(R.id.product_type_editText);
		price = (EditText)findViewById(R.id.product_price_editText);
		barcode = (EditText)findViewById(R.id.product_barcode_editText);
		
		setEditText(p);
			
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String product_code_text = id.getText().toString();
				String name_text = name.getText().toString();
				String type_text = type.getText().toString();
				int price_text = Integer.parseInt(price.getText().toString());
				String barcode_text = barcode.getText().toString();
				sCT.setDescription(product_code_text, name_text, type_text, price_text, barcode_text);
				
				
				Intent intent = new Intent(v.getContext(), ProductItem_Info.class);
				intent.putExtra("pc", p.getProduct_Code());
				startActivity(intent);
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
		
		scanButton.setOnClickListener(new OnClickListener() {
 
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    //��˹� intent 㹡�����¡�� Barcode Scanner
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    //�� Mode 㹡�� Scan ���Ѻ ����� Barcode Scanner
                    intent.putExtra("SCAN_MODE", "BAR_CODE_MODE");
                    //����� Activity �ҡ intent ����˹� �¡�˹� requestCode �� 0
                    startActivityForResult(intent, 0);
                } catch (Exception e) {
                    // TODO: handle exception
                    //��������ŧ����� Barcode Scanner �����ʴ���ͤ��� Please Install Barcode Scanner
                    Toast.makeText(getBaseContext(),"Please Install Barcode Scanner",Toast.LENGTH_SHORT).show();
                }
 
            }
        });
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        if (requestCode == 0) //�ӡ�õ�Ǩ�ͺ��� requestCode �ç�Ѻ��� Barcode Scanner �׹������������
        {
            if (resultCode == RESULT_OK) //��� Barcode Scanner �ӧҹ����ó�
            {
                    //�Ѻ�����Ũҡ Barcode Scanner �����ҡ����᡹
                    String contents = intent.getStringExtra("SCAN_RESULT");
                    //�Ѻ�ٻẺ�ҡ Barcode Scanner �����ҡ����᡹ ����繪�Դ�
                    String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                    Toast.makeText(getBaseContext(),contents,Toast.LENGTH_SHORT).show();
                    barcode.setText(contents);
            }
        }
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