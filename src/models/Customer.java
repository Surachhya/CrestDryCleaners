package models;

public class Customer {
    private int customerId;
    private String fName;
    private String lName;
    private String phone;
    private String email;
    private String address;
    private String paymentInfo;

    // Constructor
    public Customer(int customerId, String fName, String lName, String phone, String email, String address, String paymentInfo) {
        this.customerId = customerId;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.paymentInfo = paymentInfo;
    }

    // Getters
    public int getCustomerId() { return customerId; }
    public String getFName() { return fName; }
    public String getLName() { return lName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPaymentInfo() { return paymentInfo; }

    // Setters if needed
}
