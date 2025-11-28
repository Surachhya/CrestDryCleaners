package services;

import models.Store;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreService {

    public List<Store> getAllStores() {
        List<Store> stores = new ArrayList<>();
        String sql = "SELECT * FROM Store";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Store s = new Store();
                s.setStoreId(rs.getInt("Store_ID"));
                s.setLocation(rs.getString("Location"));
                s.setManagerId(rs.getObject("Manager_ID", Integer.class));
                s.setSupervisorId(rs.getObject("Supervisor_ID", Integer.class));
                stores.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stores;
    }

    public boolean addStore(Store store) {
        String sql = "INSERT INTO Store (Location, Manager_ID, Supervisor_ID) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, store.getLocation());
            if (store.getManagerId() != null) ps.setInt(2, store.getManagerId());
            else ps.setNull(2, Types.INTEGER);

            if (store.getSupervisorId() != null) ps.setInt(3, store.getSupervisorId());
            else ps.setNull(3, Types.INTEGER);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStore(Store store) {
        String sql = "UPDATE Store SET Location=?, Manager_ID=?, Supervisor_ID=? WHERE Store_ID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, store.getLocation());

            if (store.getManagerId() != null) ps.setInt(2, store.getManagerId());
            else ps.setNull(2, Types.INTEGER);

            if (store.getSupervisorId() != null) ps.setInt(3, store.getSupervisorId());
            else ps.setNull(3, Types.INTEGER);

            ps.setInt(4, store.getStoreId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStore(int storeId) {
        String sql = "DELETE FROM Store WHERE Store_ID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, storeId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Store getStoreById(int storeId) {
        String sql = "SELECT * FROM Store WHERE Store_ID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, storeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Store s = new Store();
                    s.setStoreId(rs.getInt("Store_ID"));
                    s.setLocation(rs.getString("Location"));
                    s.setManagerId(rs.getObject("Manager_ID", Integer.class));
                    s.setSupervisorId(rs.getObject("Supervisor_ID", Integer.class));
                    return s;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
