/* Hans Nam */

package models;

import java.util.List;
import java.util.ArrayList;


public class Order {
    private String id, status, date, Cus_id, Emp_id;
    private int QuantityType;
    private int TotalAmount;
    private List<OrderDetail> items = new ArrayList<>();
    
    public Order () {}

    public Order(String id, String status, String date, String Cus_id, String Emp_id, int TotalAmount, int QuantityType) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.Cus_id = Cus_id;
        this.Emp_id = Emp_id;
        this.TotalAmount = TotalAmount;
        this.QuantityType = QuantityType;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getCus_id() {
        return Cus_id;
    }

    public String getEmp_id() {
        return Emp_id;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public int getQuantityType() {
        return QuantityType;
    }

    public List<OrderDetail> getItems() {
        return items;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCus_id(String Cus_id) {
        this.Cus_id = Cus_id;
    }

    public void setEmp_id(String Emp_id) {
        this.Emp_id = Emp_id;
    }

    public void setQuantityType(int QuantityType) {
        this.QuantityType = QuantityType;
    }

    public void setTotalAmount(int TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public void setItems(List<OrderDetail> items) {
        this.items = items;
    }
    
    public int calcTotal () {
        int sum = 0;
        for (OrderDetail i : items) {
            sum += i.getSubTotal();
        }
        this.TotalAmount = sum;
        return this.TotalAmount;
    }
    
}
