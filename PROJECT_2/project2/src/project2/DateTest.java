/**
 * 
 * 
 */
package project2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * This class tests the isValid method of the date class
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 *
 */
class DateTest {

	/**
	 * Test method for {@link project2.Date#isValid()}.
	 */
	@Test
	void testIsValid() {
		
		Date d2 = new Date(2021, 11, 25); //Year cannot be greater than 2020
		assertFalse(d2.isValid());
		
		Date d3 = new Date(-1, 11, 25); //Year cannot be negative
		assertFalse(d3.isValid());
		
		Date d4 = new Date(2019, 13, 25); //Month has to be 12 or less
		assertFalse(d4.isValid());
		
		Date d5 = new Date(2019, 0, 25); //Month has to be greater than zero
		assertFalse(d5.isValid());
		
		Date d6 = new Date(2019, 11, 0); //Day has to be greater than 0
		assertFalse(d6.isValid());
		
		Date d7 = new Date(2018, 7, 25); //Normal date without any complicated boundary issues
		assertTrue(d7.isValid());
		
		Date d1 = new Date(2019, 11, 31); // Month 11 (November) has at most 30 days
		assertFalse(d1.isValid());
		
		Date d14 = new Date(2019, 3, 31); // Month 03 (March) can have 31 days
		assertTrue(d14.isValid());
		
		Date d10 = new Date(2019, 2, 30); //February cannot have 30 days
		assertFalse(d10.isValid());
		
		Date d8 = new Date(2018, 7, 31); // correct, July has 31 days (31 days, not a leap year)
		assertTrue(d8.isValid());
		
		Date d9 = new Date(1980, 2, 29); //leap year, so February 29 is a valid date
		assertTrue(d9.isValid());
		
		Date d18 = new Date(1960, 2, 28); //leap year, so February 28 is a valid date and not the last day of February
		assertTrue(d18.isValid());
		
		Date d11 = new Date(1981, 2, 29); //not a leap year, so February 29 is not a valid date
		assertFalse(d11.isValid());
		
		Date d12 = new Date(1981, 2, 28); //not a leap year, so February 28 is the last day of February
		assertTrue(d12.isValid());
		
		Date d13 = new Date(1900, 2, 29); // not a leap year (divisible by 4 and 100 but not 400), so February 29 is not a valid date
		assertFalse(d13.isValid());
		
		Date d15 = new Date(2011, 6, 30); // 30 days, not a leap year
		assertTrue(d15.isValid());
		
		Date d16 = new Date(1912, 5, 31); // 31 days, leap year
		assertTrue(d16.isValid());
		
		Date d17 = new Date(1944, 4, 30); // 30 days, leap year
		assertTrue(d17.isValid());
		
		//fail("Not yet implemented");
	}

}
