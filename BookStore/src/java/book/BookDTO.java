/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import java.io.Serializable;


public class BookDTO implements Serializable {

    private String bookID;
    private String title;
    private String image;
    private String description;
    private float price;
    private String author;
    private String importDate;
    private String lastUpdatedDate;
    private int quantity;
    private boolean isDeleted;
    private String categoryID;

    public BookDTO() {
    }

    public BookDTO(String bookID, String title, String image, String description, float price, String author, String lastUpdatedDate, int quantity, String categoryID) {
        this.bookID = bookID;
        this.title = title;
        this.image = image;
        this.description = description;
        this.price = price;
        this.author = author;
        this.lastUpdatedDate = lastUpdatedDate;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    public BookDTO(String bookID, String title, String image, String description, float price, String author, String importDate, String lastUpdatedDate, int quantity, boolean isDeleted, String categoryID) {
        this.bookID = bookID;
        this.title = title;
        this.image = image;
        this.description = description;
        this.price = price;
        this.author = author;
        this.importDate = importDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
        this.categoryID = categoryID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

}
