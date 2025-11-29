package models;

public class Store{

private int storeId;
private String location;
private int managerId;

public Store(int storeId, String location, int managerId){
this.storeId = storeId;
this.location = location;
this.managerId = managerId;
}

//Getters
public int getStoreId() {return storeId;}
public String getLocation() {return location;}
public int getManagerId() {return managerId;}

//Setters
public void setLocation(int storeId) {this.storeId = storeId;}
public void setManagerId(int managerId) {this.managerId = managerId;}
}