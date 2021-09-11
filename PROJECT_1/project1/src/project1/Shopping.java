/**
 * The Shopping class is a blueprint for a Shopping item which encapsulates the 
 * property of a ShoppingBag and methods to manage this bag for a shopping experience.
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */


package project1;
import java.util.Scanner;


public class Shopping {
	
	private ShoppingBag groceryBag;
	
	/**
	 * This constructor creates an instance of the Shopping class, by assigning a reference 
	 * of a ShoppingBag object to the groceryBag datafield.
	 * 
	 */
	public Shopping() {
		
		groceryBag = new ShoppingBag();
		
	}
	
	/**
	 * Scans in user input and process all add (A), remove (R), display (P), check out (C) and quit shopping (Q).
	 * It responds to invalid commands by printing out "Invalid command!"
	 * 
	 */
	public void run() {
		
		System.out.println("Let's start shopping!");
		Scanner sc = new Scanner(System.in);
			
			while(sc.hasNext()) {
				String input = sc.next();
				
				if(input.length()!=1) {
					System.out.println("Invalid command!");
					sc.nextLine();
					continue;
					
				}
				
				char instruction = input.charAt(0);
				
				switch(instruction) {
				
				case 'A' :
					
					addItem(sc);
					break;
					
				case 'R':
					
					removeItem(sc);
					break;
					
				case 'P':
					
					printItems(sc);
					break;
					
				case 'C':
					
					checkoutItems(sc);
					break;
					
				case 'Q':
					
					quitCheckout(sc);
					return;
					
				default: 
					System.out.println("Invalid command!"); sc.nextLine();
				}
			}
			
		sc.close();	
	}
	
	/**
	 * Adds a GroceryItem item to the shopping bag. In order to add to shopping bag, it creates a new GroceryItem item
	 * by reading in the information provided by the input and adds it to groceryBag. 
	 * Then, it prints out that this specific item has been added to the bag.
	 * 
	 * @param sc (Scanner reads in input)
	 */
	private void addItem(Scanner sc) {
		String name = sc.next();
		double price = sc.nextDouble();
		boolean taxable = sc.nextBoolean();
		
		GroceryItem item = new GroceryItem(name, price, taxable);
		
		groceryBag.add(item);
		
		System.out.println(name + " added to the bag.");
	}
	
	/**
	 * Removes a GroceryItem item from the shopping bag. In order to remove the element from the shopping bag, 
	 * it first scans in the grocery items information and initializes a GroceryItem, and then removes this item by calling the 
	 * remove method of shopping bag. If the item exists, it is removed, and the method returns the name of the item, 
	 * its price and "removed." Else, if the item is not found in the bag, it returns "Unable to remove, this item is not in the bag."
	 * 
	 * @param sc (Scanner reads in input)
	 */
	private void removeItem(Scanner sc) {
		
		String name = sc.next();
		double price = sc.nextDouble();
		boolean taxable = sc.nextBoolean();
		
		
		GroceryItem item = new GroceryItem(name, price, taxable);
		
		if(groceryBag.remove(item)) {
			
			System.out.printf(name + " %.2f removed.\n", price);
		}
		else {
			System.out.println("Unable to remove, this item is not in the bag.");
		}
	}
	
	/**
	 * Prints the GroceryItems within our shopping bag by first checking for the size of the shopping bag. If the size is 0,
	 * we print "The bag is empty!". If the size is 1, we indicate there is only one item in the bag; if there are several items,
	 * we print out how many items there are. After outputting the number of elements, we print all of the GroceryItem items 
	 * out by calling the print method of ShoppingBag class.
	 * 
	 * @param sc (Scanner reads in input)
	 */
	private void printItems(Scanner sc) {
		int size = groceryBag.numOfItems();
		
		if(size == 0) {
			System.out.println("The bag is empty!");
			
		}
		else {
			if (size == 1) {
				System.out.println("**You have " + size + " item in the bag.");
			}
			else {
				System.out.println("**You have " + size + " items in the bag.");
			}
			
			groceryBag.print();
			System.out.println("**End of list");
		}
	}
	
	/**
	 * Simulates checking out items at a grocery shop by first checking if the bag is empty or not. Then it prints all 
	 * the items. After this, it prints out total price of all GroceryItem items and sales tax, along with the 
	 * actual total due (the sum of total price and sales tax), followed by making the shopping bag empty, since all items
	 * have been processed.
	 * 
	 * @param sc (Scanner reads in input)
	 */
	private void checkoutItems(Scanner sc) {
		
		int size = groceryBag.numOfItems();
		
		if(size == 0) {
			System.out.println("Unable to check out,the bag is empty!");
		}
		else {
			
			if (size == 1) {
				System.out.println("**Checking out " + size + " item.");
			}
			else {
				System.out.println("**Checking out " + size + " items.");
			}
			
			groceryBag.print();
			System.out.printf("*Sales total: $%.2f \n",groceryBag.salesPrice());
			System.out.printf("*Sales tax: $%.2f \n",groceryBag.salesTax());
			double finalTotal = groceryBag.salesPrice()+groceryBag.salesTax();
			System.out.printf("*Total amount paid: $%.2f \n",finalTotal);
			
		}
		groceryBag.makeEmpty();
	}
	
	/**
	 * Quits the shopping experience by checking out, if there are any items in the shopping bag,
	 * and quitting (regardless of the size of the shopping bag). If there are items in the shopping bag, we call
	 * the checkoutItems method, which simulates checking out, and then stop the shopping procedure by greeting. If there are
	 * no elements in the shopping bag, we just return the quit greeting ("Thanks for shopping with us!").
	 * 
	 * @param sc (Scanner reads in input)
	 */
	private void quitCheckout(Scanner sc) {
		
		int size = groceryBag.numOfItems();
		
		if(size != 0) {
			checkoutItems(sc);
		}
		
		System.out.println("Thanks for shopping with us!"); 
	}
}
