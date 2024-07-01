package com.group55.bd_lab.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BocadilloDAO {

    private String url = "jdbc:mysql://localhost:3306/bd_lab";
    private String usuario = "root";
    private String contraseña = "admin";

    public List<Bocadillo> listarBocadillos() {
        List<Bocadillo> bocadillos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "SELECT * FROM bocadillo";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id_Bocadillo = result.getInt("id_Bocadillo");
                int id_Tamanio_Bocadillo = result.getInt("id_Tamanio_Bocadillo");
                int id_Articulo = result.getInt("id_Articulo");

                Bocadillo bocadillo = new Bocadillo(id_Bocadillo, id_Tamanio_Bocadillo, id_Articulo);
                bocadillos.add(bocadillo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bocadillos;
    }

    public void insertarBocadillo(Bocadillo bocadillo) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "INSERT INTO bocadillo (id_Bocadillo, id_Tamanio_Bocadillo, id_Articulo) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, bocadillo.id_Bocadillo);
            statement.setInt(2, bocadillo.id_Tamanio_Bocadillo);
            statement.setInt(3, bocadillo.id_Articulo);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarBocadillo(int id_Bocadillo) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "DELETE FROM bocadillo WHERE id_Bocadillo = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id_Bocadillo);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
