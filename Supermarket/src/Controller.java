import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Models.*;
import Views.*;

public class Controller implements ActionListener {
    private SQL sql;
    private GUI g;
    private User loggedUser;
    private ArrayList<Product> cart;
    
    public Controller(GUI g){
        this.sql = new SQL();
        this.g = g;
        this.g.setController(this);
        cart = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();

        if(act.equals("loginCommand")){
			System.out.println("Login");
            Login l = this.g.getLoginPage();
            if(sql.checkCredential(l.getUsername(), l.getPassword()) != -1){
                loggedUser = sql.getUser(l.getUsername(), l.getPassword());
            }

            //if the login is 0 -> employee page
            //if the login is 1 -> customer page
            //if the login is -1 -> invalid

            if (loggedUser.getType() == 0){
                System.out.println("Employee");
                this.g.switchToEmployeePage(loggedUser.getUsername());
            }

            if (loggedUser.getType() == 1){
                System.out.println("Customer");
                this.g.switchToCustomerPage(loggedUser.getUsername());
            }

            if (loggedUser.getType() == -1){
                System.out.println("Invalid");
                loggedUser = null;
            }
		}

        if(act.equals("exitCommand")){
			System.exit(0);
        }

        if(act.equals("logoutCommand")){
			System.out.println("Logout");
            this.g.switchToLogin();
            loggedUser = null;
            cart.clear();
        }

        if(act.equals("addStockCommand")){
			Employee employee = this.g.getEmployeePage();
            String productName = employee.getProductName();
            double productPrice = Double.parseDouble(employee.getProductPrice());
            int productQuantity = Integer.parseInt(employee.getProductQuantity());
            Product p = new Product(productName, productPrice, productQuantity);
            this.sql.addProduct(p);
            this.g.getEmployeePage().updateProductList(getProducts());
        }
        
        if(act.equals("editStockCommand")){
			System.out.println("Edit Stock");
        }

        if(act.equals("removeStockCommand")){
			System.out.println("Remove Stock");
        }

        if(act.equals("searchProductCommand")){
            System.out.println("Search Item");
            Product p = sql.getProduct(this.g.getCustomerPage().getProductName());
            if(p == null){
                new Popup().warning("Search Item", "No item: \"" + this.g.getCustomerPage().getProductName() + "\"");
            }else{
                this.g.getCustomerPage().setProductInfo(p);
            }
        }

        if(act.equals("addToCartCommand")){
			System.out.println("Add Add Cart");
            Product targetProduct = sql.getProduct(this.g.getCustomerPage().getProductName());
            targetProduct.updateQuantity(this.g.getCustomerPage().getProductQuantity()); //<== this won't yet change the contents of DB nor the product list as it is a sampled Product object which will be used to list the productss in the cart.
            targetProduct.updatePrice(targetProduct.getPrice()*targetProduct.getQuantity());
            cart.add(targetProduct);
            this.g.getCustomerPage().updateCartList(cart);
            //note that the product list won't update based on the contents of the cart as the items on the cart is not necessarily items that were checked out.
        }

        if(act.equals("removeFromCartCommand")){
            System.out.println("Remove From Cart");

            /***
             * Simply update the ArrayList of cart where the target item (by name) is removed.
             */
        }

        if(act.equals("checkoutCommand")){
            System.out.println("Checkout na!");
            /**
             * Code for checking out.
             * 
             * In summary, it should make changes to the DB per item in cart ArrayList(),
             * such that the stocks will be reduced accordingly.
             */
        }
    }

    public ArrayList<Product> getProducts(){
        return this.sql.getAllProducts(); //Problem with this is that it sorts the list
    }
}
