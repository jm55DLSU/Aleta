import java.util.ArrayList;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class Test_Supermarket_Java {
    private static final String DB_URL_INIT = "jdbc:mysql://localhost/"; //For cases when you build a new DB.
    private static final String DB_URL = "jdbc:mysql://localhost/Grocery";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static void addProduct(ArrayList<String[]> products) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String product_name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double product_price = scanner.nextDouble();
        System.out.print("Enter product stock: ");
        int product_stock = scanner.nextInt();
        String[] product_info = {product_name, Double.toString(product_price), Integer.toString(product_stock)};
        products.add(product_info);
        System.out.println("Product added successfully.");
    }

    public static void removeProduct(ArrayList<String[]> products) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the product to remove: ");
        String product_name = scanner.nextLine();
        boolean found = false;
        for (String[] product : products) {
            if (product[0].equals(product_name)) {
                products.remove(product);
                found = true;
                System.out.println("Product removed successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found.");
        }
    }

    public static void displayProducts(ArrayList<String[]> products) {
        System.out.println("Products:");
        System.out.println("Name ------ Price ------ Stock");
        for (String[] product : products) {
            System.out.println(product[0] + " ------ " + product[1] + " ------ " + product[2]);
        }
    }

    public static void viewStocks(ArrayList<String[]> products) {
        System.out.println("Available Stocks:");
        System.out.println("Product Name ------ Stock");
        for (String[] product : products) {
            System.out.println(product[0] + " ------ " + product[2]);
        }
    }

    public static void addStock(ArrayList<String[]> products, String product_name, int additional_stock) {
        for (String[] product : products) {
            if (product[0].equals(product_name)) {
                int stock = Integer.parseInt(product[2]);
                stock += additional_stock;
                product[2] = Integer.toString(stock);
                break;
            }
        }
    }

    public static void sortProducts(ArrayList<String[]> products) {
        products.sort((a, b) -> Double.compare(Double.parseDouble(a[1]), Double.parseDouble(b[1])));
        System.out.println("Products sorted by price:");
        displayProducts(products);
    }

    public static void searchProduct(ArrayList<String[]> products) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the product to search: ");
        String product_name = scanner.nextLine();
        boolean found = false;
        for (String[] product : products) {
            if (product[0].equals(product_name)) {
                found = true;
                System.out.println("\nProduct found:");
                System.out.println("Name ------ Price ------ Stock\n");
                System.out.println(product[0] + " --- " + product[1] + " --- " + product[2]);
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found.");
        }
    }

    public static void displayOrders(ArrayList<String[]> transactions) {
        for (String[] transaction : transactions) {
            System.out.println("Orders:");
            System.out.println("Buyer: " + transaction[0]);
            System.out.println("Address: " + transaction[1]);
            System.out.println("Phone Number: " + transaction[2]);
            System.out.println("Item: " + transaction[3]);
            System.out.println("Quantity: " + transaction[4]);
            System.out.println("Total: " + transaction[5]);
            System.out.println("==============================================================================================\n");
        }
    }

    public static void sellerMenu(ArrayList<String[]> products, ArrayList<String[]> transactions) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("==============================================================================================");
            System.out.println("\t\t\tSimplified Online");
            System.out.println("\t\t\t  Buying System");
            System.out.println("==============================================================================================\n");
            System.out.println("Product Menu");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. Display all products");
            System.out.println("4. Sort products by price");
            System.out.println("5. View order list");
            System.out.println("6. View stocks");
            System.out.println("7. Update stocks");
            System.out.println("8. Exit\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("\t\t\tAdd Product");
                System.out.println("==============================================================================================\n");
                addProduct(products);
                System.out.println();
            } else if (choice == 2) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("\t\t\tRemove Product");
                System.out.println("==============================================================================================\n");
                displayProducts(products);
                removeProduct(products);
                System.out.println();
            } else if (choice == 3) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("\t\t\tDisplay Product");
                System.out.println("==============================================================================================\n");
                displayProducts(products);
                System.out.println();
            } else if (choice == 4) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("\t\t\tSort Product");
                System.out.println("==============================================================================================\n");
                sortProducts(products);
                System.out.println();
            } else if (choice == 5) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("\t\t\t  View Order List");
                System.out.println("==============================================================================================\n");
                displayOrders(transactions);
            } else if (choice == 6) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("\t\t\t  View Stocks");
                System.out.println("==============================================================================================\n");
                viewStocks(products);
            } else if (choice == 7) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("\t\t\t  Add Stock");
                System.out.println("==============================================================================================\n");
                displayProducts(products);
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter the name of the product to add stock to: ");
                String product_name = scanner.nextLine();
                System.out.print("Enter the additional stock quantity: ");
                int additional_stock = scanner.nextInt();
                addStock(products, product_name, additional_stock);
                System.out.println("Stock for " + product_name + " has been updated.");
            } else if (choice == 8) {
                System.out.println(" Thank you for using online shop app! ");
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }

    public static void customerMenu(ArrayList<String[]> products, ArrayList<String[]> transactions) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter your name: ");
        String customer_name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        while (true) {
            System.out.println("==============================================================================================");
            System.out.println("\t\t\tOnline Buying System");
            System.out.println("==============================================================================================\n");
            System.out.println("1. Place an order");
            System.out.println("2. Search product");
            System.out.println("3. Exit\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("==============================================================================================\n");
                displayProducts(products);
                System.out.println();
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tCustomer Information");
                System.out.println("==============================================================================================\n");
                System.out.println("Your name: " + customer_name);
                System.out.println("Your address: " + address);
                System.out.println("Your phone number: " + phone);
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tCustomer Order");
                System.out.println("==============================================================================================\n");

                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter the product name: ");
                String product_name = scanner.nextLine();
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();
                boolean found = false;
                for (String[] product : products) {
                    if (product[0].equals(product_name)) {
                        found = true;
                        double price = Double.parseDouble(product[1]);
                        int stock = Integer.parseInt(product[2]);

                        System.out.print("Would you like to donate for hunger and malnutrition? (Y/N): ");
                        scanner.nextLine(); // Consume the newline character
                        String response = scanner.nextLine();

                        double amount = 0.0;

                        if (response.equalsIgnoreCase("Y")) {
                            System.out.print("How much would you like to donate? ");
                            amount = scanner.nextDouble();
                            System.out.println("Thank you for your donation of Php " + amount + ".\n");
                        }
                        double total_cost = (quantity * price) + amount;

                        String[] new_transaction = {customer_name, address, phone, product_name, Integer.toString(quantity), Double.toString(total_cost)};
                        transactions.add(new_transaction);

                        stock -= quantity;
                        product[2] = Integer.toString(stock);

                        System.out.println("==============================================================================================");
                        System.out.println("\t\t\tOrder Summary");
                        System.out.println("==============================================================================================\n");
                        System.out.println("Customer name: " + new_transaction[0]);
                        System.out.println("Address: " + new_transaction[1]);
                        System.out.println("Phone Number: " + new_transaction[2]);
                        System.out.println("Product name: " + new_transaction[3]);
                        System.out.println("Quantity: " + new_transaction[4]);
                        System.out.println("Price per item: Php " + price);

                        if (amount > 0.0) {
                            System.out.println("Donation amount: Php " + amount);
                        }

                        System.out.println("Total cost: Php " + new_transaction[5] + "\n");
                    } else {
                        System.out.println("Not enough stock available.");
                    }
                }
                if (!found) {
                    System.out.println("Product not found.");
                }
            } else if (choice == 2) {
                System.out.println("==============================================================================================");
                System.out.println("\t\t\tOnline Buying System");
                System.out.println("==============================================================================================\n");
                searchProduct(products);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static ArrayList<String[]> generateProducts(){
        ArrayList<String[]> products = new ArrayList<>();
        products.add(new String[]{"Mismo Coke", "20.0", "10"});
        products.add(new String[]{"Mountain Dew", "20.0", "15"});
        products.add(new String[]{"Royal", "20.0", "5"});
        products.add(new String[]{"Sprite", "20.0", "12"});
        products.add(new String[]{"Piattos", "17.0", "8"});
        products.add(new String[]{"Nova", "17.0", "20"});
        return products;
    }
    
    
    private static void testConnection(){
        System.out.println("Test SQL Connection");
    }
    
    private static ArrayList<String[]> readProduct(){
        String sampleReadDB = "select * from products"; //Custom Command
        ArrayList<String[]> products = new ArrayList<>();
        try(
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); //<== For permanent sql commands
                ResultSet rs = stmt.executeQuery(sampleReadDB)) {	//ResultSet if may output or return value from db  
            System.out.println("Connected database successfully...");
            while (rs.next()) {   
                products.add(new String[]{rs.getString("ProdName"), rs.getString("Price"),rs.getString("Stocks")});
            }
        } catch (Exception e) {
           //e.printStackTrace();
        }
        return products;
    }
    
    private static void buildProductTable(){
        String buildTable = "Create table products(ProdName char(20) primary key, Price decimal(6,2), Stocks int(5))"; //Custom Command
        int result = 0;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
                PreparedStatement pstmt = conn.prepareStatement(buildTable); //<== Useful if may specific query (i.e., yung ?); Although pwede rin sa permanent commands.
                result = pstmt.executeUpdate();
                if(result != 0){
                    System.out.println("Product Table Not Added!");
                }else{
                    System.out.println("Product Table Added!");
                }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    private static void buildTransactionTable(){
        String buildTransaction = "create table transactions(Buyer char(20) primary key, Subtotal decimal(6,2), Items int(5));"; //Custom Command
        int result = 0;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
                PreparedStatement pstmt = conn.prepareStatement(buildTransaction);
                result = pstmt.executeUpdate();
                if(result != 0){
                    System.out.println("Transaction Table Not Added!");
                }else{
                    System.out.println("Transaction Table Added!");
                }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    private static void addProduct(String prodname, double price, int stocks){
        String sampleWriteDB = "INSERT INTO products(Prodname, price, stocks) VALUES(?,?,?)"; //Custom Command
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {	  
            //PREPARED STATEMENT EXAMPLE
            PreparedStatement pstmt = conn.prepareStatement(sampleWriteDB);
            pstmt.setString(1, prodname);
            pstmt.setString(2, (price + ""));
            pstmt.setString(3, (stocks + ""));
            int result = pstmt.executeUpdate();
            if(result == 0){
                System.out.println("Product Not Added!");
            }else{
                System.out.println("Product Added!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        
        ArrayList<String[]> products = readProduct();
        for(int i = 0; i < products.size(); i++){
            System.out.println(products.get(i)[0] + " " + products.get(i)[1] + " " + products.get(i)[2]);
        }
    }
    
    private static void buildDB(){
        String sampleWriteDB = "Create Database Grocery"; //Custom Command
        try(Connection conn = DriverManager.getConnection(DB_URL_INIT, USER, PASS);) {	  
            //PREPARED STATEMENT EXAMPLE
            PreparedStatement pstmt = conn.prepareStatement(sampleWriteDB);
            int result = pstmt.executeUpdate();
            if(result != 0){
                System.out.println("DB Made!");
            }else{
                System.out.println("DB Not Made!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    private static void addTransaction(String buyer, double subtotal, int quantity){
        System.out.println("Add Transaction...");
        String sampleWriteDB = "INSERT INTO transactions(buyer, subtotal, items) VALUES(?,?,?)"; //Custom Command
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {	  
            //PREPARED STATEMENT EXAMPLE
            System.out.println("Preparing Statement...");
            PreparedStatement pstmt = conn.prepareStatement(sampleWriteDB);
            pstmt.setString(1, buyer);
            pstmt.setString(2, (subtotal + ""));
            pstmt.setString(3, (quantity + ""));
            System.out.println("Executing Statement...");
            if(pstmt.executeUpdate() == 0){
                System.out.println("Transaction Not Added!");
            }else{
                System.out.println("Transaction Added!");
            }
            System.out.println("Statement Executed!");
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        testConnection();
        buildDB();
        buildProductTable();
        buildTransactionTable();
        addProduct("Royal", 23.15, 1000);
        addTransaction("Juan Dela Cruz", 1234.56, 123);
        scanner.nextLine();
        
        
        ArrayList<String[]> products = generateProducts();

        ArrayList<String[]> transactions = new ArrayList<>();

        
        while (true) {
            System.out.println("==============================================================================================");
            System.out.println("\t\t\tSimplified Online");
            System.out.println("\t\t\t  Buying System");
            System.out.println("==============================================================================================\n");
            System.out.print("Are you a Seller or Customer: ");
            String user_type = scanner.nextLine().toLowerCase();
            System.out.println();

            if (user_type.equals("seller")) {
                String seller_username = "user123";
                String seller_password = "pass456";

                System.out.print("Enter your username: ");
                String entered_username = scanner.nextLine();
                System.out.print("Enter your password: ");
                String entered_password = scanner.nextLine();

                if (entered_username.equals(seller_username) && entered_password.equals(seller_password)) {
                    System.out.println("\nLogin successful. You are now logged in as a seller.");
                    sellerMenu(products, transactions);
                    break;
                } else {
                    System.out.println("Invalid username or password. Please try again.\n");
                }
            } else if (user_type.equals("customer")) {
                customerMenu(products, transactions);
            } else {
                System.out.println("Invalid user type. Please enter 'seller' or 'customer'.\n");
            }
        }
    }
}
