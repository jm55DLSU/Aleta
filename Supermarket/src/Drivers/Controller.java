package Drivers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Models.*;
import Views.*;

public class Controller implements ActionListener {
    private SQL sql;
    private GUI g;
    private User loggedUser;
    private ArrayList<Product> cart;
    private boolean userSearch = false;
    private Product selectedProduct = null;
    
    public Controller(GUI g){
        this.sql = new SQL();
        this.g = g;
        this.g.setController(this);
        cart = new ArrayList<Product>();
    }

    private void login(){
        System.out.println("Login");
        Login l = this.g.getLoginPage();

        loggedUser = sql.validateUser(l.getUsername(), l.getPassword());
        if(loggedUser != null){
             if (loggedUser.getType() == 0){
                this.g.switchToEmployeePage(loggedUser.getUsername());
            }
            if (loggedUser.getType() == 1){
                this.g.switchToCustomerPage(loggedUser.getUsername());
            }
        }else{
            System.out.println("Invalid");
            new Popup().error("Invalid Login", "Invalid Username or Password.");
            loggedUser = null;
        }
    }

    private void signup(){
        User u = new Popup().newUser(this);
        if(u != null){
            if(!sql.checkUser(u.getUsername())){ //User !exists
                if(sql.addUser(u))
                    new Popup().info("New User Account", "User Successfully Added!");
                else
                    new Popup().warning("New User Account", "User Not Added!");
            }else{ //User exists!
                new Popup().warning("New User Account", "Username is already in use, please try another username.");
            }
        }
    }

    private void logout(){
        this.g.switchToLogin();
        loggedUser = null;
        cart.clear();
        selectedProduct = null;
        userSearch = false;
    }

    private void addProduct(){
        Employee employee = this.g.getEmployeePage();
        String productName = employee.getProductName();
        double productPrice = employee.getProductPrice();
        int productQuantity = employee.getProductQuantity();
        if(sql.addProduct(new Product(productName, productPrice, productQuantity))){
            new Popup().info("Add Product", "New Product Added!");
        }else{
            new Popup().error("Add Product", "New Product Not Added!");
        }
        this.g.getEmployeePage().updateProductList(sql.getProducts()); //SQL: Get Products from Products List
    }

    private void editProduct(){
        System.out.println("Edit Product");
        selectedProduct = sql.getProduct(this.g.getEmployeePage().getProductName());
        if(selectedProduct == null){
            new Popup().warning("Search Item", "No item: \"" + this.g.getEmployeePage().getProductName() + "\"");
        }else{
            selectedProduct.updateQuantity(this.g.getEmployeePage().getProductQuantity());
            selectedProduct.updatePrice(this.g.getEmployeePage().getProductPrice());
            boolean absolute = false;
            if(new Popup().yesno("Edit Product", "Change Absolute Quantity?"))
                absolute = true;
            if(sql.editProduct(selectedProduct, absolute))
                new Popup().info("Edit Product", "Product Edited!");
            else
                new Popup().error("Edit Product", "Product Not Edited!");
        }
        this.g.getEmployeePage().updateProductList(sql.getProducts()); //SQL: Get Products from Products List
    }

    private void removeProduct(){
        System.out.println("Remove Stock");
        selectedProduct = sql.getProduct(this.g.getEmployeePage().getProductName()); 
        if (sql.removeProduct(selectedProduct.getProdID())){
            System.out.println("Product Removed!");
            new Popup().info("Remove Product", "Product Removed!");
        }else {
            System.out.println("Product Not Removed!");
            new Popup().warning("Remove Product", "Product Not Removed!");
        }
        this.g.getEmployeePage().updateProductList(sql.getProducts()); //SQL: Get Products from Products List
        selectedProduct = null;
    }

    private void addToCart(){
        System.out.println("Add Cart");
        if(this.selectedProduct == null){
            this.selectedProduct = sql.getProduct(this.g.getCustomerPage().getProductName());
            this.g.getCustomerPage().setProductInfo(this.selectedProduct);
            if(this.selectedProduct == null)
                new Popup().warning("Add to Cart", "Product Does Not Exist!");
            else{
                selectedProduct.updateQuantity(this.g.getCustomerPage().getProductQuantity()); //<== this won't yet change the contents of DB nor the product list as it is a sampled Product object which will be used to list the productss in the cart.
                cart.add(selectedProduct);
            }
        }else{
            selectedProduct.updateQuantity(this.g.getCustomerPage().getProductQuantity()); //<== this won't yet change the contents of DB nor the product list as it is a sampled Product object which will be used to list the productss in the cart.
            cart.add(selectedProduct);
        }
        this.g.getCustomerPage().updateCartList(cart);
    }

    private void removeFromCart(){
        System.out.println("Remove From Cart");
        if(this.selectedProduct == null){
            this.selectedProduct = sql.getProduct(this.g.getCustomerPage().getProductName());
            if(this.selectedProduct == null)
                new Popup().warning("Add to Cart", "Product Does Not Exist!");
            else
                cart.remove(selectedProduct);
        }else
            cart.remove(selectedProduct);
        this.g.getCustomerPage().updateCartList(cart);
    }

