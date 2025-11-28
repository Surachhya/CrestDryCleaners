package models;

public class Item {
    private int itemID;
    private int orderID;
    private String type;
    private String brand;
    private String color;
    private String material;
    private String pattern;
    private String specialRequest;
    private String barcode;

    public Item() {}

    public Item(int itemID, int orderID, String type, String brand, String color, String material, String pattern, String specialRequest, String barcode) {
        this.itemID = itemID;
        this.orderID = orderID;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.material = material;
        this.pattern = pattern;
        this.specialRequest = specialRequest;
        this.barcode = barcode;
    }

    // Getters & setters
    public int getItemID() { return itemID; }
    public void setItemID(int itemID) { this.itemID = itemID; }

    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }

    public String getSpecialRequest() { return specialRequest; }
    public void setSpecialRequest(String specialRequest) { this.specialRequest = specialRequest; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
}
