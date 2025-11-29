package models;

public class ClothingItem {

private int itemId;
private String material;
private String pattern;
private String specialRequest;
private int barcode;
private String type;
private String brand;
private String color;
private int orderId;

public ClothingItem(int itemId, String material, 
    String pattern, String specialRequest, int barcode, String type, String brand, String color, int orderId){
this.itemId = itemId;
this.material = material;
this.pattern = pattern;
this.specialRequest = specialRequest;
this.barcode = barcode;
this.type = type;
this.brand =  brand;
this.color = color;
this.orderId = orderId;
}

//Getters
public int getItemId() {return itemId;}
public String getMaterial() {return material;}
public String getPattern() {return pattern;}
public String getSpecialRequest() {return specialRequest;}
public int getBarcode() {return barcode;}
public String getType() {return type;}
public String getBrand() {return brand;}
public String getColor() {return color;}
public int getOrderId() {return orderId;}

//Setters
public void setMaterial(String material) {this.material = material;}
public void setPattern(String pattern) {this.pattern = pattern;}
public void setSpecialRequest(String specialRequest) {this.specialRequest = specialRequest;}
public void setBarcode(int barcode) {this.barcode = barcode;}
public void setType(String type) {this.type = type;}
public void setBrand(String brand) {this.brand =  brand;}
public void setColor(String color) {this.color = color;}
public void setOrderId(int orderId){this.orderId = orderId;}
}