package restaurant;

public class FoodItem {
	//Display Name
	public String name;
	//In Milliseconds
	long prepTime;
	//[IngredientID] [Amount]
	UsedIngredient[] ingredients;
	
	public FoodItem(String name, long prepTime, UsedIngredient[] ingredients)
	{
		this.name = name;
		this.prepTime = prepTime;
		this.ingredients = ingredients;
	}
	
}
