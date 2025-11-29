package services;

import utils.DBConnection;

import java.sql.*;
import java.util.*;

public class ReportService {

    public double getTotalSales() {
        String sql = "SELECT SUM(TotalAmount) AS total FROM Orders";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) return rs.getDouble("total");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public int getTotalCustomers() {
        String sql = "SELECT COUNT(*) AS cnt FROM Customer";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) return rs.getInt("cnt");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String, Integer> getVansPerStore() {
        Map<String, Integer> map = new LinkedHashMap<>();

        String sql = """
            SELECT s.Location AS store, COUNT(v.Van_ID) AS vans
            FROM Store s
            LEFT JOIN Van v ON s.Store_ID = v.Store_ID
            GROUP BY s.Store_ID
            ORDER BY s.Location
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("store"), rs.getInt("vans"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    public Map<String, Integer> getEmployeesPerStore() {
        Map<String, Integer> map = new LinkedHashMap<>();

        String sql = """
            SELECT s.Location AS store, COUNT(e.Employee_ID) AS employees
            FROM Store s
            LEFT JOIN Employee e ON s.Store_ID = e.Store_ID
            GROUP BY s.Store_ID
            ORDER BY s.Location
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("store"), rs.getInt("employees"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    public Map<String, Integer> getOrdersByStatus() {
        Map<String, Integer> map = new LinkedHashMap<>();

        String sql = """
            SELECT Status, COUNT(*) AS cnt
            FROM Orders
            GROUP BY Status
            ORDER BY Status
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("Status"), rs.getInt("cnt"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }
}