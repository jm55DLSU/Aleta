package Drivers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.*;

public class SQL {
    private final String DB_URL_INIT = "jdbc:mysql://localhost/"; //For cases when you build a new DB.
    private final String DB_URL = "jdbc:mysql://localhost/Grocery";
    private final String USER = "root";
    private final String PASS = "";

    public SQL(){
        testConnection();
    }

    private Connection getInitialConnection(){
        try{
            Connection c = DriverManager.getConnection(DB_URL_INIT, USER, PASS);
            System.out.println("SQL Connection Success!");
            return c;
        } catch (SQLException e){
            System.out.println("SQL Connection Failed!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Connection getConnection(){
        try{
            Connection c = DriverManager.getConnection(DB_URL, USER, PASS);
            return c;
        } catch (SQLException e){
            System.out.println("SQL Connection Failed!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void testConnection(){
        System.out.println("Testing SQL Connection...");
        getInitialConnection();
    }

    public void buildDB(){
        String sql = "CREATE DATABASE Grocery"; //Custom Command
        Connection c = getInitialConnection();
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            if(pstmt.executeUpdate() != 0){
                System.out.println("DB Made!");
            }else{
                System.out.println("DB Not Made!");
            }
            c.close();
        } catch(SQLException e){
            System.out.println("SQLException occured on buildDB()!");
            System.out.println(e.getMessage());
        }   
    }

    public void buildUsersTable(){
        String[] sql = new String[3];
        sql[0] = "CREATE TABLE users (userID int(11) NOT NULL, username varchar(50) NOT NULL UNIQUE, password varchar(50) NOT NULL, name varchar(100) NOT NULL, address varchar(200) NOT NULL, type INT NOT NULL);";
        sql[1] = "ALTER TABLE users ADD PRIMARY KEY (userID), ADD KEY userID (userID);";
        sql[2] = "ALTER TABLE users MODIFY userID int(11) NOT NULL AUTO_INCREMENT UNIQUE;";
        Connection c = getConnection();
        try{
            for(int i = 0; i < sql.length; i++){
                PreparedStatement pstmt = c.prepareStatement(sql[i]);
                if(pstmt.executeUpdate() == 0)
                    System.out.println("Users Table Made!");
                else
                    System.out.println("Users Table  Made!");
            }
            
        } catch(SQLException e){
            System.out.println("SQLException occured on buildUsersTable()!");
            System.out.println(e.getMessage());
        }
    }

    public void buildProductsTable(){
        String[] sql = new String[3];
        sql[0] = "CREATE TABLE products (prodID int(11) NOT NULL, name varchar(100) NOT NULL UNIQUE, price decimal(10,2) NOT NULL, quantity int(11) NOT NULL);";
        sql[1] = "ALTER TABLE products ADD PRIMARY KEY (prodID);";
        sql[2] = "ALTER TABLE products MODIFY prodID int(11) NOT NULL AUTO_INCREMENT UNIQUE;";
        Connection c = getConnection();
        try{
            for(int i = 0; i < sql.length; i++){
                PreparedStatement pstmt = c.prepareStatement(sql[i]);
                if(pstmt.executeUpdate() == 0)
                    System.out.println("Products Table Made!");
                else
                    System.out.println("Products Table Not Made!");
            }
            
        } catch(SQLException e){
            System.out.println("SQLException occured on buildProductsTable()!");
            System.out.println(e.getMessage());
        }
    }

    public void buildTransactionsTable(){
        String[] sql = new String[4];
        sql[0] = "CREATE TABLE transactions (transactID int(11) NOT NULL, userID int(11) NOT NULL, subtotal decimal(10,0) NOT NULL, quantity int(11) NOT NULL, orderdatetime timestamp NOT NULL DEFAULT current_timestamp(), donation decimal(10,0));";
        sql[1] = "ALTER TABLE transactions ADD PRIMARY KEY (transactID), ADD KEY userID (userID);";
        sql[2] = "ALTER TABLE transactions ADD CONSTRAINT transactions_ibfk_1 FOREIGN KEY (userID) REFERENCES users (userID);";
        sql[3] = "ALTER TABLE transactions MODIFY transactID int(11) NOT NULL AUTO_INCREMENT UNIQUE;";
        Connection c = getConnection();
        try{
            for(int i = 0; i < sql.length; i++){
                PreparedStatement pstmt = c.prepareStatement(sql[i]);
                if(pstmt.executeUpdate() == 0)
                    System.out.println("Transactions Table Made!");
                else
                    System.out.println("Transactions Table Not Made!");
            }
            
        } catch(SQLException e){
            System.out.println("SQLException occured on buildTransactionsTable()!");
            System.out.println(e.getMessage());
        }
    }

    public void buildTransactionItemTable(){
        String[] sql = new String[3];
        sql[0] = "CREATE TABLE transaction_item (transactID int(11) NOT NULL, prodID int(11) NOT NULL, quantity int(11) NOT NULL, subtotal decimal(10,0) NOT NULL);";
        sql[1] = "ALTER TABLE transaction_item ADD KEY transactID (transactID,prodID), ADD KEY prodID (prodID);";
        sql[2] = "ALTER TABLE transaction_item ADD CONSTRAINT transaction_item_ibfk_1 FOREIGN KEY (prodID) REFERENCES products (prodID), ADD CONSTRAINT transaction_item_ibfk_2 FOREIGN KEY (transactID) REFERENCES transactions (transactID);";
        Connection c = getConnection();
        try{
            for(int i = 0; i < sql.length; i++){
                PreparedStatement pstmt = c.prepareStatement(sql[i]);
                if(pstmt.executeUpdate() == 0)
                    System.out.println("Transaction Items Table Made!");
                else
                    System.out.println("Transaction Items Table Not Made!");
            }
            
        } catch(SQLException e){
            System.out.println("SQLException occured on buildTransactionsTable()!");
            System.out.println(e.getMessage());
        }
    }

    public boolean addProduct(Product p){
        String sql = "INSERT INTO products(name, price, quantity) VALUES (?,?,?);";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setString(1, p.getName());
            pStatement.setDouble(2, p.getPrice());
            pStatement.setInt(3, p.getQuantity());
            return !pStatement.execute();
        } catch(SQLException e){
            System.out.println("SQLException occured on addProduct()!");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean addUser(User u){
        String sql = "INSERT INTO users(username, password, name, address, type) VALUES (?,?,?,?,?);";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setString(1, u.getUsername());
            pStatement.setString(2, u.getPassword());
            pStatement.setString(3, u.getName());
            pStatement.setString(4, u.getAddress());
            pStatement.setInt(5, u.getType());
            return !pStatement.execute();
        } catch(SQLException e){
            System.out.println("SQLException occured on addUser()!");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void addTransaction(Transaction t){
        double subtotal = 0;
        int quantity = 0;
        int transactID = 0;
        for(int i = 0; i < t.getItems().size(); i++){
            subtotal += t.getItems().get(i).getSubtotal();
            quantity += t.getItems().get(i).getQuantity();
        }
        //Overall receipt
        String sql = "INSERT INTO transactions(userID, subtotal, quantity, donation) VALUES (?,?,?,?);";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql, new String[] {"transactID"});
            pStatement.setInt(1, t.getBuyer().getUserID());
            pStatement.setDouble(2, subtotal);
            pStatement.setInt(3, quantity);
            pStatement.setDouble(4, t.getDonation());
            pStatement.executeUpdate();
            ResultSet rs = pStatement.getGeneratedKeys();
            while (rs.next())
                transactID = rs.getInt(1);
        } catch(SQLException e){
            System.out.println("SQLException occured on addTransaction() Overall!");
            System.out.println(e.getMessage());
        }
        //Individual items
        sql = "INSERT INTO transaction_item(transactID, prodID, quantity, subtotal) VALUES(?,?,?,?);";
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, transactID);
            for(int i = 0; i < t.getItems().size(); i++){
                pStatement.setInt(2, t.getItems().get(i).getProdID());
                pStatement.setInt(3, t.getItems().get(i).getQuantity());
                pStatement.setDouble(4, t.getItems().get(i).getSubtotal());
                pStatement.execute();
            } 
        }catch(SQLException e){
            System.out.println("SQLException occured on addTransaction() Individual!");
            System.out.println(e.getMessage());
        }
    }

    public boolean editProduct(Product p, boolean absolute){
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE prodID = ?;";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setString(1, p.getName());
            pStatement.setDouble(2, p.getPrice());
            if(!absolute) // Relative increase/decrease to the current quantity of products
                pStatement.setInt(3, p.getQuantity() + getProduct(p.getProdID()).getQuantity());
            else // Absolute change in the quantity of products
                pStatement.setInt(3, p.getQuantity());
            pStatement.setInt(4, p.getProdID());
            int r = pStatement.executeUpdate();
            if(r > 0)
                return true;
        } catch(SQLException e){
            System.out.println("SQLException occured on editProduct()!");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deductProduct(Product p){
        String sql = "UPDATE products SET quantity = ? WHERE prodID = ?;";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, getProduct(p.getProdID()).getQuantity()-p.getQuantity());
            pStatement.setInt(2, p.getProdID());
            int r = pStatement.executeUpdate();
            if(r > 0)
                return true;
        } catch(SQLException e){
            System.out.println("SQLException occured on editProduct()!");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean removeProduct(int prodID){
        String sql = "DELETE FROM products WHERE prodID=?;";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, prodID);
            return !pStatement.execute();
        } catch(SQLException e){
            System.out.println("SQLException occured on editProduct()!");
            System.out.println(e.getMessage());
        }
        return false; 
    }

