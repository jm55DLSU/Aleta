package Models;

import java.security.Timestamp;

public class Transaction {
    private Timestamp orderDateTime;
    private String buyer;
    private double subtotal;
    private String[] items;

    public Transaction(String buyer, double subtotal, String items, String orderDateTime){
        this.buyer = buyer;
        this.subtotal = subtotal;
        this.items = extractItems(items);
    }

    public Timestamp getOrderDateTime(){
        return this.orderDateTime;
    }

    public String getBuyer(){
        return this.buyer;
    }

    public double getSubtotal(){
        return this.subtotal;
    }

    public String[] getItems(){
        return this.items;
    }

    private String[] extractItems(String items){
        String[] output = items.split(",");
        for(int i = 0; i < output.length; i++)
            output[i].replaceAll("\\s+", "");
        return output;
    }
}
