package com.group55.bd_lab.dbConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static Properties getProperties(){
        Properties properties = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return properties;
            }
            // Load the properties file
            properties.load(input);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return properties;
    }
    
    public static Connection getConnection() throws SQLException{
        Properties properties = getProperties();
        
        // Retrieve properties
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    
    public static void main(String[] args) {
        try {
            // Connect to the database
            
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
                
                // Create a statement
                Statement statement = connection.createStatement();
                
                // Execute a query
                String query = "SELECT * FROM tamanhopizza";
                ResultSet resultSet = statement.executeQuery(query);
                
                // Get metadata to dynamically process all columns
                int columnCount = resultSet.getMetaData().getColumnCount();
                
                // Process the result set
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String columnValue = resultSet.getString(i);
                        System.out.print(columnValue + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
