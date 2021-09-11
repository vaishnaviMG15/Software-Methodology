package sample;

/**
 * This is a class that is a blueprint for the OrderLine instance.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class OrderLine {

    private int lineNumber; // a serial number created when a sandwich is added to the order
    private Sandwich sandwich;
    private double price;

    /**
     * A constructor for creating an instance of OrderLine with regards to the Sandwich.
     * It initializes the sandwich in order line and sets its price to the sandwich's price.
     * @param sandwich, a Sandwich instance that we are creating an order line for.
     */
    public OrderLine(Sandwich sandwich){
        this.sandwich = sandwich;
        this.price = sandwich.price();
    }

    /**
     * A constructor for creating an instance of OrderLine object based on the state of
     * another OrderLine object, by getting the sandwich in the parameter OrderLine and its price.
     * @param obj, an OrderLine object that we are copying the state of.
     */
    public OrderLine(OrderLine obj){
        this.sandwich = obj.getSandwich();
        this.price = obj.getPrice();
    }

    /**
     * A getter method that gets the sandwich in the order line.
     * @return sandwich, the Sandwich instance that is in the order line.
     */
    public Sandwich getSandwich (){
        return sandwich;
    }

    /**
     * A getter method that gets the line number of a sandwich from the order line.
     * @return line number, the line number of a sandwich in the order line.
     */
    public int getLineNumber() {return lineNumber;}

    /**
     * A setter method for setting the line number of an OrderLine instance.
     * @param lineNumber, the line number that we are setting our OrderLine instance to.
     */
    public void setLineNumber (int lineNumber){
        this.lineNumber = lineNumber;
    }

    /**
     * A getter method for getting the price of an OrderLine instance.
     * @return price, the price of our OrderLine instance
     */
    public double getPrice (){
        return price;
    }

    /**
     * Generates a String version of how our OrderLine is displayed in the GUI by
     * appending the line number of a Sandwich to the String representation of our Sandwich instance.
     * @return result, the String representation of our OrderLine object.
     */
    @Override
    public String toString(){
        String result = lineNumber + sandwich.toString();
        return result;
    }
}
