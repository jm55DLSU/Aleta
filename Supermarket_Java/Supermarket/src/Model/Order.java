/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;
import Controller.SQL;
/**
 *
 * @author ejose
 */
public class Order {
    private String orderDateTime;
    private int orderNo;
    private int clientID;
    private int quantity;
    private double subtotal;
    private double tendered;
    private double change;
    private SQL sql;
    public Order(){
        sql = new SQL();
    }
}
