import java.util.Random;

public class Item {

    //Declaring the variables used in the Class "Item"
    private double price;
    private String name;
    private int quantity;
    private int id;
    private static int counter = 1;
    //Creating a random Barcode for TESTING purposes
    private long LOWER_RANGE = 100000000; //Assigning a lower range Long type value (minimum point) for the Barcode
    private long UPPER_RANGE = 1000000000; //Assigning upper range Long type value (maximum point) for the Barcode
    //Initializing and generating the random Barcode using the values above
    Random random = new Random();
    private long barcode = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));

    //Item Class Constructor
    public Item(int id, String name, double price, long barcode, int quantity) {
        this.id = counter;
        counter++;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.barcode = barcode;
    }

    //Copy Constructor for the Class "OrderItem"
    public Item (Item item){
        this.name = item.name;
        this.price = price;
        this.id = item.id;
        this.quantity = quantity;
        this.barcode = barcode;
    }

    //Constructor to be used in the Class "Main" for accessing non - static methods
    public Item() {

    }

    //Get methods for the ID, Name, Quantity, Barcode and Price and Set methods whenever a value is possible to be changed
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity() {
        this.quantity = quantity;
    }

    public long getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //Test method to print the Item's details
    @Override
    public String toString() {
        return "Available Items: " +
                " Price: " + price + " cents each" +
                ", Name: '" + name + '\'' +
                ", ID: " + id +
                ", Barcode: " + barcode +
                ", Quantity: " + quantity +
                '}';
    }

    //Method to reduce the Item's quantity when it is used
    public void reduceQuantity(Object object) {
        this.quantity -= quantity;
    }
}
