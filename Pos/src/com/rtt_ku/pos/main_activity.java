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
import android.app.TabActivity;
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
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

/**
 * activity on main layout.
 * @author rtt
 *
 */
public class main_activity extends TabActivity {
	
	private TabHost myTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //view matching
        myTabHost = (TabHost) findViewById(android.R.id.tabhost);
        
        TabSpec tabSpec1 = myTabHost.newTabSpec("Inventory1");
        TabSpec tabSpec2 = myTabHost.newTabSpec("Sale2");
        TabSpec tabSpec3 = myTabHost.newTabSpec("Product3");
        
        
        tabSpec1.setIndicator("Inventory");
        Intent inventoryAc = new Intent(this,Tab_Inventory_Activity.class);
        tabSpec1.setContent(inventoryAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabSpec1.setContent(inventoryAc);
        myTabHost.addTab(tabSpec1);
        
        
        tabSpec2.setIndicator("Sale");
        Intent SaleAc = new Intent(this,Tab_Sale_Activity.class);
        tabSpec2.setContent(SaleAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabSpec2.setContent(SaleAc);
        myTabHost.addTab(tabSpec2);
        
        tabSpec3.setIndicator("Product");
        Intent ProductAc = new Intent(this,Tab_Product_Activity.class);
        tabSpec3.setContent(ProductAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabSpec3.setContent(ProductAc);
        myTabHost.addTab(tabSpec3);
        
        //Set default tab
       // myTabHost.setCurrentTab(1);
    }
}
