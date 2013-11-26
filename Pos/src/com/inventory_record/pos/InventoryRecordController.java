package com.inventory_record.pos;

public class InventoryRecordController {
	private static InventoryRecordController instance;
	private static InventoryRecord iR;
	private InventoryRecordController(){}
	public static InventoryRecordController getInstance(InventoryRecord database)
	{
		if (instance == null) instance = new InventoryRecordController();
		iR = database;
		return instance;
	}

	public void addInventoryRecord(String product_code, int diff, int cost) {
		iR.InsertData(product_code, diff, cost);
		
	}
	
	
}
