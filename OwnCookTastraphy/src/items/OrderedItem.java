package items;

public class OrderedItem{
	public FoodItem orderedItem;
	public int customerID;
	public long timeOrdered;
	public long timeReady;
	
	OrderedItem(FoodItem item, int customerID)
	{
		orderedItem = item;
		this.customerID = customerID;
	}
	
	public void startItem()
	{
		timeOrdered = System.currentTimeMillis();
		timeReady = timeOrdered + orderedItem.prepTime;
	}
}
