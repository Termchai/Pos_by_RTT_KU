package com.rtt_ku.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Remove_Activity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.remove_layout);
	    
	    Button okButton = (Button)findViewById(R.id.OK_button);
		Button cancelButton = (Button)findViewById(R.id.cancel_button);
		
		okButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText pc = (EditText)findViewById(R.id.pc_text);
				EditText name = (EditText)findViewById(R.id.name_text);
				EditText quan = (EditText)findViewById(R.id.quantity_text);
				Toast.makeText(Remove_Activity.this, pc.getText() + " " + name.getText() + " " + quan.getText(), Toast.LENGTH_SHORT).show();
			}
			
		});
		
		cancelButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_main);
				startActivity(new Intent(Remove_Activity.this, main_activity.class));
			}
			
		});
	}
}
