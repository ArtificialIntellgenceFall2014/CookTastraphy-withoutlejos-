package items;

public class FoodItem {
	//Display Name
	public String name;
	//In Milliseconds
	long prepTime;
	//[IngredientID] [Amount]
	public UsedIngredient[] ingredients;
	public int orderAmount = 0;
	
	public FoodItem(String name, long prepTime, UsedIngredient[] ingredients)
	{
		this.name = name;
		this.prepTime = prepTime;
		this.ingredients = ingredients;
	}
	
}
