package restaurant;

import items.FoodItem;
import items.Ingredient;

import java.util.ArrayList;

//SupplyClerk is the person who "goes online" and orders the ingredients
public class SupplyClerk extends Thread{
	private Thread t;
	public boolean isRunning = true;
	public Ingredient[] Pantry;
	//largest amount needed for order (lanfo!)wut
	public int[] lanfo;
	//number of recipies needing ingredient
	public int[] norni;
	public long[] timeLastOrder;
	public int[] amountAtArrival;
	public long[] timeAtArrival;
	public int[] attemptToCatchUp;
	public boolean[] ranOutInIteration;
	public ArrayList<Ingredient> ordering;
	public FoodItem[] menu;
	public int strategy;
	
	public SupplyClerk(Ingredient[] Pantry, FoodItem[] menu, int strategy)
	{
		ordering = new ArrayList<Ingredient>();
		this.Pantry = Pantry;
		this.menu = menu;
		this.strategy = strategy;
		//Some of this code is a bit messy. If time permits it would be best to clean it!
		switch(strategy)
		{
		case 5:
		case 4:
			attemptToCatchUp = new int[Pantry.length];
			for(int i = 0; i < Pantry.length; i++)
			{
				attemptToCatchUp[i] = 3;
			}
			//no break intentional
		case 3:
		case 2:
		case 1:
			long lastOrder = System.currentTimeMillis() / 1000;
			lanfo = new int[Pantry.length];
			norni = new int[Pantry.length];
			timeLastOrder = new long[Pantry.length];
			amountAtArrival = new int[Pantry.length];
			ranOutInIteration = new boolean[Pantry.length];
			for(int i = 0; i < menu.length; i++)
			{
				for(int j = 0; j < menu[i].ingredients.length; j++)
				{
					addToLanfo(menu[i].ingredients[j].ingredientName, menu[i].ingredients[j].amount);
					addToNorni(menu[i].ingredients[j].ingredientName);
				}
			}
			for(int i = 0; i < lanfo.length; i++)
			{
				timeLastOrder[i] = lastOrder;
				amountAtArrival[i] = -1 * Pantry[i].amount;
				ranOutInIteration[i] = false;
				System.out.println("Item: " + Pantry[i].ingredientName + " Amount: " + lanfo[i] + " TotalOrders: " + norni[i]);
			}
		case 0:
			//Nothing
			break;
		}
	}
	
	public void addToLanfo(String name, int amount)
	{
		for(int i = 0; i < Pantry.length; i++)
		{
			if(Pantry[i].ingredientName.equals(name) & amount > lanfo[i])
			{
				lanfo[i] = amount;
			}
		}
	}

	public void addToNorni(String name)
	{
		for(int i = 0; i < Pantry.length; i++)
		{
			if(Pantry[i].ingredientName.equals(name))
			{
				norni[i]++;
			}
		}
	}
	
