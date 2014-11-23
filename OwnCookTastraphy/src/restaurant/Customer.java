package restaurant;

public class Customer {
	static int nextID;
	public int customerID;
	String customerName;
	public long timeToAppear;
	public OrderedItem expectedItem;
	
	public Customer(String name, FoodItem orderedItem, long timeToArrive)
	{
		customerID = nextID;
		nextID++;
		customerName = name;
		expectedItem = new OrderedItem(orderedItem, customerID);
		this.timeToAppear = timeToArrive;
	}
	
	void orderFood(FoodItem order)
	{
		expectedItem = new OrderedItem(order, customerID);
	}
}
