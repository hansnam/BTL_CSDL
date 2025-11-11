/* Hans Nam */

package models;


public class OrderDetail {

    private String productID, orderID;
    private int quantity;
    private int price;
    
    public OrderDetail () {};

    public OrderDetail(String orderID, String productID, int quantity, int price) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
    
    public void getOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setproductID(String productID) {
        this.productID = productID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getSubTotal() {
        return quantity * price;
    }

}
