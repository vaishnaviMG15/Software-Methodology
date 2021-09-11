
package project2;

/**
 * This class creates an instance of the TransactionManager and calls its run method 
 * in order to start reading the input and performing the transactional process.
 * 
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class RunProject2 {
	
	/**
	 * Calls the run() method of an Instantiation of TransactionManager().
	 * 
	 * @param args, which is input stream
	 */
	public static void main(String[] args) {
		
		new TransactionManager().run();
		
	}

}
