/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orderdetail;

import java.io.Serializable;


public class OrderDetailDTO implements Serializable {

    private String orderDetailID;
    private String bookID;
    private int quantity;
    private String orderID;

    public OrderDetailDTO(String orderDetailID, String bookID, int quantity, String orderID) {
        this.orderDetailID = orderDetailID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.orderID = orderID;
    }

    public OrderDetailDTO() {
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

}
