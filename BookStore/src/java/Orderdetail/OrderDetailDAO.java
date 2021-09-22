/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import Dbutils.MyConnection;


public class OrderDetailDAO implements Serializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean createOrderDetail(String orderDetailID, String bookID, int quantity, String orderID) throws SQLException, NamingException {
        try {
            connection = MyConnection.getConnection();
            String sql = "INSERT INTO OrderDetail(orderDetailID,bookID,quantity,orderID) "
                    + "VALUES(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, orderDetailID);
            ps.setString(2, bookID);
            ps.setInt(3, quantity);
            ps.setString(4, orderID);
            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    public String getLastestOrderDetailID() throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT TOP(1) "
                    + "orderDetailID from OrderDetail "
                    + "Order by orderDetailID DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String orderDetailID = "";
            while (rs.next()) {
                orderDetailID = rs.getString("orderDetailID");
            }
            return orderDetailID;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<OrderDetailDTO> getOrderDetailDTOByOrderID(String orderID) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM OrderDetail "
                    + "WHERE orderID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, orderID);
            rs = ps.executeQuery();
            List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
            while (rs.next()) {
                String orderDetailID = rs.getString("orderDetailID");
                String bookID = rs.getString("bookID");
                int quantity = rs.getInt("quantity");
                orderDetailDTOList.add(new OrderDetailDTO(orderDetailID, bookID, quantity, orderID));
            }
            return orderDetailDTOList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
