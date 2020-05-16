import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Order {

    //Declaring the variables used in the Class "Order"
    private int orderID;
    private int customerID;
    private int amount;
    private String name;
    static HashMap<String, Object> orderItems;

    //Constructor to be used in the Class "Main" for accessing non - static methods
    public Order() {
    }

    //Creating an instance of the Class "Item" to be used in this Class
    Item i1 = new Item();

    //Order Class Constructor
    public Order(int OrderID, int CustomerID, String ItemName, int Amount) {
        this.orderID = OrderID;
        this.customerID = CustomerID;
        this.name = i1.getName(); //Gets the name of the Item from the "Item" Class using the instance created above
        this.amount = Amount;
        this.orderItems = new HashMap<>(5); //Creates a Hashmap with an Initial Capacity of 5 to hold all of the ordered Items
    }

    //Get methods for the OrderID and Amount
    public int getOrderID(){
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getAmount() {
        return amount;
    }

    //Method to Add an Item to the "orderitems" Hashmap
    public static void addOrderItem(String columnName, Object object) {
        orderItems.put(columnName, object);
    }

    //Method to Remove an Item from the "orderitems" Hashmap
    public static void removeOrderItem(Object object) {
        orderItems.remove(object);
    }

    public static void printOrderItems() {
        Iterator it = orderItems.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
            it.remove(); //Avoids a ConcurrentModification Exception
        }
    }

    //Main method to call the print method for the Hashmap
    public static void main(String[] args) {
        printOrderItems();
    }
}

