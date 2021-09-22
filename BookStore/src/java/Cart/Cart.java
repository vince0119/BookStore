/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cart;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import book.BookDAO;
import book.BookDTO;


public class Cart {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addToCart(String title) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
        if (this.items.containsKey(title)) {
            quantity = this.items.get(title) + 1;
        }
        this.items.put(title, quantity);
    }

    public void removeFromCart(String title) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(title)) {
            this.items.remove(title);
            if (this.items.size() == 0) {
                this.items = null;
            }
        }
    }

    public int getSumQuantityOfProducts() {
        int quantity = 0;
        for (Map.Entry<String, Integer> item : items.entrySet()) {
            quantity += item.getValue();
        }
        return quantity;
    }

    public float getTotalPrice() {
        BookDAO bookDAO = new BookDAO();
        float total = 0.00f;
        try {
            for (Map.Entry<String, Integer> item : this.items.entrySet()) {
                BookDTO bookDTO = bookDAO.getBookByTitle(item.getKey());
                total += bookDTO.getPrice() * item.getValue();
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public void updateQuantity(String title, int quantity) {
        if (this.items.containsKey(title)) {
            this.items.put(title, quantity);
        }
    }

}
