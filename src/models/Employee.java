package models;

public class Employee {
    private int employeeId;
    private String name;
    private String role; // ENUM
    private Integer storeId; // nullable
    private String phone;

    public Employee() {}

    public Employee(int employeeId, String name, String role, Integer storeId, String phone) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.storeId = storeId;
        this.phone = phone;
    }

    // Getters & Setters
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Integer getStoreId() { return storeId; }
    public void setStoreId(Integer storeId) { this.storeId = storeId; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
