/* Hans Nam */

package models;


public class Customer {    
    private String customerID, phone, email, address;

    public Customer () {};
    
    public Customer(String Cusid, String phone, String email, String address) {
        this.customerID = Cusid;
        this.setPhone(phone);
        this.setEmail(email);
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
        if (phone == null || !phone.matches("[0-9]{10}")) {
            throw new IllegalArgumentException("Số điện thoại phải có đúng 10 chữ số");
        }
        this.phone = phone;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("[A-Za-z0-9]+@gmail.com")) {
            throw new IllegalArgumentException("Email không hợp lệ. Ví dụ: example@gmail.com");
        }
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
   
}


