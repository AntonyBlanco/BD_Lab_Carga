/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.controllers;

import com.group55.bd_lab.models.Articulo;
import com.group55.bd_lab.views.Articulo_CargoPanel;
import com.group55.bd_lab.services.Articulo_Service;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anjab
 */
public class Articulo_Cargo extends JFrame {

    private ArrayList<Articulo> listaDatos;
    private Articulo_CargoPanel formularioPanel;
    private Articulo entityLoadedForm;

    private int idx_selected = -1; // idx from listaDatos
    
    // Estado de preparación del cargo que se encuentra
    private int state = 0;
    private static final int SELECT_STATE = 0;
    private static final int ADD_STATE = 1;
    private static final int UPDATE_STATE = 2;
    private static final int ACTIVATE_STATE = 3;
    private static final int INACTIVATE_STATE = 4;
    private static final int DELETE_STATE = 5;
    private static final int HOVER_STATE = 6;

    public Articulo_Cargo() {
        this.entityLoadedForm = new Articulo();
        
        this.setupJFrame();
        this.loadDataTable();
        this.enableDisableButtons();
    }

    private void setupJFrame() {
        this.formularioPanel = new Articulo_CargoPanel(this);
        this.add(formularioPanel);
        this.pack();
        this.setTitle("Cargo - Artículo");
        this.setLocationRelativeTo(null); // Screen center
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadDataTable() {
        listaDatos = Articulo_Service.getList();

        this.setDataList(listaDatos);
    }

    public void setDataList(ArrayList<Articulo> list) {
        // Clear jTable_dataTable
        DefaultTableModel model = (DefaultTableModel) this.formularioPanel.getjTable_DataTable().getModel();
        model.setRowCount(0);

        // Fill dataTable
        for (Articulo entity : list) {
            model.addRow(new Object[]{
                entity.id_Articulo,
                entity.nombre,
                entity.precio,
                entity.tipoArticulo,
                entity.descripcion,
                entity.available
            });
        }
    }

    public void adicionarActionButton() {
        // Change State
        this.state = Articulo_Cargo.ADD_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        this.clearEntityLoadedForm();
        
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);
        
        this.formularioPanel.enableForm();
    }

    public void modificarActionButton() {
        // Change State
        this.state = Articulo_Cargo.UPDATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).id_Articulo;
        this.entityLoadedForm = Articulo_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        this.formularioPanel.enableForm();
    }


    private void clearDataRegistro() {
        this.formularioPanel.getjTextField_pkEntidad().setText("");
        this.formularioPanel.getjTextField_Nombre().setText("");
        this.formularioPanel.getjTextField_Precio().setText((double)0 + "");
        this.formularioPanel.getjTextField_Nombre().setText("");
        this.formularioPanel.getjTextArea_Descripcion().setText("");
    }
    
    private void clearEntityLoadedForm() {
        this.entityLoadedForm.id_Articulo = -1;
        this.entityLoadedForm.nombre = "";
        this.entityLoadedForm.precio = 0;
        this.entityLoadedForm.tipoArticulo = "Complementario";
        this.entityLoadedForm.descripcion = "";
        this.entityLoadedForm.available = "";
    }

    public void eliminarActionButton() {
        // Change State
        this.state = Articulo_Cargo.DELETE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).id_Articulo;
        this.entityLoadedForm = Articulo_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        // Form is not enabled
        this.formularioPanel.disableForm();
    }

    public void cancelarActionButton() {
        // Change State
        this.state = Articulo_Cargo.SELECT_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        this.clearDataRegistro();

        this.formularioPanel.disableForm();
        this.formularioPanel.unselectDataTable();
    }

    public void inactivarActionButton() {
        // Change State
        this.state = Articulo_Cargo.INACTIVATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).id_Articulo;
        this.entityLoadedForm = Articulo_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        // Form is not enabled
        this.formularioPanel.disableForm();
    }

    public void reactivarActionButton() {
        // Change State
        this.state = Articulo_Cargo.ACTIVATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).id_Articulo;
        this.entityLoadedForm = Articulo_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        // Form is not enabled
        this.formularioPanel.disableForm();
    }

    public void actualizarActionButton() {
        // Taking form data
        this.formularioPanel.updateFormToEntidad(this.entityLoadedForm);

        // Saving data via service
        boolean success;
        switch(this.state) {
            case Articulo_Cargo.ADD_STATE -> {
                this.entityLoadedForm.id_Articulo = Articulo_Service.add(this.entityLoadedForm);
                success = this.entityLoadedForm.id_Articulo != -1;
            }
            case Articulo_Cargo.UPDATE_STATE -> success = Articulo_Service.updateById(this.entityLoadedForm.id_Articulo, this.entityLoadedForm);
            case Articulo_Cargo.INACTIVATE_STATE -> success = Articulo_Service.inactivateById(this.entityLoadedForm.id_Articulo);
            case Articulo_Cargo.ACTIVATE_STATE -> success = Articulo_Service.activateById(this.entityLoadedForm.id_Articulo);
            case Articulo_Cargo.DELETE_STATE -> success = Articulo_Service.deleteById(this.entityLoadedForm.id_Articulo);
            default -> success = false;
        }

        // Change State
        this.state = Articulo_Cargo.SELECT_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        this.clearDataRegistro();

        this.formularioPanel.disableForm();
        
        //Update list
        if(success) this.loadDataTable();
    }

    public void salirActionButton() {
        this.dispose();
    }

    public void onElementSelected(ListSelectionEvent e) {
        idx_selected = this.formularioPanel.getjTable_DataTable().getSelectedRow();
        
        if(idx_selected == -1) return;
        
        // Change State
        this.state = Articulo_Cargo.HOVER_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();
        
        this.formularioPanel.getjButton_modificar().setEnabled(true);
        this.formularioPanel.getjButton_eliminar().setEnabled(true);
        this.formularioPanel.getjButton_reactivar().setEnabled(true);
        this.formularioPanel.getjButton_inactivar().setEnabled(true);
        this.formularioPanel.getjButton_cancelar().setEnabled(true);
    }
    
    public void enableDisableButtons(){
        this.formularioPanel.getjButton_adicionar().setEnabled(false);
        this.formularioPanel.getjButton_modificar().setEnabled(false);
        this.formularioPanel.getjButton_eliminar().setEnabled(false);
        this.formularioPanel.getjButton_reactivar().setEnabled(false);
        this.formularioPanel.getjButton_inactivar().setEnabled(false);
        this.formularioPanel.getjButton_actualizar().setEnabled(false);
        this.formularioPanel.getjButton_cancelar().setEnabled(false);
        
        switch(this.state){
            case Articulo_Cargo.HOVER_STATE:
                this.formularioPanel.getjButton_modificar().setEnabled(false);
            case Articulo_Cargo.SELECT_STATE:
                this.formularioPanel.getjButton_adicionar().setEnabled(true);
                break;
            default:
                this.formularioPanel.getjButton_cancelar().setEnabled(true);
                this.formularioPanel.getjButton_actualizar().setEnabled(true);
                break;
        }
        
    }
}
