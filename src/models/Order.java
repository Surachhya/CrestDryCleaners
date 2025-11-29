package models;

public class Order{
  
private int orderId;
private double totalAmount;
private String status;
private int totalPieces;
private String dueDate;
private String orderDate;
private int storeId;
private int customerId;    

public Order(int orderId, double totalAmount, String status, int totalPieces,
     String dueDate, String orderDate, int storeId, int customerId){
this.orderId = orderId;
this.totalAmount = totalAmount;
this.status = status;
this.totalPieces = totalPieces;
this.dueDate = dueDate;
this.orderDate = orderDate;
this.storeId = storeId;
this.customerId = customerId;
}

//Getters
public int getOrderId() {return orderId;}
public double getTotalAmount() {return totalAmount;}
public String getStatus() {return status;}
public int getTotalPieces() {return totalPieces;}
public String getDueDate() {return dueDate;}
public String getOrderDate() {return orderDate;}
public int getStoreId() {return storeId;}
public int getCustomerId() {return customerId;}

//Setters
public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}
public void setStatus(String status) {this.status = status;}
public void setTotalPieces(int totalPieces) {this.totalPieces = totalPieces;}
public void setDueDate(String dueDate) {this.dueDate = dueDate;}
public void setOrderDate(String orderDate) {this.orderDate = orderDate;}
public void setStoreId(int storeId) {this.storeId = storeId;}
public void setCustomerId(int customerId) {this.customerId = customerId;}
}