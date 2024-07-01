package com.group55.bd_lab.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.group55.bd_lab.models.TamanhoBocadillo;
import com.group55.bd_lab.models.TamanhoBocadilloDAO;

public class TamanhoBocadilloGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nroTamanhoBocadilloField;
    private JTextField nombreField;
    private JTextField descripcionField;
    private JComboBox<String> availableComboBox;
    private TamanhoBocadilloDAO tamanhoBocadilloDAO;

    public TamanhoBocadilloGUI() {
        tamanhoBocadilloDAO = new TamanhoBocadilloDAO();

        // Configuración básica del frame
        setTitle("Gestión de Tamaños de Bocadillos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear tabla y modelo de datos
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nro Tamaño Bocadillo");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Disponible");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear panel para agregar y eliminar datos
        JPanel panel = new JPanel(new GridLayout(5, 2));
        nroTamanhoBocadilloField = new JTextField();
        nombreField = new JTextField();
        descripcionField = new JTextField();
        availableComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");
        JButton loadButton = new JButton("Cargar");

        panel.add(new JLabel("Nro Tamaño Bocadillo:"));
        panel.add(nroTamanhoBocadilloField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Descripción:"));
        panel.add(descripcionField);
        panel.add(new JLabel("Disponible:"));
        panel.add(availableComboBox);
        panel.add(addButton);
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
                agregarTamanhoBocadillo();
            }
        });

        // Acción para eliminar datos
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTamanhoBocadillo();
            }
        });

        setVisible(true);
    }

    private void cargarDatosEnTabla() {
        List<TamanhoBocadillo> tamanhosBocadillo = tamanhoBocadilloDAO.listarTamanhosBocadillo();
        tableModel.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos

        for (TamanhoBocadillo tamanhoBocadillo : tamanhosBocadillo) {
            Object[] fila = {tamanhoBocadillo.nro_TamanhoBocadillo, tamanhoBocadillo.nombre, tamanhoBocadillo.descripcion, tamanhoBocadillo.available};
            tableModel.addRow(fila);
        }
    }

    private void agregarTamanhoBocadillo() {
        int nro_TamanhoBocadillo = Integer.parseInt(nroTamanhoBocadilloField.getText());
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        String available = (String) availableComboBox.getSelectedItem();

        TamanhoBocadillo tamanhoBocadillo = new TamanhoBocadillo(nro_TamanhoBocadillo, nombre, descripcion, available);
        tamanhoBocadilloDAO.insertarTamanhoBocadillo(tamanhoBocadillo);

        cargarDatosEnTabla(); // Actualizar tabla
    }

    private void eliminarTamanhoBocadillo() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int nro_TamanhoBocadillo = (int) tableModel.getValueAt(selectedRow, 0);
            tamanhoBocadilloDAO.eliminarTamanhoBocadillo(nro_TamanhoBocadillo);

            cargarDatosEnTabla(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TamanhoBocadilloGUI());
    }
}
