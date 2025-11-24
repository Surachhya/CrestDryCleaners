package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String ID = "sadhika4";
    private static final String PW = "COSC*ao1jk";
    private static final String TZ = "America/New_York";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/"+ID+"db?serverTimezone="+TZ+"#/";


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(SERVER, ID, PW);
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
        return null;
    }
}
