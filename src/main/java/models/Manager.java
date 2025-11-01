/* Hans Nam */

package models;


public class Manager extends Employee {
    private String title;

    public Manager(String E_id, String E_name, String E_Gender, String Phone, String Email, int salary, String title) {
        super(E_id, E_name, E_Gender, Phone, Email, salary);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