	public void run()
	{
		while(isRunning)
		{
			orderSupplies();
			stockPantry();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
	
	public void orderSupplies()
	{
		switch(strategy)
		{
		case 0:
		//nothing
			break;
		case 1:
			strategy1();
			break;
		case 2:
			strategy2();
			break;
		case 3:
			strategy3();
			break;
		case 4:
			strategy4();
			break;
		case 5:
			strategy5();
			break;
		}
	}

	//Adds if there are only 10 left
	public void strategy1()
	{
		for(int i = 0; i < Pantry.length; i++)
		{
			if(Pantry[i].amount < 10 && !ordering.contains(Pantry[i]))
			{
				Ingredient toAdd = Pantry[i];
				toAdd.arrivalTime = System.currentTimeMillis() + Pantry[i].timeToOrderMore;
				ordering.add(toAdd);
			}
		}
		
	}
	
	//Creates multiple orders if there are less than 30 of the ingredient, 
	//	and orders until there will be more than 30 when the orders arrive
	public void strategy2() {
		for(int i = 0; i < Pantry.length; i++)
		{
			if(!ordering.contains(Pantry[i]))
			{
				for(int j = 30 - Pantry[i].amount; j > 0; j -= Pantry[i].amountInOrder) 
				{
					Ingredient toAdd = Pantry[i];
					toAdd.arrivalTime = System.currentTimeMillis() + Pantry[i].timeToOrderMore;
					ordering.add(toAdd);
				}
			}
		}
	}
	
	//Adds 10 of an ingredient times how many recipies use it times the max amount needed for a recipe
	//Pretty much makes sure you have above a certain number of the ingredient (EX: alway have more than 100)
	//Probably the best strategy
	public void strategy3() {
		for(int i = 0; i < Pantry.length; i++)
		{
			if(!ordering.contains(Pantry[i]))
			{
				for(int j = (lanfo[i]*norni[i]*15) - Pantry[i].amount; j > 0; j -= Pantry[i].amountInOrder) 
				{
					Ingredient toAdd = Pantry[i];
					toAdd.arrivalTime = System.currentTimeMillis() + Pantry[i].timeToOrderMore;
					ordering.add(toAdd);
				}
			}
		}
	}
	
	//This one has a catch up feature
	//It orders once, and the next time it checks if its on zero, it orders twice, then three times, ect
	//Once it gets to the golden number (never runs out of the ingredient) it sticks to that many orders
	//Huge overflow, not best idea
	public void strategy4() {
		long currTime = System.currentTimeMillis() / 1000;
		for(int i = 0; i < Pantry.length; i++)
		{
			if(!ordering.contains(Pantry[i]))
			{
				if(timeLastOrder[i] == currTime)
				{
					continue;
				}
//				System.out.println("Amount " + Pantry[i].amount + " lanfo[" + i + "] " + lanfo[i]);
				if(ranOutInIteration[i])
				{
					for(int j = 0; j < attemptToCatchUp[i]; j++)
					{
						Ingredient toAdd = Pantry[i];
						toAdd.arrivalTime = System.currentTimeMillis() + Pantry[i].timeToOrderMore;
						ordering.add(toAdd);	
					}
					ranOutInIteration[i] = false;
					attemptToCatchUp[i]++;
				}
				else 
				{
					for(int j = 0; j < attemptToCatchUp[i]; j++)
					{
						Ingredient toAdd = Pantry[i];
						toAdd.arrivalTime = System.currentTimeMillis() + Pantry[i].timeToOrderMore;
						ordering.add(toAdd);	
					}
				}
				timeLastOrder[i] = currTime;
				amountAtArrival[i] = -1 * Pantry[i].amount;
			}
		}
	}

	//See Strategy 4 first
	//Uses strategy 4 until it reaches a point where it doesn't run out
	//Then the rate of change is calculated (aka how much we are losing per second), and how fast the items can be ordered (Amount you are ordering/Time until arrival)
	//Then we order enough to catch up to the rate of change (or loss) 
	//It kind of works well, but you still eventually run into situations where you have 0
	//Then there is a massive order, and after running a while orders a ton. not optimal but attempts to adapt to drastic changes in ordering
	//	(if its possible to make it reach a point where it takes a long time to reach 0,
	//		then orders a CONSTANT amount to make up for it, that might work right.
	//		or maybe use a PID controller)
	public void strategy5() {
		long currTime = System.currentTimeMillis() / 1000;
		for(int i = 0; i < Pantry.length; i++)
		{
			if(!ordering.contains(Pantry[i]))
			{
				if(timeLastOrder[i] == currTime)
				{
					continue;
				}
//				System.out.println("Amount " + Pantry[i].amount + " lanfo[" + i + "] " + lanfo[i]);
				if(ranOutInIteration[i])
				{
					for(int j = 0; j < attemptToCatchUp[i]; j++)
					{
						Ingredient toAdd = Pantry[i];
						toAdd.arrivalTime = System.currentTimeMillis() + Pantry[i].timeToOrderMore;
						ordering.add(toAdd);	
					}
					ranOutInIteration[i] = false;
					attemptToCatchUp[i]++;
				}
				else 
				{
					for(long j = ((Pantry[i].amount + amountAtArrival[i])/(currTime - timeLastOrder[i])); j < 0; j += (Pantry[i].amountInOrder/(Pantry[i].timeToOrderMore/1000))) 
					{
						Ingredient toAdd = Pantry[i];
						toAdd.arrivalTime = System.currentTimeMillis() + Pantry[i].timeToOrderMore;
						ordering.add(toAdd);
					}
				}
				timeLastOrder[i] = currTime;
				amountAtArrival[i] = -1 * Pantry[i].amount;
			}
		}
	}
	
	public void stockPantry()
	{
		for(int i = 0; i < ordering.size(); i++)
		{
			if(ordering.get(i).arrivalTime < System.currentTimeMillis())
			{
				stockItem(ordering.remove(i).ingredientName);
			}
		}
	}
	
	public void stockItem(String ingredient)
	{
		for(int i = 0; i < Pantry.length; i++)
		{
			if(Pantry[i].ingredientName.equals(ingredient))
			{
				if(Pantry[i].amount <= lanfo[i])
				{
					ranOutInIteration[i] = true;
				}
				amountAtArrival[i] -= Pantry[i].amountInOrder;
				Pantry[i].amount += Pantry[i].amountInOrder;
				break;
			}
		}
	}
	
	public Ingredient[] getOrderedIngredients()
	{
		Ingredient[] ret = new Ingredient[1];
		ret = ordering.toArray(ret);
		return ret;
	}
}
