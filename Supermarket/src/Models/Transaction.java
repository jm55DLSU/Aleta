package Models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Transaction {
    private Timestamp orderDateTime;
    private User buyer;
    private ArrayList<Product> items;
    private int transactID;

    public Transaction(int transactID, User buyer, ArrayList<Product> items, Timestamp orderDateTime){
        this.transactID = transactID;
        this.buyer = buyer;
        this.items = items;
        this.orderDateTime = orderDateTime;
    }

    public Transaction(User buyer, ArrayList<Product> items){
        this.transactID = -1;
        this.buyer = buyer;
        this.items = items;
        this.orderDateTime = null;
    }

    public int getTransactID(){
        return this.transactID;
    }

    public Timestamp getOrderDateTime(){
        return this.orderDateTime;
    }

    public User getBuyer(){
        return this.buyer;
    }

    public double getSubtotal(){
        double subtotal = 0;
        for(int i = 0; i < this.items.size(); i++)
            subtotal += this.items.get(i).getSubtotal();
        return subtotal;
    }

    public ArrayList<Product> getItems(){
        return this.items;
    }

    public int getQuantity(){
        int quantity = 0;
        for(int i = 0; i < this.items.size(); i++)
            quantity += this.items.get(i).getQuantity();
        return quantity;
    }

    public String getSummary(){
        return "Order Date & Time: " + this.orderDateTime.toString() + "\nTransaction ID: " + this.transactID + "\nQuantity: " + this.getQuantity() + "\nSubtotal: Php" + this.getSubtotal() + "\n\nItems:\n";
    }

    public String getVerboseSummary(){
        String bar = "========================================\n";
        String output = this.buyer.getSummary() + "\n" + getSummary();
        for(int i = 0; i < this.items.size(); i++)
            output += "    - " + this.items.get(i).getSummary() + "\n";
        return bar + output + bar;
    }

    public void printVerboseSummary(){
        System.out.println(getVerboseSummary());
    }

    public void printSummary(){
        System.out.println(getSummary());
    }
}
