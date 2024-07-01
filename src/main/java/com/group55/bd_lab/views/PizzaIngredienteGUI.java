package com.group55.bd_lab.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.group55.bd_lab.models.PizzaIngrediente;
import com.group55.bd_lab.models.PizzaIngredienteDAO;

public class PizzaIngredienteGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idPizzaIngredienteField;
    private JTextField idPizzaField;
    private JTextField idIngredienteField;
    private PizzaIngredienteDAO pizzaIngredienteDAO;

    public PizzaIngredienteGUI() {
        pizzaIngredienteDAO = new PizzaIngredienteDAO();

        // Configuración básica del frame
        setTitle("Gestión de Ingredientes de Pizzas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear tabla y modelo de datos
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Id Pizza Ingrediente");
        tableModel.addColumn("Id Pizza");
        tableModel.addColumn("Id Ingrediente");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear panel para agregar y eliminar datos
        JPanel panel = new JPanel(new GridLayout(5, 2));
        idPizzaIngredienteField = new JTextField();
        idPizzaField = new JTextField();
        idIngredienteField = new JTextField();
        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Modificar");
        JButton deleteButton = new JButton("Desactivar");
        JButton loadButton = new JButton("Cargar");

        panel.add(new JLabel("Id Pizza Ingrediente:"));
        panel.add(idPizzaIngredienteField);
        panel.add(new JLabel("Id Pizza:"));
        panel.add(idPizzaField);
        panel.add(new JLabel("Id Ingrediente:"));
        panel.add(idIngredienteField);
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
                agregarPizzaIngrediente();
            }
        });

        // Acción para modificar datos
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarPizzaIngrediente();
            }
        });

        // Acción para eliminar datos
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPizzaIngrediente();
            }
        });

        setVisible(true);
    }

    private void cargarDatosEnTabla() {
        List<PizzaIngrediente> pizzaIngredientes = pizzaIngredienteDAO.listarPizzaIngredientes();
        tableModel.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos

        for (PizzaIngrediente pizzaIngrediente : pizzaIngredientes) {
            Object[] fila = {pizzaIngrediente.id_Pizza_Ingrediente, pizzaIngrediente.id_Pizza, pizzaIngrediente.id_Ingrediente};
            tableModel.addRow(fila);
        }
    }

    private void agregarPizzaIngrediente() {
        int id_Pizza_Ingrediente = Integer.parseInt(idPizzaIngredienteField.getText());
        int id_Pizza = Integer.parseInt(idPizzaField.getText());
        int id_Ingrediente = Integer.parseInt(idIngredienteField.getText());

        PizzaIngrediente pizzaIngrediente = new PizzaIngrediente(id_Pizza_Ingrediente, id_Pizza, id_Ingrediente);
        pizzaIngredienteDAO.insertarPizzaIngrediente(pizzaIngrediente);

        cargarDatosEnTabla(); // Actualizar tabla
    }

    private void modificarPizzaIngrediente() {
        int id_Pizza_Ingrediente = Integer.parseInt(idPizzaIngredienteField.getText());
        int id_Pizza = Integer.parseInt(idPizzaField.getText());
        int id_Ingrediente = Integer.parseInt(idIngredienteField.getText());

        PizzaIngrediente pizzaIngrediente = new PizzaIngrediente(id_Pizza_Ingrediente, id_Pizza, id_Ingrediente);
        pizzaIngredienteDAO.actualizarPizzaIngrediente(pizzaIngrediente);

        cargarDatosEnTabla(); // Actualizar tabla
    }

    private void eliminarPizzaIngrediente() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id_Pizza_Ingrediente = (int) tableModel.getValueAt(selectedRow, 0);
            pizzaIngredienteDAO.eliminarPizzaIngrediente(id_Pizza_Ingrediente);

            cargarDatosEnTabla(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzaIngredienteGUI());
    }
}

