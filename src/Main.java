import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    //Buttons (Actions are modified by the Event Handlers below with the use of Lamba Expressions)
    Button listitems;
    Button insertitems;
    Button findbybarcode;
    Button orderitem;
    Button completeorder;
    Button exit;
    Button markasprep;
    Button markasdel;
    Button removeitem;

    //Label to display text as a label
    Label l1;
    Label l2;
    Label l3;

    //Layout Component: Lays out its children in a single horizontal row
    HBox hbox;

    //Layout Component: Lays out its children in a back-to-front stack
    StackPane stackpane;

    //The container for all content in a scene graph
    Scene scene;

    @Override
    public void start(Stage primaryStage) {

        //Create the Items from the "Warehouse" Class (used as a test)
        Warehouse.createItems();

        //Create an instance of an Item from the "Item" Class
        Item i1 = new Item(5, "Cucumbers", 30, 1920384627, 500);

        //Initializing components
        //Button that lists the Items
        listitems = new Button();
        listitems.setText("List of Items");

        //Button that adds an order (for employees)
        insertitems = new Button();
        insertitems.setText("Add new Item");

        //Button that finds an Item by entering its Barcode
        findbybarcode = new Button();
        findbybarcode.setText("Find Item by Barcode");

        //Button that creates a new Order
        orderitem = new Button();
        orderitem.setText("Order an Item");

        //Button that completes the Order
        completeorder = new Button();
        completeorder.setText("Complete Order!");

        //Button that marks the Order as prepared
        markasprep = new Button();
        markasprep.setText("Mark Order as Prepared");

        //Button that marks the Order as delivered
        markasdel = new Button();
        markasdel.setText("Mark Order as Delivered");

        //Button that removes an Item from the Order
        removeitem = new Button();
        removeitem.setText("Remove an Item from the Order");

        //Button that exits the GUI's Primary Stage
        exit = new Button();
        exit.setText("Exit");

        //Labels that are used for explaining the Buttons
        l1 = new Label("Options for Customers:");
        l2 = new Label("Options for Employees:");
        l3 = new Label("Thank you for shopping from our store!");

        //Hbox to lay out the 3 labels in a single horizontal row
        hbox = new HBox();
        hbox.getChildren().addAll(l1, l2, l3);
        hbox.setSpacing(10);

        //Event Handlers
        //Button with text "List of Items" that gets the list from the MySql Database table "items"
        listitems.setOnAction(e -> {
            try {
                listitems();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //Button with text "Add new Order" that creates an order and inserts it to the "items" table
        insertitems.setOnAction(e -> {
            try {
                insertitems();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //Button with text "Find Item by Barcode" that uses a BufferedReader to locate
        //an Item from the MySql Database by inputting its Barcode
        findbybarcode.setOnAction(e -> {
            try {
                findbybarcode();
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });

        //Button with text "Order an Item" that uses a BufferedReader to order an Item from the MySql Database
        //by inputting its ID, while also adding the Order to the Hashmap "Received" of the ItemList Class
        //and the Hashmap "orderitems" of the "Order" Class
        //When an Item is clicked - on it reduces its Quantity
        orderitem.setOnAction(e -> {
            try {
                orderitem();
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });

        //Button that completes the Order
        completeorder.setOnAction(e -> {
            try {
                completeorder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //Button that marks the Order as prepared and adds it to the "prepared" Hashmap of the "ItemList" Class
        markasprep.setOnAction(e -> {
            try {
                markasprep();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //Button that marks the Order as delivered and adds it to the "delivered" Hashmap of the "ItemList" Class
        markasdel.setOnAction(e -> {
            try {
                markasdel();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        //Button that removes an Item from the Order by inputting its ID
        //while also removing the Item from the Hashmap "Received" of the ItemList Class
        //and the Hashmap "orderitems" of the "Order" Class
        removeitem.setOnAction(e -> {
            try {
                removeitem();
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });

        //Button that exits the GUI's Primary Stage
        exit.setOnAction(e -> primaryStage.close());

        //Stackpane that lays all the buttons in a back-to-front stack
        stackpane = new StackPane();
        stackpane.getChildren().addAll(listitems, insertitems, findbybarcode, orderitem, completeorder, markasprep, markasdel, removeitem, exit, l1, l2, l3);

        //Set the Margins, Aligment and Position of the elements in the Scene
        StackPane.setMargin(listitems, new Insets(60, 200, 140, 50));
        StackPane.setMargin(insertitems, new Insets(60, 50, 140, 200));
        StackPane.setMargin(findbybarcode, new Insets(100, 200, 180, 50));
        StackPane.setMargin(orderitem, new Insets(215, 200, 220, 50));
        StackPane.setMargin(completeorder, new Insets(260, 200, 101, 50));
        StackPane.setMargin(markasprep, new Insets(10, 50, 89, 0));
        StackPane.setMargin(markasdel, new Insets(170, 50, 174, 0));
        StackPane.setMargin(removeitem, new Insets(280, 200, 62, 50));
        StackPane.setMargin(exit, new Insets(200, 50, 30, 0));
        StackPane.setMargin(l1, new Insets(20, 200, 200, 50));
        StackPane.setMargin(l2, new Insets(20, 50, 50, 200));
        StackPane.setMargin(l3, new Insets(260, 50, 30, 50));

        StackPane.setAlignment(listitems, Pos.TOP_LEFT);
        StackPane.setAlignment(insertitems, Pos.TOP_RIGHT);
        StackPane.setAlignment(findbybarcode, Pos.CENTER_LEFT);
        StackPane.setAlignment(orderitem, Pos.CENTER_LEFT);
        StackPane.setAlignment(completeorder, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(markasprep, Pos.CENTER_RIGHT);
        StackPane.setAlignment(markasdel, Pos.CENTER_RIGHT);
        StackPane.setAlignment(removeitem, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(exit, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(l1, Pos.TOP_LEFT);
        StackPane.setAlignment(l2, Pos.TOP_RIGHT);
        StackPane.setAlignment(l3, Pos.BOTTOM_CENTER);

        //Create the Scene and the Primary Stage to display the elements
        scene = new Scene(stackpane, 500, 300);
        primaryStage.setTitle("Grocery Store GUI System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Method that creates an order and inserts it to the "items" table
    public static void insertitems() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/grocerystore";
        String user = "root";
        String password = "loizos";

        try {
            Connection myConn = DriverManager.getConnection(url, user, password);

            //The MySql Insert Statement
            String query = " insert into items (ID, Name, Price, Barcode, Quantity)"
                    + " values (?, ?, ?, ?, ?)";

            Item i1 = new Item(1, "Cucumbers", 30, 1293020394, 500);

            //Create the MySql Insert PreparedStatement to Insert the Items into the Table "items" of the database "grocerystore"
            PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, i1.getId());
            preparedStmt.setString(2, i1.getName());
            preparedStmt.setDouble(3, i1.getPrice());
            preparedStmt.setLong(4, i1.getBarcode());
            preparedStmt.setInt(5, i1.getQuantity());

            // execute the preparedstatement
            preparedStmt.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method that uses a BufferedReader to locate an Item from the MySql Database by inputting its Barcode
    public static void findbybarcode() throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/grocerystore";
        String user = "root";
        String password = "loizos";
        String bcode;

        //Buffered Reader to Input the Barcode
        int bcode2;
        BufferedReader dataIn = new BufferedReader (new InputStreamReader(System.in));

        System.out.print("Insert the Item's Barcode: ");
        bcode = dataIn.readLine();
        bcode2 = Integer.parseInt(bcode);

        try {
            Connection myConn = DriverManager.getConnection(url, user, password);

            //Statement and Query to print the Items of the Table using the given Barcode
            String query = "select * from grocerystore.items WHERE Barcode = ?";
            PreparedStatement preparedStatement = myConn.prepareStatement(query);
            preparedStatement.setLong(1, bcode2);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(rs.next()) {
                for(int i = 1; i < (columnsNumber + 1); i++)
                    System.out.print(rs.getString(i) + "  | ");
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method that that uses a BufferedReader and a While loop to order an Item from the MySql Database by inputting its ID, and calculate the Total Price of the Order
    //It also reduces the quantity of the Item and adds the Order to the Hashmap "Received" of the ItemList Class and the Hashmap "orderItems" of the Order Class
    public static void orderitem() throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/grocerystore";
        String user = "root";
        String password = "loizos";
        String id;
        int id2;
        int totalprice = 0;
        boolean check = true; //Check variable for the While Loop
        String ans;
        BufferedReader dataIn = new BufferedReader (new InputStreamReader(System.in));  //Buffered Reader to Input the Item's ID
        while (check == true) {
            System.out.print("Enter the desirable Item's ID: ");
            id = dataIn.readLine();
            id2 = Integer.parseInt(id);
            try {
                Connection myConn = DriverManager.getConnection(url, user, password);

                //Statement and Query to print the Items of the Table using the given ID
                String query = "select * from grocerystore.items WHERE ID = ?";
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                preparedStatement.setLong(1, id2);
                ResultSet rs = preparedStatement.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    System.out.print("Item Selected: ");
                    System.out.println(rs.getString("Name"));
                    System.out.print("Price: ");
                    System.out.print(rs.getInt("Price"));
                    totalprice += rs.getInt("Price");
                }

                System.out.println();
                System.out.print("Would you like to buy more Items? (Yes or No)");
                System.out.println();
                ans = dataIn.readLine();
                if (ans.equals("Yes")) {
                    check = true;
                } else if (ans.equals("No")) {
                    check = false;
                    break;
                } else {
                    System.out.println("Wrong answer given. Exiting...");
                    break;
                }

                //Add order to the Hashmap "Received" of the ItemList Class
                for (int i = 1; i < (columnsNumber + 1); i++)
                    ItemList.addRecItem(rsmd.getColumnName(i), rs.getObject(i));

                //Add order to the Hashmap "orderItems" of the Order Class
                for (int i = 1; i < (columnsNumber + 1); i++)
                    Order.addOrderItem(rsmd.getColumnName(i), rs.getObject(i));

                //Reduce the quantity of the Item
                Item i5 = new Item();
                for (int i = 1; i < (columnsNumber + 1); i++)
                    i5.reduceQuantity(rs.getObject(i));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total Price of the Items: " + totalprice);
    }

    //Method to Remove an Item from the Order
    public static void removeitem() throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/grocerystore";
        String user = "root";
        String password = "loizos";
        String id;
        int id2;
        BufferedReader dataIn = new BufferedReader (new InputStreamReader(System.in));  //Buffered Reader to Input the Item's ID

        System.out.print("Enter the Item ID that you want to remove from the Order: ");
        id = dataIn.readLine();
        id2 = Integer.parseInt(id);
        try {
            Connection myConn = DriverManager.getConnection(url, user, password);

            //Statement and Query to print the Items of the Table
            String query = "select * from grocerystore.items WHERE ID = ?";
            PreparedStatement preparedStatement = myConn.prepareStatement(query);
            preparedStatement.setLong(1, id2);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                System.out.print("The following Item will be removed from the Order: ");
                System.out.println(rs.getString("Name"));
            }

            //Remove Order Item from the Hashmap "Received" of the ItemList Class
            for (int i = 1; i < (columnsNumber + 1); i++)
                ItemList.removeRecItem(rs.getObject(i));

            //Remove Order Item from the "orderitems" Hashmap of the ItemList Class
            for (int i = 1; i < (columnsNumber + 1); i++)
                Order.removeOrderItem(rs.getObject(i));

        } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    //Method that completes the order and adds it to the "orders" table of the MySql database
    public static void completeorder() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/grocerystore";
        String user = "root";
        String password = "loizos";

        try {
            Connection myConn = DriverManager.getConnection(url, user, password);

            //Add the Order to the "orders" table of the MySql database
            String query2 = " insert into orders (OrderID, CustomerID, ItemName, Amount)"
                    + " values (?, ?, ?, ?)";

            Item i1 = new Item();
            Item i2 = new Item(i1.getId(), i1.getName(), i1.getPrice(), i1.getBarcode(), i1.getQuantity());
            Order o1 = new Order(i2.getId(), 3921, i1.getName(), 3);

            //Create the MySql Insert PreparedStatement to Insert the Order into the Table "orders" of the database "grocerystore"
            PreparedStatement preparedStmt = myConn.prepareStatement(query2);
            preparedStmt.setInt(1, o1.getOrderID());
            preparedStmt.setInt(2, o1.getCustomerID());
            preparedStmt.setString(3, i1.getName());
            preparedStmt.setInt(4, o1.getAmount());

            // execute the preparedstatement
            preparedStmt.execute();

            System.out.println("Order Successfully Added!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method that gets the list from the MySql Database table "items"
    public static void listitems() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/grocerystore";
        String user = "root";
        String password = "loizos";

        try {
            Connection myConn = DriverManager.getConnection(url, user, password);

            //Statement and Query to print the Items of the Table
            String sql = "select * from grocerystore.items";
            Statement myStmt = myConn.createStatement();
            ResultSet rs = myStmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(rs.next()) {
                for(int i = 1; i < (columnsNumber + 1); i++)
                    System.out.print(rs.getString(i) + "  | ");
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method that marks the order as prepared and adds it to the "prepared" Hashmap of the ItemList Class
    public static void markasprep() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/grocerystore";
        String user = "root";
        String password = "loizos";

        try {
            Connection myConn = DriverManager.getConnection(url, user, password);

            String sql = "select * from grocerystore.items";
            Statement myStmt = myConn.createStatement();
            ResultSet rs = myStmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(rs.next()) {
                //Add order to the Hashmap "Prepared" of the ItemList Class
                for (int i = 1; i < (columnsNumber + 1); i++)
                    ItemList.addPrepItem(rsmd.getColumnName(i), rs.getObject(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Method that marks the order as delivered and adds it to the "delivered" Hashmap of the ItemList Class
    public static void markasdel() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/grocerystore";
        String user = "root";
        String password = "loizos";

        try {
            Connection myConn = DriverManager.getConnection(url, user, password);

            String sql = "select * from grocerystore.items";
            Statement myStmt = myConn.createStatement();
            ResultSet rs = myStmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(rs.next()) {
                //Add order to the Hashmap "Prepared" of the ItemList Class
                for (int i = 1; i < (columnsNumber + 1); i++)
                    ItemList.addDelItem(rsmd.getColumnName(i), rs.getObject(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Launch the Primary Stage of the Scene
    public static void main(String[] args) {
        launch(args);
    }
}

