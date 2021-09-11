

package project2;

/**
 * 
 * MoneyMarket class is a subclass of the Accounts class. The objects in this class have an 
 * additional property 'withdrawals'. This class implements the abstract methods in the Accounts class according 
 * to the particular characteristics of a MoneyMarket account like its interest and fee amounts. 
 * Moreover, the class overrides the toString and equals method of the Accounts class appropriately.
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class MoneyMarket extends Account{
	
	private int withdrawals;
	
	/**
	 * This is a parameterized constructor which creates and object of this class with the specified values 
	 * for the data fields. 
	 *
	 *@param holder, the Profile of this account's owner
	 *@param balance, the initial starting balance amount for this account
	 *@param dateOpen, the date when this account was opened
	 *@param withdrawals, the number of withdrawals that have been made for this account
	 */
	public MoneyMarket(Profile holder, double balance, Date dateOpen, int withdrawals) {
		
		super(holder, balance, dateOpen);
		
		this.withdrawals = withdrawals;
		
	}
	
	/**
	 * This method increases the number of withdrawals for this account by 1.
	 * 
	 */
	public void updateWithdrawals() {
		withdrawals++;
	}
	
	/**
	 * Calculates the monthly interest of this MoneyMarket account by dividing the total
	 * annual interest by 12. The total annual interest is the interest rate for
	 * MoneyMarket account divided by 100. It then return the monthly interest.
	 * 
	 * @return monthly interest of this MoneyMarket account
	 */
	public double monthlyInterest() {
		
		
		double monthlyIr = super.getBalance()*Constants.moneyMarketMonthlyInterestRate;
		return monthlyIr;
		
	}
	
	/**
	 * monthlyFee() first finds the monthly fee that should be charged onto an account. 
	 * If the balance within that account is greater than the specified balance and the number of withdrawals
	 * is less than or equal to 6, then there would be a different fee. In the end, we return the value of the fee.
	 * 
	 * @return fee (the monthly fee charged on the account)
	 */
	public double monthlyFee(){
		
		double fee = Constants.moneyMarketMonthlyFee;
		
		if(super.getBalance() >= Constants.moneyMarketBalanceForFeeException && (withdrawals <= Constants.moneyMarketMaxWithdrawalsForFeeException) ) {
			fee = Constants.moneyMarketExceptionalFee;
		}
		
		return fee;
		
	}
	
	/**
	 * This method creates a string version of this MoneyMarket account and returns it.
	 * The string version is the same as that of the parent class (Account) with an
	 * addition "*Money Market" in the front and the number of withdrawals at the end.
	 * 
	 * @return the string version of MoneyMarket account
	 */
	@Override
	public String toString() {
		String numberWithdrawals = "";
		
		if (withdrawals == Constants.moneyMarketWithdrawalsToString) {
			numberWithdrawals = "1 withdrawal";
		}
		else {
			numberWithdrawals = withdrawals + " withdrawals";
		}
		return "*Money Market" + super.toString() + "*" + numberWithdrawals;
		
	}
	
	/**
	 * equals() is an override method that determines if the parameter  object is identical to the account already existing.
	 * If the Object obj is an instance of MoneyMarket account and equals the super Account class, we return true (it is 
	 * equal to the previously existing MoneyMarket account). In all other cases, we return false.
	 * 
	 * @param obj Object to which this account's instance needs to be compared
	 * @return true or false, depending on if the parameter is equal to the existing MoneyMarket account object
	 */
	@Override
	public boolean equals(Object obj) {
		
		
		if (super.equals(obj)) {
			
			if(!(obj instanceof MoneyMarket)){
				
				return false;
				
			}
			return true;
		}
		
		return false;
		
	}
	

}
