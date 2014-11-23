package graphics;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GameBoard extends JPanel {
	public JFrame frame = new JFrame("CookTastrophy!");
	public JPanel orders = new JPanel(new FlowLayout());
	public JPanel waitingOrders = new JPanel(new FlowLayout());
	public JPanel left = new JPanel(new FlowLayout());
	public JPanel pantry = new JPanel(new FlowLayout());
	public JPanel right = new JPanel(new FlowLayout());
	public JPanel runner = new JPanel(new FlowLayout());
	public JPanel chef = new JPanel(new FlowLayout());
	
	public GameBoard(){
		JPanel p = new JPanel(new BorderLayout());
		JLabel header = new JLabel("CookTastrophy is running", JLabel.CENTER);
		p.add(header, BorderLayout.PAGE_START);
		JLabel footer = new JLabel("  ");
		p.add(footer, BorderLayout.PAGE_END);
		
		orders.setPreferredSize(new Dimension(200, 200));
		orders.setBackground(Color.black);
		waitingOrders.setPreferredSize(new Dimension(200, 300));
		waitingOrders.setBackground(Color.black);
		JLabel waitingHeader = new JLabel("Incoming Orders (Waiting)", JLabel.CENTER);
		left.setPreferredSize(new Dimension(200, 300));
		left.setBackground(Color.black);
		JLabel orderHeader = new JLabel("Incoming Orders (Seated)", JLabel.CENTER);
		orderHeader.setForeground(Color.YELLOW);
		waitingHeader.setForeground(Color.YELLOW);
		left.add(orderHeader);
		left.add(orders);
		left.add(waitingHeader);
		left.add(waitingOrders);
		p.add(left, BorderLayout.LINE_START);
		
		right.setPreferredSize(new Dimension(200, 300));
		right.setBackground(Color.black);
		pantry.setPreferredSize(new Dimension(200, 200));
		pantry.setBackground(Color.BLACK);
		JLabel pantryHeader = new JLabel("Pantry Inventory", JLabel.CENTER);
		pantryHeader.setForeground(Color.YELLOW);
		right.add(pantryHeader);
		right.add(pantry);
		p.add(right, BorderLayout.LINE_END);
		
		JPanel chefRunner = new JPanel(new FlowLayout());
		chef.setPreferredSize(new Dimension(200, 300));
		chef.setBackground(Color.cyan);
		JLabel chefHeader = new JLabel("The Chef is Cooking: ", JLabel.CENTER);
		chefHeader.setForeground(Color.black);
		chefRunner.add(chefHeader);
		chefRunner.add(chef);
		
		runner.setPreferredSize(new Dimension(200, 300));
		runner.setBackground(Color.red);
		JLabel runnerHeader = new JLabel("The Runner is Delivering: ", JLabel.CENTER);
		runnerHeader.setForeground(Color.black);
		chefRunner.add(runnerHeader);
		chefRunner.add(runner);
		
		p.add(chefRunner, BorderLayout.CENTER);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(p);
	    frame.setSize(700, 700);
	    frame.setVisible(true);
	}
	
	public void addNewOrder(String order, int custNumber){
		JLabel addingSomething = new JLabel(custNumber + " " + order, JLabel.LEFT);
		addingSomething.setPreferredSize(new Dimension(190,20));
		addingSomething.setForeground(Color.WHITE);
		orders.add(addingSomething);
		orders.revalidate();
	}
	
	public void removeAll(){
		orders.removeAll();
		waitingOrders.removeAll();
		pantry.removeAll();
		chef.removeAll();
		runner.removeAll();
	}
	
	public void addToWaiting(String order, int custNumber){
		JLabel addingSomething = new JLabel(custNumber + " " + order, JLabel.LEFT);
		addingSomething.setPreferredSize(new Dimension(190,20));
		addingSomething.setForeground(Color.WHITE);
		waitingOrders.add(addingSomething);
		waitingOrders.revalidate();
	}
	
	public void addProcessingOrder(String order, int custNumber){
		JLabel addingSomething = new JLabel(custNumber + " " + order, JLabel.LEFT);
		addingSomething.setPreferredSize(new Dimension(190,20));
		addingSomething.setForeground(Color.BLACK);
		pantry.add(addingSomething);
		pantry.revalidate();
	}
	
	public void addDoneOrder(String order, int custNumber){
		JLabel addingSomething = new JLabel(custNumber + " " + order, JLabel.LEFT);
		addingSomething.setPreferredSize(new Dimension(190,20));
		addingSomething.setForeground(Color.BLACK);
		pantry.add(addingSomething);
		pantry.revalidate();
	}
	
	public void addPantry(String name, int quantity){
		JLabel addingSomething = new JLabel(name + " " + quantity, JLabel.LEFT);
		addingSomething.setPreferredSize(new Dimension(190,20));
		addingSomething.setForeground(Color.WHITE);
		pantry.add(addingSomething);
		pantry.revalidate();
	}

}