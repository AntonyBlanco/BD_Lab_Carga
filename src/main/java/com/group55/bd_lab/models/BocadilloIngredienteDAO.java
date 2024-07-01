package com.group55.bd_lab.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BocadilloIngredienteDAO {

    private String url = "jdbc:mysql://localhost:3306/nombre_base_de_datos";
    private String usuario = "usuario";
    private String contraseña = "contraseña";

    public List<BocadilloIngrediente> listarBocadilloIngredientes() {
        List<BocadilloIngrediente> bocadilloIngredientes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "SELECT * FROM Bocadillo_Ingredientes";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id_Bocadillo_Ingrediente = result.getInt("Id_Bocadillo_Ingrediente");
                int id_Bocadillo = result.getInt("Id_Bocadillo");
                String ingrediente = result.getString("Ingrediente");

                BocadilloIngrediente bocadilloIngrediente = new BocadilloIngrediente(id_Bocadillo_Ingrediente, id_Bocadillo, ingrediente);
                bocadilloIngredientes.add(bocadilloIngrediente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bocadilloIngredientes;
    }

    public void insertarBocadilloIngrediente(BocadilloIngrediente bocadilloIngrediente) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "INSERT INTO Bocadillo_Ingredientes (Id_Bocadillo_Ingrediente, Id_Bocadillo, Ingrediente) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, bocadilloIngrediente.id_Bocadillo_Ingrediente);
            statement.setInt(2, bocadilloIngrediente.id_Bocadillo);
            statement.setString(3, bocadilloIngrediente.ingrediente);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarBocadilloIngrediente(int id_Bocadillo_Ingrediente) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "DELETE FROM Bocadillo_Ingredientes WHERE Id_Bocadillo_Ingrediente = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id_Bocadillo_Ingrediente);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
