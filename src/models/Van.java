package models;

public class Van {
    private int vanID;
    private int storeID;
    private String plateNumber;
    private String model;
    private int year;
    private int capacity;

    public Van() {}

    public Van(int vanID, int storeID, String plateNumber, String model, int year, int capacity) {
        this.vanID = vanID;
        this.storeID = storeID;
        this.plateNumber = plateNumber;
        this.model = model;
        this.year = year;
        this.capacity = capacity;
    }

    // Getters and setters
    public int getVanID() { return vanID; }
    public void setVanID(int vanID) { this.vanID = vanID; }

    public int getStoreID() { return storeID; }
    public void setStoreID(int storeID) { this.storeID = storeID; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    @Override
    public String toString() {
        return plateNumber + " (" + model + ")";
    }
}
