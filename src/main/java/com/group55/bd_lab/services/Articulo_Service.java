/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.services;

import com.group55.bd_lab.models.Articulo;
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
public class Articulo_Service {
    public static ArrayList<Articulo> getList(){
        ArrayList<Articulo> list = new ArrayList<>();
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Create a statement
                Statement statement = connection.createStatement();
                
                // Execute a query
                String query = "SELECT id_Articulo,nombre,descripcion,precio,tipoArticulo,available FROM articulo";
                ResultSet resultSet = statement.executeQuery(query);
                
                // Process the result set
                while (resultSet.next()) {
                    Articulo entidadRow = new Articulo();
                    entidadRow.id_Articulo = resultSet.getInt("id_Articulo");
                    entidadRow.nombre = resultSet.getString("nombre");
                    entidadRow.descripcion = resultSet.getString("descripcion");
                    entidadRow.precio = resultSet.getInt("precio");
                    entidadRow.tipoArticulo = resultSet.getString("tipoArticulo");
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
    public static Articulo getById(int id_Articulo){
        Articulo tamanhoPizza = null;
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "SELECT id_Articulo, nombre, descripcion, precio, tipoArticulo, available FROM articulo WHERE id_Articulo = ?";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Articulo);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                // Process the result set
                while (resultSet.next()) {
                    Articulo entidadRow = new Articulo();
                    entidadRow.id_Articulo = resultSet.getInt("id_Articulo");
                    entidadRow.nombre = resultSet.getString("nombre");
                    entidadRow.descripcion = resultSet.getString("descripcion");
                    entidadRow.precio = resultSet.getInt("precio");
                    entidadRow.tipoArticulo = resultSet.getString("tipoArticulo");
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
    public static int add(Articulo entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "INSERT INTO articulo SET " +
                        "nombre = ?," +
                        "precio = ?," +
                        "tipoArticulo = ?," +
                        "descripcion = ?," +
                        "`update` = CURRENT_TIMESTAMP";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                
                // Setting parammeters
                preparedStatement.setString(1, entidad.nombre);
                preparedStatement.setInt(2, entidad.precio);
                preparedStatement.setString(3, entidad.tipoArticulo);
                preparedStatement.setString(4, entidad.descripcion);
                
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
    public static boolean updateById(int id_Articulo, Articulo entidad){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE articulo SET " +
                        "nombre = ?," +
                        "precio = ?," +
                        "tipoArticulo = ?," +
                        "descripcion = ?," +
                        "`update` = CURRENT_TIMESTAMP " +
                        "WHERE id_Articulo = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setString(1, entidad.nombre);
                preparedStatement.setInt(2, entidad.precio);
                preparedStatement.setString(3, entidad.tipoArticulo);
                preparedStatement.setString(4, entidad.descripcion);
                preparedStatement.setInt(5, id_Articulo);
                
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
    public static boolean deleteById(int id_Articulo){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE articulo SET " +
                        "available = \"*\"" +
                        "WHERE id_Articulo = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Articulo);
                
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
    public static boolean activateById(int id_Articulo){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE articulo SET " +
                        "available = \"A\"" +
                        "WHERE id_Articulo = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Articulo);
                
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
    public static boolean inactivateById(int id_Articulo){
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Execute a query
                String query = "UPDATE articulo SET " +
                        "available = \"I\"" +
                        "WHERE id_Articulo = ?;";
                
                // Create a statement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                // Setting parammeters
                preparedStatement.setInt(1, id_Articulo);
                
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
