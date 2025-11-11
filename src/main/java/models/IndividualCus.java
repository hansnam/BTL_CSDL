/* Hans Nam */

package models;


public class IndividualCus extends Customer {
    private String ICName, Gender;

    public IndividualCus(String Cusid, String name, String gender, String phone, String email, String address) {
        super(Cusid, phone, email, address);
        this.ICName = name;
        this.Gender = gender;
    }

    public String getICName() {
        return ICName;
    }

    public String getGender() {
        return Gender;
    }

    public void setICName(String ICName) {
        this.ICName = ICName;
    }

    public void setICGender(String ICGender) {
        this.Gender = ICGender;
    }

    
}
