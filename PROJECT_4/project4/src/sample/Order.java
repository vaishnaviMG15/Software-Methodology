package sample;
import java.util.*;
/**
 * This is a class that is a blueprint for the Order instance.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Order implements Customizable{

    public int lineNumber; //reset for each new order
    private ArrayList<OrderLine> orderlines;

    /**
     * A constructor for creating an instance of Order, which also initializes the ArrayList of orderlines,
     * and sets the lineNumber to one.
     */
    public Order (){
        orderlines = new ArrayList<>();
        lineNumber = 1;
    }

    /**
     * Finds the total price of the every single Sandwich element in the orderlines
     * by adding each element's price to a starting total variable.
     *
     * @return total, the cumulative price of every Sandwich (Beef, Chicken, Fish) in
     * the ArrayList orderlines.
     */
    public double totalPrice (){
        double total = 0.0;
        for (OrderLine element: orderlines){
            total += element.getPrice();
        }
        return total;
    }

    /**
     * A getter method for returning the ArrayList of orderlines.
     * @return orderlines, the ArrayList of orderlines.
     */
    public ArrayList<OrderLine> getArray(){
        return orderlines;
    }

    /**
     * A method for adding an orderline to the Order.
     * @param obj, an object that we are looking to add into the Order, if it is an instance of orderline.
     * @return true, if the orderline object was added successfully, false if the orderline object was not added.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof OrderLine) {
            OrderLine orderline = (OrderLine)obj;
            for(OrderLine element : orderlines){
                if (element == orderline){
                    return false;
                }
            }
            orderline.setLineNumber(lineNumber);
            orderlines.add(orderline);
            lineNumber++;
            return true;
        }
        return false;
    }

    /**
     * A method for removing an orderline object from the Order.
     * @param obj, an object that we are looking to remove from the Order, if it is an instance of orderline.
     * @return true, if the orderline object was removed successfully, false if the orderline object was not removed.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof OrderLine) {

            if(orderlines.remove((OrderLine)obj)){
                reset();
                return true;
            }

            return false;
        }
        return false;
    }

    /**
     * Resets all of the line numbers when an orderline element is removed
     * and makes sure that the line numbers are in ascending order.
     */
    private void reset(){
        for(OrderLine element: orderlines){
            element.setLineNumber(orderlines.indexOf(element) + 1);
        }
        lineNumber = orderlines.size()+1;
    }

    /**
     * Removes every element from orderlines and sets the lineNumber to 1.
     */
    public void clear (){
        orderlines.clear();
        lineNumber = 1;
    }



}
