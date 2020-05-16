import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.io.*;

//Test Class for tracking the Stock without the use of the MySql Database
public class Warehouse {

    //Declaring the variables used in the Class "Warehouse"
    //Creating the Hashmap "stock" to track the stock of the Store
    private static HashMap<Integer, Item> stock = new HashMap<>();

    //Creating an instance of the Class "Item" to be used in this Class
    Item i1 = new Item();

    //Method to add an Item to the Hashmap "stock"
    public static void addItem(Item item) {
        stock.put(item.getId(), item);
    }

    //Method to remove an Item from the Hashmap "stock"
    public static void removeItem(Item item) {
        stock.remove(item.getId());
    }

    //Method to print the items of the Hashmap "stock
    public static void printItems() {
        Set itemKeySet = stock.keySet();
        Iterator iterator = itemKeySet.iterator();
        while (iterator.hasNext()) {
            System.out.println(stock.get(iterator.next()));
        }
    }

    //Method that re - stocks the Warehouse of the Store
    public static void fillUpWarehouse(){
        Item item = new Item (4, "Tomatoes", 45, 1928382910, 350);
        Warehouse.addItem(item);
        item = new Item(5, "Mushrooms", 25, 1783920102, 250);
        Warehouse.addItem(item);
    }

    //Method that creates the Items without the use of a GUI
    public static void createItems() {
        Item item = new Item(1, "Potatoes", 35, 1330294922, 300);
        Warehouse.addItem(item);
        item = new Item(2, "Bananas", 40, 1728392048, 450);
        Warehouse.addItem(item);
    }

    //Main method to create the Items, re - stock the Warehouse and print the Items from the Hashmap "stock"
    public static void main(String[] args) throws IOException {
        createItems();
        fillUpWarehouse();
        printItems();
    }
}
