package restaurant;

import items.FoodItem;
import items.Ingredient;
import items.OrderedItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Kitchen extends Thread{
	private Thread t;
	public boolean isRunning = true;
//	//Customers drop orders directly into here
//	public ArrayList<OrderedItem> PendingOrders = new ArrayList<OrderedItem>();
//	//Complete orders will be taken out
//	public ArrayList<OrderedItem> CompleteOrders = new ArrayList<OrderedItem>();
	//Pantry
	public Ingredient[] Pantry;
	
	//The cook
	//Okay seriously don't name classes after real people
	//Not very intuitive but I thought it seemed nice lol
	public Chef gusteau;
	public SupplyClerk amy;
	
	public Kitchen(int ovens, FoodItem[] menu, int chefStrat, int suppStrat)
	{
		loadPantry();
		gusteau = new Chef(ovens, Pantry, chefStrat);
		amy = new SupplyClerk(Pantry, menu, suppStrat);
	}
	
	public void run()
	{
		gusteau.start();
		amy.start();
		while(isRunning)
		{
			
			//Pantry = clerk.order
		}
	}
	
	public void start()
	{
		if(t == null)
		{
			t = new Thread(this);
			t.start();
		}
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
			e.printStackTrace();
		}
	}
	
	public Ingredient[] getPantry()
	{
		return Pantry;
	}
	
	public OrderedItem[] getPendingOrders()
	{
		OrderedItem[] ret = new OrderedItem[4];
		ret = gusteau.PendingOrders.toArray(ret);
		return ret;
	}
	
	public OrderedItem[] getInProgress()
	{
		return gusteau.InProgress;
	}
	
	public OrderedItem[] getCompleteOrders()
	{
		OrderedItem[] ret = new OrderedItem[4];
		ret = gusteau.CompleteOrders.toArray(ret);
		return ret;
	}
	
	public Ingredient[] getOrderedIngredients()
	{
		return amy.getOrderedIngredients();
	}
}
