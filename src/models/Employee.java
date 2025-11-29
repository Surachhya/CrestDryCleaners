package models;

public class Employee{
private int employeeId;
private int SSN;
private String name;
private int phone;
private String role;
private int storeId;


public Employee(int employeeId, int SSN, String name, int phone, String role, int storeId){
this.employeeId = employeeId;
this.SSN = SSN;
this.name = name;
this.phone = phone;
this.role = role;
this.storeId = storeId;
}

//Getters
public int getEmployeeId() {return employeeId;}
public int getSSN() {return SSN;}
public String getName() {return name;}
public int getPhone() {return phone;}
public String getRole() {return role;}
public int getStoreId() {return storeId;}


//Setters
public void setSSN(int SSN) {this.SSN = SSN;}
public void setName(String name) {this.name = name;}
public void setPhone(int phone) {this.phone = phone;}
public void setRole(String role) {this.role = role;}
public void setStoreId(int storeId) {this.storeId = storeId;}










}