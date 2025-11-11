/* Hans Nam */

package models;


public class Manager extends Employee {
    private String title;

    public Manager(String EmployeeID, String Ename, String EGender, String Phone, String Email, int salary, String title) {
        super(EmployeeID, Ename, EGender, Phone, Email, salary);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
