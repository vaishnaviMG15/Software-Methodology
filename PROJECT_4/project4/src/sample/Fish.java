package sample;

/**
 * This is a class that is a blueprint for the Fish instance.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class Fish extends Sandwich {

    public static final String basicIngredient1 = "Grilled Snapper";
    public static final String basicIngredient2 = "Cilantro";
    public static final String basicIngredient3 = "Lime";
    public static final double basicPrice = 12.99;

    /**
     * Constructor that creates an instance of Fish class by calling the super class (Sandwich).
     */
    public Fish(){
        super();
    }

    /**
     * Returns the cumulative price of a fish sandwich that charges a flat fee of $1.99 per extra ingredient added.
     * @return result, the price of a fish sandwich, with consideration to extras that were added.
     */
    @Override
    public double price(){
        Double result = basicPrice;
        result += PER_EXTRA * extras.size();
        return result;
    }

    /**
     * Returns the contents of the fish sandwich within a String by first setting up a template for name and basic ingredients,
     * and then calling the Sandwich class's toString method (the superclass of Fish).
     * @return result, a String which displays the contents of a Fish sandwich, and its price.
     */
    @Override
    public String toString( ){
        String result = "Fish Sandwich " + basicIngredient1 +","+ basicIngredient2+"," + basicIngredient3+",";
        result = result.concat(super.toString());
        result = result.concat(String.format(" Price $ %.02f" ,price()));

        return result;
    }

    /**
     * Tests to see if Object obj is equivalent to our instance of Fish
     * by first checking to see if obj is an instance of Fish class, and then
     * making sure that it is equivalent to our actual Fish sandwich.
     * @param obj, the object we are comparing our Fish sandwich instance with
     * @return true if object is equal with our sandwich, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if(obj instanceof Fish) {
                return true;
            }
            return false;
        }
        return false;
    }


}
