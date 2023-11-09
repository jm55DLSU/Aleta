import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Views.*;

public class Controller implements ActionListener {
    SQL sql;
    GUI g;
    public Controller(GUI g){
        this.sql = new SQL();
        this.g = g;
        this.g.setController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();

        if(act.equals("loginCommand")){
			System.out.println("Login");
            Login l = this.g.getLoginPage();
            String username = l.getUsername();
            String password = l.getPassword();
            int login = sql.checkCredential(username, password);

            //if the login is 0 -> employee page
            //if the login is 1 -> customer page
            //if the login is -1 -> invalid

            if (login == 0){
                System.out.println("Employee");
                this.g.switchToEmployeePage(username);
            }

            if (login == 1){
                System.out.println("Customer");
                this.g.switchToCustomerPage(username);
            }

            if (login == -1){
                System.out.println("Invalid");
                username = "";
                password = "";
            }
		}

        if(act.equals("exitCommand")){
			System.exit(0);
        }

        if(act.equals("logoutCommand")){
			System.out.println("Logout");
            this.g.switchToLogin();
        }

        if(act.equals("addStockCommand")){
			Employee employee = this.g.getEmployeePage();
            String productName = employee.getProductName();
            double productPrice = Double.parseDouble(employee.getProductPrice());
            int productQuantity = Integer.parseInt(employee.getProductQuantity());
            this.sql.addProduct(productName, productPrice, productQuantity);
        }
        
        if(act.equals("editStockCommand")){
			System.out.println("Edit Stock");
        }

        if(act.equals("removeStockCommand")){
			System.out.println("Remove Stock");
        }

        if(act.equals("addCartCommand")){
			System.out.println("Add Cart");
        }
    }

    public ArrayList<String[]> getProducts(){
        return this.sql.getAllProducts();
    }
}
