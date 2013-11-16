package com.rtt_ku.pos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OnClickChangeView extends Activity implements OnClickListener {

	private Intent intent;
	private Context thisPage;
	private Class<?> nextPage;
	
	public OnClickChangeView(Context thisPage, Intent intent){
		this.thisPage = thisPage;
		this.intent = intent;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(thisPage,"fuck off", Toast.LENGTH_SHORT).show();
	}
}
