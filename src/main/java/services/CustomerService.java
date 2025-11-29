package services;

import models.Customer;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();

        String sql = "SELECT Customer_ID, FName, LName, Phone, Email, Address, PaymentInfo FROM Customer";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("FName"),
                        rs.getString("LName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("PaymentInfo")
                );
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT Customer_ID, FName, LName, Phone, Email, Address, PaymentInfo FROM Customer WHERE Customer_ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getInt("Customer_ID"),
                            rs.getString("FName"),
                            rs.getString("LName"),
                            rs.getString("Phone"),
                            rs.getString("Email"),
                            rs.getString("Address"),
                            rs.getString("PaymentInfo")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // if not found
    }

    // Add Customer
    public boolean addCustomer(Customer c){
        String sql = "INSERT INTO Customer (FName, LName, Phone, Email, Address, PaymentInfo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getAddress());
            ps.setString(6, c.getPaymentInfo());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    // Update Customer
    public boolean updateCustomer(Customer c) {
        String sql = "UPDATE Customer SET FName=?, LName=?, Phone=?, Email=?, Address=?, PaymentInfo=? WHERE Customer_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getAddress());
            ps.setString(6, c.getPaymentInfo());
            ps.setInt(7, c.getCustomerId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Delete Customer
    public boolean deleteCustomer(int id){
        String sql = "DELETE FROM Customer WHERE Customer_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

}
