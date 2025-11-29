package services;

import models.CorporateClient;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CorporateClientService {

    // Create
    public boolean addCorporateClient(CorporateClient client) {
        String sql = "INSERT INTO CorporateClient (Company_Name, Contact_Person, Discount_Rate) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, client.getCompanyName());
            stmt.setString(2, client.getContactPerson());
            stmt.setDouble(3, client.getDiscountRate());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read all
    public List<CorporateClient> getAllClients() {
        List<CorporateClient> clients = new ArrayList<>();
        String sql = "SELECT Corporate_ID, Company_Name, Contact_Person, Discount_Rate FROM CorporateClient";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CorporateClient client = new CorporateClient();
                client.setCorporateID(rs.getInt("Corporate_ID"));
                client.setCompanyName(rs.getString("Company_Name"));
                client.setContactPerson(rs.getString("Contact_Person"));
                client.setDiscountRate(rs.getDouble("Discount_Rate"));
                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    // Update
    public boolean updateCorporateClient(CorporateClient client) {
        String sql = "UPDATE CorporateClient SET Company_Name=?, Contact_Person=?, Discount_Rate=? WHERE Corporate_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, client.getCompanyName());
            stmt.setString(2, client.getContactPerson());
            stmt.setDouble(3, client.getDiscountRate());
            stmt.setInt(4, client.getCorporateID());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteCorporateClient(int corporateID) {
        String sql = "DELETE FROM CorporateClient WHERE Corporate_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, corporateID);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search by keyword in company name or contact person
    public List<CorporateClient> searchClients(String keyword) {
        List<CorporateClient> clients = new ArrayList<>();
        String sql = "SELECT Corporate_ID, Company_Name, Contact_Person, Discount_Rate FROM CorporateClient " +
                "WHERE Company_Name LIKE ? OR Contact_Person LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            String likeKeyword = "%" + keyword + "%";
            stmt.setString(1, likeKeyword);
            stmt.setString(2, likeKeyword);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CorporateClient client = new CorporateClient();
                    client.setCorporateID(rs.getInt("Corporate_ID"));
                    client.setCompanyName(rs.getString("Company_Name"));
                    client.setContactPerson(rs.getString("Contact_Person"));
                    client.setDiscountRate(rs.getDouble("Discount_Rate"));
                    clients.add(client);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
}
