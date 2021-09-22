/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;


public class Validate {

    public String getLoginFormValidated(String username, String password) {
        String error = "";
        if (username.isEmpty() && password.isEmpty()) {
            error = "Username and Password can not be blank !";
        } else if (username.isEmpty()) {
            error = "Username can not be blank !";
        } else if (password.isEmpty()) {
            error = "Password can not be blank !";
        }
        return error;
    }

    public String getCreateNewBookFormValidated(String title, String image,
            String description, String priceString, String author,
            String quantityString, String categoryName) {
        String error = "";
        System.out.println("Validate :" + quantityString);
        if (title.isEmpty() || image.isEmpty() || description.isEmpty()
                || priceString.isEmpty() || author.isEmpty()
                || quantityString.isEmpty() || categoryName.isEmpty()) {
            error = "Please make sure that none of above fields is empty !";
        } else {
            if (!isFloatNumber(priceString)) {
                error = "Price must be number !";
            } else {
                if (Float.parseFloat(priceString) <= 0) {
                    error = "Price must be more than 0";
                }
            }
            if (!isIntegerNumber(quantityString)) {
                error = "Quantity must be number [',' and '.' are not allowed] !";
            } else {
                if (Integer.parseInt(quantityString) <= 0) {
                    error = "Quantity must be more than 0";
                }
            }
        }

        return error;
    }

    public boolean isFloatNumber(String priceString) {
        try {
            Float.parseFloat(priceString);
            return true;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isIntegerNumber(String quantityString) {
        try {
            Integer.parseInt(quantityString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCreateVoucherFormValidated(String title, String percent, String day, String month, String year) {
        String error = "";
        if (title.isEmpty() || percent.isEmpty()) {
            error = "One of those above fields maybe empty ! Please check again !";
        } else if (!isIntegerNumber(percent)) {
            error = "Percent must be number";
        } else if (!isTimeValidated(day, month, year)) {
            error = "Check your Valid again !";
        }
        return error;
    }

    public boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isTimeValidated(String day, String month, String year) {
        if (!isIntegerNumber(year) || !isIntegerNumber(month) || !isIntegerNumber(day)) {
            return false;
        } else {
            int dayTmp = Integer.parseInt(day);
            int monthTmp = Integer.parseInt(month);
            int yearTmp = Integer.parseInt(year);
            if ((monthTmp == 4 || monthTmp == 6 || monthTmp == 9 || monthTmp == 11) && dayTmp == 31) {
                return false;
            }
            if (isLeapYear(yearTmp)) {
                if (monthTmp == 2 && dayTmp > 29) {
                    return false;
                }
            } else {
                if (monthTmp == 2 && dayTmp > 28) {
                    return false;
                }
            }
        }
        return true;
    }

    public String isValidDateRight(int currYear, int currMonth, int currDay,
            int validYear, int validMonth, int validDay) {
        String error = "";
        boolean check = false;
        if (validYear > currYear) {
            check = true;
        } else if (validYear < currYear) {
            check = false;
        } else if (validYear == validYear) {
            if (validMonth > currMonth) {
                check = true;
            } else if (validMonth < currMonth) {
                check = false;
            } else if (validMonth == currMonth) {
                if (validDay >= currDay) {
                    check = true;
                } else {
                    check = false;
                }
            }
        }
        if (check == false) {
            error = "Make sure that Valid Date is larger than Current Date !";
        }
        return error;
    }

}
