/**
 GroceryItem class is a blueprint for the object GroceryItem that has the properties of 
 name, price and if the item is taxable or not for creation.
 It also defines methods that allow a client to work with an instance of GroceryItem.
 
 @author Vaishnavi Manthena, Sanjana Pendharkar
 */
package project1;
import java.lang.Double;

public class GroceryItem {
	
	private String name;
	private double price;
	private boolean taxable;
	
	/**
	 * Creates a new GroceryItem object with the specified properties in parameters.
	 * @param name (a string of nonspace characters)
	 * @param price (a double)
	 * @param taxable (a boolean that determines whether tax should be applied or not)
	 */
	public GroceryItem(String name, double price, boolean taxable) {
		
		this.name = name;
		this.price = price;
		this.taxable = taxable;
		
	}
	
	/**
	 * Finds if a given object is (1) a GroceryItem and (2) is equivalent to the current GroceryItem instance.
	 * 
	 * @param obj (an object that needs to be compared with the current GroceryItem)
	 * @return true if obj is equivalent to current GroceryItem instance, false otherwise
	 */
	public boolean equals(Object obj) {
		
		if (!(obj instanceof GroceryItem)){
			return false;
		}
		
		GroceryItem o = (GroceryItem)obj;
		
		if(name.equals(o.name) && (Double.compare(o.price,price) == 0) && o.taxable == taxable) {
			return true;
		}
	
		return false;
		
	}
	
	/**
	 * Returns the String version of the object.
	 * The form of the string is name: unit_price: tax / tax free
	 *
	 * @return String version of the object 
	 */
	public String toString() {
		
		String tax = (taxable)? "is taxable": "tax free";
		String result = String.format(name + ":" + " $%.2f : "+ tax, price);
		
		return result;
		
	}
	
	/**
	 * Fetches the taxable property of the GroceryItem and returns it (taxable is a boolean)
	 * @return true if there is a tax on the GroceryItem, false otherwise
	 */
	public boolean isTaxable() {
		return taxable;
	}
	
	/**
	 * Fetches the price of the GroceryItem and returns it (price is a double)
	 * @return price of GroceryItem
	 */
	public double unitPrice() {
		return price;
	}
	

}
