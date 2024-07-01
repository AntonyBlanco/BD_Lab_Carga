/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.services;

import com.group55.bd_lab.models.Pizza;
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
public class Pizza_Service {
    public static ArrayList<Pizza> getList(){
        ArrayList<Pizza> list = new ArrayList<>();
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Create a statement
                Statement statement = connection.createStatement();
                
                // Execute a query
                String query = "SELECT id_Pizza, precioIngrediente, nro_TamanhoPizza, id_Articulo, available FROM pizza";
                ResultSet resultSet = statement.executeQuery(query);
                
                // Process the result set
                while (resultSet.next()) {
                    Pizza entidadRow = new Pizza();
                    entidadRow.id_Pizza = resultSet.getInt("id_Pizza");
                    entidadRow.precioIngrediente = resultSet.getInt("precioIngrediente");
                    entidadRow.nro_TamanhoPizza = resultSet.getInt("nro_TamanhoPizza");
                    entidadRow.id_Articulo = resultSet.getInt("id_Articulo");
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
    public static Pizza getById(int id_Pizza){
        Pizza entidad = null;
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "SELECT id_Pizza, precioIngrediente, nro_TamanhoPizza, id_Articulo, available FROM pizza WHERE id_Pizza = ?";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Pizza);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                // Process the result set
                while (resultSet.next()) {
                    Pizza entidadRow = new Pizza();
                    entidadRow.id_Pizza = resultSet.getInt("id_Pizza");
                    entidadRow.precioIngrediente = resultSet.getInt("precioIngrediente");
                    entidadRow.nro_TamanhoPizza = resultSet.getInt("nro_TamanhoPizza");
                    entidadRow.id_Articulo = resultSet.getInt("id_Articulo");
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
    public static int add(Pizza entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "INSERT INTO pizza SET " +
                        "precioIngrediente = ?," +
                        "nro_TamanhoPizza = ?," +
                        "id_Articulo = ?," +
                        "`update` = CURRENT_TIMESTAMP";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                
                // Setting parammeters
                preparedStatement.setInt(1, entidad.precioIngrediente);
                preparedStatement.setInt(2, entidad.nro_TamanhoPizza);
                preparedStatement.setInt(3, entidad.id_Articulo);
                
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
    public static boolean updateById(int id_Pizza, Pizza entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE pizza SET " +
                        "precioIngrediente = ?," +
                        "nro_TamanhoPizza = ?," +
                        "id_Articulo = ?," +
                        "`update` = CURRENT_TIMESTAMP " +
                        "WHERE id_Pizza = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, entidad.precioIngrediente);
                preparedStatement.setInt(2, entidad.nro_TamanhoPizza);
                preparedStatement.setInt(3, entidad.id_Articulo);
                preparedStatement.setInt(4, id_Pizza);
                
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
    public static boolean deleteById(int id_Pizza){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE pizza SET " +
                        "available = \"*\"" +
                        "WHERE id_Pizza = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Pizza);
                
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
    public static boolean activateById(int id_Pizza){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE pizza SET " +
                        "available = \"A\"" +
                        "WHERE id_Pizza = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Pizza);
                
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
    public static boolean inactivateById(int id_Pizza){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE pizza SET " +
                        "available = \"I\"" +
                        "WHERE id_Pizza = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Pizza);
                
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
