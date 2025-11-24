package services;

import models.Customer;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("FName"),
                        rs.getString("LName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("PaymentInfo")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}
