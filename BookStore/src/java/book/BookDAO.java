/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import Dbutils.MyConnection;


public class BookDAO implements Serializable {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<BookDTO> list = null;

    public List<BookDTO> getHotList() throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT TOP(3) * "
                    + "FROM Book "
                    + "WHERE quantity > 0 and isDelete = 'False' "
                    + "ORDER BY price ASC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String bookID = rs.getString("bookID");
                String title = rs.getString("title");
                String imageURL = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String author = rs.getString("author");
                String importDate = rs.getString("importDate");
                String lastUpdateDate = rs.getString("lastUpdateDate");
                int quantity = rs.getInt("quantity");
                boolean isDeleted = rs.getBoolean("isDelete");
                String categoryID = rs.getString("categoryID");
                BookDTO bookDTO = new BookDTO(bookID, title, imageURL,
                        description, price, author, importDate,
                        lastUpdateDate, quantity, isDeleted, categoryID);
                list.add(bookDTO);
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
        return list;
    }

    public List<BookDTO> getRandomList() throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT TOP(9) * "
                    + "FROM Book "
                    + "WHERE quantity > 0 and isDelete = 'False' "
                    + "ORDER BY NEWID()";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String bookID = rs.getString("bookID");
                String title = rs.getString("title");
                String imageURL = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String author = rs.getString("author");
                String importDate = rs.getString("importDate");
                String lastUpdateDate = rs.getString("lastUpdateDate");
                int quantity = rs.getInt("quantity");
                boolean isDeleted = rs.getBoolean("isDelete");
                String categoryID = rs.getString("categoryID");
                BookDTO bookDTO = new BookDTO(bookID, title, imageURL,
                        description, price, author, importDate,
                        lastUpdateDate, quantity, isDeleted, categoryID);
                list.add(bookDTO);
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
        return list;
    }

    public BookDTO getBookByTitle(String title) throws NamingException, SQLException {
        try {
            System.out.println("Check UDA : " + title);
            connection = MyConnection.getConnection();
            String sql = "SELECT * "
                    + "FROM Book "
                    + "WHERE title=? and quantity > 0 and isDelete = 'False' ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            rs = ps.executeQuery();
            BookDTO bookDTO = null;
            while (rs.next()) {
                System.out.println("Check UDA in rs ");
                String bookID = rs.getString("bookID");
                String titleTmp = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String author = rs.getString("author");
                String importDate = rs.getString("importDate");
                String lastUpdateDate = rs.getString("lastUpdateDate");
                int quantity = rs.getShort("quantity");
                boolean isDelete = rs.getBoolean("isDelete");
                String categoryID = rs.getString("categoryID");
                bookDTO = new BookDTO(bookID, title, image, description,
                        price, author, importDate, lastUpdateDate, quantity,
                        isDelete, categoryID);
            }
            return bookDTO;
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

    public float getMinPrice() throws SQLException, NamingException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT MIN(price) AS minPrice "
                    + "FROM Book";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            float temp = 0;
            while (rs.next()) {
                temp = rs.getFloat("minPrice");
            }
            return temp;
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

    public float getMaxPrice() throws SQLException, NamingException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT MAX(price) AS maxPrice "
                    + "FROM Book";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            float temp = 0;
            while (rs.next()) {
                temp = rs.getFloat("maxPrice");
            }
            return temp;
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

    public List<BookDTO> getListBySearchAndFilterbyPrice(String searchValue, float min, float max) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * "
                    + "FROM Book "
                    + "WHERE title LIKE ? and price > ? and price <= ? "
                    + "and quantity > 0 and isDelete = 'False' ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ps.setFloat(2, min);
            ps.setFloat(3, max);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String bookID = rs.getString("bookID");
                String titleTmp = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String author = rs.getString("author");
                String importDate = rs.getString("importDate");
                String lastUpdateDate = rs.getString("lastUpdateDate");
                int quantity = rs.getShort("quantity");
                boolean isDelete = rs.getBoolean("isDelete");
                String categoryID = rs.getString("categoryID");
                BookDTO bookDTO = new BookDTO(bookID, titleTmp, image, description,
                        price, author, importDate, lastUpdateDate, quantity,
                        isDelete, categoryID);
                list.add(bookDTO);
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

    public List<BookDTO> getBooksByCategoryID(String categoryID) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * "
                    + "FROM Book "
                    + "WHERE categoryID=? and quantity > 0 and isDelete = 'False' ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, categoryID);
            rs = ps.executeQuery();
            BookDTO bookDTO = null;
            list = new ArrayList<>();
            while (rs.next()) {
                String bookID = rs.getString("bookID");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String author = rs.getString("author");
                String importDate = rs.getString("importDate");
                String lastUpdateDate = rs.getString("lastUpdateDate");
                int quantity = rs.getShort("quantity");
                boolean isDelete = rs.getBoolean("isDelete");
                String categoryIDtemp = rs.getString("categoryID");
                bookDTO = new BookDTO(bookID, title, image, description,
                        price, author, importDate, lastUpdateDate, quantity,
                        isDelete, categoryID);
                list.add(bookDTO);
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

    public boolean createBook(String bookID, String title,
            String image, String description,
            float price, String author, String importDate,
            String lastUpdateDate, int quantity,
            boolean isDelete, String categoryID) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "INSERT INTO Book(bookID,title,image,description,price,author,importDate,lastUpdateDate,quantity,isDelete,categoryID) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, bookID);
            ps.setString(2, title);
            ps.setString(3, image);
            ps.setString(4, description);
            ps.setFloat(5, price);
            ps.setString(6, author);
            ps.setString(7, importDate);
            ps.setString(8, lastUpdateDate);
            ps.setInt(9, quantity);
            ps.setBoolean(10, isDelete);
            ps.setString(11, categoryID);
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

    public String getLastBookID() throws NamingException, SQLException {
        try {
            String lastBookID = "";
            connection = MyConnection.getConnection();
            String sql = "SELECT TOP(1) bookID From Book Order By bookID DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lastBookID = rs.getString("bookID");
            }
            return lastBookID;
        } finally {

        }
    }

    public boolean update(BookDTO bookDTO) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "UPDATE Book "
                    + "SET title=?,image=?,description=?,"
                    + "price=?,author=?,lastUpdateDate=?,"
                    + "quantity=?,categoryID=? "
                    + "WHERE bookId = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, bookDTO.getTitle());
            ps.setString(2, bookDTO.getImage());
            ps.setString(3, bookDTO.getDescription());
            ps.setFloat(4, bookDTO.getPrice());
            ps.setString(5, bookDTO.getAuthor());
            ps.setString(6, bookDTO.getLastUpdatedDate());
            ps.setInt(7, bookDTO.getQuantity());
            ps.setString(8, bookDTO.getCategoryID());
            ps.setString(9, bookDTO.getBookID());
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

    public boolean delete(String bookID) throws NamingException, SQLException {
        try {
            connection = MyConnection.getConnection();
            String sql = "UPDATE Book "
                    + "SET isDelete = ? "
                    + "WHERE bookId = ? ";
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setString(2, bookID);
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

    public BookDTO getBookDTOByBookID(String bookID) throws SQLException, NamingException {
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM Book "
                    + "WHERE bookID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, bookID);
            rs = ps.executeQuery();
            BookDTO bookDTO = null;
            while (rs.next()) {
                String title = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String author = rs.getString("author");
                String importDate = rs.getString("importDate");
                String lastUpdateDate = rs.getString("lastUpdateDate");
                int quantity = rs.getShort("quantity");
                boolean isDelete = rs.getBoolean("isDelete");
                String categoryID = rs.getString("categoryID");
                bookDTO = new BookDTO(bookID, title, image, description,
                        price, author, importDate, lastUpdateDate, quantity,
                        isDelete, categoryID);
            }
            return bookDTO;
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

    public boolean updateQuantity(String bookID, int quantity) throws SQLException, NamingException {
        try {
            connection = MyConnection.getConnection();
            String sql = "UPDATE Book "
                    + "SET quantity = ? "
                    + "WHERE bookID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setString(2, bookID);
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
