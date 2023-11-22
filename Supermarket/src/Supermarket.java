import java.util.ArrayList;

import Drivers.Controller;
import Drivers.GUI;
import Drivers.SQL;
import Models.*;
import Views.Popup;

public class Supermarket {
    public static void main(String[] args) {
        //Building default SQL server data
        if(false){
            SQL sql = new SQL();
            sql.buildDB();
            sql.buildUsersTable();
            sql.buildProductsTable();
            sql.buildTransactionsTable();
            sql.buildTransactionItemTable();

            sql.addUser(new User("admin", "admin", 0, "Test Admin", "Philippines"));
            sql.addUser(new User("user", "user", 1, "Test Client", "Philippines"));
            ArrayList<User> users = sql.getUsers();
            for(int i = 0; i < users.size(); i++){
                users.get(i).printSummary();
            }
            System.out.println("");
            
            sql.addProduct(new Product("Test", 69.69, 1000));
            sql.addProduct(new Product("Test2", 420.420, 1000));
            ArrayList<Product> p = sql.getProducts();
            for(int i = 0; i < p.size(); i++){
                p.get(i).printSummary();
            }
            System.out.println("");

            User u = sql.getUser(2);
            u.printSummary();
            Product a = sql.getProduct(1);
            a.updateQuantity(420);
            Product b = sql.getProduct("Test2");
            b.updateQuantity(69);
            ArrayList<Product> cart = new ArrayList<Product>();
            cart.add(a); //Retrieve by prodID
            cart.add(b); //Retrieve by name
            for(int i = 0; i < cart.size(); i++)
                cart.get(i).printSummary();
            Transaction t = new Transaction(u, cart);
            sql.addTransaction(t);
            t.printSummary();
            System.out.println("");

            Transaction tr = sql.getTransaction(1);
            tr.printVerboseSummary();

            ArrayList<Transaction> ts = sql.getTransactionsByUser(2);
            for(int i = 0; i < ts.size(); i++){
                ts.get(i).printVerboseSummary();
            }
        }
        
        GUI g = new GUI();
        Controller c = new Controller(g);

        g.setVisible(true);
        g.switchToLogin();
        // g.switchToCustomerPage("johndoe");
        // g.switchToSalesPage("johndoe");
    }
}