package models;

public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String paymentInfo;

    public Customer(int customerId, String firstName, String lastName,
                    String phone, String email, String address, String paymentInfo) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.paymentInfo = paymentInfo;
    }

    // Getters
    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPaymentInfo() { return paymentInfo; }
}
