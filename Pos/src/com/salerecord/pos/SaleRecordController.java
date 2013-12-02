package com.salerecord.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Inventory.Product;
import Sale.Basket;
import android.app.Activity;
import android.text.format.Time;

import com.rtt_ku.pos.main_activity;

public class SaleRecordController {
	DatabaseSaleRecord dbSr;
	public SaleRecordController(DatabaseSaleRecord dbSr) {
		this.dbSr = dbSr;
	}
	
	public void insert (Time time)
	{
		String d = time.monthDay+"";
		String m = (time.month)+"";
		String y = time.year+"";
		System.out.println("d : " + d + " m : " + m + " y : " + y);
		ArrayList<Wan> wans = dbSr.SelectAllData();
		for (int i=0; i<wans.size(); i++)
		{
			Wan temp = wans.get(i);
			if (temp.getDay().equals(d))
				if (temp.getMonth().equals(m))
					if (temp.getYear().equals(y))
						return;
		}
		dbSr.InsertData(d, m, y);
	}

	public void confirmSale(Basket basket, DailyRecord dr) {
		Time now = new Time();
		now.setToNow();
		insert(now);
		String temp = "";
		ArrayList<Product> list = basket.getList();
		HashMap<Product,Integer> mapQuan = basket.getMapQuan();
		for (int i=0 ; i<list.size(); i++)
		{
			Product p = list.get(i);
			String product_code = p.getProduct_Code();
			String price = basket.getMapPrice().get(p)+"";
			String quantity = mapQuan.get(p)+"";
			String before = product_code + " " + price + " " + quantity;
			if (temp.equals("")) temp = before;
			else temp = temp + ":" + before;
		}
		
		dr.InsertData(now.hour+"", now.minute+"", temp);
		
	}
	

	public ArrayList<Wan> getListSaleRecord() {
		// TODO Auto-generated method stub
		return dbSr.SelectAllData();
	}
//	public ArrayList<Wan> getDaily()
//	{
//		Set<Wan> set = new HashSet<Wan>();
//		ArrayList<Wan> list = getListSaleRecord();
//		for (int i=0; i<list.size(); i++)
//		{
//			
//		}
//	}

}
