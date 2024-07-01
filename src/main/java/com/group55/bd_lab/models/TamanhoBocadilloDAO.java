package com.group55.bd_lab.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TamanhoBocadilloDAO {

    private String url = "jdbc:mysql://localhost:3306/nombre_base_de_datos";
    private String usuario = "usuario";
    private String contraseña = "contraseña";

    public List<TamanhoBocadillo> listarTamanhosBocadillo() {
        List<TamanhoBocadillo> tamanhosBocadillo = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "SELECT * FROM Tamanho_Bocadillo";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int nro_TamanhoBocadillo = result.getInt("Nro_tamanhoBocadillo");
                String nombre = result.getString("Nombre");
                String descripcion = result.getString("Descripcion");
                String available = result.getString("Available");

                TamanhoBocadillo tamanhoBocadillo = new TamanhoBocadillo(nro_TamanhoBocadillo, nombre, descripcion, available);
                tamanhosBocadillo.add(tamanhoBocadillo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tamanhosBocadillo;
    }

    public void insertarTamanhoBocadillo(TamanhoBocadillo tamanhoBocadillo) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "INSERT INTO Tamanho_Bocadillo (Nro_tamanhoBocadillo, Nombre, Descripcion, Available) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, tamanhoBocadillo.nro_TamanhoBocadillo);
            statement.setString(2, tamanhoBocadillo.nombre);
            statement.setString(3, tamanhoBocadillo.descripcion);
            statement.setString(4, tamanhoBocadillo.available);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTamanhoBocadillo(int nro_TamanhoBocadillo) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "DELETE FROM Tamanho_Bocadillo WHERE Nro_tamanhoBocadillo = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, nro_TamanhoBocadillo);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
