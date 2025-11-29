package models;


public class Van{

private int vanId;
private int storeId;
private String licensePlate;



public Van(int vanId, int storeId, String licensePlate){
this.vanId = vanId;
this.storeId = storeId;
this.licensePlate = licensePlate;
}


//Getters 
public int getVanId() {return vanId;}
public int getStoreId() {return storeId;}
public String getLicensePlate() {return licensePlate;}

//Setters
public void setStoreId(int storeId) {this.storeId = storeId;}
public void setLicensePlate(String licensePlate) {this.licensePlate = licensePlate;}

}