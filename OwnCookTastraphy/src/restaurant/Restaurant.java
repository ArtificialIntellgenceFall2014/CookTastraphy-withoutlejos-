package restaurant;

import generators.CustomerGenerator;
import items.Customer;
import items.FoodItem;
import items.Ingredient;
import items.OrderedItem;
import items.UsedIngredient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant extends Thread{
	Thread t;
	private CustomerGenerator cg;
	public boolean isRunning = true;
	
	int tables;
	FoodItem[] menuBillboard;
	private Customer[] seatedCustomers;
	//Complete orders will be taken out
	Kitchen mainKitchen;
	
	public Restaurant(int tables, int maxWait, int chefStrat, int supStrat)
	{
		this.tables = tables;
		loadMenu();
		cg = new CustomerGenerator(maxWait, menuBillboard);
		seatedCustomers = new Customer[tables];
		mainKitchen = new Kitchen(4, menuBillboard, chefStrat, supStrat);
	}
	
	public void run()
	{
		cg.start();
		mainKitchen.start();
		while(isRunning)
		{
			int openSpot = getOpenTable();
			if(openSpot != -1)
			{
				seatedCustomers[openSpot] = cg.getCustomer();
				if(seatedCustomers[openSpot] != null)
				{
					mainKitchen.gusteau.PendingOrders.add(seatedCustomers[openSpot].expectedItem);
				}
			}
			if(mainKitchen.gusteau.CompleteOrders.size() > 0)
			{
				deliverFood();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("ExitingThread");
	}
	
	public void start()
	{
		if(t == null)
		{
			t = new Thread(this);
			t.start();
		}
	}
	
	public int deliverFood()
	{
		for(int i = 0; i < seatedCustomers.length; i++)
		{
			if(seatedCustomers[i] == null)
			{
				continue;
			}
			
			if(mainKitchen.gusteau.CompleteOrders.get(0).customerID == seatedCustomers[i].customerID)
			{
				seatedCustomers[i] = null;
				mainKitchen.gusteau.CompleteOrders.remove(0);
				return i;
			}
		}
		return -1;
	}
	
	int getOpenTable()
	{
		for(int i = 0; i < seatedCustomers.length; i++)
		{
			if(seatedCustomers[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	void loadMenu()
	{

		File ingFile = new File("Recipies");
		Scanner fileInput;
		try {
			fileInput = new Scanner(ingFile);
			int numberOfRecipies = fileInput.nextInt();
			menuBillboard = new FoodItem[numberOfRecipies];
			fileInput.nextLine();
			for(int i = 0; fileInput.hasNext() && i < numberOfRecipies; i++)
			{
				//name amountofing [ing amt] [ing amt] ...
				String ing = fileInput.nextLine();
				Scanner stringParse = new Scanner(ing);
				//Gets everything before the first space (aka name)
				String name = stringParse.next();
				int numberOfIng = stringParse.nextInt();
				long prepTime = stringParse.nextLong(); 
				UsedIngredient[] ingredients = new UsedIngredient[numberOfIng];
				for(int j = 0; stringParse.hasNext() && j < numberOfIng; j++){
					ingredients[j] = new UsedIngredient(stringParse.next()
							, stringParse.nextInt());
				}
				menuBillboard[i] = new FoodItem(name, prepTime, ingredients);
				stringParse.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//All of this is for the GUI
	//Also there should be an array for
	//	Items waiting to be cooked (OrderedItem[])
	//	Items being cooked (OrderedItem[])
	//	Items that are ready (OrderedItem[])
	// 	Ingredients being shipped (Ingredient[])
	public Customer[] getWaitingCustomers()
	{
		return cg.getWaitingCustomers();
	}
	
	public Customer[] getSeatedCustomers()
	{
		return seatedCustomers;
	}
	
	public FoodItem[] getMenu()
	{
		return menuBillboard;
	}
	
	public Ingredient[] getPantry()
	{
		return mainKitchen.getPantry();
	}
	
	public OrderedItem[] getPendingOrders()
	{
		return mainKitchen.getPendingOrders();
	}
	
	public OrderedItem[] getInProgress()
	{
		return mainKitchen.getInProgress();
	}
	
	public OrderedItem[] getCompleteOrders()
	{
		return mainKitchen.getCompleteOrders();
	}
	
	public Ingredient[] getOrderedIngredients()
	{
		return mainKitchen.getOrderedIngredients();
	}
	
	public void closeProgram()
	{
		cg.isRunning = false;
		mainKitchen.gusteau.isRunning = false;
		mainKitchen.amy.isRunning = false;
		mainKitchen.isRunning = false;
		isRunning = false;
	}
}
