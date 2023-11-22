package Views;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import Views.Values;
import Models.Transaction;
import Models.TransactionItem;

public class Sales extends javax.swing.JPanel{
    JLabel title, searchName, mode, productPrice, salesQuantity, sales;
    JTextField name, price, quantity;
    JButton add, remove, edit, logout, stocksPage;
    JTextArea salesText;
    JCheckBox searchMode;

    public Sales(ActionListener ac, String loggedUser){ //Constructor
        setLayout(null);
        Values v = new Values(); //pang font

        title = new JLabel();
        title.setText("Supermarket - Welcome " + loggedUser);
        title.setBounds(0+20,20, 600,40);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setFont(v.titleFont);
        add(title);

        searchName = new JLabel();
        searchName.setText("Search Name/ID: ");
        searchName.setBounds(0+20,20+60, 400,40);
        searchName.setVerticalAlignment(SwingConstants.CENTER);
        searchName.setHorizontalAlignment(SwingConstants.LEFT);
        searchName.setFont(v.labelFont);
        add(searchName);

        mode = new JLabel();
        mode.setText("User Search Mode: ");
        mode.setBounds(800/2+160, 20+(60*2), 300, 40);
        mode.setVerticalAlignment(SwingConstants.CENTER);
        mode.setHorizontalAlignment(SwingConstants.LEFT);
        mode.setFont(v.labelFont);
        add(mode);

        searchMode = new JCheckBox();
        searchMode.setBounds(800/2+330, 20+(60*2), 50, 40);
        searchMode.setVerticalAlignment(SwingConstants.CENTER);
        searchMode.setHorizontalAlignment(SwingConstants.LEFT);
        searchMode.setFont(v.labelFont);
        add(searchMode);

        productPrice = new JLabel();
        productPrice.setText("Total Sales: ");
        productPrice.setBounds(0+20,20+(60*2), 400,40);
        productPrice.setVerticalAlignment(SwingConstants.CENTER);
        productPrice.setHorizontalAlignment(SwingConstants.LEFT);
        productPrice.setFont(v.labelFont);
        add(productPrice);

        salesQuantity = new JLabel();
        salesQuantity.setText("Total Volume: ");
        salesQuantity.setBounds(0+20,20+(60*3), 400,40);
        salesQuantity.setVerticalAlignment(SwingConstants.CENTER);
        salesQuantity.setHorizontalAlignment(SwingConstants.LEFT);
        salesQuantity.setFont(v.labelFont);
        add(salesQuantity);

        sales = new JLabel();
        sales.setText("Search Results: ");
        sales.setBounds(0+20,20+(60*4), 400,40);
        sales.setVerticalAlignment(SwingConstants.CENTER);
        sales.setHorizontalAlignment(SwingConstants.LEFT);
        sales.setFont(v.labelFont);
        add(sales);

        name = new JTextField();
        name.setBounds(200, 20+60, 350, 40);
        name.setFont(v.inputFont);
        add(name);

        price = new JTextField();
        price.setBounds(200, 20+(60*2), 350, 40);
        price.setFont(v.inputFont);
        price.setEditable(false);
        add(price);

        quantity = new JTextField();
        quantity.setBounds(200, 20+(60*3), 350, 40);
        quantity.setFont(v.inputFont);
        quantity.setEditable(false);
        add(quantity);

        logout = new JButton();
        logout.setFont(v.btnFont);
        logout.setBounds(800/2+160, 500, 200, 40);
        logout.setText("Logout");
        logout.addActionListener(ac);
        logout.setActionCommand("logoutCommand");
        add(logout);

        stocksPage = new JButton();
        stocksPage.setFont(v.btnFont);
        stocksPage.setBounds(0+20, 500, 200, 40);
        stocksPage.setText("Stocks Page");
        stocksPage.addActionListener(ac);
        stocksPage.setActionCommand("switchToStocksCommand");
        add(stocksPage);
        
        add = new JButton();
        add.setFont(v.btnFont);
        add.setBounds(800/2+160, 20+(60*1), 200, 40);
        add.setText("Search");
        add.addActionListener(ac);
        add.setActionCommand("searchSalesCommand");
        add(add);

        // edit = new JButton();
        // edit.setFont(v.btnFont);
        // edit.setBounds(800/2+160, 20+(60*2), 200, 40);
        // edit.setText("Edit");
        // edit.addActionListener(ac);
        // edit.setActionCommand("editStockCommand");
        // add(edit);

        // remove = new JButton();
        // remove.setFont(v.btnFont);
        // remove.setBounds(800/2+160, 20+(60*3), 200, 40);
        // remove.setText("Remove");
        // remove.addActionListener(ac);
        // remove.setActionCommand("removeStockCommand");
        // add(remove);

        salesText = new JTextArea();
        salesText.setFont(v.textAreaFont_small);
        salesText.setLineWrap(true);
        salesText.setWrapStyleWord(true);
        salesText.setBounds(20, 20+(60*5), 740, 160);
        salesText.setEditable(false);
        JScrollPane scroll = new JScrollPane (salesText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(salesText.getX(), salesText.getY(), salesText.getWidth(), salesText.getHeight());
        add(scroll);

        revalidate();
        //updateProductList(products);
    }

    public void updateSalesList(ArrayList<Transaction> transactions){
        salesText.setText("");
        for(int i = 0; i < transactions.size(); i++)
            salesText.append(transactions.get(i).getVerboseSummary());
    }

    public void clearSalesList(){
        salesText.setText("");
    }

    public void updateSalesData(int quantity, double subtotal){
        this.quantity.setText(quantity + "pc(s).");
        this.price.setText("Php " + subtotal);
    }

    public void clearSalesData(){
        this.quantity.setText("");
        this.price.setText("");
    }

    public String getSearchName(){
        return name.getText();
    }

    public boolean getModeState(){
        return this.searchMode.isSelected();
    }
}
