/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Category;

import java.io.Serializable;


public class CategoryDTO implements Serializable {

    private String categoryID;
    private String categoryName;
    private boolean isDeleted;

    public CategoryDTO(String categoryID, String categoryName, boolean isDeleted) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.isDeleted = isDeleted;
    }

    public CategoryDTO() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
