/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.group55.bd_lab.views;

import com.group55.bd_lab.models.TamanhoPizza;
import com.group55.bd_lab.controllers.Cliente;
import com.group55.bd_lab.controllers.TamanhoBocadillo;
import com.group55.bd_lab.controllers.TamanhoPizza_Cargo;
import java.lang.reflect.Field;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author anjab
 */
public class TamanhoPizza_CargoPanel extends javax.swing.JPanel {
    private TamanhoPizza_Cargo gui;
    
    private int selectedRow = -1;
    
    /**
     * Creates new form TamanhoPizzaForm
     * @param gui
     */
    
    public TamanhoPizza_CargoPanel(TamanhoPizza_Cargo gui) {
        initComponents();
        
        this.gui = gui;
        
        // Deshabilitar tabla para valores editables
        modifyTableProperties();
        
    }
    

	public TamanhoPizza_CargoPanel(Cliente cliente) {
		// TODO Auto-generated constructor stub
	}


	public TamanhoPizza_CargoPanel(TamanhoBocadillo tamanhoBocadillo) {
		// TODO Auto-generated constructor stub
	}

	private void modifyTableProperties() {
        // Get the table model
        TableModel model = jTable_dataTable.getModel();
        
        Field[] fields = TamanhoPizza.class.getDeclaredFields();
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
        jTextField_Nombre.setEnabled(true);
        jTextArea_Descripcion.setEnabled(true);
    }
    public void disableForm(){
        jTextField_Nombre.setEnabled(false);
        jTextArea_Descripcion.setEnabled(false);
    }
    public void updateFormToEntidad(TamanhoPizza entidad){
        entidad.nombre = jTextField_Nombre.getText();
        entidad.descripcion = jTextArea_Descripcion.getText();
    }
    public void updateEntidadToForm(TamanhoPizza entidad) {
        if(entidad.nro_TamanhoPizza == -1)
            this.getjTextField_pkEntidad().setText("NEW");
        else
            this.getjTextField_pkEntidad().setText(entidad.nro_TamanhoPizza + "");
        this.getjTextField_Nombre().setText(entidad.nombre);
        this.getjTextArea_Descripcion().setText(entidad.descripcion);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField_Nombre = new javax.swing.JTextField();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_Descripcion = new javax.swing.JTextArea();
        jScrollPane_dataTable = new javax.swing.JScrollPane();
        jTable_dataTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripción:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tabla de Datos");

        jTextField_Nombre.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Registro de Datos");

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

        jTextArea_Descripcion.setColumns(20);
        jTextArea_Descripcion.setRows(5);
        jTextArea_Descripcion.setEnabled(false);
        jScrollPane3.setViewportView(jTextArea_Descripcion);

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

        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane_dataTable, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField_Nombre)
                            .addComponent(jScrollPane3))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane_dataTable;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable_dataTable;
    private javax.swing.JTextArea jTextArea_Descripcion;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_Nombre;
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
        return this.jTextField1;
    }
    
    public JTextField getjTextField_Nombre() {
        return jTextField_Nombre;
    }
    
    public JTextArea getjTextArea_Descripcion() {
        return jTextArea_Descripcion;
    }
    
    public JTable getjTable_DataTable() {
        return jTable_dataTable;
    }

}
