package restaurant;

public class OrderedItem{
	public FoodItem orderedItem;
	int customerID;
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
