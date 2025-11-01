/* Hans Nam */

package models;


public class IndividualCus extends Customer {
    private String IC_Name, Gender;

    public IndividualCus(String Cus_id, String name, String gender, String phone, String email, String address) {
        super(Cus_id, phone, email, address);
        this.IC_Name = name;
        this.Gender = gender;
    }

    public String getIC_name() {
        return IC_Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setIC_name(String IC_name) {
        this.IC_Name = IC_name;
    }

    public void setIC_gender(String IC_gender) {
        this.Gender = IC_gender;
    }

    
}
