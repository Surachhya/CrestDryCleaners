package services;

import models.Order;
import utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();

        String sql = "SELECT o.Order_ID, o.Customer_ID, c.FName, c.LName, o.OrderDate, o.DueDate, " +
                "o.TotalPieces, o.Status, o.TotalAmount " +
                "FROM Orders o " +
                "JOIN Customer c ON o.Customer_ID = c.Customer_ID";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order o = new Order();

                o.setOrderId(rs.getInt("Order_ID"));
                o.setCustomerId(rs.getInt("Customer_ID"));
                o.setOrderDate(rs.getDate("OrderDate").toLocalDate());
                o.setCustomerName(rs.getString("FName") + " " + rs.getString("LName")); // Add this field in Order model
                o.setDueDate(rs.getDate("DueDate").toLocalDate());
                o.setTotalPieces(rs.getInt("TotalPieces"));
                o.setStatus(rs.getString("Status"));
                o.setTotalAmount(rs.getDouble("TotalAmount"));

                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Order getOrderById(int id) {
        String sql = "SELECT * FROM Orders WHERE Order_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("Order_ID"));
                o.setCustomerId(rs.getInt("Customer_ID"));
                o.setOrderDate(rs.getDate("OrderDate").toLocalDate());
                o.setDueDate(rs.getDate("DueDate").toLocalDate());
                o.setTotalPieces(rs.getInt("TotalPieces"));
                o.setStatus(rs.getString("Status"));
                o.setTotalAmount(rs.getDouble("TotalAmount"));
                return o;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addOrder(Order order) {
        String sql = "INSERT INTO Orders (Customer_ID, OrderDate, DueDate, TotalPieces, Status, TotalAmount) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getCustomerId());
            stmt.setDate(2, Date.valueOf(order.getOrderDate()));
            stmt.setDate(3, Date.valueOf(order.getDueDate()));
            stmt.setInt(4, order.getTotalPieces());
            stmt.setString(5, order.getStatus());
            stmt.setDouble(6, order.getTotalAmount());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateOrder(Order order) {
        String sql = "UPDATE Orders SET Customer_ID=?, OrderDate=?, DueDate=?, TotalPieces=?, Status=?, TotalAmount=? WHERE Order_ID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getCustomerId());
            stmt.setDate(2, Date.valueOf(order.getOrderDate()));
            stmt.setDate(3, Date.valueOf(order.getDueDate()));
            stmt.setInt(4, order.getTotalPieces());
            stmt.setString(5, order.getStatus());
            stmt.setDouble(6, order.getTotalAmount());
            stmt.setInt(7, order.getOrderId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteOrder(int id) {
        String sql = "DELETE FROM Orders WHERE Order_ID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
