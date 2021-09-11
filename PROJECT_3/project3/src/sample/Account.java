
package sample;

/**
 * This class is a blueprint for the account object. An account object encapsulates information about 
 * the holder of the account, the balance in the account, and the date on which the account is opened.
 * It's functionality includes updating the balance, conversion to a string, retrieving information (getter methods), and
 * comparing it's instances with other objects. This is an abstract class and provides abstract methods which its children have to
 * implement.
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public abstract class Account {
	
	private Profile holder;
	private double balance;
	private Date dateOpen;
	
	/**
	 * This is a parameterized constructor which creates and object of this class with the specified values 
	 * for the data fields.
	 * 
	 * @param holder (of Type Profile) of the account
	 * @param balance in account
	 * @param dateOpen when the account is opened
	*/
	public Account(Profile holder, double balance, Date dateOpen) {
		
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
		
	}
	
	/**
	 * Decreases the balance of this object by the specified amount
	 * 
	 * @param amount by which balance has to be decreased
	*/
	public void debit(double amount) { //decreases the balance by amount
		
		balance = balance - amount;
		
	}
	
	/**
	 * Increases the balance of this object by the specified amount
	 * 
	 * @param amount by which balance has to be increased
	 */
	public void credit(double amount) { //increases the balance by amount
		
		balance = balance + amount;
		
	}
	
	/**
	 * Converts the object into a string of the form *firstname lastname* $Balanace* dateOpen
	 * 
	 *
	 * @return String version of the object
	 */
	@Override
	public String toString() {
		
		String name = holder.getFirstName() + " " + holder.getLastName();
		
		String amount = String.format("$%.2f", balance);
		
		String date = dateOpen.toString();
		
		return "*" + name + "*" + amount + "*" + date;
		
	}
	
	/**
	 * Returns a reference to the holder of current object
	 * 
	 * @return holder (of Type Profile) of the object
	 */
	public Profile getHolder() {
		return holder;
	}
	
	/**
	 * Retrieves this object's balance
	 * 
	 * @return returns the balance in this object
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Retrieves this object's date
	 * 
	 * @return date (of type date) of this object
	 */
	public Date getDate() {
		return dateOpen;
	}
	
	/**
	 * Checks if the object is an instance of account class and has the same holder
	 * 
	 * @param obj, which we are comparing current account to
	 * @return true if the two objects are equal, else false
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Account)){
			
			return false;
			
		}
		
		Account o = (Account)obj;
		
		if ((holder.getFirstName()).equals(o.holder.getFirstName()) && (holder.getLastName()).equals(o.holder.getLastName())) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * This is an abstract method that needs to be specifically implemented in the child classes. 
	 * It calculates the monthly interest for this specific account and returns it. This interest varies based on 
	 * account type and other details.
	 * 
	 * @return the double monthly interest 
	 */
	public abstract double monthlyInterest();
	
	/**
	 * This is an abstract method that needs to be specifically implemented in the child classes. 
	 * It calculates the monthly fee for this specific account and returns it. This fee varies based on 
	 * account type and other details.
	 * 
	 * @return the double monthly fee 
	 */
	public abstract double monthlyFee();
	
	

}
