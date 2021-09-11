package sample;

import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * This is a class that tests the public methods in the Order class.
 *
 * @author Vaishnavi Manthena, Sanjana Pendharkar
 */
public class OrderTest {

    Chicken c, c2;
    Beef b, b2;
    Fish f, f2;
    OrderLine chicken1, beef1, fish1, chicken2, beef2, fish2;

    /**
     * Setting up all the sandwich and order line objects which can be used
     * by other testing methods.
     *
     * @throws Exception
     */
    @org.junit.Before
    public void setUp() throws Exception {

        c = new Chicken();
        b = new Beef();
        f = new Fish();

        c2 = new Chicken();
        c2.add(Extra.eggs);
        c2.add(Extra.guacamole);
        c2.add(Extra.ham);

        b2 = new Beef();
        b2.add(Extra.coleslaw);
        b2.add(Extra.bacon);

        f2 = new Fish();
        f2.add(Extra.lettuce);
        f2.add(Extra.tomatoes);
        f2.add(Extra.onion);
        f2.add(Extra.mayonnaise);

        chicken1 = new OrderLine(c);
        beef1 = new OrderLine(b);
        fish1 = new OrderLine(f);

        chicken2 = new OrderLine(c2);
        beef2 = new OrderLine(b2);
        fish2 = new OrderLine(f2);

    }

    /**
     * There is nothing here now, but its purpose is to complete any tasks that should be finished
     * before the end of running the JUnit test class.
     *
     * @throws Exception
     */
    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * Testing the totalPrice() method of Order class using Black Box Testing
     *
     */
    @org.junit.Test
    public void totalPrice() {

        //Test 1: The price of an empty order should be zero.
        Order order1 = new Order();
        Double actualValue = order1.totalPrice();
        Double expectedValue = 0.0;
        assertTrue(expectedValue.equals(actualValue));

        //Test 2: The price should be calculated correctly for an order with one orderLine and a sandwich
        //with no extras.
        Order order2 = new Order();
        order2.add(chicken1);
        Double actualNoExtra = order2.totalPrice();

        Double expectedNoExtra = 8.99;
        assertTrue((Double.compare(expectedNoExtra,actualNoExtra)) == 0  );


        //Test 3: The price should be calculated correctly for an order with one orderLine and a sandwich
        //with extras.
        Order order3 = new Order();
        order3.add(fish2);
        Double actualWithExtra = order3.totalPrice();
        Double expectedWithExtra = 20.95;
        assertTrue(actualWithExtra.equals(expectedWithExtra));

        //Test 4: The price should be calculated correctly for an order with multiple orderLines and
        //different types of sandwiches.
        Order order4 = new Order();
        Order order5 = new Order();
        order4.add(chicken1);
        order4.add(beef2);
        order4.add(fish1);
        order4.add(fish2);
        // 57.90

        order5.add(chicken2);
        order5.add(beef1);
        order5.add(beef2);
        order5.add(chicken1);
        order5.add(fish2);
        // 70.86

        Double order4Actual = order4.totalPrice();
        Double order4Expected = 57.90;
        Double order5Actual = order5.totalPrice();
        Double order5Expected = 70.86;
        //assertTrue((Double.compare(order4Actual,order4Expected))   );
        assertTrue(((order4Actual - order4Expected) < 0.001) && ((order4Actual - order4Expected) > -0.001));
        assertTrue(order5Actual.equals(order5Expected));
    }

    /**
     * Testing the add() method of Order class using Black Box Testing
     *
     */
    @org.junit.Test
    public void add(){


        Order order = new Order();
        //Test 1: Make sure that order lines for different sandwiches with no extras are added properly.
        assertTrue(order.add(chicken1));
        assertTrue(order.add(beef1));
        assertTrue(order.add(fish1));

        // Test 2: Make sure that order lines for same type of sandwich do not get mixed if
        // one sandwich has extras but the other one does not (i.e. both should be added separately).
        assertTrue(order.add(chicken2));
        assertTrue(order.add(beef2));
        assertTrue(order.add(fish2));

        // Test 3: Make sure that all sandwiches got added into the order array in consideration to
        // when they were added to the array, and that their line numbers are assigned correctly.
        ArrayList<OrderLine> testArray = order.getArray();
        assertTrue((testArray.get(0)).getLineNumber() == 1);
        assertTrue((testArray.get(1)).getLineNumber() == 2);
        assertTrue((testArray.get(2)).getLineNumber() == 3);
        assertTrue((testArray.get(3)).getLineNumber() == 4);
        assertTrue((testArray.get(4)).getLineNumber() == 5);
        assertTrue((testArray.get(5)).getLineNumber() == 6);


        assertTrue(((testArray.get(0)).getSandwich()).equals(c));
        assertTrue(((testArray.get(1)).getSandwich()).equals(b));
        assertTrue(((testArray.get(2)).getSandwich()).equals(f));
        assertTrue(((testArray.get(3)).getSandwich()).equals(c2));
        assertTrue(((testArray.get(4)).getSandwich()).equals(b2));
        assertTrue(((testArray.get(5)).getSandwich()).equals(f2));


        assertTrue(testArray.size() == 6);

        // Test 4: Make sure that add() does not add a Null OrderLine object.
        assertFalse(order.add(null));
        assertTrue((order.getArray()).size() == 6);

        // Test 5: Make sure that add() does not add a Sandwich object.
        assertFalse(order.add(c));
        assertTrue((order.getArray()).size() == 6);

        // Test 6: Make sure that add() does not add objects that are not OrderLines or Sandwiches (i.e.
        // data types we have not dealt with in this project).
        String s = "abcd";
        assertFalse(order.add(s));
        assertTrue((order.getArray()).size() == 6);

        // Test 7: Make sure that add() does not add the element twice, because the line number for the
        //same element would be different at both of the occurrences.
        assertFalse(order.add(chicken1));
        assertFalse(order.add(chicken1));

        assertEquals((order.getArray()).size(),6);

        //Test 8: To make sure that a clone of an orderline is added properly. A different orderline instance
        //with the same values for sandwich should be added properly.

        assertTrue(order.add(new OrderLine(fish2)));

        ArrayList<OrderLine> newTestArray = order.getArray();
        assertTrue(newTestArray.size() == 7);
        assertTrue((newTestArray.get(6)).getLineNumber() == 7);

        assertTrue(((newTestArray.get(6)).getSandwich()).equals(f2));

    }

