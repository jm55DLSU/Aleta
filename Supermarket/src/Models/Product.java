package Models;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, double price, int quantity){
        this.id = -1;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void updatePrice(double newPrice){
        this.price = newPrice;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Get the discounted price of the product given the discount % in decimal (e.g., 90% = 0.90)
     * @param discount
     * @return Discounted price of the item.
     */
    public double discountPrice(double discount){
        return this.price - (this.price * discount);
    }

    public int getProdID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }
    
    public int getQuantity(){
        return this.quantity;
    }

    public double getSubtotal(){
        return this.quantity * this.price;
    }

    public String getSummary(){
        return this.name + " : Php" + String.format("%.2f", this.price) + " @ " + this.quantity + "pc(s).";
    }

    public void printSummary(){
        System.out.println(getSummary());
    }
}