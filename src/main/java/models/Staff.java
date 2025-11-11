/* Hans Nam */

package models;


public class Staff extends Employee {
    private String HireDate;
        
    public Staff(String EmployeeID, String Ename, String EGender, String Phone, String Email, int salary, String HireDate) {
        super(EmployeeID, Ename, EGender, Phone, Email, salary);
        this.HireDate = HireDate;
    }

    public String getHireDate() {
        return HireDate;
    }

    public void setHireDate(String HireDate) {
        this.HireDate = HireDate;
    }
}
