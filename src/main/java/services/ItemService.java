package services;

import models.Item;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    public boolean addItem(Item item) {
        String sql = "INSERT INTO Item (Order_ID, Type, Brand, Color, Material, Pattern, SpecialRequest, Barcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderID());
            ps.setString(2, item.getType());
            ps.setString(3, item.getBrand());
            ps.setString(4, item.getColor());
            ps.setString(5, item.getMaterial());
            ps.setString(6, item.getPattern());
            ps.setString(7, item.getSpecialRequest());
            ps.setString(8, item.getBarcode());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> getAllItems() {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM Item";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("Item_ID"),
                        rs.getInt("Order_ID"),
                        rs.getString("Type"),
                        rs.getString("Brand"),
                        rs.getString("Color"),
                        rs.getString("Material"),
                        rs.getString("Pattern"),
                        rs.getString("SpecialRequest"),
                        rs.getString("Barcode")
                );
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateItem(Item item) {
        String sql = "UPDATE Item SET Order_ID=?, Type=?, Brand=?, Color=?, Material=?, Pattern=?, SpecialRequest=?, Barcode=? WHERE Item_ID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderID());
            ps.setString(2, item.getType());
            ps.setString(3, item.getBrand());
            ps.setString(4, item.getColor());
            ps.setString(5, item.getMaterial());
            ps.setString(6, item.getPattern());
            ps.setString(7, item.getSpecialRequest());
            ps.setString(8, item.getBarcode());
            ps.setInt(9, item.getItemID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItem(int itemID) {
        String sql = "DELETE FROM Item WHERE Item_ID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> searchItems(String keyword) {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM Item WHERE Type LIKE ? OR Brand LIKE ? OR Color LIKE ? OR Material LIKE ? OR Pattern LIKE ? OR SpecialRequest LIKE ? OR Barcode LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String k = "%" + keyword + "%";
            for (int i = 1; i <= 7; i++) ps.setString(i, k);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getInt("Item_ID"),
                            rs.getInt("Order_ID"),
                            rs.getString("Type"),
                            rs.getString("Brand"),
                            rs.getString("Color"),
                            rs.getString("Material"),
                            rs.getString("Pattern"),
                            rs.getString("SpecialRequest"),
                            rs.getString("Barcode")
                    );
                    list.add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
