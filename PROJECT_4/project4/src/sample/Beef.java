package sample;
/**
 * This is a class that is a blueprint for the Beef instance.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Beef extends Sandwich{

    public static final String basicIngredient1 = "Roast Beef";
    public static final String basicIngredient2 = "Provolone Cheese";
    public static final String basicIngredient3 = "Mustard";
    public static final double basicPrice = 10.99;

    /**
     * Constructor that creates an instance of Beef class by calling the super class (Sandwich).
     */
    public Beef(){
        super();
    }

    /**
     * Returns the cumulative price of a beef sandwich that charges a flat fee of $1.99 per extra ingredient added.
     * @return result, the price of a beef sandwich, with consideration to extras that were added.
     */
    @Override
    public double price(){
        Double result = basicPrice;
        result += PER_EXTRA * extras.size();
        return result;
    }


    /**
     * Returns the contents of the beef sandwich within a String by first setting up a template for name and basic ingredients,
     * and then calling the Sandwich class's toString method (the superclass of Beef).
     * @return result, a String which displays the contents of a Beef sandwich, and its price.
     *
     */
    @Override
    public String toString( ){
        String result = "Beef Sandwich " + basicIngredient1 +","+ basicIngredient2+"," + basicIngredient3+",";
        result = result.concat(super.toString());


        result = result.concat(String.format(" Price $ %.02f" ,price()));

        return result;
    }

    /**
     * Tests to see if Object obj is equivalent to our instance of Beef
     * by first checking to see if obj is an instance of Beef class, and then
     * making sure that it is equivalent to our actual Beef sandwich.
     *
     * @param obj, the object we are comparing our Beef sandwich instance with
     * @return true if object is equal with our sandwich, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if(obj instanceof Beef) {
                return true;
            }
            return false;
        }
        return false;
    }

}
