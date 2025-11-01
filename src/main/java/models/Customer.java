/* Hans Nam */

package models;


public class Customer {
    private String Customer_ID, Phone, Email, Address;

    public Customer(String Cus_id, String phone, String email, String address) {
        this.Customer_ID = Cus_id;
        this.Phone = phone;
        this.Email = email;
        this.Address = address;
    }

    public Customer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setCustomer_ID(String Customer_id) {
        this.Customer_ID = Customer_id;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}


