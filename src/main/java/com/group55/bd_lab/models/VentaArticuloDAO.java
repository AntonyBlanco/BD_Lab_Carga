package com.group55.bd_lab.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaArticuloDAO {

    private String url = "jdbc:mysql://localhost:3306/nombre_base_de_datos";
    private String usuario = "usuario";
    private String contraseña = "contraseña";

    public List<VentaArticulo> listarVentaArticulos() {
        List<VentaArticulo> ventaArticulos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "SELECT * FROM Venta_Articulo";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id_Venta = result.getInt("Id_Venta");
                Date fecha = result.getDate("Fecha");
                double total = result.getDouble("Total");
                String descripcion = result.getString("Descripcion");

                VentaArticulo ventaArticulo = new VentaArticulo(id_Venta, fecha, total, descripcion);
                ventaArticulos.add(ventaArticulo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ventaArticulos;
    }

    public void insertarVentaArticulo(VentaArticulo ventaArticulo) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "INSERT INTO Venta_Articulo (Id_Venta, Fecha, Total, Descripcion) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ventaArticulo.id_Venta);
            statement.setDate(2, new java.sql.Date(ventaArticulo.fecha.getTime()));
            statement.setDouble(3, ventaArticulo.total);
            statement.setString(4, ventaArticulo.descripcion);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarVentaArticulo(VentaArticulo ventaArticulo) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "UPDATE Venta_Articulo SET Fecha = ?, Total = ?, Descripcion = ? WHERE Id_Venta = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(ventaArticulo.fecha.getTime()));
            statement.setDouble(2, ventaArticulo.total);
            statement.setString(3, ventaArticulo.descripcion);
            statement.setInt(4, ventaArticulo.id_Venta);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVentaArticulo(int id_Venta) {
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "DELETE FROM Venta_Articulo WHERE Id_Venta = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id_Venta);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
