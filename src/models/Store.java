package models;

public class Store {
    private int storeId;
    private String location;

    public Store(int storeId, String location, Integer managerId, Integer supervisorId) {
        this.storeId = storeId;
        this.location = location;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return location; // displayed in JComboBox
    }
}
