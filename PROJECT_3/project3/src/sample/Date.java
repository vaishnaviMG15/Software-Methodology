package sample;

/**
 * 
 * The date class defines the date object which has three data fields (year, month, day).
 * It provides methods to compare two objects by implementing compareTo method of comparable interface, convert a date object to a 
 * string, and check if a date object is valid.
 * 
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
 
public class Date implements Comparable<Date> {
	
	private int year;
	private int month;
	private int day;
	
	/**
	 * This is a parameterized constructor which creates and object of this class with the specified values 
	 * for the data fields.
	 * 
	 * @param year, which is an integer that tells the year of this date object
	 * @param month, which is an integer that tells the month of this date object
	 * @param day, which is an integer that tells the day of this date object
	 */
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	/**
	 * compareTo() is a method that compares the parameter date to the current date.
	 * 
	 * @param date that we want our current date to be compared to
	 * @return 0 if both dates are equal, -1 if the current date is less than the parameter date and 1 if the current date
	 * is greater than the parameter date
	 */
	@Override
	public int compareTo(Date date) { 
		
		
		if(this.year == date.year) {
			
			if(this.month == date.month) {
				
				if(this.day == date.day) {
					return 0;
				} else if (this.day > date.day) {
					return 1;
				} else {
					return -1;
				}
				
			} else if (this.month > date.month) {
				return 1;
			} else {
				return -1;
			}
				
		} else if (this.year > date.year) {
			return 1;
		} else {
			return -1;
		}
		
	}
	
	/**
	 * toString() returns the String version of the date in mm/dd/yyyy format.
	 * It adds extra zeros based on if the month is a single digit, and then puts the values into the correct format.
	 * 
	 * @return result, which is String version of the date in mm/dd/yyyy
	 */
	@Override
	public String toString() { //in the format mm/dd/yyyy
		
		String m = Integer.toString(month);
		String d = Integer.toString(day);
		
		if (month < Constants.caseForSingleDigit) {
			
			 m = Constants.singleDigitAdditional + m;
		}
		
		if (day < Constants.caseForSingleDigit) {
			
			 d = Constants.singleDigitAdditional + d;
		}
		
		
		String result = m + "/" + d + "/" + year;
		
		return result;
		
	}
	
	/**
	 * isValid() makes sure that the year is not greater than 2020 and the month is between 1 and 12.
	 * Moreover, it makes sure that the number of days in any month is valid depending on the month and 
	 * other factors like whether or not the year is a leap year.
	 * 
	 * @return true if current date is valid, or false if it is not
	 */
	public boolean isValid() {
		if (this.year > Constants.currentYear || this.year < 0 || day <= 0) {
			return false;
		}
		
		switch (month) {
		case (Constants.January):
		case (Constants.March):
		case (Constants.May):
		case (Constants.July):
		case (Constants.August):
		case (Constants.October):
		case (Constants.December): if (day > Constants.daysInThirtyOneMonth) return false; break;
		case (Constants.April):
		case (Constants.June):
		case (Constants.September):
		case (Constants.November): if (day > Constants.daysInThirtyMonth) return false; break;
		case (Constants.February): 
			if(isLeapYear(this.year)) {
				if(day > Constants.leapYearFebruary)  return false;
			}
			else {
				if (day > Constants.standardFebruary) return false;
			}
			break;
		default: return false;
		
		}
		return true;
	}
	
	/**
	 * isLeapYear() is a method that takes in a year as a parameter and checks to see if it is a leap year
	 * by making sure the year is divisible by 4, and if it is divisible by 100 then it should also be 
	 * divisible by 400.
	 *
	 * @param year that we are checking is a leap year or not
	 * @return true if the year is a leap year, false if it is not
	 */
	private static boolean isLeapYear(int year) {
		if (year % Constants.divisibleByFour == 0) {
			if (year % Constants.divisibleByHundred == 0) {
				if (year % Constants.divisibleByFourHundred == 0) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

}
