/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.controllers;

import com.group55.bd_lab.models.Venta;
import com.group55.bd_lab.views.Venta_CargoPanel;
import com.group55.bd_lab.services.Venta_Service;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anjab
 */
public class Venta_Cargo extends JFrame {

    private ArrayList<Venta> listaDatos;
    private Venta_CargoPanel formularioPanel;
    private Venta entityLoadedForm;

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

    public Venta_Cargo() {
        this.entityLoadedForm = new Venta();
        
        this.setupJFrame();
        this.loadDataTable();
        this.enableDisableButtons();
    }

    private void setupJFrame() {
        this.formularioPanel = new Venta_CargoPanel(this);
        this.add(formularioPanel);
        this.pack();
        this.setTitle("Cargo - Tamaño Pizza");
        this.setLocationRelativeTo(null); // Screen center
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadDataTable() {
        listaDatos = Venta_Service.getList();

        this.setDataList(listaDatos);
    }

    public void setDataList(ArrayList<Venta> list) {
        // Clear jTable_dataTable
        DefaultTableModel model = (DefaultTableModel) this.formularioPanel.getjTable_DataTable().getModel();
        model.setRowCount(0);

        // Fill dataTable
        for (Venta entity : list) {
            model.addRow(new Object[]{
                entity.id_Venta,
                entity.fecha,
                entity.total,
                entity.descripcion,
                entity.available
            });
        }
    }

    public void adicionarActionButton() {
        // Change State
        this.state = Venta_Cargo.ADD_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        this.clearEntityLoadedForm();
        
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);
        
        this.formularioPanel.enableForm();
    }

    public void modificarActionButton() {
        // Change State
        this.state = Venta_Cargo.UPDATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).id_Venta;
        this.entityLoadedForm = Venta_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        this.formularioPanel.enableForm();
    }


    private void clearDataRegistro() {
        this.formularioPanel.getjTextField_pkEntidad().setText("");
        this.formularioPanel.getjTextField_Fecha().setText("");
        this.formularioPanel.getjTextField_Total().setText("");
        this.formularioPanel.getjTextArea_Descripcion().setText("");
    }
    
    private void clearEntityLoadedForm() {
        this.entityLoadedForm.id_Venta = -1;
        this.entityLoadedForm.fecha = "";
        this.entityLoadedForm.total = 0;
        this.entityLoadedForm.descripcion = "";
        this.entityLoadedForm.available = "";
    }

    public void eliminarActionButton() {
        // Change State
        this.state = Venta_Cargo.DELETE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).id_Venta;
        this.entityLoadedForm = Venta_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        // Form is not enabled
        this.formularioPanel.disableForm();
    }

    public void cancelarActionButton() {
        // Change State
        this.state = Venta_Cargo.SELECT_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        this.clearDataRegistro();

        this.formularioPanel.disableForm();
        this.formularioPanel.unselectDataTable();
    }

    public void inactivarActionButton() {
        // Change State
        this.state = Venta_Cargo.INACTIVATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).id_Venta;
        this.entityLoadedForm = Venta_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        // Form is not enabled
        this.formularioPanel.disableForm();
    }

    public void reactivarActionButton() {
        // Change State
        this.state = Venta_Cargo.ACTIVATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).id_Venta;
        this.entityLoadedForm = Venta_Service.getById(id_selected);
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
            case Venta_Cargo.ADD_STATE -> {
                this.entityLoadedForm.id_Venta = Venta_Service.add(this.entityLoadedForm);
                success = this.entityLoadedForm.id_Venta != -1;
            }
            case Venta_Cargo.UPDATE_STATE -> success = Venta_Service.updateById(this.entityLoadedForm.id_Venta, this.entityLoadedForm);
            case Venta_Cargo.INACTIVATE_STATE -> success = Venta_Service.inactivateById(this.entityLoadedForm.id_Venta);
            case Venta_Cargo.ACTIVATE_STATE -> success = Venta_Service.activateById(this.entityLoadedForm.id_Venta);
            case Venta_Cargo.DELETE_STATE -> success = Venta_Service.deleteById(this.entityLoadedForm.id_Venta);
            default -> success = false;
        }

        // Change State
        this.state = Venta_Cargo.SELECT_STATE;
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
        this.state = Venta_Cargo.HOVER_STATE;
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
            case Venta_Cargo.HOVER_STATE:
                this.formularioPanel.getjButton_modificar().setEnabled(false);
            case Venta_Cargo.SELECT_STATE:
                this.formularioPanel.getjButton_adicionar().setEnabled(true);
                break;
            default:
                this.formularioPanel.getjButton_cancelar().setEnabled(true);
                this.formularioPanel.getjButton_actualizar().setEnabled(true);
                break;
        }
        
    }
}
