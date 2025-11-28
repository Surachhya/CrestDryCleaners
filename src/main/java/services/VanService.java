package services;

import models.Van;
import models.Store;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VanService {

    // Create
    public boolean addVan(Van van) {
        String sql = "INSERT INTO Van (Store_ID, PlateNumber, Model, Year, Capacity) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, van.getStoreID());
            stmt.setString(2, van.getPlateNumber());
            stmt.setString(3, van.getModel());
            stmt.setInt(4, van.getYear());
            stmt.setInt(5, van.getCapacity());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read all
    public List<Van> getAllVans() {
        List<Van> list = new ArrayList<>();
        String sql = "SELECT Van_ID, Store_ID, PlateNumber, Model, Year, Capacity FROM Van";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Van v = new Van(
                        rs.getInt("Van_ID"),
                        rs.getInt("Store_ID"),
                        rs.getString("PlateNumber"),
                        rs.getString("Model"),
                        rs.getInt("Year"),
                        rs.getInt("Capacity")
                );
                list.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Update
    public boolean updateVan(Van van) {
        String sql = "UPDATE Van SET Store_ID=?, PlateNumber=?, Model=?, Year=?, Capacity=? WHERE Van_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, van.getStoreID());
            stmt.setString(2, van.getPlateNumber());
            stmt.setString(3, van.getModel());
            stmt.setInt(4, van.getYear());
            stmt.setInt(5, van.getCapacity());
            stmt.setInt(6, van.getVanID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteVan(int vanID) {
        String sql = "DELETE FROM Van WHERE Van_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, vanID);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search by plate number or model
    public List<Van> searchVans(String keyword) {
        List<Van> list = new ArrayList<>();
        String sql = "SELECT Van_ID, Store_ID, PlateNumber, Model, Year, Capacity FROM Van " +
                "WHERE PlateNumber LIKE ? OR Model LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            String kw = "%" + keyword + "%";
            stmt.setString(1, kw);
            stmt.setString(2, kw);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Van v = new Van(
                            rs.getInt("Van_ID"),
                            rs.getInt("Store_ID"),
                            rs.getString("PlateNumber"),
                            rs.getString("Model"),
                            rs.getInt("Year"),
                            rs.getInt("Capacity")
                    );
                    list.add(v);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Optional: get Vans by Store_ID
    public List<Van> getVansByStore(int storeID) {
        List<Van> list = new ArrayList<>();
        String sql = "SELECT Van_ID, Store_ID, PlateNumber, Model, Year, Capacity FROM Van WHERE Store_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, storeID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Van v = new Van(
                            rs.getInt("Van_ID"),
                            rs.getInt("Store_ID"),
                            rs.getString("PlateNumber"),
                            rs.getString("Model"),
                            rs.getInt("Year"),
                            rs.getInt("Capacity")
                    );
                    list.add(v);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
