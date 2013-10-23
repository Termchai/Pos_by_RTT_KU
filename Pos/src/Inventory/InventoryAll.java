package Inventory;

public class InventoryAll extends AbstractInventory{
	private static InventoryAll instance;
	private InventoryAll() {
		super();
	}
	
	public static InventoryAll getInstance(){
		if(instance==null){
			instance = new InventoryAll();
		}
		return instance;
	}
	
	
	
}
