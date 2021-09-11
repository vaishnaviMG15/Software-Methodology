
package project2;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The Transaction Manager class is a blueprint for an object which has one datafield: the account database bank.
 * This class provides methods in order create a real life banking experience in order to allow the user to
 * add accounts in a bank, close accounts in a bank, deposit or withdraw from a particular account, and examine a list of the
 * accounts and their details in particular ways. 
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class TransactionManager {
	
	private AccountDatabase bank;
	
	/**
	 *This is a non-parameterized constructor which creates an instance of TransactionManager class
	 *and initializes its data field bank with a new AccountDatabase instance. 
	 * 
	 */
	public TransactionManager() {
		
		bank = new AccountDatabase();
		
	}
	
	/**
	 * The run method is responsible for taking in the first command per each input line and performing a respective action.
	 * Based on whether this command requires the opening of an account(O), the closing of the account(C), 
	 * depositing money into an account (D), withdrawing money from an account(W), or terminating this process (Q), the run method
	 * calls the required helper methods to perform the task. Also, this method prints out the required output to indicate an
	 * invalid input.
	 * 
	 */
	public void run() {
		
		System.out.println("Transaction processing starts...");
		
		Scanner sc =  new Scanner(System.in);
		
		while(sc.hasNext()) {
			
			String input = sc.next();
			
			if(input.length() > Constants.inputNotSupported) {
				System.out.println("Command '" + input+ "' not supported!");
				sc.nextLine();
				continue;
			}
			
			if(input.length() == 1) {
				if(input.charAt(0) == 'Q') {
					System.out.println("Transaction process completed.");
					sc.close();
					return;
				}else {
					System.out.println("Command '" + input+ "' not supported!");
					sc.nextLine();
					continue;
				}
			}
			
			char command = input.charAt(0);
			char accountType = input.charAt(1);
			
			switch(command) {
			
			case 'O':
				
				if(accountType != 'C'&& accountType != 'S' && accountType != 'M') {
					
					System.out.println("Command '" + input+ "' not supported!");
					sc.nextLine();
					continue;
				}
				
				add(sc, accountType);
				break;
				
			case 'C':
				
				close(sc, accountType, input);
				break;
				
			case 'D':
				
				deposit(sc, accountType, input);
				break;
				
			case 'W':
				
				withdraw(sc, accountType, input);
				break;
			
			case 'P':
				
				if(accountType != 'A' &&  accountType != 'D' && accountType != 'N') {
					System.out.println("Command '" + input+ "' not supported!");
					sc.nextLine();
					continue;
				}
				
				print(sc, accountType);
				
				break;
				
			default: 
				
				System.out.println("Command '" + input+ "' not supported!");
				sc.nextLine();
			
			}
				
		}
		
	}
	
	/**
	 * Returns the corresponding account to open by creating a new account of the respective type (checking, savings, Money Market)
	 * by reading in the input. If the input does not provide correct/valid information (for example an invalid double balance, or
	 * an invalid boolean value) then an exception occurs and the method returns null
	 * 
	 * @param sc (Scanner to read input)
	 * @param accountType (C for checking, S for savings, M for moneyMarket)
	 * @return account that needs to be opened 
	 */
	private static Account getAccountToOpen(Scanner sc, char accountType) {
		
		String firstName = sc.next();
		String lastName = sc.next();
		Profile holder = new Profile(firstName, lastName);
		
		Double money;
		try {
			money = sc.nextDouble();
		}catch(InputMismatchException e) {
			System.out.println("Input datatype mismatch."); 
			return null;
		}
		
		String dateInput = sc.next();
		StringTokenizer tokenizedDate = new StringTokenizer(dateInput, "/");
		int month = Integer.parseInt(tokenizedDate.nextToken());
		int day = Integer.parseInt(tokenizedDate.nextToken());
		int year = Integer.parseInt(tokenizedDate.nextToken());
		Date date = new Date(year, month, day);
		
		if(!date.isValid()) {
			System.out.println(date.toString() + " is not a valid date!");
			return null;
		}
		
		Boolean property = false;
		if(accountType == 'C' | accountType == 'S') {
			
			try {
				property = sc.nextBoolean();
			}catch(InputMismatchException e) {
				System.out.println("Input datatype mismatch."); 
				return null;
			}
				
		}
		
		Account account = null;
		
		if(accountType == 'C') {
			
			account = new Checking(holder, money, date, property);
			
		}
		if(accountType == 'S') {
			
			account = new Savings(holder, money, date, property);
			
		}
		if(accountType == 'M') {
			
			account = new MoneyMarket(holder, money, date, Constants.initialWithdrawals);
		}
		
		return account;
		
	}
	
	/**
	 * This method creates and returns an account that we need to find in the 'bank' data field and then
	 * either deposit to, withdraw from, or close this account. The method reads the user input to check the account type to create.
	 * The method also scans in information from the input stream to get the name of the holder of the account.
	 * The holder and account Type are enough to identify an account in the database, so the other data fields like
	 * balance, date open, and boolean values are given random/default values. The account is created and returned from this 
	 * information. If the account Type is not valid then the method returns null. 
	 * 
	 * @param sc (Scanner to read input)
	 * @param accountType (C for checking, S for savings, M for moneyMarket)
	 * @param input, which provides a command (like WC, DS, CM, ...)
	 * @return account that we need to search for in order to deposit, withdraw, or close
	 */
	private static Account depositWithdrawClose(Scanner sc, char accountType, String input) {
		
		if(accountType != 'C' &&  accountType != 'S' && accountType != 'M') {
			System.out.println("Command '" + input+ "' not supported!");
			return null;
		}
		
		String firstName = sc.next();
		String lastName = sc.next();
		Profile holder = new Profile(firstName, lastName);
		Date defaultDate = new Date(Constants.randomYear, Constants.randomMonth, Constants.randomDay);
		
		Account testerAccount = null;
		
		if(accountType == 'C') {
			
			testerAccount = new Checking(holder, Constants.randomBalance, defaultDate, Constants.randomBoolean);
			
		}
		if(accountType == 'S') {
			
			testerAccount = new Savings(holder, Constants.randomBalance, defaultDate, Constants.randomBoolean);
			
		}
		if(accountType == 'M') {
			
			testerAccount = new MoneyMarket(holder, Constants.randomBalance, defaultDate, Constants.initialWithdrawals);
		}
		
		return testerAccount;
		
	}
	
	/**
	 * This method performs the add functionality. It calls getAccountToOpen to retrieve the account according to input. 
	 * Then it calls the add method of the accountDatabase class. Based on whether the addition was successful or nor
	 * the respective output will be printed on the console.
	 * 
	 * @param sc (Scanner to read input)
	 * @param accountType (C for checking, S for savings, M for moneyMarket)
	 */
	private void add(Scanner sc, char accountType) {
		
		Account account = null;
		
		account = getAccountToOpen(sc, accountType);
		
		if(account != null) {
			if(bank.add(account)) {
				
				System.out.println("Account opened and added to the database.");
				
			}else{
				System.out.println("Account is already in the database.");
			}
		}else {
			sc.nextLine();
			
		}
		
	}
	
	/**
	 * This method performs the close functionality. It calls depositWithdrawClose method to retrieve the account according to input. 
	 * Then it calls the remove method of the accountDatabase class. Based on whether the removal was successful or nor
	 * the respective output will be printed on the console.
	 * 
	 * @param sc (Scanner to read input)
	 * @param accountType (C for checking, S for savings, M for moneyMakret)
	 * @param input command (like WC, DS, CM, ...)
	 */
	private void close(Scanner sc, char accountType, String input) {
		
		Account testerAccount = depositWithdrawClose(sc, accountType, input);
		
		if(testerAccount != null) {
			if (bank.remove(testerAccount) == true) {
				System.out.println("Account closed and removed from the database.");
			}
			else {
				System.out.println("Account does not exist.");
			}
		}else {
			sc.nextLine();
		}
		
	}
	
	/**
	 * This method performs the deposit functionality. It calls depositWithdrawClose method to retrieve the account according to input. 
	 * Then it calls the deposit method of the accountDatabase class. Based on whether the deposit was successful or nor
	 * the respective output will be printed on the console.
	 * 
	 * @param sc (Scanner to read input)
	 * @param accountType (C for checking, S for savings, M for moneyMarket)
	 * @param input command (like WC, DS, CM, ...)
	 */
	private void deposit(Scanner sc, char accountType, String input) {
		
		Account testerAccount = depositWithdrawClose(sc, accountType, input);
		
		if(testerAccount != null) {
			double depositAmount;
			try {
				depositAmount = sc.nextDouble();
				if (bank.deposit(testerAccount, depositAmount) == true) {
					System.out.printf("%.02f deposited to account.\n", depositAmount);
				}
				else {
					System.out.println("Account does not exist.");
				}
			}catch(InputMismatchException e) {
				System.out.println("Input datatype mismatch.");
				sc.nextLine();
			}
		}else {
			sc.nextLine();
		}
		
	}
	
	/**
	 * This method performs the withdraw functionality. It calls depositWithdrawClose method to retrieve the account according to input. 
	 * Then it calls the withdraw method of the accountDatabase class. Based on whether the withdrawal was successful or nor
	 * the respective output will be printed on the console.
	 * 
	 * @param sc (Scanner to read input)
	 * @param accountType (C for checking, S for savings, M for moneyMakret)
	 * @param input, which provides a command (like WC, DS, CM, ...)
	 */
	private void withdraw(Scanner sc, char accountType, String input) {
		Account testerAccount = depositWithdrawClose(sc, accountType, input);
		double withdrawAmount;
		
		
		if (testerAccount != null) {
			try {
				withdrawAmount = sc.nextDouble();
				int value = bank.withdrawal(testerAccount, withdrawAmount);
			
				if (value == -1) {
					System.out.println("Account does not exist.");
				
				}else if (value == 1) {
					System.out.println("Insufficient funds.");
				}else {
					System.out.printf("%.02f withdrawn from account.\n", withdrawAmount);
				}
			}catch(InputMismatchException e) {
				System.out.println("Input datatype mismatch.");
				sc.nextLine();
			}
		}
		else {
			sc.nextLine();
		}
	}
	
	/**
	 * This method calls the respective methods of account database in order to print the accounts regularly or according 
	 * to their openDate or lastName.
	 * 
	 * @param sc (Scanner that reads the input)
	 * @param operation (A to print accounts regularly, D to print by dateOpen, N to print by last name) 
	 * 
	 */
	private void print(Scanner sc, char operation) {
		
		if (operation == 'A') {
			bank.printAccounts();
		}
		
		if (operation == 'D') {
			bank.printByDateOpen();
		}
		
		if (operation == 'N') {
			bank.printbyLastName();
		}
		
	}
	

}
