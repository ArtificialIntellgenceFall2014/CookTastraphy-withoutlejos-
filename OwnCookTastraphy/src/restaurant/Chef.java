package restaurant;

import items.Ingredient;
import items.OrderedItem;

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
			if(Pantry[0].amount > 0)
			{
				Pantry[0].amount--;
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
		
	}
	
	public void Strategy2()
	{
		
	}
	
	public void Strategy3()
	{
		
	}

}
