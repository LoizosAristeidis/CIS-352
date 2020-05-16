import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class ItemList {

    //Declaring the variables used in the Class "ItemList"
    //Hashmaps containing the Received, Prepared and Delivered Items as declared in the Main Class
    public static HashMap<String, Object> received = new HashMap<>();
    public static HashMap<String, Object> prepared = new HashMap<>();
    public static HashMap<String, Object> delivered = new HashMap<>();

    //Methods for the 3 Hashmaps to Add and Remove Items as declared in the Main Class
    public static void addRecItem(String columnName, Object object) {
        received.put(columnName, object);
    }

    public static void removeRecItem(Object object) {
        received.remove(object);
    }

    public static void addPrepItem(String columnName, Object object) {
        prepared.put(columnName, object);
    }

    public static void removePrepItem(Object object) {
        prepared.remove(object);
    }


    public static void addDelItem(String columnName, Object object) {
        delivered.put(columnName, object);
    }

    public static void removeDelItem(Object object) {
        delivered.remove(object);
    }

    //Print methods for the 3 Hashmaps with the use of an Iterator intended for the Store Employees
    public static void printRecItems() {
        Iterator it = received.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
            it.remove(); //Avoids a ConcurrentModification Exception
        }
    }

    public static void printPrepItems() {
        Iterator it = prepared.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
            it.remove(); //Avoids a ConcurrentModification Exception
        }
    }

    public static void printDelItems() {
        Iterator it = delivered.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
            it.remove(); //Avoids a ConcurrentModification Exception
        }
    }

    //Main method to call the 3 print methods for the 3 Hashmaps
    public static void main(String[] args) {
        printRecItems();
        printPrepItems();
        printDelItems();
    }
}