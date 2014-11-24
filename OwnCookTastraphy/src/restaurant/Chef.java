package restaurant;

import items.Ingredient;
import items.OrderedItem;
import items.UsedIngredient;

import java.util.ArrayList;

//Will exist in the restaurant
//Different strategies!
//Give Chef an in progress and complete to send back to the kitchen!
public class Chef extends Thread{
	private Thread t;
	public boolean isRunning = true;
	//Customers drop orders directly into here
	public ArrayList<OrderedItem> PendingOrders = new ArrayList<OrderedItem>();
	//Only four ovens
	public OrderedItem[] InProgress;
	//Complete orders will be taken out
	public ArrayList<OrderedItem> CompleteOrders = new ArrayList<OrderedItem>();
	//Pantry
	public Ingredient[] Pantry;
	
	public int strategy;
	
	public Chef(int ovens, Ingredient[] Pantry, int strategy)
	{
		this.Pantry = Pantry;
		this.strategy = strategy;
		InProgress = new OrderedItem[ovens];
	}
	
	public void run()
	{
		while(isRunning)
		{
			switch(strategy)
			{
				case 0:
					//on strike!
					break;
				case 1:
					Strategy1();
					break;
				case 2:
					Strategy2();
					break;
				case 3:
					Strategy3();
					break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
	
	public void Strategy1()
	{
		for(int i = 0; i < PendingOrders.size(); i++)
		{
			if(hasIngredients(PendingOrders.get(i)))
			{
				startPendingOrder(i);
			}
		}
		long currTime = System.currentTimeMillis();
		for(int i = 0; i < InProgress.length; i++)
		{
			if(InProgress[i] ==  null)
			{
				continue;
			}
			if(currTime > InProgress[i].timeReady)
			{
				CompleteOrders.add(InProgress[i]);
				InProgress[i] = null;
			}
		}
	}
	
	public void Strategy2()
	{
		
	}
	
	public void Strategy3()
	{
		
	}
	
	public void startPendingOrder(int index)
	{
		for(int i = 0; i < InProgress.length; i++)
		{
			if(InProgress[i] == null)
			{
				InProgress[i] = PendingOrders.remove(index);
				InProgress[i].startItem();
				takeIngredients(InProgress[i]);
				return;
			}
		}
	
	}

	public void takeIngredients(OrderedItem order)
	{
		for(int i = 0; i < order.orderedItem.ingredients.length; i++)
		{
			for(int j = 0; j < Pantry.length; j++)
			{
				if(Pantry[j].ingredientName.equals(order.orderedItem.ingredients[i].ingredientName))
				{
					Pantry[j].amount -= order.orderedItem.ingredients[i].amount;	
				}
			}
		}
	}
	
	public boolean hasIngredients(OrderedItem order)
	{
		for(int i = 0; i < order.orderedItem.ingredients.length; i++)
		{
			if(!hasEnoughIngredient(order.orderedItem.ingredients[i]))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean hasEnoughIngredient(UsedIngredient ing)
	{
		for(int i = 0; i < Pantry.length; i++)
		{
			if(ing.ingredientName.equals(Pantry[i].ingredientName))
			{
				return ing.amount <= Pantry[i].amount;
			}
		}
		return false;
	}
}
