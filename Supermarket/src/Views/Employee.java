package Views;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import Views.Values;

public class Employee extends javax.swing.JPanel{
    JLabel title, productName, productPrice, productQuantity, productList;
    JTextField name, price, quantity;
    JButton add, remove, edit, logout;
    JTextArea productListText;

    public Employee(ActionListener ac, String loggedUser, ArrayList<String[]> products){ //Constructor
        setLayout(null);
        Values v = new Values(); //pang font

        title = new JLabel();
        title.setText("Supermarket - Welcome " + loggedUser);
        title.setBounds(0+20,20, 400,40);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setFont(v.titleFont);
        add(title);

        productName = new JLabel();
        productName.setText("Product Name: ");
        productName.setBounds(0+20,20+60, 400,40);
        productName.setVerticalAlignment(SwingConstants.CENTER);
        productName.setHorizontalAlignment(SwingConstants.LEFT);
        productName.setFont(v.labelFont);
        add(productName);

        productPrice = new JLabel();
        productPrice.setText("Product Price: ");
        productPrice.setBounds(0+20,20+(60*2), 400,40);
        productPrice.setVerticalAlignment(SwingConstants.CENTER);
        productPrice.setHorizontalAlignment(SwingConstants.LEFT);
        productPrice.setFont(v.labelFont);
        add(productPrice);

        productQuantity = new JLabel();
        productQuantity.setText("Product Quantity: ");
        productQuantity.setBounds(0+20,20+(60*3), 400,40);
        productQuantity.setVerticalAlignment(SwingConstants.CENTER);
        productQuantity.setHorizontalAlignment(SwingConstants.LEFT);
        productQuantity.setFont(v.labelFont);
        add(productQuantity);

        productList = new JLabel();
        productList.setText("Product List: ");
        productList.setBounds(0+20,20+(60*4), 400,40);
        productList.setVerticalAlignment(SwingConstants.CENTER);
        productList.setHorizontalAlignment(SwingConstants.LEFT);
        productList.setFont(v.labelFont);
        add(productList);

        name = new JTextField();
        name.setBounds(200, 20+60, 350, 40);
        name.setFont(v.inputFont);
        add(name);

        price = new JTextField();
        price.setBounds(200, 20+(60*2), 350, 40);
        price.setFont(v.inputFont);
        add(price);

        quantity = new JTextField();
        quantity.setBounds(200, 20+(60*3), 350, 40);
        quantity.setFont(v.inputFont);
        add(quantity);

        logout = new JButton();
        logout.setFont(v.btnFont);
        logout.setBounds(800/2+160, 500, 200, 40);
        logout.setText("Logout");
        logout.addActionListener(ac);
        logout.setActionCommand("logoutCommand");
        add(logout);

        add = new JButton();
        add.setFont(v.btnFont);
        add.setBounds(800/2+160, 20+(60*1), 200, 40);
        add.setText("Add");
        add.addActionListener(ac);
        add.setActionCommand("addStockCommand");
        add(add);

        edit = new JButton();
        edit.setFont(v.btnFont);
        edit.setBounds(800/2+160, 20+(60*2), 200, 40);
        edit.setText("Edit");
        edit.addActionListener(ac);
        edit.setActionCommand("editStockCommand");
        add(edit);

        remove = new JButton();
        remove.setFont(v.btnFont);
        remove.setBounds(800/2+160, 20+(60*3), 200, 40);
        remove.setText("Remove");
        remove.addActionListener(ac);
        remove.setActionCommand("removeStockCommand");
        add(remove);

        productListText = new JTextArea();
        productListText.setFont(v.textAreaFont);
        //productListText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Turpis egestas sed tempus urna et pharetra pharetra massa. At ultrices mi tempus imperdiet nulla. Lorem ipsum dolor sit amet. Nunc vel risus commodo viverra. Fringilla ut morbi tincidunt augue. Ullamcorper velit sed ullamcorper morbi tincidunt ornare massa eget. Orci nulla pellentesque dignissim enim sit amet. Nec sagittis aliquam malesuada bibendum arcu vitae elementum curabitur vitae. Sed vulputate odio ut enim blandit. Penatibus et magnis dis parturient. Vitae ultricies leo integer malesuada nunc vel risus commodo. Sit amet venenatis urna cursus eget. Morbi tristique senectus et netus et. Sem et tortor consequat id porta nibh venenatis cras. Hendrerit gravida rutrum quisque non tellus. Tellus at urna condimentum mattis pellentesque id nibh tortor. Ultricies integer quis auctor elit sed. Donec enim diam vulputate ut pharetra sit amet aliquam. Semper auctor neque vitae tempus quam pellentesque nec. Senectus et netus et malesuada fames ac turpis egestas sed. Nisl pretium fusce id velit ut tortor pretium. Tellus molestie nunc non blandit massa enim nec dui nunc. Varius vel pharetra vel turpis nunc eget lorem dolor sed. Fringilla phasellus faucibus scelerisque eleifend donec pretium vulputate sapien. Praesent tristique magna sit amet purus gravida quis blandit. Enim praesent elementum facilisis leo vel fringilla est ullamcorper. Pellentesque pulvinar pellentesque habitant morbi tristique senectus et. Interdum posuere lorem ipsum dolor. Enim sed faucibus turpis in eu mi bibendum neque. Imperdiet sed euismod nisi porta lorem mollis aliquam ut porttitor. Morbi non arcu risus quis varius. Quam lacus suspendisse faucibus interdum posuere lorem ipsum dolor. Justo donec enim diam vulputate ut pharetra sit amet aliquam. Enim lobortis scelerisque fermentum dui faucibus. Massa tempor nec feugiat nisl pretium fusce. Vitae tortor condimentum lacinia quis. Risus viverra adipiscing at in tellus integer feugiat scelerisque varius. Aliquam ut porttitor leo a diam. Ultrices vitae auctor eu augue ut. Habitant morbi tristique senectus et netus. In fermentum et sollicitudin ac. Consequat ac felis donec et odio pellentesque diam volutpat. Enim nunc faucibus a pellentesque sit amet porttitor eget dolor. Commodo elit at imperdiet dui accumsan sit amet. Eget egestas purus viverra accumsan in. Congue quisque egestas diam in arcu cursus. Orci phasellus egestas tellus rutrum tellus pellentesque eu. Pharetra sit amet aliquam id diam maecenas ultricies mi eget. Proin sed libero enim sed. Sed euismod nisi porta lorem mollis aliquam ut porttitor leo. In tellus integer feugiat scelerisque varius morbi enim. Vestibulum rhoncus est pellentesque elit ullamcorper dignissim cras. Diam volutpat commodo sed egestas egestas fringilla phasellus faucibus. Ipsum nunc aliquet bibendum enim facilisis gravida. Tortor aliquam nulla facilisi cras fermentum. Tempor id eu nisl nunc mi ipsum. Bibendum enim facilisis gravida neque convallis a cras semper. Pharetra massa massa ultricies mi quis hendrerit dolor. Tempus urna et pharetra pharetra massa massa ultricies. Pellentesque habitant morbi tristique senectus et netus et. Elit pellentesque habitant morbi tristique senectus et netus. Lacus sed viverra tellus in hac. Vel fringilla est ullamcorper eget nulla facilisi etiam. Fringilla phasellus faucibus scelerisque eleifend donec. Magna sit amet purus gravida quis blandit turpis. Consequat ac felis donec et. Eget lorem dolor sed viverra ipsum nunc aliquet bibendum enim. Et netus et malesuada fames ac turpis egestas. Sagittis nisl rhoncus mattis rhoncus urna neque viverra. Adipiscing elit duis tristique sollicitudin nibh sit amet commodo. Auctor eu augue ut lectus arcu. Turpis egestas integer eget aliquet nibh praesent. Massa tempor nec feugiat nisl pretium fusce id. Sapien et ligula ullamcorper malesuada proin libero nunc consequat interdum. Bibendum arcu vitae elementum curabitur vitae. Tellus in metus vulputate eu scelerisque felis imperdiet proin. Vitae purus faucibus ornare suspendisse sed nisi lacus sed viverra. Nunc sed blandit libero volutpat sed cras ornare arcu. Aenean et tortor at risus. At tellus at urna condimentum mattis pellentesque id nibh. Quis lectus nulla at volutpat. Amet mattis vulputate enim nulla aliquet porttitor. Sed vulputate odio ut enim blandit volutpat maecenas. Volutpat maecenas volutpat blandit aliquam etiam erat. Mi in nulla posuere sollicitudin aliquam ultrices sagittis.");
        productListText.setLineWrap(true);
        productListText.setWrapStyleWord(true);
        productListText.setBounds(20, 20+(60*5), 740, 160);
        productListText.setEditable(true);
        JScrollPane scroll = new JScrollPane (productListText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(productListText.getX(), productListText.getY(), productListText.getWidth(), productListText.getHeight());
        //add(productListText);
        add(scroll);

        revalidate();

        updateProductList(products);
    }

    public void updateProductList(ArrayList<String[]> products){
        for(int i = 0; i < products.size(); i++){
            String nameStr = products.get(i)[0] + "";
            String priceStr = products.get(i)[1] + "";
            String quantityStr = products.get(i)[2] + "";
            productListText.append(nameStr + " - Php " + priceStr + " @ " + quantityStr + "pc(s)\n");
        }
    }

    public String getProductName(){
        return name.getText();
    }

    public String getProductPrice(){
        return price.getText();
    }

    public String getProductQuantity(){
        return quantity.getText();
    }
}
