package graphics;
import java.awt.*;
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
	public JPanel orderHistoryPanel = new JPanel(new FlowLayout());
	public JPanel menuPanel = new JPanel(new FlowLayout());
	
	public GameBoard(){
		JPanel p = new JPanel(new BorderLayout());
		orders.setPreferredSize(new Dimension(200, 200));
		orders.setBackground(Color.black);
		waitingOrders.setPreferredSize(new Dimension(200, 300));
		waitingOrders.setBackground(Color.black);
		JLabel waitingHeader = new JLabel("Incoming Orders (Waiting)",  JLabel.CENTER);
		Font font = waitingHeader.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
		waitingHeader.setFont(boldFont);
		left.setPreferredSize(new Dimension(300, 300));
		left.setBackground(Color.black);
		JLabel orderHeader = new JLabel("Incoming Orders (Seated)", JLabel.CENTER);
		Font font2 = orderHeader.getFont();
		Font boldFont2 = new Font(font2.getFontName(), Font.BOLD, font2.getSize());
		orderHeader.setFont(boldFont2);
		orderHeader.setForeground(Color.YELLOW);		
		waitingHeader.setForeground(Color.YELLOW);
		
		JPanel ordersColHead = new JPanel(new FlowLayout());
		JLabel orderNumber = new JLabel("Order Number", JLabel.LEFT);
		orderNumber.setPreferredSize(new Dimension (100, 20));
		JLabel itemOrdered = new JLabel("Item Ordered", JLabel.LEFT);
		itemOrdered.setPreferredSize(new Dimension (100, 20));
		orderNumber.setForeground(Color.yellow);
		itemOrdered.setForeground(Color.yellow);
		ordersColHead.setBackground(Color.black);
		ordersColHead.setPreferredSize(new Dimension (300, 20));
		ordersColHead.add(orderNumber);
		ordersColHead.add(itemOrdered);
		
		JPanel  waitingColHead = new JPanel(new FlowLayout());
		JLabel worderNumber = new JLabel("Order Number", JLabel.LEFT);
		worderNumber.setPreferredSize(new Dimension (100, 20));
		JLabel witemOrdered = new JLabel("Item Ordered", JLabel.LEFT);
		witemOrdered.setPreferredSize(new Dimension (100, 20));
		worderNumber.setForeground(Color.yellow);
		witemOrdered.setForeground(Color.yellow);
		waitingColHead.setBackground(Color.black);
		waitingColHead.setPreferredSize(new Dimension (300, 20));
		waitingColHead.add(worderNumber);
		waitingColHead.add(witemOrdered);
		
		left.add(orderHeader);
		left.add(ordersColHead);
		left.add(orders);
		left.add(waitingHeader);
		left.add(waitingColHead);
		left.add(waitingOrders);
		p.add(left, BorderLayout.LINE_START);
		
		right.setPreferredSize(new Dimension(300, 300));
		right.setBackground(Color.black);
		pantry.setPreferredSize(new Dimension(200, 250));
		pantry.setBackground(Color.BLACK);
		JLabel pantryHeader = new JLabel("Pantry Inventory", JLabel.CENTER);
		pantryHeader.setForeground(Color.YELLOW);
		
		JPanel  pantryColHead = new JPanel(new FlowLayout());
		JLabel pantryItem = new JLabel("Item", JLabel.LEFT);
		pantryItem.setPreferredSize(new Dimension (100, 20));
		JLabel itemQuantity = new JLabel("Quantity", JLabel.LEFT);
		itemQuantity.setPreferredSize(new Dimension (100, 20));
		
		pantryItem.setForeground(Color.yellow);
		itemQuantity.setForeground(Color.yellow);
		
		pantryColHead.setBackground(Color.black);
		pantryColHead.setPreferredSize(new Dimension (300, 20));
		pantryColHead.add(pantryItem);
		pantryColHead.add(itemQuantity);
		right.add(pantryHeader);
		right.add(pantryColHead);
		right.add(pantry);
		
		JPanel chefRunner = new JPanel(new FlowLayout());
		chefRunner.setBackground(Color.cyan);
		chef.setPreferredSize(new Dimension(300, 300));
		chef.setBackground(Color.cyan);
		JLabel chefHeader = new JLabel("The Chef is Cooking: ", JLabel.CENTER);
		chefHeader.setForeground(Color.black);
		chefHeader.setPreferredSize(new Dimension(300, 20));
		
		JPanel chefColHead = new JPanel(new FlowLayout());
		JLabel orderNumber2 = new JLabel("Order Number", JLabel.LEFT);
		orderNumber2.setPreferredSize(new Dimension (100, 20));
		JLabel itemOrdered2 = new JLabel("Item Ordered", JLabel.LEFT);
		itemOrdered2.setPreferredSize(new Dimension (100, 20));
		orderNumber2.setForeground(Color.black);
		itemOrdered2.setForeground(Color.black);
		chefColHead.setBackground(Color.cyan);
		chefColHead.setPreferredSize(new Dimension (300, 20));
		chefColHead.add(orderNumber2);
		chefColHead.add(itemOrdered2);
		
		chefRunner.add(chefHeader);
		chefRunner.add(chefColHead);
		chefRunner.add(chef);
		
		runner.setPreferredSize(new Dimension(300, 300));
		runner.setBackground(Color.cyan);
		JLabel runnerHeader = new JLabel("The Runner is Delivering: ", JLabel.CENTER);
		runnerHeader.setForeground(Color.black);
		
		JPanel runnerColHead = new JPanel(new FlowLayout());
		JLabel runnerOrderNum = new JLabel("Order Number", JLabel.LEFT);
		runnerOrderNum.setPreferredSize(new Dimension (100, 20));
		JLabel runnerItem = new JLabel("Item Ordered", JLabel.LEFT);
		runnerItem.setPreferredSize(new Dimension (100, 20));
		runnerOrderNum.setForeground(Color.black);
		runnerItem.setForeground(Color.black);
		runnerColHead.setBackground(Color.cyan);
		runnerColHead.setPreferredSize(new Dimension (300, 20));
		runnerColHead.add(runnerOrderNum);
		runnerColHead.add(runnerItem);
		
		chefRunner.add(runnerHeader);
		chefRunner.add(runnerColHead);
		chefRunner.add(runner);
		p.add(chefRunner, BorderLayout.CENTER);
		
		orderHistoryPanel.setPreferredSize(new Dimension(300, 200));
		orderHistoryPanel.setBackground(Color.black);
		JLabel orderHistLabel = new JLabel("All Past Orders: ", JLabel.CENTER);
		orderHistLabel.setForeground(Color.YELLOW);
		right.add(orderHistLabel);
		right.add(orderHistoryPanel);;
		
		menuPanel.setPreferredSize(new Dimension(300, 200));
		menuPanel.setBackground(Color.black);
		JLabel menuHeader = new JLabel("The Menu", JLabel.CENTER);
		menuHeader.setForeground(Color.YELLOW);
		right.add(menuHeader);
		right.add(menuPanel);
		p.add(right, BorderLayout.LINE_END);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(p);
	    frame.setSize(1000, 700);
	    frame.setVisible(true);
	}
	
	public void removeAll(){
			orders.removeAll();
			orders.revalidate();
			waitingOrders.removeAll();
			waitingOrders.revalidate();
			pantry.removeAll();
			pantry.revalidate();
			chef.removeAll();
			chef.revalidate();
			runner.removeAll();
			runner.revalidate();
			orderHistoryPanel.removeAll();
			orderHistoryPanel.revalidate();
			frame.repaint();
	}
	
	public void addNewOrder(String order, int custNumber){
		JPanel somePanel = new JPanel(new FlowLayout());
		
		JLabel addingOrderNumber = new JLabel(String.valueOf(custNumber), JLabel.LEFT);
		JLabel addingOrderedItem = new JLabel(order, JLabel.LEFT);
		
		addingOrderNumber.setPreferredSize(new Dimension(95, 20));
		addingOrderedItem.setPreferredSize(new Dimension(95, 20));
		
		addingOrderNumber.setForeground(Color.WHITE);
		addingOrderedItem.setForeground(Color.WHITE);
		
		somePanel.setPreferredSize(new Dimension (200, 20));
		somePanel.setBackground(Color.black);
		somePanel.add(addingOrderNumber);
		somePanel.add(addingOrderedItem);
		
		orders.add(somePanel);
		orders.revalidate();
		orders.repaint();
	}
	
	public void addToWaiting(String order, int custNumber){
		JPanel somePanel = new JPanel(new FlowLayout());
		
		JLabel addingOrderNumber = new JLabel(String.valueOf(custNumber), JLabel.LEFT);
		JLabel addingOrderedItem = new JLabel(order, JLabel.LEFT);
		
		addingOrderNumber.setPreferredSize(new Dimension(95, 20));
		addingOrderedItem.setPreferredSize(new Dimension(95, 20));
		
		addingOrderNumber.setForeground(Color.WHITE);
		addingOrderedItem.setForeground(Color.WHITE);
		
		somePanel.setPreferredSize(new Dimension (200, 20));
		somePanel.setBackground(Color.black);
		somePanel.add(addingOrderNumber);
		somePanel.add(addingOrderedItem);
		
		waitingOrders.add(somePanel);
		waitingOrders.revalidate();
		waitingOrders.repaint();
	}
	
	public void addProcessingOrder(String order, int custNumber){
		JPanel somePanel = new JPanel(new FlowLayout());
		
		JLabel addingOrderNumber = new JLabel(String.valueOf(custNumber), JLabel.LEFT);
		JLabel addingOrderedItem = new JLabel(order, JLabel.LEFT);
		
		addingOrderNumber.setPreferredSize(new Dimension(95, 20));
		addingOrderedItem.setPreferredSize(new Dimension(95, 20));
		
		addingOrderNumber.setForeground(Color.black);
		addingOrderedItem.setForeground(Color.black);
		
		somePanel.setPreferredSize(new Dimension (200, 20));
		somePanel.setBackground(Color.cyan);
		somePanel.add(addingOrderNumber);
		somePanel.add(addingOrderedItem);
		chef.add(somePanel);
		chef.revalidate();
		chef.repaint();
	}
	
	public void addDoneOrder(String order, int custNumber){
		JPanel somePanel = new JPanel(new FlowLayout());
		
		JLabel addingOrderNumber = new JLabel(String.valueOf(custNumber), JLabel.LEFT);
		JLabel addingOrderedItem = new JLabel(order, JLabel.LEFT);
		
		addingOrderNumber.setPreferredSize(new Dimension(95, 20));
		addingOrderedItem.setPreferredSize(new Dimension(95, 20));
		
		addingOrderNumber.setForeground(Color.black);
		addingOrderedItem.setForeground(Color.black);
		
		somePanel.setPreferredSize(new Dimension (200, 20));
		somePanel.setBackground(Color.cyan);
		somePanel.add(addingOrderNumber);
		somePanel.add(addingOrderedItem);
		runner.add(somePanel);
		runner.revalidate();
		runner.repaint();
	}
	
	public void addPantry(String name, int quantity){
JPanel somePanel = new JPanel(new FlowLayout());
		
		JLabel addingOrderNumber = new JLabel(name, JLabel.LEFT);
		JLabel addingOrderedItem = new JLabel(String.valueOf(quantity), JLabel.LEFT);
		
		addingOrderNumber.setPreferredSize(new Dimension(95, 20));
		addingOrderedItem.setPreferredSize(new Dimension(95, 20));
		
		addingOrderNumber.setForeground(Color.WHITE);
		addingOrderedItem.setForeground(Color.WHITE);
		
		somePanel.setPreferredSize(new Dimension (200, 20));
		somePanel.setBackground(Color.black);
		somePanel.add(addingOrderNumber);
		somePanel.add(addingOrderedItem);
		
		pantry.add(somePanel);
		pantry.revalidate();
		pantry.repaint();
	}
	
	public void addToOrderHistory(String theOrder){
		JLabel addingSomething = new JLabel(theOrder + "; ");
		addingSomething.setForeground(Color.WHITE);
		orderHistoryPanel.add(addingSomething);
		orderHistoryPanel.revalidate();
		orderHistoryPanel.repaint();
	}
	
	public void addToMenu(String menuItem){
		JLabel addingSomething = new JLabel(menuItem);
		addingSomething.setForeground(Color.WHITE);
		addingSomething.setPreferredSize(new Dimension(190,20));
		menuPanel.add(addingSomething);
		menuPanel.revalidate();
		menuPanel.repaint();
	}

}
