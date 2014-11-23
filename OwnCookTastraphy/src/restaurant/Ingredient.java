package restaurant;

public class Ingredient {
	String ingredientName;
	int amount;
	long timeToOrderMore;
	int amountInOrder;

	Ingredient(String name, int amount, long timeToOrderMore, int amountInOrder)
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
