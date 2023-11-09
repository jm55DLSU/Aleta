import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.Control;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;

public class Supermarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Building default SQL server data
        if(false){
            SQL sql = new SQL();
            sql.buildDB();
            sql.buildProductTable();
            sql.buildTransactionTable();
            sql.buildUserTable();

            //Testing SQL calls
            ArrayList<String[]> products = sql.generateProducts();
            for (int counter = 0; counter < products.size(); counter++){
                sql.addProduct(products.get(counter)[0], Double.parseDouble(products.get(counter)[1]), Integer.parseInt(products.get(counter)[2]));
            }
            ArrayList<String[]> users = sql.generateUsers();
            for (int counter = 0; counter < users.size(); counter++){
                sql.addUser(users.get(counter)[0], users.get(counter)[1], Integer.parseInt(users.get(counter)[2]));
            }
        }
        
        GUI g = new GUI();
        Controller c = new Controller(g);

        g.setVisible(true);
        g.switchToLogin();
    }
}

