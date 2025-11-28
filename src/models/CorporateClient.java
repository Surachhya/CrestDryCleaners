package models;


public class CorporateClient{



private int corporateId;
private String companyName;
private String contactPerson;  
private double discountRate;
private int customerId;




public CorporateClient(int corporateId, 
    String companyName, String contactPerson, double discountRate, int customerId){
this.corporateId = corporateId;
this.companyName = companyName;
this.contactPerson = contactPerson;
this.discountRate = discountRate;
this.customerId = customerId;
}

//Getters
public int getCorporateId() {return corporateId;}
public String getCompanyName() {return companyName;}
public String getContactPerson() {return contactPerson;}
public double getDiscountRate() {return discountRate;}
public int getCustomerId() {return customerId;}

//Setters
public void setCompanyName(String companyName) {this.companyName = companyName;}
public void setContactPerson(String contactPerson) {this.contactPerson = contactPerson;}
public void setDiscountRate(double discountRate) {this.discountRate = discountRate;}
public void setCustomerId(int customerId) {this.customerId = customerId;}














}








