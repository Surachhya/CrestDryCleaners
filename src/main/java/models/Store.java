package models;

public class Store {
    private int storeId;
    private String location;
    private Integer managerId;
    private Integer supervisorId;

    public Store() {}

    public Store(int storeId, String location, Integer managerId, Integer supervisorId) {
        this.storeId = storeId;
        this.location = location;
        this.managerId = managerId;
        this.supervisorId = supervisorId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public String toString() {
        return location; // useful for combo boxes
    }
}
