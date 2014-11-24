package generators;

import items.Customer;
import items.FoodItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerGenerator extends Thread{
	private Thread t;
	public boolean isRunning = true;
	int maxEnter;
	ArrayList<Customer> waitingCustomers = new ArrayList<Customer>();
	ArrayList<Customer> customersToAppear = new ArrayList<Customer>();
	public CustomerGenerator(int maxEnter, FoodItem[] menu)
	{
		this.maxEnter = maxEnter;
		System.out.println("CustomerGenerator is alive");
		loadCustomers(menu);
	}
	
	//DONT CALL THIS, call [classname].start();
	//That function is a part of thread and will start run();
	public void run()
	{
		long timeStarted = System.currentTimeMillis();
		long timeSinceStarted = 0;
		while(isRunning)
		{
			timeSinceStarted = System.currentTimeMillis() - timeStarted;
			if(customersToAppear.size() == 0)
			{
				continue;
			}
			if(customersToAppear.get(0).timeToAppear < timeSinceStarted)
			{
				waitingCustomers.add(customersToAppear.remove(0));
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
	
	public void loadCustomers(FoodItem[] menu)
	{
		File cusFile = new File("Customers");
		Scanner fileInput;
		try {
			fileInput = new Scanner(cusFile);
			int numberOfCustomers = fileInput.nextInt();
			fileInput.nextLine();
			for(int i = 0; fileInput.hasNext() && i < numberOfCustomers; i++)
			{
				//name amountofing [ing amt] [ing amt] ...
				String ing = fileInput.nextLine();
				Scanner stringParse = new Scanner(ing);
				
				String name = stringParse.next();
				String foodItemName = stringParse.next();
				FoodItem foodItemOrdered = null;
				long timeToArrive = stringParse.nextLong();
				for(int j = 0; j < menu.length; j++)
				{
					if(menu[j].name.equals(foodItemName))
					{
						foodItemOrdered = menu[j];
						break;
					}
				}
				if(foodItemOrdered == null)
				{
					continue;
				}
				
				customersToAppear.add(new Customer(name
						, foodItemOrdered, timeToArrive));
				
				stringParse.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Customer getCustomer()
	{
		//removes that customer and sends it
		if(waitingCustomers.size() != 0)
		{
			//System.out.println("Customer removed");
			return waitingCustomers.remove(0);
		}
		return null;
	}
	
	public Customer[] getWaitingCustomers()
	{
		Customer[] ret = new Customer[maxEnter];
		ret = waitingCustomers.toArray(ret);
		return ret;
	}
}
