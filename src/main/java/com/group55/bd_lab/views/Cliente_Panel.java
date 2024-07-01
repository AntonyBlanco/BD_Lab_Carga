package com.group55.bd_lab.views;

import com.group55.bd_lab.models.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Cliente_Panel extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField dniField;
    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField descripcionField;
    private JComboBox<String> availableComboBox;

    public Cliente_Panel() {
        setTitle("Gestión de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear tabla con modelo de datos
        tableModel = new DefaultTableModel();
        tableModel.addColumn("DNI Cliente");
        tableModel.addColumn("Nombres");
        tableModel.addColumn("Apellidos");
        tableModel.addColumn("Dirección");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Available");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear panel para formulario de registro
        JPanel formPanel = new JPanel(new GridLayout(8, 2));
        dniField = new JTextField();
        nombresField = new JTextField();
        apellidosField = new JTextField();
        direccionField = new JTextField();
        telefonoField = new JTextField();
        descripcionField = new JTextField();
        availableComboBox = new JComboBox<>(new String[]{"Yes", "No"});

        formPanel.add(new JLabel("DNI Cliente:"));
        formPanel.add(dniField);
        formPanel.add(new JLabel("Nombres:"));
        formPanel.add(nombresField);
        formPanel.add(new JLabel("Apellidos:"));
        formPanel.add(apellidosField);
        formPanel.add(new JLabel("Dirección:"));
        formPanel.add(direccionField);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(telefonoField);
        formPanel.add(new JLabel("Descripción:"));
        formPanel.add(descripcionField);
        formPanel.add(new JLabel("Available:"));
        formPanel.add(availableComboBox);

        JButton agregarButton = new JButton("Agregar Cliente");
        agregarButton.addActionListener(e -> agregarCliente());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);

        // Crear contenedor principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void agregarCliente() {
        // Obtener datos del formulario
        int dniCliente = Integer.parseInt(dniField.getText());
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        String direccion = direccionField.getText();
        int telefono = Integer.parseInt(telefonoField.getText());
        String descripcion = descripcionField.getText();
        String available = (String) availableComboBox.getSelectedItem();

        // Crear objeto Cliente y guardarlo en la base de datos
        Cliente cliente = new Cliente(dniCliente, nombres, apellidos, direccion, telefono, descripcion, available);
        cliente.guardarCliente();

        // Actualizar tabla
        actualizarTabla();
    }

    private void actualizarTabla() {
        // Limpiar tabla
        tableModel.setRowCount(0);

        // Obtener todos los clientes de la base de datos
        List<Cliente> clientes = Cliente.obtenerTodosClientes();

        // Llenar la tabla con los datos
        for (Cliente cliente : clientes) {
            Object[] row = {
                    cliente.getDniCliente(),
                    cliente.getNombres(),
                    cliente.getApellidos(),
                    cliente.getDireccion(),
                    cliente.getTelefono(),
                    cliente.getDescripcion(),
                    cliente.getAvailable()
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        // Inicializar la GUI
        SwingUtilities.invokeLater(() -> new Cliente_Panel());
    }
}

