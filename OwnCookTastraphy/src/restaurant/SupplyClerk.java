package restaurant;

import items.FoodItem;
import items.Ingredient;

import java.util.ArrayList;

//SupplyClerk is the person who "goes online" and orders the ingredients
public class SupplyClerk extends Thread{
	private Thread t;
	public boolean isRunning = true;
	public Ingredient[] Pantry;
	public ArrayList<Ingredient> ordering;
	public ArrayList<Ingredient> arrived;
	public FoodItem[] menu;
	public int strategy;
	
	public SupplyClerk(Ingredient[] Pantry, FoodItem[] menu, int strategy)
	{
		ordering = new ArrayList<Ingredient>();
		arrived = new ArrayList<Ingredient>();
		this.Pantry = Pantry;
		this.menu = menu;
		this.strategy = strategy;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void orderSupplies()
	{
		switch(strategy)
		{
		case 1:
			strategy1();
			break;
		case 2:
			strategy2();
			break;
		}
	}

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
	
	public void strategy2() {
		for(int i = 0; i < Pantry.length; i++)
		{
			if(Pantry[i].amount == 0 && !ordering.contains(Pantry[i]))
			{
				System.out.println("Test");
				Ingredient toAdd = Pantry[i];
				toAdd.arrivalTime = System.currentTimeMillis() + Pantry[i].timeToOrderMore;
				ordering.add(toAdd);
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
				Pantry[i].amount += Pantry[i].amountInOrder;
				break;
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
}
