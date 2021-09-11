package sample;
import java.util.*;
/**
 * This is a class that is a blueprint for the Sandwich instance.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public abstract class Sandwich implements Customizable {

    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;

    /**
     * A constructor that creates an empty ArrayList of extra ingredients.
     */
    public Sandwich (){
        extras = new ArrayList<>();
    }


    public abstract double price();

    /**
     * Returns the names of the extra ingredients that were add to the sandwich. If no extras were
     * added, we just append 'No extras added' to our result, which is a String representation of the extras in our sandwich.
     * @return result, the String representation of the extra ingredients in our sandwich.
     */
    @Override
    public String toString(){

        String result = " Extra: ";

        if (extras.size() == 0){
            result = result.concat("No extras added,");
            return result;
        }

        for(Extra element: extras){
            result = result.concat(element.name() + ",");
        }

        return result;
    }

    /**
     * A method for adding an Extra object to the sandwich.
     * @param obj, an object that we are looking to add into the sandwich, if it is an instance of Extra.
     * @return true, if the Extra object was added successfully, false if the Extra object was not added.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Extra) {
            extras.add((Extra)obj);
            return true;
        }
        return false;
    }

    /**
     * A method for removing an Extra object from the sandwich.
     * @param obj, an object that we are looking to remove from the sandwich, if it is an instance of Extra.
     * @return true, if the Extra object was removed successfully, false if the Extra object was not removed.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Extra) {

            if(extras.remove((Extra)obj)){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Tests to see if Object obj is equivalent to our instance of Sandwich
     * by first checking to see if obj is an instance of Sandwich class, and then
     * making sure that it is equivalent to our actual Sandwich sandwich. We
     * also want to make sure that our ArrayList of extras are also the same.
     * @param obj, the object we are comparing our Sandwich instance with
     * @return true if object is equal with our sandwich, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sandwich) {
            if(this.extras.equals(((Sandwich) obj).extras)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Removes all extras from the sandwich and returns it to its basic form.
     */
    public void removeAll(){
        extras.clear();
    }

}
