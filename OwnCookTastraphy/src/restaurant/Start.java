package restaurant;

import threads.Restaurant;

public class Start {
	static Restaurant rest;
	public static void main(String[] args) {
		//Restaurant(tables, sizeOfWaitingList)
		rest = new Restaurant(8, 6);
		rest.start();
		boolean isRunning = true;
		printMenu();
		while(isRunning)
		{
			printAll();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static void printAll()
	{
		System.out.print("\n\n\n\n");
//		printMenu();
		printPantry();
		printWaitingCustomers();
		printTables();
	}
	
	static void printWaitingCustomers()
	{
		Customer[] toPrint = rest.getWaitingCustomers();
		for(int i = 0; i < toPrint.length; i++)
		{
			if(toPrint[i] == null)
			{
				System.out.println("No one is waiting");
			}
			else
			{
				System.out.println("Customer " + toPrint[i].customerID
						+ " is waiting");
			}
		}
	}
	
	static void printTables()
	{
		Customer[] seatedCustomers = rest.getSeatedCustomers();
		for(int i = 0; i < seatedCustomers.length; i++)
		{
			if(seatedCustomers[i] != null)
			{
				System.out.println("Seat " + i + " has customer "
						+ seatedCustomers[i].customerName
						+ " with ID: "
						+ seatedCustomers[i].customerID
						+ " who asked for "
						+ seatedCustomers[i].expectedItem.orderedItem.name);
			}
			else
			{
				System.out.println("Seat " + i + " is empty");
			}
		}
	}
	
	static void printMenu()
	{
		FoodItem[] toPrint = rest.getMenu();
		for(int i = 0; i < toPrint.length; i++)
		{
			String strToPrint = "Name: " + toPrint[i].name 
					+ "\n\tAmountOfIngredients: " 
					+ toPrint[i].ingredients.length;
			for(int j = 0; j < toPrint[i].ingredients.length; j++)
			{
//				System.out.println("j : " + j + " i : " + i);
				strToPrint += "\n\t\tIngredientName: " 
						+ toPrint[i].ingredients[j].ingredientName
						+ "\n\t\tAmountNeeded: "
						+ toPrint[i].ingredients[j].amount;
			}
			System.out.println(strToPrint);
		}
	}
	
	static void printPantry()
	{
		Ingredient[] toPrint = rest.getPantry();
		for(int i = 0; i < toPrint.length; i++)
		{
			System.out.println("Name: " + toPrint[i].ingredientName 
					+ "\tAmount: " + toPrint[i].amount
					+ "\tTimeToOrder: " + toPrint[i].timeToOrderMore
					+ "\tAmountInOrder: " + toPrint[i].amountInOrder);
		}
	}
}
