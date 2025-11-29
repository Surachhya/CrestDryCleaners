package models;

public class CorporateClient {
    private int corporateID;          // Corporate_ID in DB
    private String companyName;       // Company_Name
    private String contactPerson;     // Contact_Person
    private double discountRate;      // Discount_Rate

    // Constructor
    public CorporateClient() {}

    public CorporateClient(int corporateID, String companyName, String contactPerson, double discountRate) {
        this.corporateID = corporateID;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.discountRate = discountRate;
    }

    // Getters and Setters
    public int getCorporateID() {
        return corporateID;
    }

    public void setCorporateID(int corporateID) {
        this.corporateID = corporateID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return companyName; // For dropdowns and readable display
    }
}
