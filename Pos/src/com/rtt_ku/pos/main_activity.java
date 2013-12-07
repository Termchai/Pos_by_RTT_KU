package com.rtt_ku.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.database.pos.InventoryDatabase;
import com.database.pos.InventoryDatabaseController;
import com.database.pos.InventoryDatabaseReader;
import com.gui_tab_catalog.pos.Tab_Product_Activity;
import com.gui_tab_inventory.pos.Tab_Inventory_Activity;
import com.gui_tab_sale.pos.Tab_Sale_Activity;
import com.rtt_store.pos.StoreController;

import Inventory.InventoryAll;
import Inventory.Product;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

/**
 * activity on main layout.
 * @author rtt
 *
 */
public class main_activity extends TabActivity implements OnTabChangeListener{
	
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
        
        
        
        tabSpec2.setIndicator("Sale");
        Intent SaleAc = new Intent(this,Tab_Sale_Activity.class);
        tabSpec2.setContent(SaleAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabSpec2.setContent(SaleAc);
        
        
        tabSpec3.setIndicator("Product");
        Intent ProductAc = new Intent(this,Tab_Product_Activity.class);
        tabSpec3.setContent(ProductAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabSpec3.setContent(ProductAc);
        
        
        //add tab
        myTabHost.addTab(tabSpec1);
        myTabHost.addTab(tabSpec2);
        myTabHost.addTab(tabSpec3);
        
        //Set default tab
//        myTabHost.setCurrentTab(1);
    }
	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		for(int i=0;i<myTabHost.getTabWidget().getChildCount();i++)
		{
			myTabHost.getTabWidget().getChildAt(i).setBackgroundColor(R.drawable.greenbutton);
		}

		myTabHost.getTabWidget().getChildAt(myTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#C35817"));
	}
	
}
