/**
 * 
 */
package project2;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * This class tests the monthlyInterest and monthlyFee method of the Checking class
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 *
 */
class MoneyMarketTest {
	
	MoneyMarket acc1;
	MoneyMarket acc2;
	MoneyMarket acc3;
	MoneyMarket acc4;
	MoneyMarket acc5;
	MoneyMarket acc6;
	MoneyMarket acc7;

	/**
	 * This sets up the instances of the MoneyMarket objects to use in the test cases
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		Date d1 = new Date (2018, 5, 7);
		Profile h1 = new Profile("Jane", "Doe");
		
		acc1 = new MoneyMarket(h1, 3000, d1, 7); 
		
		acc2 = new MoneyMarket(h1, 2800, d1, 6); 
		
		acc3 = new MoneyMarket(h1, 4000, d1, 5); 
		
		acc4 = new MoneyMarket(h1, 2500, d1, 4); 
		
		acc5 = new MoneyMarket(h1, 2400, d1, 6); 
		
		acc6 = new MoneyMarket(h1, 1000, d1, 8); 
		
		acc7 = new MoneyMarket(h1, 500, d1, 4); 
			
		
	}

	/**
	 * This performs tear down action at the end of every time we run this class
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link project2.MoneyMarket#monthlyInterest()}.
	 */
	@Test
	void testMonthlyInterest() {
		
		//Interest for all of the object instances has to be calculated only according the the balance
		//Annual interest rate is fixed at 0.65%. 
		
		//test case # 1: account 1 has a balance of 3000, which should give a monthly interest of 1.625
		double d11 = acc1.monthlyInterest() - 1.625;
		double d12 = 1.625 - acc1.monthlyInterest();
		
		assertTrue((d11 < 0.0001) && (d12 < 0.0001));
		
		
		// test case # 2: account 7 has a balance of 500, which should give a monthly interest of 13/48
		//This is just to ensure that an account with different properties also gives the correct result
		double d71 = acc7.monthlyInterest() - (13.00/48.00);
		double d72 = (13.00/48.00) - acc7.monthlyInterest();
				
		assertTrue((d71 < 0.0001) && (d72 < 0.0001));
		
		
		//fail("Not yet implemented");
		
		
		
	}

	/**
	 * Test method for {@link project2.MoneyMarket#monthlyFee()}.
	 */
	@Test
	void testMonthlyFee() {
		
		//fail("Not yet implemented");
		
		assertTrue((Double.compare(acc1.monthlyFee(), 12)) == 0); // test case # 1: Even though balance exceed 2500, withdrawals exceeds 6 so fee is not waived.
		
	
		assertTrue((Double.compare(acc2.monthlyFee(), 0)) == 0); //test case # 2: balance> 2500, and withdrawals = 6 (edge case), so fee is waived
		
		
		assertTrue((Double.compare(acc3.monthlyFee(), 0)) == 0); //test case # 3: balance>2500, and withdrawals does not exceed 6, so the fee is waived
		
		
	    assertTrue((Double.compare(acc4.monthlyFee(), 0)) == 0); //test case # 4: balance = 2500, and withdrawals do not exceed 6, so fee is waived
		
		
		assertTrue((Double.compare(acc5.monthlyFee(), 12)) == 0); //test case # 5: balance < 2500 and withdrawals are exactly 6, so fee is not waived
		
		
		assertTrue((Double.compare(acc6.monthlyFee(), 12)) == 0); //test case # 6: balance < 2500 and withdrawals exceed 6, so fee is not waived
		
		
		assertTrue((Double.compare(acc7.monthlyFee(), 12)) == 0); //test case # 7: balance < 2500 and withdrawals less than 6, so fee is not waived
		
	}

}
