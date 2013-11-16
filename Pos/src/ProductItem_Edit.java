import com.rtt_ku.pos.ProductItem_Info;
import com.rtt_ku.pos.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class ProductItem_Edit extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_edit_item);
		
		EditText id = (EditText)findViewById(R.id.product_id_editText);
		EditText name = (EditText)findViewById(R.id.product_name_editText);
		EditText quantity = (EditText)findViewById(R.id.product_quantity_editText);
		EditText cost = (EditText)findViewById(R.id.product_cost_editText);
		EditText price = (EditText)findViewById(R.id.product_price_editText);
		
		Button okButton = (Button)findViewById(R.id.product_info_okButton);
		Button cancelButton = (Button)findViewById(R.id.product_info_cancelButton);
		
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
				startActivity(new Intent(v.getContext(), ProductItem_Info.class));
			}
		});
	}
}
