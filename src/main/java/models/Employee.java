/* Hans Nam */

package models;


public class Employee {
    private String id, name, gender, phone, email;
    int salary;

    public Employee(String Eid, String Ename, String EGender, String Phone, String Email, int salary) {
        this.id = Eid;
        this.name = Ename;
        this.gender = EGender;
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

    public void setID(String Eid) {
        this.id = Eid;
    }

    public void setName(String Ename) {
        this.name = Ename;
    }

    public void setGender(String EGender) {
        this.gender = EGender;
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
