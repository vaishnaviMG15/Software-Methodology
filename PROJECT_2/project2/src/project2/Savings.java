

package project2;

/**
 * Savings class is a subclass of the Accounts class. The objects in this class have an 
 * additional property isLoyal (to see if the customer is loyal or not). This class implements
 * the abstract methods in the Accounts class according to the characteristics of savings account like its particular
 * interest and fee amounts. Moreover, the class overrides the toString and equals method of the Accounts class appropriately.
 * 
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Savings extends Account {
	
	private boolean isLoyal;
	
	/**
	 * This is a parameterized constructor which creates and object of this class with 
	 * the specified values for the data fields.
	 *
	 *@param holder, the Profile of the owner of this account
	 *@param balance, the initial starting balance amount within this account
	 *@param dateOpen, the date when this account was opened
	 *@param isLoyal, which tells whether or not this holder is loyal
	 */
	public Savings(Profile holder, double balance, Date dateOpen, boolean isLoyal) {
		
		super(holder, balance, dateOpen);
		
		this.isLoyal = isLoyal;
		
		
	}
	
	/**
	 * Calculates the monthly interest of this Savings account by dividing the total
	 * annual interest by 12. The total annual interest is the interest rate for
	 * savings account divided by a 100. There are two different rates based on whether the customer is loyal
	 * or not.This process is calculated in the constants. It then return the monthly interest.
	 * 
	 * 
	 * @return monthly interest of this savings account
	 */
	public double monthlyInterest() {
		
		double monthlyIr;
		
		
		if(isLoyal) {
			
			monthlyIr = super.getBalance()*Constants.isLoyalMonthlyInterestRate;
		}else {
			
			monthlyIr = super.getBalance()*Constants.savingsMonthlyInterestRate;
		}
		
		return monthlyIr;
		
	}
	
	/**
	 * monthlyFee() first finds the monthly fee that should be charged onto an account. 
	 * If the balance within that account is greater than the specified balance that amounts in a fee, 
	 * we change the value of the fee. 
	 * In the end, we return the value of the fee.
	 * 
	 * @return fee (the monthly fee charged on the account)
	 */
	public double monthlyFee(){
		
		double fee = Constants.savingsMonthlyFee;
		
		if(super.getBalance() >= Constants.savingsBalanceForFeeException) {
			fee = Constants.savingsExceptionalFee;
		}
		
		return fee;
		
	}
	
	/**
	 * This method creates a string version of this Savings account and returns it.
	 * The string version is the same as that of the parent class (Account) with an
	 * addition "*Savings" in the front and *special Savings account* if appropriate.
	 * 
	 * @return the string version of Savings account
	 */
	@Override
	public String toString() {
		
		if (isLoyal) {
			return "*Savings" + super.toString()  + "*special Savings account*";
		}
		else {
			return "*Savings" + super.toString();
		}
		
	}
	
	/**
	 * equals() is an override method that determines if the parameter  object is identical to the current Savings account.
	 * If the Object obj is an instance of Savings account and equals the super Account class, we return true (it is 
	 * equal to the current Savings account). In all other cases, we return false.
	 * 
	 * @return true or false, depending on if the parameter is equal to the current Savings account object
	 */
	@Override
	public boolean equals(Object obj) {
		
		
		if (super.equals(obj)) {
			
			if(!(obj instanceof Savings)){
				
				return false;
				
			}
			return true;
		}
		
		return false;
		
	}

}