    private void checkout(){
        System.out.println("Checkout");
        Transaction t = new Transaction(loggedUser, cart);
        sql.addTransaction(t);
        for(int p = 0; p < cart.size(); p++)
            sql.deductProduct(cart.get(p));
        cart.clear();
        this.g.getCustomerPage().clearCart();
        this.g.getCustomerPage().updateProductList(sql.getProducts()); //SQL: Get Products from Products List
    }

    private void searchCustomerProduct(){
        System.out.println("Search Product (Customer)");
        // Assuming that the Employee class has a method getProductName() to get the product name
        Customer customer = this.g.getCustomerPage();
        String productName = customer.getProductName();
        this.selectedProduct = sql.getProduct(productName);
        if (this.selectedProduct != null) {
            System.out.println("Product Found!");
            customer.setProductInfo(this.selectedProduct);
            new Popup().info("Search Result", "Product Found!");
        } else {
            System.out.println("Product Not Found!");
            new Popup().warning("Search Result", "Product Not Found!");
        }
        customer.updateProductList(sql.getProducts()); //SQL: Get Products from Products List
    }

    private void searchEmployeeProduct(){
        System.out.println("Search Product (Employee)");
        // Assuming that the Employee class has a method getProductName() to get the product name
        Employee employee = this.g.getEmployeePage();
        this.selectedProduct = sql.getProduct(employee.getProductName());
        if (this.selectedProduct != null) {
            System.out.println("Product Found!");
            employee.setProductInfo(this.selectedProduct);
            new Popup().info("Search Result", "Product Found!");
        } else {
            System.out.println("Product Not Found!");
            new Popup().warning("Search Result", "Product Not Found!");
        }
        employee.updateProductList(sql.getProducts()); //SQL: Get Products from Products List
    }
    
    private boolean isInt(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private void searchSales(){
        g.getSalesPage().clearSalesData();
        g.getSalesPage().clearSalesList();
        if(g.getSalesPage().getModeState()){
            System.out.println("Search Sales Mode: User");
            User u = null;
            if(isInt(g.getSalesPage().getSearchName()))
                u = sql.getUser(Integer.parseInt(g.getSalesPage().getSearchName()));
            else
                u = sql.getUser(g.getSalesPage().getSearchName());
            if(u == null){
                new Popup().warning("Search Sales: User", "Username/UserID does not exist");
            }else{
                ArrayList<Transaction> ts = sql.getTransactionsByUser(u.getUserID());
                if(ts == null)
                    new Popup().info("Search Sales: User", "No Sales Found for User");
                else
                    this.g.getSalesPage().updateSalesList(ts);
            }
        }else{
            System.out.println("Search Sales Mode: Product");
            String input = g.getSalesPage().getSearchName();
            ArrayList<Product> sales = new ArrayList<Product>();
            if(isInt(input))
                sales = sql.getSales(Integer.parseInt(input));
            else
                sales = sql.getSales(input);
            if(sales.size() > 0){
                int totalQuantity = 0;
                double totalSubtotal = 0;
                for(int i = 0; i < sales.size(); i++){
                    totalQuantity += sales.get(i).getQuantity();
                    totalSubtotal += sales.get(i).getSubtotal();
                }
                this.g.getSalesPage().updateSalesData(totalQuantity, totalSubtotal);
            }else{
                new Popup().warning("Search Sales: Product", "No Sales Found for Product");
            }
        }
    }

    private void switchToSales(){
        this.g.switchToSalesPage(this.loggedUser.getUsername());
    }

    private void switchToStocks(){
        this.g.switchToEmployeePage(this.loggedUser.getUsername());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        //Login/Signup Commands
        if(act.equals("loginCommand"))
			login();
        if(act.equals("exitCommand"))
			System.exit(0);
        if(act.equals("signupCommand"))
            signup();
        if(act.equals("logoutCommand"))
			logout();

        //Supermarket Employee Commands
        if(act.equals("addStockCommand"))
			addProduct();
        if(act.equals("editStockCommand"))
            editProduct();
        if (act.equals("removeStockCommand"))
            removeProduct();
        if (act.equals("searchEmployeeProductCommand"))
            searchEmployeeProduct();
        if (act.equals("searchSalesCommand"))
            searchSales();
        if (act.equals("switchToSalesCommand"))
            switchToSales();
        if (act.equals("switchToStocksCommand"))
            switchToStocks();
    
        //Supermarket Client Commands
        if(act.equals("addToCartCommand"))
            addToCart();
        if(act.equals("removeFromCartCommand"))
            removeFromCart();
        if (act.equals("checkoutCommand"))
            checkout();
        if (act.equals("searchCustomerProductCommand"))
            searchCustomerProduct();
    }

    public boolean usernameExists(String username){
        return false;
    }

    public ArrayList<Product> getProducts(){
        return sql.getProducts();
    }
}
