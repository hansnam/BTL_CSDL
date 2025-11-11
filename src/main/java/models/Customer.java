/* Hans Nam */

package models;


public class Customer {    
    private String customerID, phone, email, address;

    public Customer () {};
    
    public Customer(String Cusid, String phone, String email, String address) {
        this.customerID = Cusid;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
   
}


