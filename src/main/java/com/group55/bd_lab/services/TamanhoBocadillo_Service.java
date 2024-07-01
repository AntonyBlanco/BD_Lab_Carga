/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.services;

import com.group55.bd_lab.models.TamanhoBocadillo;
import com.group55.bd_lab.dbConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author anjab
 */
public class TamanhoBocadillo_Service {
    public static ArrayList<TamanhoBocadillo> getList(){
        ArrayList<TamanhoBocadillo> list = new ArrayList<>();
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Create a statement
                Statement statement = connection.createStatement();
                
                // Execute a query
                String query = "SELECT nro_TamanhoBocadillo,nombre,descripcion,available FROM tamanhobocadillo";
                ResultSet resultSet = statement.executeQuery(query);
                
                // Process the result set
                while (resultSet.next()) {
                    TamanhoBocadillo entidadRow = new TamanhoBocadillo();
                    entidadRow.nro_TamanhoBocadillo = resultSet.getInt("nro_TamanhoBocadillo");
                    entidadRow.nombre = resultSet.getString("nombre");
                    entidadRow.descripcion = resultSet.getString("descripcion");
                    entidadRow.available = resultSet.getString("available");
                    list.add(entidadRow);
                }
                
                // Return thel list
                
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public static TamanhoBocadillo getById(int nro_TamanhoBocadillo){
        TamanhoBocadillo entidad = null;
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "SELECT nro_TamanhoBocadillo, nombre, descripcion, available FROM tamanhobocadillo WHERE nro_TamanhoBocadillo = ?";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, nro_TamanhoBocadillo);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                // Process the result set
                while (resultSet.next()) {
                    TamanhoBocadillo entidadRow = new TamanhoBocadillo();
                    entidadRow.nro_TamanhoBocadillo = resultSet.getInt("nro_TamanhoBocadillo");
                    entidadRow.nombre = resultSet.getString("nombre");
                    entidadRow.descripcion = resultSet.getString("descripcion");
                    entidadRow.available = resultSet.getString("available");
                    
                    entidad = entidadRow;
                }
                
                // Return thel element
                
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return entidad;
    }
    public static int add(TamanhoBocadillo entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "INSERT INTO tamanhobocadillo SET " +
                        "nombre = ?," +
                        "descripcion = ?," +
                        "`update` = CURRENT_TIMESTAMP";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                
                // Setting parammeters
                preparedStatement.setString(1, entidad.nombre);
                preparedStatement.setString(2, entidad.descripcion);
                
                // Execute the insert statement
                int affectedRows = preparedStatement.executeUpdate();

                // Check if any rows were affected
                if (affectedRows > 0) {
                    // Retrieve the auto-generated key(s)
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        // Return the ID of the newly inserted row
                        return generatedKeys.getInt(1);
                    }
                }
                
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    public static boolean updateById(int nro_TamanhoBocadillo, TamanhoBocadillo entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE tamanhobocadillo SET " +
                        "nombre = ?," +
                        "descripcion = ?," +
                        "`update` = CURRENT_TIMESTAMP " +
                        "WHERE nro_TamanhoBocadillo = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setString(1, entidad.nombre);
                preparedStatement.setString(2, entidad.descripcion);
                preparedStatement.setInt(3, nro_TamanhoBocadillo);
                
                preparedStatement.executeUpdate();
                
                // Return thel element
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public static boolean deleteById(int nro_TamanhoBocadillo){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE tamanhobocadillo SET " +
                        "available = \"*\"" +
                        "WHERE nro_TamanhoBocadillo = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, nro_TamanhoBocadillo);
                
                preparedStatement.executeUpdate();
                
                // Return thel element
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public static boolean activateById(int nro_TamanhoBocadillo){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE tamanhobocadillo SET " +
                        "available = \"A\"" +
                        "WHERE nro_TamanhoBocadillo = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, nro_TamanhoBocadillo);
                
                preparedStatement.executeUpdate();
                
                // Return thel element
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public static boolean inactivateById(int nro_TamanhoBocadillo){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE tamanhobocadillo SET " +
                        "available = \"I\"" +
                        "WHERE nro_TamanhoBocadillo = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, nro_TamanhoBocadillo);
                
                preparedStatement.executeUpdate();
                
                // Return thel element
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
