/* Hans Nam */

package models;


public class CorporateCus extends Customer {
    private String CompanyName, TaxCode, ContactPerson;
    
    public CorporateCus(String Cus_id, String CompanyName, String TaxCode, String ContactPerson, String phone, String email, String address) {
        super(Cus_id, phone, email, address);
        this.CompanyName = CompanyName;
        this.TaxCode = TaxCode;
        this.ContactPerson = ContactPerson;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getTaxCode() {
        return TaxCode;
    }

    public String getContactPerson() {
        return ContactPerson;
    } 

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public void setTaxCode(String TaxCode) {
        this.TaxCode = TaxCode;
    }

    public void setContactPerson(String ContactPerson) {
        this.ContactPerson = ContactPerson;
    }
    
}
