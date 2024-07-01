/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.group55.bd_lab.views;

import com.group55.bd_lab.models.Pizza;
import com.group55.bd_lab.controllers.Pizza_Cargo;
import com.group55.bd_lab.models.Articulo;
import com.group55.bd_lab.models.TamanhoPizza;
import com.group55.bd_lab.services.Articulo_Service;
import com.group55.bd_lab.services.TamanhoPizza_Service;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author anjab
 */
public class Pizza_CargoPanel extends javax.swing.JPanel {
    
    private Pizza_Cargo gui;
    
    private int selectedRow = -1;
    
    // External tables
    private ArrayList<Articulo> lista_Articulo;
    private ArrayList<TamanhoPizza> lista_TamanhoPizza;
    
    private String[] listaStr_Articulo;
    private HashMap<String, Articulo> hashLista_Str_Articulo;
    private HashMap<Integer, String> hashLista_Id_Articulo_Str;
    private String[] listaStr_TamanhoPizza;
    private HashMap<String, TamanhoPizza> hashLista_Str_TamanhoPizza;
    private HashMap<Integer, String> hashLista_Nro_TamanhoPizza_Str;
    
    private Object[] jComboBoxModel_Articulo;
    private Object[] jComboBoxModel_TamanhoPizza;
    
    /**
     * Creates new form PizzaForm
     * @param gui
     */
    public Pizza_CargoPanel(Pizza_Cargo gui) {
        initComponents();
        
        this.gui = gui;
        
        // Deshabilitar tabla para valores editables
        modifyTableProperties();
        loadForaignDataTables();
        
    }
    
    private void loadForaignDataTables(){
        // == TamanhoPizza ==
        lista_TamanhoPizza = TamanhoPizza_Service.getList();
        
        hashLista_Str_TamanhoPizza = new HashMap<String, TamanhoPizza>();
        hashLista_Nro_TamanhoPizza_Str = new HashMap<Integer, String>();
        listaStr_TamanhoPizza = new String[lista_TamanhoPizza.size()];
        
        for(int i = 0; i < lista_TamanhoPizza.size(); i++){
            TamanhoPizza entity = lista_TamanhoPizza.get(i);
            listaStr_TamanhoPizza[i] = entity.nro_TamanhoPizza + " - " + entity.nombre;
            hashLista_Str_TamanhoPizza.put(listaStr_TamanhoPizza[i], entity);
            hashLista_Nro_TamanhoPizza_Str.put(entity.nro_TamanhoPizza, listaStr_TamanhoPizza[i]);
        }
        
        this.jComboBoxModel_TamanhoPizza = listaStr_TamanhoPizza;
        this.jComboBox_TamanhoPizza.setModel(new DefaultComboBoxModel(listaStr_TamanhoPizza));
        
        // == Articulo ==
        lista_Articulo = Articulo_Service.getList();
        
        hashLista_Str_Articulo = new HashMap<String, Articulo>();
        hashLista_Id_Articulo_Str = new HashMap<Integer, String>();
        listaStr_Articulo = new String[lista_Articulo.size()];
        
        for(int i = 0; i < lista_Articulo.size(); i++){
            Articulo entity = lista_Articulo.get(i);
            listaStr_Articulo[i] = entity.id_Articulo + " - " + entity.nombre;
            hashLista_Str_Articulo.put(listaStr_Articulo[i], entity);
            hashLista_Id_Articulo_Str.put(entity.id_Articulo, listaStr_Articulo[i]);
        }
        
        this.jComboBoxModel_Articulo = listaStr_Articulo;
        this.jComboBox_Articulo.setModel(new DefaultComboBoxModel(listaStr_Articulo));
        
    }
    
    private void modifyTableProperties() {
        // Get the table model
        TableModel model = jTable_dataTable.getModel();
        
        Field[] fields = Pizza.class.getDeclaredFields();
        Vector<String> attributeNames = new Vector<String>();
        
        // Add field names to the list
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        
        // Columns Names
        attributeNames.set(0, "Id");
        attributeNames.set(1, "Nombre");
        attributeNames.set(2, "Descripcion");
        attributeNames.set(attributeNames.size() - 1, "Estado Registro");

        if (model instanceof DefaultTableModel) {
            DefaultTableModel defaultTableModel = (DefaultTableModel) model;

            // Create a new DefaultTableModel with the same data and columns, but override isCellEditable
            DefaultTableModel nonEditableModel = new DefaultTableModel(defaultTableModel.getDataVector(), attributeNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make cells non-editable
                }
            };

            // Set the new model to the table
            jTable_dataTable.setModel(nonEditableModel);
            
            // Adjust column widths
            TableColumnModel columnModel = jTable_dataTable.getColumnModel();

            // Columns width
            columnModel.getColumn(0).setPreferredWidth(30);
            columnModel.getColumn(0).setMaxWidth(30);
            columnModel.getColumn(1).setPreferredWidth(100);
            columnModel.getColumn(1).setMaxWidth(100);
            columnModel.getColumn(attributeNames.size() - 1).setPreferredWidth(90);
            columnModel.getColumn(attributeNames.size() - 1).setMaxWidth(90);
            
            
        }
        