    /**
     * Testing the remove() method of Order class using Black Box Testing
     *
     */
    @org.junit.Test
    public void remove() {

        //Test Case 1: Removing from an empty order
        Order order1 = new Order();
        assertFalse(order1.remove(beef1));

        //Test Case 2: Removing an object that is not a line
        Order order2 = new Order();
        order2.add(chicken1);
        String s = "abc";
        assertFalse(order1.remove(s));

        //Test Case 3: Removing items from a nonempty order
        Order order3 =  new Order();
        order3.add(chicken2);
        order3.add(beef1);
        order3.add(beef2);
        order3.add(chicken1);
        order3.add(fish2);

        //Test Case 3a: Removing first item from a nonempty order
        assertTrue(order3.remove(chicken2));

        //Test 3b: Removing middle item from nonempty order
        assertTrue(order3.remove(beef2));

        //Test 3c: Removing last item from nonempty order
        assertTrue(order3.remove(fish2));

        //Test 3d: Removing null from the order should not work
        assertFalse(order3.remove(null));

        //Test 3f: To make sure that it does not remove chicken2 (an item that it used to have) again
        assertFalse(order3.remove(chicken2));

        //Test 3g: To make sure that it does not remove an orderLine that it never had and does not have
        assertFalse(order3.remove(fish1));

        //Test 3h: Making sure that as we remove items the order of the items and line number is being reset
        //accordingly.

        //After the above manipulations to order 3, it should have 2 elements:
        //1.beef1 (sandwich : b; lineNumber: 1)
        //2.chicken1 (sandwich : c; lineNumber: 2)
        //Moreover the current value of line number should be set to 3.

        ArrayList<OrderLine> testArray = order3.getArray();

        assertTrue(((testArray.get(0)).getSandwich()).equals(b));
        assertTrue(((testArray.get(1)).getSandwich()).equals(c));

        assertTrue(((testArray.get(0)).getLineNumber()) == 1);
        assertTrue(((testArray.get(1)).getLineNumber()) == 2);

        assertTrue(order3.lineNumber == 3);



        //Test 4: Removing an item that occurs multiple times (however it is not the same instance)
        Order order4 = new Order();

        order4.add(fish2);
        order4.add(chicken1);
        OrderLine clone = new OrderLine(fish2);
        order4.add(clone);
        order4.add(beef1);
        OrderLine clone2 = new OrderLine(fish2);
        order4.add(clone2);

        //removing the clone of fish2 will remove this clone only and would leave the first fish2,
        // and the second clone of fish2 unchanged.

        assertTrue(order4.remove(clone));
        //remaining elements should be fish2, chicken1, beef1, clone2
        //fish2 and clone2 point to the same sandwich f2

        ArrayList<OrderLine> testArray2 = order4.getArray();

        assertTrue(((testArray2.get(0)).getSandwich()).equals(f2));
        assertTrue(((testArray2.get(1)).getSandwich()).equals(c));
        assertTrue(((testArray2.get(2)).getSandwich()).equals(b));
        assertTrue(((testArray2.get(3)).getSandwich()).equals(f2));


    }

    /**
     * Testing the clear() method of Order class using Black Box Testing
     *
     */
    @org.junit.Test
    public void clear() {
        //The main function of clear is to empty the list and set the lineNumber back to one.

        //Test Case1: When we have an empty order. After we clear this order, it should remain empty
        //and the line number should be one.

        Order order1 = new Order();
        order1.clear();
        assertTrue((order1.getArray()).size() == 0);
        assertTrue(order1.lineNumber == 1);

        //Test Case2: When we have a non empty order of various kinds of sandwiches.
        // After we clear this order, it should become empty and the line number should be one.

        Order order2 = new Order();
        order2.add(chicken1);
        order2.add(beef1);
        order2.add(fish1);
        order2.add(chicken2);
        order2.add(beef2);
        order2.add(fish2);

        order2.clear();
        assertTrue((order2.getArray()).size() == 0);
        assertTrue(order2.lineNumber == 1);

    }
}