/**
 * 
 */
package project2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the monthlyInterest and monthlyFee method of the Checking class
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 * 
 *
 */
class CheckingTest {
	
	Checking acc1;
	Checking acc2;
	Checking acc3;
	Checking acc4;

	/**
	 * This sets up the instances of the checking objects to use in the test cases
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		Date d1 = new Date (2018, 5, 7);
		Profile h1 = new Profile("Jane", "Doe");
		
		acc1 = new Checking(h1, 1000, d1, false);
		
		acc2 = new Checking(h1, 950, d1, true);
		
		acc3 = new Checking(h1, 3000, d1, true);
		
		acc4 = new Checking(h1, 1500, d1, false);
	}

	/**
	 * This performs tear down action at the end of every time we run this class
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link project2.Checking#monthlyInterest()}.
	 */
	@Test
	void testMonthlyInterest() {
		
		//Interest for all of the object instances has to be calculated only according the the balance
		//Annual interest rate is fixed at 0.05%
		
		// account 1 has a balance of 1000, which should give a monthly interest of 1/24
		double d11 = acc1.monthlyInterest() - (1.00/24.00);
		double d12 = (1.00/24.00) - acc1.monthlyInterest();
				
		assertTrue((d11 < 0.0001) && (d12 < 0.0001));
		
		// account 3 has a balance of 3000, which should give a monthly interest of 1/8
		//This is just to ensure that an account with different properties (different balance and direct deposit properties) 
		//also gives the correct result
		double d31 = acc3.monthlyInterest() - (1.00/8.00);
		double d32 = (1.00/8.00) - acc3.monthlyInterest();
								
		assertTrue((d31 < 0.0001) && (d32 < 0.0001));
				
				
		// account 4 has a balance of 1500, which should give a monthly interest of 1/16
		//This is just to ensure that an account with different properties also gives the correct result
		double d41 = acc4.monthlyInterest() - (1.00/16.00);
		double d42 = (1.00/16.00) - acc4.monthlyInterest();
						
		assertTrue((d41 < 0.0001) && (d42 < 0.0001));
		
	}

	/**
	 * Test method for {@link project2.Checking#monthlyFee()}.
	 */
	@Test
	void testMonthlyFee() {
		
		assertTrue((Double.compare(acc1.monthlyFee(), 25)) == 0); //test case # 1: balance < 1500 and directDeposit is false: fee = 25
		
	
		assertTrue((Double.compare(acc2.monthlyFee(), 0)) == 0); //test case # 2: balance < 1500 and directDeposit is true: fee = 0
		
		
		assertTrue((Double.compare(acc3.monthlyFee(), 0)) == 0); //test case # 3: balance > 1500 and directDeposit is true: fee = 0
		
		
		assertTrue((Double.compare(acc4.monthlyFee(), 0)) == 0); //test case # 4: balance = 1500 (edge case) and directDeposit is false: fee = 0
		
		
		
		//fail("Not yet implemented");
		
		
	}

}