        // Set table list selection listener
        jTable_dataTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            this.jTable_listSelection(e);
        });
    }
    
    public void enableForm(){
        jTextField_PrecioIngrediente.setEnabled(true);
        jComboBox_TamanhoPizza.setEnabled(true);
        jComboBox_Articulo.setEnabled(true);
    }
    public void disableForm(){
        jTextField_PrecioIngrediente.setEnabled(false);
        jComboBox_TamanhoPizza.setEnabled(false);
        jComboBox_Articulo.setEnabled(false);
    }
    public void updateFormToEntidad(Pizza entidad){
        entidad.precioIngrediente = (int)(Double.parseDouble(this.jTextField_PrecioIngrediente.getText()) * 100);
        entidad.nro_TamanhoPizza = this.hashLista_Str_TamanhoPizza.get(
                jComboBox_TamanhoPizza.getSelectedItem()
        ).nro_TamanhoPizza;
        entidad.id_Articulo = this.hashLista_Str_Articulo.get(
                jComboBox_Articulo.getSelectedItem()
        ).id_Articulo;
    }
    public void updateEntidadToForm(Pizza entidad) {
        if(entidad.id_Pizza == -1)
            this.getjTextField_pkEntidad().setText("NEW");
        else
            this.getjTextField_pkEntidad().setText(entidad.id_Pizza + "");
        
        jTextField_PrecioIngrediente.setText((double)0 + "");
        if(entidad.nro_TamanhoPizza == -1)
            jComboBox_TamanhoPizza.setSelectedIndex(0);
        else
            jComboBox_TamanhoPizza.setSelectedItem(
                    this.hashLista_Nro_TamanhoPizza_Str.get(entidad.nro_TamanhoPizza)
            );
        if(entidad.nro_TamanhoPizza == -1)
            jComboBox_Articulo.setSelectedIndex(0);
        else
            jComboBox_Articulo.setSelectedItem(
                    this.hashLista_Id_Articulo_Str.get(entidad.id_Articulo)
            );
    }
    public void unselectDataTable(){
        this.jTable_dataTable.getSelectionModel().clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField_PrecioIngrediente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jButton_adicionar = new javax.swing.JButton();
        jButton_modificar = new javax.swing.JButton();
        jButton_eliminar = new javax.swing.JButton();
        jButton_cancelar = new javax.swing.JButton();
        jButton_inactivar = new javax.swing.JButton();
        jButton_reactivar = new javax.swing.JButton();
        jButton_actualizar = new javax.swing.JButton();
        jButton_salir = new javax.swing.JButton();
        jScrollPane_dataTable = new javax.swing.JScrollPane();
        jTable_dataTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jTextField_pk = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_TamanhoPizza = new javax.swing.JComboBox<>();
        jComboBox_Articulo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("Tamaño de Pizza:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tabla de Datos");

        jTextField_PrecioIngrediente.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Registro de Datos");

        panel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel1.setLayout(new java.awt.GridLayout(2, 0, 5, 5));

        jButton_adicionar.setText("Adicionar");
        jButton_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_adicionarActionPerformed(evt);
            }
        });
        panel1.add(jButton_adicionar);

        jButton_modificar.setText("Modificar");
        jButton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificarActionPerformed(evt);
            }
        });
        panel1.add(jButton_modificar);

        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.setMaximumSize(new java.awt.Dimension(81, 23));
        jButton_eliminar.setMinimumSize(new java.awt.Dimension(81, 23));
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });
        panel1.add(jButton_eliminar);

        jButton_cancelar.setText("Cancelar");
        jButton_cancelar.setMaximumSize(new java.awt.Dimension(81, 23));
        jButton_cancelar.setMinimumSize(new java.awt.Dimension(81, 23));
        jButton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelarActionPerformed(evt);
            }
        });
        panel1.add(jButton_cancelar);

        jButton_inactivar.setText("Inactivar");
        jButton_inactivar.setMaximumSize(new java.awt.Dimension(81, 23));
        jButton_inactivar.setMinimumSize(new java.awt.Dimension(81, 23));
        jButton_inactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_inactivarActionPerformed(evt);
            }
        });
        panel1.add(jButton_inactivar);

        jButton_reactivar.setText("Reactivar");
        jButton_reactivar.setMaximumSize(new java.awt.Dimension(81, 23));
        jButton_reactivar.setMinimumSize(new java.awt.Dimension(81, 23));
        jButton_reactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_reactivarActionPerformed(evt);
            }
        });
        panel1.add(jButton_reactivar);

        jButton_actualizar.setText("Actualizar");
        jButton_actualizar.setMaximumSize(new java.awt.Dimension(81, 23));
        jButton_actualizar.setMinimumSize(new java.awt.Dimension(81, 23));
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        panel1.add(jButton_actualizar);

        jButton_salir.setText("Salir");
        jButton_salir.setMaximumSize(new java.awt.Dimension(81, 23));
        jButton_salir.setMinimumSize(new java.awt.Dimension(81, 23));
        jButton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_salirActionPerformed(evt);
            }
        });
        panel1.add(jButton_salir);

        jTable_dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Campo3", "Estado Registro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_dataTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_dataTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane_dataTable.setViewportView(jTable_dataTable);
        if (jTable_dataTable.getColumnModel().getColumnCount() > 0) {
            jTable_dataTable.getColumnModel().getColumn(0).setMaxWidth(60);
            jTable_dataTable.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTable_dataTable.getColumnModel().getColumn(3).setMaxWidth(120);
        }

        jLabel5.setText("Id:");

        jTextField_pk.setEnabled(false);
        jTextField_pk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_pkActionPerformed(evt);
            }
        });

        jLabel6.setText("<html>Precio por<br>Ingrediente:</html>");

        jLabel7.setText("Articulo");

        jComboBox_TamanhoPizza.setEnabled(false);

        jComboBox_Articulo.setEnabled(false);

        jLabel1.setText("s/.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane_dataTable, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(84, 84, 84)
                                        .addComponent(jTextField_pk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox_TamanhoPizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox_Articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField_PrecioIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_pk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_PrecioIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_TamanhoPizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox_Articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane_dataTable, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_adicionarActionPerformed
        gui.adicionarActionButton();
    }//GEN-LAST:event_jButton_adicionarActionPerformed

    private void jButton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificarActionPerformed
        gui.modificarActionButton();
    }//GEN-LAST:event_jButton_modificarActionPerformed

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
        gui.eliminarActionButton();
    }//GEN-LAST:event_jButton_eliminarActionPerformed

    private void jButton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelarActionPerformed
        gui.cancelarActionButton();
    }//GEN-LAST:event_jButton_cancelarActionPerformed

    private void jButton_inactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_inactivarActionPerformed
        gui.inactivarActionButton();
    }//GEN-LAST:event_jButton_inactivarActionPerformed

    private void jButton_reactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_reactivarActionPerformed
        gui.reactivarActionButton();
    }//GEN-LAST:event_jButton_reactivarActionPerformed

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed
        gui.actualizarActionButton();
    }//GEN-LAST:event_jButton_actualizarActionPerformed

    private void jButton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_salirActionPerformed
        gui.salirActionButton();
    }//GEN-LAST:event_jButton_salirActionPerformed

    private void jTextField_pkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_pkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_pkActionPerformed
    
    private void jTable_listSelection(ListSelectionEvent ev){
        gui.onElementSelected(ev);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_adicionar;
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_inactivar;
    private javax.swing.JButton jButton_modificar;
    private javax.swing.JButton jButton_reactivar;
    private javax.swing.JButton jButton_salir;
    private javax.swing.JComboBox<String> jComboBox_Articulo;
    private javax.swing.JComboBox<String> jComboBox_TamanhoPizza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane_dataTable;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable_dataTable;
    private javax.swing.JTextField jTextField_PrecioIngrediente;
    private javax.swing.JTextField jTextField_pk;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables

    public JButton getjButton_actualizar() {
        return jButton_actualizar;
    }

    public JButton getjButton_adicionar() {
        return jButton_adicionar;
    }

    public JButton getjButton_cancelar() {
        return jButton_cancelar;
    }

    public JButton getjButton_eliminar() {
        return jButton_eliminar;
    }

    public JButton getjButton_inactivar() {
        return jButton_inactivar;
    }

    public JButton getjButton_modificar() {
        return jButton_modificar;
    }

    public JButton getjButton_reactivar() {
        return jButton_reactivar;
    }

    public JButton getjButton_salir() {
        return jButton_salir;
    }
    
    public JTextField getjTextField_pkEntidad() {
        return this.jTextField_pk;
    }
    
    public JTextField getjTextField_PrecioIngrediente() {
        return jTextField_PrecioIngrediente;
    }
    
    public JComboBox getjComboBox_TamanhoPizza() {
        return jComboBox_TamanhoPizza;
    }
    
    public JComboBox getjComboBox_Articulo() {
        return jComboBox_Articulo;
    }
    
    public JTable getjTable_DataTable() {
        return jTable_dataTable;
    }

    private ComboBoxModel get_jComboBoxModel_Articulo(){
        return new DefaultComboBoxModel(this.jComboBoxModel_Articulo);
    }
    private ComboBoxModel get_jComboBoxModel_TamanhoPizza(){
        return new DefaultComboBoxModel(this.jComboBoxModel_TamanhoPizza);
    }

}
