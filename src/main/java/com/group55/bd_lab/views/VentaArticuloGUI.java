package com.group55.bd_lab.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.group55.bd_lab.models.VentaArticulo;
import com.group55.bd_lab.models.VentaArticuloDAO;

public class VentaArticuloGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idVentaField;
    private JTextField fechaField;
    private JTextField totalField;
    private JTextField descripcionField;
    private VentaArticuloDAO ventaArticuloDAO;

    public VentaArticuloGUI() {
        ventaArticuloDAO = new VentaArticuloDAO();

        // Configuración básica del frame
        setTitle("Gestión de Ventas de Artículos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear tabla y modelo de datos
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Id Venta");
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Total");
        tableModel.addColumn("Descripcion");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear panel para agregar y eliminar datos
        JPanel panel = new JPanel(new GridLayout(5, 2));
        idVentaField = new JTextField();
        fechaField = new JTextField();
        totalField = new JTextField();
        descripcionField = new JTextField();
        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Modificar");
        JButton deleteButton = new JButton("Desactivar");
        JButton loadButton = new JButton("Cargar");

        panel.add(new JLabel("Id Venta:"));
        panel.add(idVentaField);
        panel.add(new JLabel("Fecha (yyyy-mm-dd):"));
        panel.add(fechaField);
        panel.add(new JLabel("Total:"));
        panel.add(totalField);
        panel.add(new JLabel("Descripcion:"));
        panel.add(descripcionField);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(loadButton);

        // Agregar tabla y panel al frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.SOUTH);

        // Acción para cargar datos
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatosEnTabla();
            }
        });

        // Acción para agregar datos
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVentaArticulo();
            }
        });

        // Acción para modificar datos
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarVentaArticulo();
            }
        });

        // Acción para eliminar datos
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVentaArticulo();
            }
        });

        setVisible(true);
    }

    private void cargarDatosEnTabla() {
        List<VentaArticulo> ventaArticulos = ventaArticuloDAO.listarVentaArticulos();
        tableModel.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos

        for (VentaArticulo ventaArticulo : ventaArticulos) {
            Object[] fila = {ventaArticulo.id_Venta, ventaArticulo.fecha, ventaArticulo.total, ventaArticulo.descripcion};
            tableModel.addRow(fila);
        }
    }

    private void agregarVentaArticulo() {
        int id_Venta = Integer.parseInt(idVentaField.getText());
        java.util.Date fecha = java.sql.Date.valueOf(fechaField.getText());
        double total = Double.parseDouble(totalField.getText());
        String descripcion = descripcionField.getText();

        VentaArticulo ventaArticulo = new VentaArticulo(id_Venta, fecha, total, descripcion);
        ventaArticuloDAO.insertarVentaArticulo(ventaArticulo);

        cargarDatosEnTabla(); // Actualizar tabla
    }

    private void modificarVentaArticulo() {
        int id_Venta = Integer.parseInt(idVentaField.getText());
        java.util.Date fecha = java.sql.Date.valueOf(fechaField.getText());
        double total = Double.parseDouble(totalField.getText());
        String descripcion = descripcionField.getText();

        VentaArticulo ventaArticulo = new VentaArticulo(id_Venta, fecha, total, descripcion);
        ventaArticuloDAO.actualizarVentaArticulo(ventaArticulo);

        cargarDatosEnTabla(); // Actualizar tabla
    }

    private void eliminarVentaArticulo() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id_Venta = (int) tableModel.getValueAt(selectedRow, 0);
            ventaArticuloDAO.eliminarVentaArticulo(id_Venta);

            cargarDatosEnTabla(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentaArticuloGUI());
    }
}
