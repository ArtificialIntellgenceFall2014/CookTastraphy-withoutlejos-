package restaurant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import threads.Chef;

public class Kitchen {
	//Customers drop orders directly into here
	public ArrayList<OrderedItem> PendingOrders = new ArrayList<OrderedItem>();
	//Only four ovens
	public OrderedItem[] InProgress = new OrderedItem[4];
	//Complete orders will be taken out
	public ArrayList<OrderedItem> CompleteOrders = new ArrayList<OrderedItem>();
	//Pantry
	public Ingredient[] Pantry;
	
	//The cook
	public Chef gusteau;
	
	public Kitchen()
	{
		loadPantry();
		gusteau = new Chef();
		gusteau.start();
	}
	
	public void loadPantry()
	{
		File ingFile = new File("Ingredients");
		Scanner fileInput;
		try {
			fileInput = new Scanner(ingFile);
			int numberOfIngredients = fileInput.nextInt();
			Pantry = new Ingredient[numberOfIngredients];
			fileInput.nextLine();
			for(int i = 0; fileInput.hasNext() && i < numberOfIngredients; i++)
			{
				//name amount timetoorder amountinorder
				String ing = fileInput.nextLine();
				Scanner stringParse = new Scanner(ing);
				//Gets everything before the first space (aka name)
				Pantry[i] = new Ingredient(stringParse.next() 
						,stringParse.nextInt()
						,stringParse.nextLong()
						,stringParse.nextInt());
				stringParse.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Ingredient[] getPantry()
	{
		return Pantry;
	}
}
