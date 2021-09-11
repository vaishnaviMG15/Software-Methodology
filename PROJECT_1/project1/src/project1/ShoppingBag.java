/**
 * ShoppingBag class is a blueprint for a shopping bag item that stores an array of GroceryItem objects.
 * It provides methods to work with the GroceryItem objects in the shopping bag.
 * It starts with a capacity of 5 and size of 0, and grows as necessary.
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
package project1;


public class ShoppingBag {
	
	static final double taxPercent = 0.06625;
	
	private GroceryItem[] bag;
	private int size; // Number of items currently in the bag
	private int capacity; // Current capacity
	
	/**
	 * Creates a ShoppingBag object with an array of 5 null GroceryItems, a size of 0 and a capacity
	 * (for storing items) of 5.
	 * 
	 */
	public ShoppingBag() {
		
		bag = new GroceryItem[5];
		size = 0;
		capacity = 5;
		
	}
	
	/**
	 * Fetches the location of a GroceryItem item in the shopping bag.
	 * @param item (an instance of GroceryItem)
	 * @return the index of the item if it exists in the bag, otherwise returns -1 (item was not found)
	 */
	private int find(GroceryItem item) { 
		
		
		
		for (int i = 0; i < size; i++) {
			if( (bag[i]).equals(item)) {
				return i;
			}
		}
		
		return -1;
		
	}
	
	/**
	 * Grows the capacity of the shopping bag by creating a new array that contains all of the items in the original shopping bag,
	 * and has 5 more empty GroceryItem spaces that can be filled.
	 */
	private void grow() {
		
		GroceryItem[] largerBag = new GroceryItem[capacity + 5];
		
		for (int i = 0; i < capacity; i++) {
			largerBag[i] = bag[i];
		}
		
		bag = largerBag;
		capacity += 5;
		
	}
	
	/**
	 * Adds a GroceryItem item to the shopping bag, increases its size by 1, and checks if the shopping bag is full 
	 * (when the bag's size is equivalent to its capacity). If it is, we grow the bag.
	 * 
	 * @param item (an instance of GroceryItem)
	 * 
	 */
	public void add(GroceryItem item) {
		
		bag[size] = item;
		size ++;
		
		if(size == capacity) {
			grow();
		}
		
		
	}
	
	/**
	 * Removes a GroceryItem item from the Shoppingbag by first finding the item's index. Then, it 
	 * checks to see if the index is valid (meaning it is not -1). If the index is equal to the size (meaning
	 * the object is in the last position) we set the last reference to null. Else we make the GroceryItem 
	 * at the index refer to the object referred by the last GoceryItem, and we set the last GrocerItem to null.
	 * 
	 * @param item (an instance of GroceryItem)
	 * @return true if the item was able to be removed, false otherwise
	 */
	public boolean remove(GroceryItem item) {
		
		int indexToRemove = find(item);  
		
		
		if(indexToRemove == -1) {
			return false;
		}
		
		if( indexToRemove != (size - 1)) {
			bag[indexToRemove] = bag[size - 1];
			bag[size - 1] = null;
		}
		else {
			
			bag[indexToRemove] = null;
		}
		
		size --;
		return true;
		
	}
	
	/**
	 * Finds the total price of all of the GroceryItem items in the shoppingBag by iterating 
	 * through the array shoppingBag, and adding the price of every item to the variable for the 
	 * total price (totalSales).
	 * 
	 * @return value of total price (of all GroceryItems in shopping bag)
	 */
	public double salesPrice() {
		
		double totalSales = 0.0;
		
		for (int i = 0; i < size; i++) {
			totalSales = totalSales + bag[i].unitPrice();
		}
		
		return totalSales;
		
	}
	
	/**
	 * Finds the total sales tax by iterating through the shoppingBag to get the total price of 
	 * all GroceryItem items in the bag which are taxable, and then multiplying this total price by the 
	 * tax percent (0.06625).
	 * 
	 * @return tax, which represents the total sales tax
	 */
	public double salesTax() {
		
		double tax = 0.0;
		
		for (int i = 0; i < size; i++) {
			
			if(bag[i].isTaxable()) {
				
				tax = tax + bag[i].unitPrice();
			}
		}
		
		tax = tax * taxPercent;
		
		return tax;
		
	}
	
	/**
	 * Prints out the string version of all of the GroceryItem items in the shopping bag.
	 * 
	 */
	public void print() {
		
		for (int i = 0; i < size; i++) {
			
			System.out.println("·" + bag[i].toString());
			
		}
		
	}
	
	/**
	 * Finds the number of GroceryItem items in the shoppingBag, which is equivalent to the size of the shopping bag.
	 * 
	 * @return size of shopping bag
	 */
	public int numOfItems() {
		return size;
	}
	
	/**
	 * Sets all of the GroceryItem items in the shopping bag to null, which empties the contents of the shopping bag.
	 * Then, it sets the size of the shopping bag to 0, as it is no longer filled with any items.
	 * 
	 */
	public void makeEmpty() {
		
		for(int i = 0; i < size; i++) {
			
			bag[i] = null;
			
		}
		
		size = 0;
		
	}
	
	/**
	 * This is our testbed main method. Every important line is commented with its purpose.
	 * It tests the add, remove, grow, and SalesTax methods of ShoppingBag class.
	 * 
	 * @param args (takes in an input stream)
	 */
	public static void main (String args[]) {
		
		//create an object of ShoppingBag class, and
		//create 6 grocery items to use in tests.
		
		ShoppingBag shoppingBag = new ShoppingBag();
		GroceryItem item1 = new GroceryItem("pineapples", 2.99, true);
		GroceryItem item2 = new GroceryItem("olives", 2.12, false);
		GroceryItem item3 = new GroceryItem("red_bell_peppers", 4.53, true);
		GroceryItem item4 = new GroceryItem("pizza_dough", 12.10, false);
		GroceryItem item5 = new GroceryItem("sauce", 2.92, false);
		GroceryItem item6 = new GroceryItem("cheese", 4.58, true);
		
		//Add four values to it and print out the items in the bag
		shoppingBag.add(item1);
		shoppingBag.add(item2);
		shoppingBag.add(item3);
		shoppingBag.add(item4);
		
		//Make sure size is 4 and capacity is 5
		System.out.println("There are " + shoppingBag.size + " items in your bag.");
		System.out.println("This is the capacity of your bag: " + shoppingBag.capacity + ".");
		shoppingBag.print();
		
		//Add remaining 2 items
		shoppingBag.add(item5);
		shoppingBag.add(item6);
		
		//Make sure size is 6 and capacity is 10
		System.out.println("There are " + shoppingBag.size + " items in your bag.");
		System.out.println("This is the capacity of your bag: " + shoppingBag.capacity + ".");
		shoppingBag.print();
		
		//calculate your own sales tax for these 6 items and makes sure it matches
		//the output
		
		double correctSalesTax = (2.99 + 4.53 + 4.58)* taxPercent;
		if (Double.compare(shoppingBag.salesTax(),correctSalesTax) == 0 ){
			System.out.printf("Correct sales tax: %.2f!\n", correctSalesTax);
		}
		else {
			System.out.printf("Incorrect sales tax. The sales tax should be %.2f\n", correctSalesTax);
		}
		
		//remove the last item from the bag
		if(shoppingBag.remove(item6)) {
			System.out.println("Test passed: remove method returned true, because item is in the bag. ");
		}
		
		//remove item6 (last item in bag, which has already been removed)
		if(shoppingBag.remove(item6) == false) {
			System.out.println("Test passed: remove method returned false, because item is no longer in the bag. ");
		}
		
		//remove a middle item from the bag
		if(shoppingBag.remove(item2)) {
			System.out.println("Test passed: remove method returned true, because item is in the bag. ");
		}
		
		//remove the first item from the bag
		if(shoppingBag.remove(item1)) {
			System.out.println("Test passed: remove method returned true, because item is in the bag. ");
		}
		
		//try to remove an item that never existed in the bag
		GroceryItem notIncluded = new GroceryItem("yam", 3.44, true);
		if(shoppingBag.remove(notIncluded) == false) {
			System.out.println("Test passed: remove method returned false because item was not in the bag. ");
		}
		
		//Make sure size is 3 and capacity is 10
		System.out.println("There are " + shoppingBag.size + " items in your bag.");
		shoppingBag.print();
		
		//grow the size of the bag and display its size and length of bag
		//currently our bag has a capacity of 10 and size of 3
		shoppingBag.grow();
		
		//currently our bag has a capacity of 15 and size of 3
		System.out.println("There are " + shoppingBag.size + " items in your bag.");
		System.out.println("This is the capacity of your bag: " + shoppingBag.capacity + ".");
		
	}
}
