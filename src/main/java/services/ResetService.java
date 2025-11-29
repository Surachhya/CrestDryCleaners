package services;

import utils.DBConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

public class ResetService {

    public void resetDatabase() {
        runSqlScript("/db/reset_schema.sql");
    }

    public void loadSampleData() {
        runSqlScript("/db/sample_data.sql");
    }

    private void runSqlScript(String resourcePath) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            InputStream in = getClass().getResourceAsStream(resourcePath);
            if (in == null) throw new RuntimeException("SQL file not found: " + resourcePath);

            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }

            String[] commands = sb.toString().split(";");

            for (String cmd : commands) {
                String sql = cmd.trim();
                if (!sql.isEmpty()) stmt.execute(sql);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error executing SQL script: " + resourcePath, e);
        }
    }
}