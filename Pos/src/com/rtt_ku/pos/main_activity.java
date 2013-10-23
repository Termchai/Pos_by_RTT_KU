package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.List;

import com.database.pos.Database;
import com.database.pos.DatabaseController;
import com.database.pos.DatabaseReader;
import com.rtt_ku.pos.Sale_Activity.MyAdapter;
import com.rtt_ku.pos.Sale_Activity.MyAdapter.Holder;
import com.rtt_store.pos.StoreController;

import Inventory.InventoryAll;
import Inventory.Product;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * activity on main layout.
 * @author rtt
 *
 */
public class main_activity extends Activity {
	
	private TabHost myTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //view matching
        myTabHost = (TabHost) findViewById(android.R.id.tabhost);
        myTabHost.setup();
        
        TabHost.TabSpec tabSpec;
        tabSpec = myTabHost.newTabSpec("Inventory");
        tabSpec.setIndicator("Inventory");
        tabSpec.setContent(R.id.tab1_ref);
        myTabHost.addTab(tabSpec);
        
        tabSpec = myTabHost.newTabSpec("Sale");
        tabSpec.setIndicator("Sale");
        tabSpec.setContent(R.id.tab2_ref);
        myTabHost.addTab(tabSpec);
        
        tabSpec = myTabHost.newTabSpec("Product");
        tabSpec.setIndicator("Product");
        tabSpec.setContent(R.id.tab3_ref);
        myTabHost.addTab(tabSpec);
        
        //Set default tab
        myTabHost.setCurrentTab(1);
    }
}
