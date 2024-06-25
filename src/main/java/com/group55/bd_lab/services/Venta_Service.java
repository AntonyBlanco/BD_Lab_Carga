/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.services;

import com.group55.bd_lab.models.Venta;
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
public class Venta_Service {
    public static ArrayList<Venta> getList(){
        ArrayList<Venta> list = new ArrayList<>();
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Create a statement
                Statement statement = connection.createStatement();
                
                // Execute a query
                String query = "SELECT id_Venta,fecha,total,descripcion,available FROM venta";
                ResultSet resultSet = statement.executeQuery(query);
                
                // Process the result set
                while (resultSet.next()) {
                    Venta entidadRow = new Venta();
                    entidadRow.id_Venta = resultSet.getInt("id_Venta");
                    entidadRow.fecha = resultSet.getString("fecha");
                    entidadRow.total = resultSet.getInt("total");
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
    public static Venta getById(int id_Venta){
        Venta entidad = null;
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "SELECT id_Venta, fecha, total, descripcion, available FROM venta WHERE id_Venta = ?";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Venta);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                // Process the result set
                while (resultSet.next()) {
                    Venta entidadRow = new Venta();
                    entidadRow.id_Venta = resultSet.getInt("id_Venta");
                    entidadRow.fecha = resultSet.getString("fecha");
                    entidadRow.total = resultSet.getInt("total");
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
    public static int add(Venta entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "INSERT INTO venta SET " +
                        "fecha = ?," +
                        "total = ?," +
                        "descripcion = ?," +
                        "`update` = CURRENT_TIMESTAMP";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                
                // Setting parammeters
                preparedStatement.setString(1, entidad.fecha);
                preparedStatement.setInt(2, entidad.total);
                preparedStatement.setString(3, entidad.descripcion);
                
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
    public static boolean updateById(int id_Venta, Venta entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE venta SET " +
                        "fecha = ?," +
                        "total = ?," +
                        "descripcion = ?," +
                        "`update` = CURRENT_TIMESTAMP " +
                        "WHERE id_Venta = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setString(1, entidad.fecha);
                preparedStatement.setInt(2, entidad.total);
                preparedStatement.setString(3, entidad.descripcion);
                preparedStatement.setInt(4, id_Venta);
                
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
    public static boolean deleteById(int id_Venta){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE venta SET " +
                        "available = \"*\"" +
                        "WHERE id_Venta = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Venta);
                
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
    public static boolean activateById(int id_Venta){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE venta SET " +
                        "available = \"A\"" +
                        "WHERE id_Venta = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Venta);
                
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
    public static boolean inactivateById(int id_Venta){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE venta SET " +
                        "available = \"I\"" +
                        "WHERE id_Venta = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Venta);
                
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
