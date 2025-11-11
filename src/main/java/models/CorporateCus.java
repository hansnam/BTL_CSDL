/* Hans Nam */

package models;


public class CorporateCus extends Customer {    
    private String companyName, taxCode, contactPerson;
    
    public CorporateCus () {};
    
    public CorporateCus(String CusID, String companyName, String taxCode, String contactPerson, String phone, String email, String address) {
        super(CusID, phone, email, address);
        this.companyName = companyName;
        this.taxCode = taxCode;
        this.contactPerson = contactPerson;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public String getContactPerson() {
        return contactPerson;
    } 

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    
}
