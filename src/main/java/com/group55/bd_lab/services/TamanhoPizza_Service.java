/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.services;

import com.group55.bd_lab.models.TamanhoPizza;
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
public class TamanhoPizza_Service {
    public static ArrayList<TamanhoPizza> getList(){
        ArrayList<TamanhoPizza> list = new ArrayList<>();
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Create a statement
                Statement statement = connection.createStatement();
                
                // Execute a query
                String query = "SELECT nro_TamanhoPizza,nombre,descripcion,available FROM tamanhopizza";
                ResultSet resultSet = statement.executeQuery(query);
                
                // Process the result set
                while (resultSet.next()) {
                    TamanhoPizza entidadRow = new TamanhoPizza();
                    entidadRow.nro_TamanhoPizza = resultSet.getInt("nro_TamanhoPizza");
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
    public static TamanhoPizza getById(int nro_TamanhoPizza){
        TamanhoPizza tamanhoPizza = null;
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "SELECT nro_TamanhoPizza, nombre, descripcion, available FROM tamanhopizza WHERE nro_TamanhoPizza = ?";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, nro_TamanhoPizza);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                // Process the result set
                while (resultSet.next()) {
                    TamanhoPizza entidadRow = new TamanhoPizza();
                    entidadRow.nro_TamanhoPizza = resultSet.getInt("nro_TamanhoPizza");
                    entidadRow.nombre = resultSet.getString("nombre");
                    entidadRow.descripcion = resultSet.getString("descripcion");
                    entidadRow.available = resultSet.getString("available");
                    
                    tamanhoPizza = entidadRow;
                }
                
                // Return thel element
                
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tamanhoPizza;
    }
    public static int add(TamanhoPizza entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "INSERT INTO tamanhopizza SET " +
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
    public static boolean updateById(int nro_TamanhoPizza, TamanhoPizza entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE tamanhopizza SET " +
                        "nombre = ?," +
                        "descripcion = ?," +
                        "`update` = CURRENT_TIMESTAMP " +
                        "WHERE nro_TamanhoPizza = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setString(1, entidad.nombre);
                preparedStatement.setString(2, entidad.descripcion);
                preparedStatement.setInt(3, nro_TamanhoPizza);
                
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
    public static boolean deleteById(int nro_TamanhoPizza){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE tamanhopizza SET " +
                        "available = \"*\"" +
                        "WHERE nro_TamanhoPizza = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, nro_TamanhoPizza);
                
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
    public static boolean activateById(int nro_TamanhoPizza){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE tamanhopizza SET " +
                        "available = \"A\"" +
                        "WHERE nro_TamanhoPizza = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, nro_TamanhoPizza);
                
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
    public static boolean inactivateById(int nro_TamanhoPizza){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE tamanhopizza SET " +
                        "available = \"I\"" +
                        "WHERE nro_TamanhoPizza = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, nro_TamanhoPizza);
                
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