    public ArrayList<User> getUsers(){
        String sql = "SELECT * FROM users";
        ArrayList<User> list = new ArrayList<User>();
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next()){
                User u = new User(  rSet.getInt("userID"), rSet.getString("username"), 
                                    rSet.getString("password"), rSet.getInt("type"), 
                                    rSet.getString("name"), rSet.getString("address"));
                list.add(u);
            }
        } catch(SQLException e){
            System.out.println("SQLException occured on getUsers()!");
            System.out.println(e.getMessage());
            return null;
        }
        return list;
    }

    public ArrayList<Product> getProducts(){
        String sql = "SELECT * FROM products";
        ArrayList<Product> list = new ArrayList<Product>();
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next()){
                Product p = new Product(rSet.getInt("prodID"), rSet.getString("name"), rSet.getDouble("price"), rSet.getInt("quantity"));
                list.add(p);
            }
        } catch(SQLException e){
            System.out.println("SQLException occured on getProducts()!");
            System.out.println(e.getMessage());
            return null;
        }
        return list;
    }

    public Product getProduct(int prodID){
        String sql = "SELECT * FROM products WHERE prodID=?";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, prodID);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next())
                return new Product(rSet.getInt("prodID"), rSet.getString("name"), rSet.getDouble("price"), rSet.getInt("quantity"));
        } catch(SQLException e){
            System.out.println("SQLException occured on getProduct()!");
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public Product getProduct(String name){
        String sql = "SELECT * FROM products WHERE name=?";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setString(1, name);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next())
                return new Product(rSet.getInt("prodID"), rSet.getString("name"), rSet.getDouble("price"), rSet.getInt("quantity"));
        } catch(SQLException e){
            System.out.println("SQLException occured on getProduct()!");
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public User getUser(String username){
        String sql = "SELECT * FROM users WHERE username=?";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setString(1, username);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next())
                return new User(rSet.getInt("userID"), rSet.getString("username"), 
                                rSet.getString("password"), rSet.getInt("type"), 
                                rSet.getString("name"), rSet.getString("address"));
        } catch(SQLException e){
            System.out.println("SQLException occured on getUser()!");
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public User getUser(int userID){
        String sql = "SELECT * FROM users WHERE userID=?";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, userID);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next())
                return new User(rSet.getInt("userID"), rSet.getString("username"), 
                                rSet.getString("password"), rSet.getInt("type"), 
                                rSet.getString("name"), rSet.getString("address"));
        } catch(SQLException e){
            System.out.println("SQLException occured on getUser()!");
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public User getBuyerTransaction(int transactID){
        String sql = "SELECT * FROM transactions WHERE transactID=?;";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, transactID);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next()){
                int userID = rSet.getInt("userID");
                System.out.println("User ID: " + userID);
                return getUser(userID);
            }
            return null;
        } catch(SQLException e){
            System.out.println("SQLException occured on getBuyerTransaction()!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Transaction> getTransactionsByUser(int userID){ // When retrieving transactions (by userID)
        String sql = "SELECT transactID FROM transactions WHERE userID=?;";
        String sql2 = "SELECT * FROM transactions WHERE transactID=?;";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, userID);
            ResultSet rSet = pStatement.executeQuery();
            ArrayList<Integer> transactIDs = new ArrayList<Integer>();
            while(rSet.next())
                transactIDs.add(rSet.getInt("transactID"));
            pStatement = c.prepareStatement(sql2);
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            for(int i = 0; i < transactIDs.size(); i++){
                pStatement.setInt(1, transactIDs.get(i));
                rSet = pStatement.executeQuery();
                transactions.add(getTransaction(transactIDs.get(i)));
            }
            return transactions;
        } catch(SQLException e){
            System.out.println("SQLException occured on getTransactionsByUser()!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Transaction getTransaction(int transactID){ // When retrieving a specific transaction (by ID)
        String sql = "SELECT * FROM transactions WHERE transactID=?;";
        Connection c = getConnection();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, transactID);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next()){
                User u = getUser(rSet.getInt("userID"));
                ArrayList<Product> items = getTransactItemsByTransactProducts(transactID);
                return new Transaction(rSet.getInt("transactID"), u, items, rSet.getTimestamp("orderdatetime"), rSet.getDouble("donation"));
            }
            return null;
        } catch(SQLException e){
            System.out.println("SQLException occured on getTransaction()!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Product> getSales(int prodID){
        ArrayList<TransactionItem> ts = getTransactItemsByProduct(prodID);
        ArrayList<Product> productsOnly = new ArrayList<Product>();
        for(int i = 0; i < ts.size(); i++)
            productsOnly.add(ts.get(i).getProduct());
        return productsOnly;
    }

    public ArrayList<Product> getSales(String name){
        return getSales(getProduct(name).getProdID());
    }

    private ArrayList<TransactionItem> getTransactItemsByProduct(int prodID){
        String sql = "SELECT * FROM transaction_item WHERE prodID=?;";
        Connection c = getConnection();
        ArrayList<TransactionItem> ti = new ArrayList<TransactionItem>();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, prodID);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next()){
                Product p = getProduct(rSet.getInt("prodID"));
                ti.add(new TransactionItem(rSet.getInt("transactID"), p, rSet.getInt("quantity"), rSet.getDouble("subtotal")));
            }
            return ti;
        } catch(SQLException e){
            System.out.println("SQLException occured on getTransactItemsByTransact()!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    private ArrayList<TransactionItem> getTransactItemsByTransact(int transactID){
        String sql = "SELECT * FROM transaction_item WHERE transactID=?;";
        Connection c = getConnection();
        ArrayList<TransactionItem> ti = new ArrayList<TransactionItem>();
        try{
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, transactID);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next()){
                Product p = getProduct(rSet.getInt("prodID"));
                ti.add(new TransactionItem(transactID, p, rSet.getInt("quantity"), rSet.getDouble("subtotal")));
            }
            return ti;
        } catch(SQLException e){
            System.out.println("SQLException occured on getTransactItemsByTransact()!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    private ArrayList<Product> getTransactItemsByTransactProducts(int transactID){
        ArrayList<TransactionItem> ti = getTransactItemsByTransact(transactID);
        ArrayList<Product> productsOnly = new ArrayList<Product>();
        for(int i = 0; i < ti.size(); i++)
            productsOnly.add(ti.get(i).getProduct());
        return productsOnly;
    }

    public User validateUser(String username, String password){
        User u = getUser(username);
        if(u != null)
            if(u.getPassword().equals(password))
                return u;
        return null;
    }

    public boolean checkUser(String username){
        if(getUser(username) != null)
            return true;
        return false;
    }
}