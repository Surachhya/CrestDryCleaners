package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String ID = "sadhika4db";
    private static final String PW = "COSC*ao1jk";
    private static final String SERVER = "jdbc:mysql://triton.towson.edu:3360/?serverTimezone=EST#/"+ID+"db";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(SERVER, ID, PW);
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                System.err.println("Database connection failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
