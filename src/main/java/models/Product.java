/* Hans Nam */

package models;


public class Product {
    
    private String id, name, descriptions;
    private int price;

    public Product () {};     
    
    public Product(String productID, String productName, int productPrice, String descriptions) {
        this.id = productID;
        this.name = productName;
        this.descriptions = descriptions;
        this.price = productPrice;
    }
    public String getID() {
        return id;
    }
    public void setID(String productID) {
        this.id = productID;
    }
    public void setName(String productName) {
        this.name = productName;
    }
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    public void setPrice(int productPrice) {
        this.price = productPrice;
    }
    public String getName() {
        return this.name;
    }
    public String getDescriptions() {
        return this.descriptions;
    }
    public int getPrice() {
        return this.price;
    }
    
}

