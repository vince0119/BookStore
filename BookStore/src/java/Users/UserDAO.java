/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import Dbutils.MyConnection;


public class UserDAO {
    
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<UserDTO> list = null;
    
    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT username, password "
                    + "FROM [User] "
                    + "WHERE username=? and password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
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
        return false;
    }
    
    public boolean checkRoleByUsername(String username) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT isAdmin "
                    + "FROM [User] "
                    + "WHERE username=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            boolean isAdmin = false;
            while (rs.next()) {
                isAdmin = rs.getBoolean("isAdmin");
            }
            return isAdmin;
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
    
    public UserDTO getUserByUsername(String username) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM [User] "
                    + "WHERE username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            UserDTO userDTO = null;
            while (rs.next()) {
                String usernameTmp = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                boolean isAdmin = rs.getBoolean("isAdmin");
                int coin = rs.getInt("coin");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                String email = rs.getString("email");
                userDTO = new UserDTO(username, password, fullname,
                        isAdmin, coin, phoneNumber, address, email);
            }
            return userDTO;
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
    
    public boolean updateCoin(int coin, String username) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "UPDATE [User] "
                    + "SET coin = ? "
                    + "WHERE username = ? ";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, coin);
            ps.setString(2, username);
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
