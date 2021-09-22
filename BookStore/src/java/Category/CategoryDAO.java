/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import Dbutils.MyConnection;


public class CategoryDAO implements Serializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<CategoryDTO> list = null;

    public List<CategoryDTO> getCategories() throws SQLException, NamingException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * "
                    + "FROM Category";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                String categoryName = rs.getString("categoryName");
                boolean isDeleted = rs.getBoolean("isDeleted");
                CategoryDTO categoryDTO
                        = new CategoryDTO(categoryID, categoryName, isDeleted);
                list.add(categoryDTO);
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

    public String getIDByName(String categoryName) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT categoryID "
                    + "FROM Category "
                    + "WHERE categoryName = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            String categoryID = "";
            while (rs.next()) {
                categoryID = rs.getString("categoryID");
            }
            return categoryID;
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
