package Drivers;
import javax.swing.*;
import java.awt.*;
import Views.*;

public class GUI extends JFrame {
    public Controller c;
    private Login loginPage;
    private Employee employeePage;
    private Customer customerPage;
    private Sales salesPage;

    public GUI(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Supermarket");
        setName("Window"); // NOI18N
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        pack();
    }

    public void setController(Controller c){
        this.c = c;
    }

    public void switchToLogin(){
        this.loginPage = new Login(c);
        getContentPane().removeAll();
        getContentPane().add(this.loginPage);
        revalidate();
    }

    public Login getLoginPage(){
        return this.loginPage;
    }

    public void switchToEmployeePage(String username){
        this.employeePage = new Employee(c, username, c.getProducts());
        getContentPane().removeAll();
        getContentPane().add(this.employeePage);
        revalidate();
    }

    public Employee getEmployeePage(){
        return this.employeePage;
    }

    public void switchToSalesPage(String username){
        this.salesPage = new Sales(c, username);
        getContentPane().removeAll();
        getContentPane().add(this.salesPage);
        revalidate();
    }

    public Sales getSalesPage(){
        return this.salesPage;
    }

    public void switchToCustomerPage(String username){
        this.customerPage = new Customer(c, username, c.getProducts());
        getContentPane().removeAll();
        getContentPane().add(this.customerPage);
        revalidate();
    }

    public Customer getCustomerPage(){
        return customerPage;
    }
}
