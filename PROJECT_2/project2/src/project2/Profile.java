
package project2;

/**
 * The profile class defines a Profile object which includes a first name and a last name. This class
 * is used to denote a particular person. The class provides getter methods in order to recieve an
 * objects implementation.
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Profile {
	
	private String fname;
	private String lname;
	
	/**
	 * This is a parameterized constructor which creates and object of this class with the specified values 
	 * for the data fields.
	 * 
	 * @param fname, which is first name of Profile
	 * @param lname, which is last name Profile
	 */
	public Profile(String fname, String lname){
		
		this.fname = fname;
		this.lname = lname;
		
	}
	
	/**
	 * getter method that retrieves this object's first name
	 * 
	 * @return returns the first name in this object
	 */
	public String getFirstName() {
		return fname;
	}
	
	/**
	 * getter method that retrieves this object's last name
	 * 
	 * @return returns the last name in this object
	 */
	public String getLastName() {
		return lname;
	}

}
