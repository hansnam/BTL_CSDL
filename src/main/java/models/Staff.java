/* Hans Nam */

package models;


public class Staff extends Employee {
    private String HireDate, M_id; 
    
    public Staff(String E_id, String E_name, String E_Gender, String Phone, String Email, int salary, String HireDate) {
        super(E_id, E_name, E_Gender, Phone, Email, salary);
        this.HireDate = HireDate;
        // this.M_id = M_id;
    }

    public String getHireDate() {
        return HireDate;
    }


    public void setHireDate(String HireDate) {
        this.HireDate = HireDate;
    }
}
