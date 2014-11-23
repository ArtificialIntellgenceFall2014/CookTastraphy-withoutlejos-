package threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import restaurant.Customer;
import restaurant.FoodItem;
import restaurant.Ingredient;
import restaurant.Kitchen;
import restaurant.UsedIngredient;

public class Restaurant extends Thread{
	Thread t;
	private CustomerGenerator cg;
	public boolean isRunning = true;
	
	int tables;
	FoodItem[] menuBillboard;
	private Customer[] seatedCustomers;
	Kitchen mainKitchen;
	
	public Restaurant(int tables, int maxWait)
	{
		this.tables = tables;
		loadMenu();
		cg = new CustomerGenerator(maxWait, menuBillboard);
		seatedCustomers = new Customer[tables];
		mainKitchen = new Kitchen();
	}
	
	public void run()
	{
		
		cg.start();
		try{
			while(isRunning)
			{
				int openSpot = getOpenTable();
				System.out.println("");
				if(openSpot != -1)
				{   
					seatedCustomers[openSpot] = cg.getCustomer();
				}
				Thread.sleep(500);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("Interrupted");
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
	//	Items waiting to be cooked (OrderedItems[])
	//	Items being cooked (OrderedItems[])
	//	Items that are ready (OrderedItems[])
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
}
