
package project2;

/**
 * The account database class is a blue print of an object whose main property is an array of accounts.
 * This class provides the functionality to work with the different kinds of accounts in an AccountDatabase object.
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class AccountDatabase {
	
	private Account [] accounts;
	private int size;
	
	/**
	 *This is a non-parameterized constructor which creates an instance of Account class
	 *and initializes its data field with default values. 
	 * 
	 * Default size = 0
	 * Default accounts array is an accounts array of initial capacity 5.
	 * 
	 */
	public AccountDatabase() {
		size = Constants.initialSize;
		accounts = new Account[Constants.initialCapacity];
	}
	
	/**
	 * find() is a method that iterates through the accounts array and 
	 * returns the index of the parameterized account, 
	 * if it exists in the array. If the account does not exist, it returns -1.
	 * 
	 * @param account that we are trying to find in the accounts array
	 * @return index i if the account is in the accounts array, -1 if account cannot be found in array
	 */
	private int find(Account account) {
		for(int i = 0; i < size; i++) {
			if (accounts[i].equals(account)) {
				return i;
			}
		}
		return Constants.indexNotFound;
	}
	
	/**
	 * grow() increases the capacity of the accounts array by 5.
	 * First a new array of accounts is created with an additional length of five.
	 * Then the data values of current array account are transferred to the new array. 
	 * Then the "accounts" data field is updated to point to the new array. 
	 * 
	 */
	private void grow() {
		Account [] newaccounts = new Account[accounts.length+ Constants.growthFactor];
		for(int i = 0; i < accounts.length; i++) {
			newaccounts[i] = accounts[i];
		}
		accounts = newaccounts;
		
	}
	
	/**
	 * Add() is a method that adds the parameter account into the accounts array.
	 * It first finds the accounts index to ensure that the account does not already exist in the accounts array. 
	 * In the case that the accounts array has reached its maximum size, the method grows the accounts array and 
	 * increases its size by the capacity of 5. 
	 * It then inserts the parameter account into the end of the accounts array.
	 * 
	 * @param account that we are trying to add to our accounts array
	 * @return true if account was added, false if account cannot be found in accounts array
	 */
	public boolean add(Account account) {
		
		int index = find(account);
		if (index != Constants.indexNotFound) {
			return false;
		}
		
		accounts[size] = account;
		size++;
		
		if (size == accounts.length) {
			grow();
		}
		return true;
	}
	
	/**
	 * This method finds the account in the array. It return false if the account is not found.
	 * If the account is found at the last position and then it simply sets the last element to null,
	 * else it makes the element at the position point to the last element and then sets the last element to null.
	 * When an element is found and removed the size is reduced by 1 and true is returned.
	 * 
	 * @param account to remove (Account account)
	 * @return true if account was found and hence successfully removed, false otherwise
	 */
	public boolean remove(Account account) {
		
		int indexToRemove = find(account);
		
		if(indexToRemove == Constants.indexNotFound) {
			return false;
		}
		
		if(indexToRemove == (size - 1)) {
			accounts[size-1] = null;
			
		}else {
			
			accounts[indexToRemove] = accounts[size - 1];
			accounts[size-1] = null;
		}
		
		size--;
		return true;
		
	}
	
	/**
	 * deposit() is a method that adds a specific parameter amount to the parameterized account object.
	 * First, it checks to see if account is a part of the accounts array. If the account is not found, then we return false. 
	 * Once the account is found, we add the specific amount to the account’s balance.
	 * 
	 * @param account, which is the Account object we want to add an amount to
	 * @param amount that we want to deposit (amount)
	 * @return true if deposit amount was added to account, false if account was not found and deposit amount cannot be added
	 */
	public boolean deposit(Account account, double amount){
		
		int userAccountIndex = find(account);
		if (userAccountIndex == Constants.indexNotFound) {
			return false;
		}
		accounts[userAccountIndex].credit(amount);
		return true;
		
	}
	
	/**
	 *This method tries the find the account in the array, and if the account is found then the balance is checked to see if
	 *it is sufficient for the withdrawal. If it is then the balance of that account is updated by decrementing it by the amount.
	 * 
	 * @param account to withdraw from (Account account)
	 * @param amount to withdraw from account (amount)
	 * @return -1 if account is not found, 1 if account does not have enough balance, 0 if account is found and has sufficient balance
	 */
	public int withdrawal (Account account, double amount) {
		
		int userAccountIndex = find(account);
		
		if (userAccountIndex == Constants.indexNotFound) {
			return -1;
		}
		
		if(accounts[userAccountIndex].getBalance() < amount) {
			return 1;
		}
		
		accounts[userAccountIndex].debit(amount);
		
		if(accounts[userAccountIndex] instanceof MoneyMarket) {
			
			MoneyMarket m = (MoneyMarket)accounts[userAccountIndex];
			m.updateWithdrawals();
		}
		
		return 0;
		
	}
	
	/**
	 * exchangeAccount() is a helper method that swaps two accounts in the accounts array using a standard swapping procedure.
	 * 
	 * @param array of Accounts that we will take accounts to swap from
	 * @param index1 of first account
	 * @param index2 of second account
	 */
	private static void exchangeAccount(Account[] array, int index1, int index2) {
		
		Account temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		
	}
	
	/**
	 * sortByDateOpen() is a method that uses selection sort to find the earliest date,
	 * and arranges the accounts array in ascending order of dates.
	 */
	private void sortByDateOpen() {
		
		for (int i = 0; i < size - 1; i++) {
			int minimum = i;
			for (int j = i+1; j < size; j++) {
				if ((accounts[j].getDate()).compareTo(accounts[minimum].getDate()) == -1) {
					minimum = j;
				}
			}
			exchangeAccount(accounts, i, minimum);
		}
		
	}
	
	/**
	 * sortByLastName() sorts the accounts in the array of Accounts in ascending order of the last name of the holder. 
	 * The selection sort algorithm is used.
	 * 
	 */
	private void sortByLastName() {
		
		for (int i = 0; i < size - 1; i++) {
			int minimum = i;
			for (int j = i+1; j < size; j++) {
				if ((accounts[j].getHolder().getLastName()).compareTo(accounts[minimum].getHolder().getLastName()) < 0) {
					minimum = j;
				}
			}
			exchangeAccount(accounts, i, minimum);
		}
		
	}
	
	/**
	 * printByDateOpen() prints out the list of accounts in ascending order of their openDate.
	 * The list includes the string versions of the accounts, the monthly interest, monthly fee, and updated balance.
	 * Also, a call to this function ensures that the balance in all accounts are updated by increasing the value according to interest
	 * and decreasing the value according to the free. The call to printAccounts update is responsible for this
	 * functionality.
	 */
	public void printByDateOpen() {
		if(size == 0) {
			System.out.println("Database is empty.");
			return;
		}
		System.out.println();
		System.out.println("--Printing Statements by date open--\n");
		sortByDateOpen();
		printAccountsUpdate();
		System.out.println("--End of printing--\n");
		
	}
	
	/**
	 * printByLastName() prints out the list of accounts in ascending order of their last names.
	 * The list includes the string versions of the accounts, the monthly interest, monthly fee, and updated balance.
	 * Also, a call to this function ensures that the balance in all accounts are updated by increasing the value according to interest
	 * and decreasing the value according to the free. The call to printAccounts update is responsible for this
	 * functionality.
	 */
	public void printbyLastName() {
		if(size == 0) {
			System.out.println("Database is empty.");
			return;
		}
		System.out.println();
		System.out.println("--Printing Statements by last name--\n");
		sortByLastName();
		printAccountsUpdate();
		System.out.println("--End of Printing--\n");
		
	}
	
	/**
	 * printAccounts() prints out the string versions of all accounts in the
	 * accounts array data field. 
	 * 
	 */
	public void printAccounts() {
		if(size == 0) {
			System.out.println("Database is empty.");
			return;
		}
		System.out.println("--Listing accounts in database--");
		for (int i = 0; i < size; i++) {
			String accountDetails = accounts[i].toString();
			System.out.println(accountDetails);
		}
		System.out.println("--end of listing--");
	}
	
	/**
	 * printAccountsUpdate() is a method that prints out all the accounts in the accounts array.
	 * For each account, it prints out:
	 * 1. The string version of the account
	 * 2. The monthlyInterest
	 * 3. The monthlyFee
	 * 4. The new monthly balance, which is the old monthly balance + monthlyInterest - monthlyFee
	 * 
	 * Moreover, the balance of individual accounts is updated accordingly.
	 * 
	 */
	private void printAccountsUpdate() {
		for (int i = 0; i < size; i++) {
			String accountDetails = accounts[i].toString();
			System.out.println(accountDetails);
			
			double monthlyFee = accounts[i].monthlyFee();
			double monthlyInterest = accounts[i].monthlyInterest();
			(accounts[i]).credit(monthlyInterest);
			(accounts[i]).debit(monthlyFee);
			//double newBalance = accounts[i].getBalance() + monthlyInterest - monthlyFee;
			
			System.out.printf("-interest: $%.2f \n", monthlyInterest);
			System.out.printf("-fee: $%.2f \n", monthlyFee);
			System.out.printf("-New Balance: $%.2f \n\n", accounts[i].getBalance());
			
		}
	}
	
}
