package models;

public class Payment {
    
private int paymentId;
private String paymentDate;
private String paymentType;
private double amount;
private int orderId;

public Payment(int paymentId, String paymentDate, String paymentType, double amount, int orderId){
this.paymentId = paymentId;
this.paymentDate = paymentDate;
this.paymentType = paymentType;
this.amount = amount;
this.orderId = orderId;
}

//Getters
public int getPaymentId() {return paymentId;}
public String getPaymentDate() {return paymentDate;}
public String getPaymentType() {return paymentType;}
public double getAmount() {return amount;}
public int getOrderId() {return orderId;}

//Setters
public void setPaymentDate(String paymentDate) {this.paymentDate = paymentDate;}
public void setPaymentType(String paymentType) {this.paymentType = paymentType;}
public void setAmount(double amount) {this.amount = amount;}
public void setOrderId(int orderId) {this.orderId = orderId;}


}
