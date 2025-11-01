/* Hans Nam */

package models;


public class Employee {
    private String id, name, gender, phone, email;
    int salary;

    public Employee(String E_id, String E_name, String E_Gender, String Phone, String Email, int salary) {
        this.id = E_id;
        this.name = E_name;
        this.gender = E_Gender;
        this.phone = Phone;
        this.email = Email;
        this.salary = salary;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getSalary() {
        return salary;
    }

    public void setID(String E_id) {
        this.id = E_id;
    }

    public void setname(String E_name) {
        this.name = E_name;
    }

    public void setGender(String E_Gender) {
        this.gender = E_Gender;
    }

    public void setPhone(String Phone) {
        this.phone = Phone;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    
}
