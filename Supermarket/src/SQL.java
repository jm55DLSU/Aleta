import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {
    private final String DB_URL_INIT = "jdbc:mysql://localhost/"; //For cases when you build a new DB.
    private final String DB_URL = "jdbc:mysql://localhost/Grocery";
    private final String USER = "root";
    private final String PASS = "";

    public SQL(){
        testConnection();
    }

    public void testConnection(){
        System.out.println("Testing SQL Connection...");
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
            System.out.println("SQL Connection Success!");
        } catch (SQLException e) {
            System.out.println("SQL Connection Failed!");
        } 
    }

    public ArrayList<String[]> generateProducts(){
        ArrayList<String[]> products = new ArrayList<>();
        products.add(new String[]{"Mismo Coke", "20.0", "10"});
        products.add(new String[]{"Mountain Dew", "20.0", "15"});
        products.add(new String[]{"Royal", "20.0", "5"});
        products.add(new String[]{"Sprite", "20.0", "12"});
        products.add(new String[]{"Piattos", "17.0", "8"});
        products.add(new String[]{"Nova", "17.0", "20"});
        return products;
    }

    public ArrayList<String[]> generateUsers(){
        ArrayList<String[]> users = new ArrayList<>();
        users.add(new String[]{"admin", "admin", "0"});
        users.add(new String[]{"juan", "juan", "1"});
        return users;
    }

    public void addUser(String username, String password, int type){
        String sampleWriteDB = "INSERT INTO user(username, password, type) VALUES(?,?,?)"; //Custom Command
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {	  
            //PREPARED STATEMENT EXAMPLE
            PreparedStatement pstmt = conn.prepareStatement(sampleWriteDB);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, (type+""));
            int result = pstmt.executeUpdate();
            if(result == 0){
                System.out.println("User Not Added!");
            }else{
                System.out.println("User Added!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public ArrayList<String[]> getAllProducts(){
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
    
    //0 = employee, 1 = client, -1 = invalid
    public int checkCredential(String username, String password){
        String sampleReadDB = "SELECT * from user where username=? and password=?"; //Custom Command
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {	//ResultSet if may output or return value from db  
            System.out.println("Connected database successfully...");
            PreparedStatement pstmt = conn.prepareStatement(sampleReadDB); //<== For permanent sql commands
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {   
                return Integer.parseInt(rs.getString("type")); //either 0/1
            }
        } catch (Exception e) {
           //e.printStackTrace();
        }
        return -1; //invalid or error
    }

    public void buildUserTable(){
        String buildTable = "Create table user(username char(20) primary key, password char(20), type int(1))"; //Custom Command
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

    public void buildProductTable(){
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
    
    public void buildTransactionTable(){
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
    
    public void addProduct(String prodname, double price, int stocks){
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
    }
    
    public void buildDB(){
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
    
    public void addTransaction(String buyer, double subtotal, int quantity){
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
}
