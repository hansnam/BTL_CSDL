/* Hans Nam */

package models;

import java.util.List;
import java.util.ArrayList;


public class Order {
    
    private String id, status, date, CusID, EmpID;
    private int quantityType;
    private int totalAmount;
    private List<OrderDetail> items = new ArrayList<>();
    
    public Order () {};

    public Order(String id, String status, String date, String CusID, String EmpID, int totalAmount, int quantityType) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.CusID = CusID;
        this.EmpID = EmpID;
        this.totalAmount = totalAmount;
        this.quantityType = quantityType;
    }

    public String getID() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
    
    public String getCusID() {
        return CusID;
    }

    public String getEmpID() {
        return EmpID;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getQuantityType() {
        return quantityType;
    }

    public List<OrderDetail> getItems() {
        return items;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCusID(String CusID) {
        this.CusID = CusID;
    }

    public void setEmpID(String EmpID) {
        this.EmpID = EmpID;
    }

    public void setQuantityType(int quantityType) {
        this.quantityType = quantityType;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItems(List<OrderDetail> items) {
        this.items = items;
    }
    
    public int calcTotal () {
        int sum = 0;
        for (OrderDetail i : items) {
            sum += i.getSubTotal();
        }
        this.totalAmount = sum;
        return this.totalAmount;
    }
    
}
