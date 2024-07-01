/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.services;

import com.group55.bd_lab.models.Cliente;
import com.group55.bd_lab.models.TamanhoPizza;
import com.group55.bd_lab.dbConnection.DatabaseConnection;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author anjab daniel
 */
public class Cliente_Service {
    private static final String[][] fields = getClassFields();
    public static ArrayList<TamanhoPizza> getList(){
        ArrayList<TamanhoPizza> list = new ArrayList<>();
        try {
            // Connect to the database
            
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                // Create a statement
                Statement statement = connection.createStatement();
                
                // Execute a query
                String query = "SELECT ";
                
                for(int i = 0; i < fields.length; i++){
                    if(i!=0) query += ",";
                    query += fields[i][0];
                    System.out.println(" (*) " + fields[i][0] + " : " + fields[i][1]);
                }
                query += " FROM tamanhopizza";
                ResultSet resultSet = statement.executeQuery(query);
                
                // Get metadata to dynamically process all columns
                int columnCount = resultSet.getMetaData().getColumnCount();
                
                if(columnCount != fields.length){
                    System.out.println("Database entity doesn't match class attributes");
                    return list;
                }
                
                // Process the result set
                while (resultSet.next()) {
                    Cliente entidadRow = new Cliente();
                    for (int i = 1; i <= columnCount; i++) {
                        if(fields[i-1][1].equals(String.class.getName())){
                            System.out.println("Si equivale");
                        }else System.out.println("No equivale");
                        
                        String columnValue = resultSet.getString(i);
                    }
                    list.add(entidadRow);
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
        }
        return list;
    }
    public static TamanhoPizza getTamanhoPizza(int nro_TamanhoPizza){
        return null;
    }
    
    private static String[][] getClassFields(){
                 // Get all the fields (attributes) of the Person class
        Field[] classFields = TamanhoPizza.class.getDeclaredFields();
        String[][] fieldsTable = new String[classFields.length][2];
        
        // Print the name of each field
        for (int i = 0; i < classFields.length; i++) {
            fieldsTable[i][0] = classFields[i].getName();
            fieldsTable[i][1] = classFields[i].getType().getName();
        }
        
        return fieldsTable;
    }
}
