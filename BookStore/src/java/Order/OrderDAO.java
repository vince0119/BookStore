/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import Dbutils.MyConnection;


public class OrderDAO implements Serializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<OrderDTO> list = null;

    public boolean createNewOrder(String orderID, String username, String time, float total) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "INSERT INTO tblOrder(orderID,username,time,total) "
                    + "VALUES(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, orderID);
            ps.setString(2, username);
            ps.setString(3, time);
            ps.setFloat(4, total);
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

    public String getLastestOrderID() throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT TOP(1) "
                    + "orderID from tblOrder "
                    + "Order by orderID DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String orderID = "";
            while (rs.next()) {
                orderID = rs.getString("orderID");
            }
            return orderID;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<OrderDTO> getOrderDTOListByUsername(String username) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM tblOrder "
                    + "WHERE username = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            List<OrderDTO> list = new ArrayList<>();
            while (rs.next()) {
                String orderID = rs.getString("orderID");
                String time = rs.getString("time");
                float total = rs.getFloat("total");
                OrderDTO orderDTO = new OrderDTO(orderID, username, time, total);
                list.add(orderDTO);
            }
            return list;
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

    public OrderDTO getOrderDTOByOrderID(String orderID) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * "
                    + "FROM tblOrder "
                    + "WHERE orderID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, orderID);
            rs = ps.executeQuery();
            OrderDTO orderDTO = null;
            while (rs.next()) {
                String username = rs.getString("username");
                String time = rs.getString("time");
                float total = rs.getFloat("total");
                orderDTO = new OrderDTO(orderID, username, time, total);
            }
            return orderDTO;
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
