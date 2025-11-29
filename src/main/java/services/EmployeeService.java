package services;

import models.Employee;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    // GET ALL EMPLOYEES
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT Employee_ID, Name, Role, Store_ID, Phone FROM Employee";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee e = new Employee(
                        rs.getInt("Employee_ID"),
                        rs.getString("Name"),
                        rs.getString("Role"),
                        rs.getObject("Store_ID") != null ? rs.getInt("Store_ID") : null,
                        rs.getString("Phone")
                );
                list.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }

        return list;
    }

    // GET BY ID
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM Employee WHERE Employee_ID = ?";
        Employee emp = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emp = new Employee(
                        rs.getInt("Employee_ID"),
                        rs.getString("Name"),
                        rs.getString("Role"),
                        rs.getObject("Store_ID") != null ? rs.getInt("Store_ID") : null,
                        rs.getString("Phone")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching employee: " + e.getMessage());
        }

        return emp;
    }

    // ADD EMPLOYEE
    public boolean addEmployee(Employee e) {
        String sql = "INSERT INTO Employee (Name, Role, Store_ID, Phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getName());
            stmt.setString(2, e.getRole());
            if (e.getStoreId() != null) stmt.setInt(3, e.getStoreId());
            else stmt.setNull(3, Types.INTEGER);
            stmt.setString(4, e.getPhone());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error adding employee: " + ex.getMessage());
            return false;
        }
    }

    // UPDATE EMPLOYEE
    public boolean updateEmployee(Employee e) {
        String sql = "UPDATE Employee SET Name=?, Role=?, Store_ID=?, Phone=? WHERE Employee_ID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getName());
            stmt.setString(2, e.getRole());
            if (e.getStoreId() != null) stmt.setInt(3, e.getStoreId());
            else stmt.setNull(3, Types.INTEGER);
            stmt.setString(4, e.getPhone());
            stmt.setInt(5, e.getEmployeeId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error updating employee: " + ex.getMessage());
            return false;
        }
    }

    // DELETE EMPLOYEE
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM Employee WHERE Employee_ID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error deleting employee: " + ex.getMessage());
            return false;
        }
    }

    // EXTRA QUERIES (for reporting/search)

    // FIND EMPLOYEES BY STORE
    public List<Employee> getEmployeesByStore(int storeId) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE Store_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, storeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("Employee_ID"),
                        rs.getString("Name"),
                        rs.getString("Role"),
                        rs.getObject("Store_ID") != null ? rs.getInt("Store_ID") : null,
                        rs.getString("Phone")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error getting employees by store: " + ex.getMessage());
        }

        return list;
    }

    // FIND EMPLOYEES BY ROLE
    public List<Employee> getEmployeesByRole(String role) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE Role = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("Employee_ID"),
                        rs.getString("Name"),
                        rs.getString("Role"),
                        rs.getObject("Store_ID") != null ? rs.getInt("Store_ID") : null,
                        rs.getString("Phone")
                ));
            }

        } catch (SQLException ex) {
            System.out.println("Error getting employees by role: " + ex.getMessage());
        }

        return list;
    }
}
