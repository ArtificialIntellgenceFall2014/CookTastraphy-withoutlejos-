package items;

public class Ingredient {
	public String ingredientName;
	public int amount;
	public long timeToOrderMore;
	public int amountInOrder;
	
	public long arrivalTime;

	public Ingredient(String name, int amount, long timeToOrderMore, int amountInOrder)
	{
		ingredientName = name;
		this.amount = amount;
		this.timeToOrderMore = timeToOrderMore;
		this.amountInOrder = amountInOrder;
	}
	
	void setAmount(int amount)
	{
		this.amount = amount;
	}
}
