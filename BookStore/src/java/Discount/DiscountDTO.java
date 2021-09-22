/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Discount;

import java.io.Serializable;


public class DiscountDTO implements Serializable {

    private String discountID;
    private String discountTitle;
    private int discountPercent;
    private String createDate;
    private String validDate;
    private boolean isUsed;

    public DiscountDTO(String discountID, String discountTitle, int discountPercent, String createDate, String validDate, boolean isUsed) {
        this.discountID = discountID;
        this.discountTitle = discountTitle;
        this.discountPercent = discountPercent;
        this.createDate = createDate;
        this.validDate = validDate;
        this.isUsed = isUsed;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

}
