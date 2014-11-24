package items;

public class OrderedItem{
	public FoodItem orderedItem;
	public int customerID;
	long timeOrdered;
	long timeDeadline;
	
	OrderedItem(FoodItem item, int customerID)
	{
		orderedItem = item;
		this.customerID = customerID;
		timeOrdered = System.currentTimeMillis();
		timeDeadline = timeOrdered + orderedItem.prepTime;
	}
}
