/* Hans Nam */

package models;


public class Product {
    private String id, name, descript;
    private int price;

    public Product () {}       
    
    public Product(String P_id, String P_name, int P_price, String P_descript) {
        this.id = P_id;
        this.name = P_name;
        this.descript = P_descript;
        this.price = P_price;
    }

    public String getP_id() {
        return id;
    }
    
    public String getP_name() {
        return name;
    }
    public String getP_descript() {
        return descript;
    }
    public int getP_price() {
        return price;
    }     
    public void setP_id(String P_id) {
        this.id = P_id;
    }
    public void setP_name(String P_name) {
        this.name = P_name;
    }
    public void setP_descript(String P_descript) {
        this.descript = P_descript;
    }
    public void setP_price(int P_price) {
        this.price = P_price;
    }
}
