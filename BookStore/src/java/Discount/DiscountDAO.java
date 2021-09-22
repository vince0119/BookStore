/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Discount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import Dbutils.MyConnection;


public class DiscountDAO implements Serializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean createNewVoucher(DiscountDTO discountDTO) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "INSERT INTO Discount(discountID,discountTitle,discountPercent,createDate,validDate,isUsed) "
                    + "VALUES(?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, discountDTO.getDiscountID());
            ps.setString(2, discountDTO.getDiscountTitle());
            ps.setInt(3, discountDTO.getDiscountPercent());
            ps.setString(4, discountDTO.getCreateDate());
            ps.setString(5, discountDTO.getValidDate());
            ps.setBoolean(6, false);
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

    public DiscountDTO getDiscountDTOPercent(int start, int percent) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT TOP(1)* FROM Discount WHERE discountPercent > ?  and discountPercent <= ? and isUsed='False'";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, percent);
            rs = ps.executeQuery();
            DiscountDTO discountDTO = null;
            while (rs.next()) {
                String discountID = rs.getString("discountID");
                String discountTitle = rs.getString("discountTitle");
                int discountPercent = rs.getInt("discountPercent");
                String createDate = rs.getString("createDate");
                String validDate = rs.getString("validDate");
                boolean isUsed = rs.getBoolean("isUsed");
                discountDTO = new DiscountDTO(discountID, discountTitle, discountPercent, createDate, validDate, isUsed);
            }
            return discountDTO;
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

    public boolean updateDiscountIsUsed(String discountID) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "UPDATE Discount "
                    + "SET isUsed = ? "
                    + "WHERE discountID = ?";
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setString(2, discountID);
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

}
