/* Hans Nam */

package models;


public class OrderDetail {
    private String P_id, Order_id;
    private int quantity;
    private double price;
    private String ProductName;
    
    public OrderDetail() {}

    public OrderDetail(String P_id, String Order_id, int quantity, double price) {
        this.P_id = P_id;
        this.Order_id = Order_id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getP_id() {
        return P_id;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    
    public String getProductName() {
        return ProductName;
    }

    public void setP_id(String P_id) {
        this.P_id = P_id;
    }

    public void setOrder_id(String Order_id) {
        this.Order_id = Order_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getSubTotal() {
        return quantity * price;
    }
    
    public void setProductName(String pn) {
        this.ProductName = pn;
    }
}
