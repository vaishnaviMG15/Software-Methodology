
package sample;

/**
 * This class is a subclass of the Accounts class. The objects in this class have an 
 * additional property 'directDeposit'. This class implements the abstract methods in the Accounts class 
 * according to the particular characteristics of a Checking account like its interest and fee
 * calculation. Moreover, the class overrides the toString and equals method of the Accounts class appropriately.
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 * 
 */
public class Checking extends Account {
	
	private boolean directDeposit;
	
	/**
	 * This is a parameterized constructor which creates and object of this class with the specified values 
	 * for the data fields.
	 *
	 *@param holder, which is the Profile we are initializing this account for
	 *@param balance, an initial balance amount for this account
	 *@param dateOpen, the date the account was opened
	 *@param directDeposit, which tells us if this account is a direct deposit account or not
	 */
	public Checking(Profile holder, double balance, Date dateOpen, boolean directDeposit) {
		
		super(holder, balance, dateOpen);
		
		this.directDeposit = directDeposit;
		
	}

	/**
	 * Getter method which return whether or not the checking account is money market.
	 *
	 * @return true if checking account is direct deposit, false otherwise
	 */
	public boolean isDirectDeposit(){
		return directDeposit;
	}
	
	/**
	 * Calculates the monthly interest of this checking account by dividing the total
	 * annual interest by 12. The total annual interest is the interest rate for
	 * checking account divided by a 100. It then return the monthly interest.
	 * 
	 * 
	 * @return monthly interest of this checking account
	 */
	@Override
	public double monthlyInterest() {
		
		
		double monthlyIr = super.getBalance() * Constants.checkingMonthlyInterestRate;
		
		return monthlyIr;
		
	}

	/**
	 * monthlyFee() first finds the monthly fee that should be charged onto an account. 
	 * If the balance within that account is greater than the specified balance that amounts in a fee and the 
	 * account is a direct deposit account, we change the value of the fee. 
	 * In the end, we return the value of the fee.
	 * 
	 * @return fee (the monthly fee charged on the account)
	 */
	@Override
	public double monthlyFee(){
		
		double fee = Constants.checkingMonthlyFee;
		
		if(super.getBalance() >= Constants.checkingBalanceForFeeException || directDeposit == true) {
			fee = Constants.checkingExceptionalFee;
		}
		
		return fee;
		
	}
	
	/**
	 * This method creates a string version of this Checking account and returns it.
	 * The string version is the same as that of the parent class (Account) with an
	 * addition "*Checking" in the front and *direct deposit account* if appropriate.
	 * 
	 * @return the string version of Checking account
	 */
	 @Override
	public String toString() {
		
		if (directDeposit) {
			return "*Checking" + super.toString()  + "*direct deposit account*";
		}
		else {
			return "*Checking" + super.toString();
		}
		
	}
	
	/**
	 * equals() is an override method that determines if the parameter  object is identical to the current account.
	 * If the Object obj is an instance of Checking account and equals the super Account class, we return true (it is 
	 * equal to the current Checking account). In all other cases, we return false.
	 * 
	 * @param obj to which this account's instance needs to be compared
	 * @return true or false, depending on if the parameter is equal to the current Checking account object
	 */
	 @Override
	public boolean equals(Object obj) {
		
		
		if (super.equals(obj)) {
			
			if(!(obj instanceof Checking)){
				
				return false;
				
			}
			return true;
		}
		
		return false;
		
	}

}
