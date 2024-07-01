package com.group55.bd_lab.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.group55.bd_lab.models.Bocadillo;
import com.group55.bd_lab.models.BocadilloDAO;

public class BocadilloGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idBocadilloField;
    private JTextField idTamanioBocadilloField;
    private JTextField idArticuloField;
    private BocadilloDAO bocadilloDAO;

    public BocadilloGUI() {
        bocadilloDAO = new BocadilloDAO();

        // Configuración básica del frame
        setTitle("Gestión de Bocadillos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear tabla y modelo de datos
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID Bocadillo");
        tableModel.addColumn("ID Tamaño Bocadillo");
        tableModel.addColumn("ID Artículo");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear panel para agregar y eliminar datos
        JPanel panel = new JPanel(new GridLayout(5, 2));
        idBocadilloField = new JTextField();
        idTamanioBocadilloField = new JTextField();
        idArticuloField = new JTextField();
        JButton addButton = new JButton("Agregar");
        JButton deleteButton = new JButton("Eliminar");
        JButton loadButton = new JButton("Cargar");

        panel.add(new JLabel("ID Bocadillo:"));
        panel.add(idBocadilloField);
        panel.add(new JLabel("ID Tamaño Bocadillo:"));
        panel.add(idTamanioBocadilloField);
        panel.add(new JLabel("ID Artículo:"));
        panel.add(idArticuloField);
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
                agregarBocadillo();
            }
        });

        // Acción para eliminar datos
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarBocadillo();
            }
        });

        setVisible(true);
    }

    private void cargarDatosEnTabla() {
        List<Bocadillo> bocadillos = bocadilloDAO.listarBocadillos();
        tableModel.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos

        for (Bocadillo bocadillo : bocadillos) {
            Object[] fila = {bocadillo.id_Bocadillo, bocadillo.id_Tamanio_Bocadillo, bocadillo.id_Articulo};
            tableModel.addRow(fila);
        }
    }

    private void agregarBocadillo() {
        int id_Bocadillo = Integer.parseInt(idBocadilloField.getText());
        int id_Tamanio_Bocadillo = Integer.parseInt(idTamanioBocadilloField.getText());
        int id_Articulo = Integer.parseInt(idArticuloField.getText());

        Bocadillo bocadillo = new Bocadillo(id_Bocadillo, id_Tamanio_Bocadillo, id_Articulo);
        bocadilloDAO.insertarBocadillo(bocadillo);

        cargarDatosEnTabla(); // Actualizar tabla
    }

    private void eliminarBocadillo() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id_Bocadillo = (int) tableModel.getValueAt(selectedRow, 0);
            bocadilloDAO.eliminarBocadillo(id_Bocadillo);

            cargarDatosEnTabla(); // Actualizar tabla
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BocadilloGUI());
    }
}

