package com.group55.bd_lab.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Cliente extends JFrame {
    private JTextField dniField;
    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField descripcionField;
    private JComboBox<String> availableComboBox;
    private JButton saveButton;
    private JButton cancelButton;
    private static int dni_Cliente;
    private String nombres;
    private static String apellidos;
    private String dirección;
    private int telefono;
    private String descripcion;
    private String available;
    private Cliente cliente; // Este campo parece no ser necesario aquí

    public Cliente(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Modificar Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // Initialize components
        dniField = new JTextField(String.valueOf(cliente.dni_Cliente));
        nombresField = new JTextField(cliente.nombres);
        apellidosField = new JTextField(cliente.apellidos);
        direccionField = new JTextField(cliente.dirección);
        telefonoField = new JTextField(String.valueOf(cliente.telefono));
        descripcionField = new JTextField(cliente.descripcion);
        availableComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        availableComboBox.setSelectedItem(cliente.available);

        saveButton = new JButton("Guardar");
        cancelButton = new JButton("Cancelar");

        // Add components to the frame
        add(new JLabel("DNI Cliente:"));
        add(dniField);
        add(new JLabel("Nombres:"));
        add(nombresField);
        add(new JLabel("Apellidos:"));
        add(apellidosField);
        add(new JLabel("Dirección:"));
        add(direccionField);
        add(new JLabel("Teléfono:"));
        add(telefonoField);
        add(new JLabel("Descripción:"));
        add(descripcionField);
        add(new JLabel("Available:"));
        add(availableComboBox);
        add(saveButton);
        add(cancelButton);

        // Add action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCliente();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public Cliente(int dniCliente, String nombres, String apellidos, String direccion, int telefono, String descripcion, String available) {
        this.dni_Cliente = dniCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dirección = direccion;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.available = available;
    }

    public Cliente() {
		// TODO Auto-generated constructor stub
	}

	private void saveCliente() {
        // Aquí deberías crear un nuevo objeto Cliente si es necesario
        // Cliente cliente = new Cliente(...); // Ajusta según necesidades

        // Actualizar los campos del cliente según los valores de los campos de texto y combo box
        cliente.dni_Cliente = Integer.parseInt(dniField.getText());
        cliente.nombres = nombresField.getText();
        cliente.apellidos = apellidosField.getText();
        cliente.dirección = direccionField.getText();
        cliente.telefono = Integer.parseInt(telefonoField.getText());
        cliente.descripcion = descripcionField.getText();
        cliente.available = (String) availableComboBox.getSelectedItem();

        JOptionPane.showMessageDialog(this, "Datos guardados correctamente!");
        dispose();
    }
    public void guardarCliente() {
        // Aquí puedes agregar la lógica para guardar el cliente en la base de datos
        // Por ejemplo, podrías utilizar una clase DAO para manejar la persistencia

        // Ejemplo de cómo imprimir los datos del cliente para propósitos de demostración
        System.out.println("Guardando cliente:");
        System.out.println("DNI: " + dni_Cliente);
        System.out.println("Nombres: " + nombres);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Dirección: " + dirección);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Available: " + available);
    }

    public static void main(String[] args) {
        // Crear un objeto Cliente de ejemplo para la interfaz gráfica
        Cliente cliente = new Cliente(12345678, "Juan", "Pérez", "Calle Falsa 123", 987654321, "Cliente regular", "Yes");

        // Mostrar la interfaz gráfica para modificar el cliente
        new Cliente(cliente);
    }

    // Métodos getter para obtener los datos del cliente
    public int getDniCliente() {
        return this.dni_Cliente;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getDireccion() {
        return this.dirección;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getAvailable() {
        return this.available;
    }

	public static List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return null;
	}
}
