package com.group55.bd_lab.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaIngredienteDAO {

    private String url = "jdbc:mysql://localhost:3306/nombre_base_de_datos";
    private String usuario = "usuario";
    private String contraseña = "contraseña";

    public List<PizzaIngrediente> listarPizzaIngredientes() {
        List<PizzaIngrediente> pizzaIngredientes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "SELECT * FROM Pizza_Ingredientes";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id_Pizza_Ingrediente = result.getInt("Id_Pizza_Ingrediente");
                int id_Pizza = result.getInt("Id_Pizza");
                int id_Ingrediente = result.getInt("Id_Ingrediente");

                PizzaIngrediente pizzaIngrediente = new PizzaIngrediente(id_Pizza_Ingrediente, id_Pizza, id_Ingrediente);
                pizzaIngredientes.add(pizzaIngrediente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pizzaIngredientes;
    }

    public void insertarPizzaIngrediente(PizzaIngrediente pizzaIngrediente) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "INSERT INTO Pizza_Ingredientes (Id_Pizza_Ingrediente, Id_Pizza, Id_Ingrediente) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, pizzaIngrediente.id_Pizza_Ingrediente);
            statement.setInt(2, pizzaIngrediente.id_Pizza);
            statement.setInt(3, pizzaIngrediente.id_Ingrediente);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPizzaIngrediente(PizzaIngrediente pizzaIngrediente) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "UPDATE Pizza_Ingredientes SET Id_Pizza = ?, Id_Ingrediente = ? WHERE Id_Pizza_Ingrediente = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, pizzaIngrediente.id_Pizza);
            statement.setInt(2, pizzaIngrediente.id_Ingrediente);
            statement.setInt(3, pizzaIngrediente.id_Pizza_Ingrediente);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPizzaIngrediente(int id_Pizza_Ingrediente) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "DELETE FROM Pizza_Ingredientes WHERE Id_Pizza_Ingrediente = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id_Pizza_Ingrediente);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
