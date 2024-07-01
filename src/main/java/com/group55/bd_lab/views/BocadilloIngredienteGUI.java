package com.group55.bd_lab.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.group55.bd_lab.models.BocadilloIngrediente;
import com.group55.bd_lab.models.BocadilloIngredienteDAO;

public class BocadilloIngredienteGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idBocadilloIngredienteField;
    private JTextField idBocadilloField;
    private JTextField ingredienteField;
    private BocadilloIngredienteDAO bocadilloIngredienteDAO;

    public BocadilloIngredienteGUI() {
        bocadilloIngredienteDAO = new BocadilloIngredienteDAO();

        // Configuración básica del frame
        setTitle("Gestión de Ingredientes de Bocadillos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear tabla y modelo de datos
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Id Bocadillo Ingrediente");
        tableModel.addColumn("Id Bocadillo");
        tableModel.addColumn("Ingrediente");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear panel para agregar y eliminar datos
        JPanel panel = new JPanel(new GridLayout(4, 2));
        idBocadilloIngredienteField = new JTextField();
        idBocadilloField = new JTextField();
        ingredienteField = new JTextField();
        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");
        JButton loadButton = new JButton("Cargar");

        panel.add(new JLabel("Id Bocadillo Ingrediente:"));
        panel.add(idBocadilloIngredienteField);
        panel.add(new JLabel("Id Bocadillo:"));
        panel.add(idBocadilloField);
        panel.add(new JLabel("Ingrediente:"));
        panel.add(ingredienteField);
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
                agregarBocadilloIngrediente();
            }
        });

        // Acción para eliminar datos
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarBocadilloIngrediente();
            }
        });

        setVisible(true);
    }

    private void cargarDatosEnTabla() {
        List<BocadilloIngrediente> bocadilloIngredientes = bocadilloIngredienteDAO.listarBocadilloIngredientes();
        tableModel.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos

        for (BocadilloIngrediente bocadilloIngrediente : bocadilloIngredientes) {
            Object[] fila = {bocadilloIngrediente.id_Bocadillo_Ingrediente, bocadilloIngrediente.id_Bocadillo, bocadilloIngrediente.ingrediente};
            tableModel.addRow(fila);
        }
    }

    private void agregarBocadilloIngrediente() {
        int id_Bocadillo_Ingrediente = Integer.parseInt(idBocadilloIngredienteField.getText());
        int id_Bocadillo = Integer.parseInt(idBocadilloField.getText());
        String ingrediente = ingredienteField.getText();

        BocadilloIngrediente bocadilloIngrediente = new BocadilloIngrediente(id_Bocadillo_Ingrediente, id_Bocadillo, ingrediente);
        bocadilloIngredienteDAO.insertarBocadilloIngrediente(bocadilloIngrediente);

        cargarDatosEnTabla(); // Actualizar tabla
    }

    private void eliminarBocadilloIngrediente() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id_Bocadillo_Ingrediente = (int) tableModel.getValueAt(selectedRow, 0);
            bocadilloIngredienteDAO.eliminarBocadilloIngrediente(id_Bocadillo_Ingrediente);

            cargarDatosEnTabla(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BocadilloIngredienteGUI());
    }
}
