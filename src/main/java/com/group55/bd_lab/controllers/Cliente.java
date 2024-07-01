/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group55.bd_lab.controllers;

import com.group55.bd_lab.models.TamanhoPizza;
import com.group55.bd_lab.views.Cliente_Panel;
import com.group55.bd_lab.views.TamanhoPizza_CargoPanel;
import com.group55.bd_lab.services.TamanhoPizza_Service;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anjab, daniel
 */
public class Cliente extends JFrame {

    private ArrayList<TamanhoPizza> listaDatos;
    private TamanhoPizza_CargoPanel formularioPanel;
    private TamanhoPizza entityLoadedForm;

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

    public Cliente() {
        this.entityLoadedForm = new TamanhoPizza();
        
        this.setupJFrame();
        this.loadDataTable();
        this.enableDisableButtons();
    }

    private void setupJFrame() {
        this.formularioPanel = new Cliente_Panel(this);
        this.add(formularioPanel);
        this.pack();
        this.setTitle("Cargo - Tamaño Pizza");
        this.setLocationRelativeTo(null); // Screen center
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadDataTable() {
        listaDatos = TamanhoPizza_Service.getList();

        this.setDataList(listaDatos);
    }

    public void setDataList(ArrayList<TamanhoPizza> list) {
        // Clear jTable_dataTable
        DefaultTableModel model = (DefaultTableModel) this.formularioPanel.getjTable_DataTable().getModel();
        model.setRowCount(0);

        // Fill dataTable
        for (TamanhoPizza entity : list) {
            model.addRow(new Object[]{
                entity.nro_TamanhoPizza,
                entity.nombre,
                entity.descripcion,
                entity.available
            });
        }
    }

    public void adicionarActionButton() {
        // Change State
        this.state = Cliente.ADD_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        this.clearEntityLoadedForm();
        
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);
        
        this.formularioPanel.enableForm();
    }

    public void modificarActionButton() {
        // Change State
        this.state = Cliente.UPDATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).nro_TamanhoPizza;
        this.entityLoadedForm = TamanhoPizza_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        this.formularioPanel.enableForm();
    }


    private void clearDataRegistro() {
        this.formularioPanel.getjTextField_pkEntidad().setText("");
        this.formularioPanel.getjTextField_Nombre().setText("");
        this.formularioPanel.getjTextArea_Descripcion().setText("");
    }
    
    private void clearEntityLoadedForm() {
        this.entityLoadedForm.nro_TamanhoPizza = -1;
        this.entityLoadedForm.nombre = "";
        this.entityLoadedForm.descripcion = "";
        this.entityLoadedForm.available = "";
    }

    public void eliminarActionButton() {
        // Change State
        this.state = Cliente.DELETE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).nro_TamanhoPizza;
        this.entityLoadedForm = TamanhoPizza_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        // Form is not enabled
        this.formularioPanel.disableForm();
    }

    public void cancelarActionButton() {
        // Change State
        this.state = Cliente.SELECT_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        this.clearDataRegistro();

        this.formularioPanel.disableForm();
        this.formularioPanel.unselectDataTable();
    }

    public void inactivarActionButton() {
        // Change State
        this.state = Cliente.INACTIVATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).nro_TamanhoPizza;
        this.entityLoadedForm = TamanhoPizza_Service.getById(id_selected);
        this.formularioPanel.updateEntidadToForm(this.entityLoadedForm);

        // Form is not enabled
        this.formularioPanel.disableForm();
    }

    public void reactivarActionButton() {
        // Change State
        this.state = Cliente.ACTIVATE_STATE;
        // Enable and disable jbuttons
        enableDisableButtons();

        // Query data
        int id_selected = listaDatos.get(idx_selected).nro_TamanhoPizza;
        this.entityLoadedForm = TamanhoPizza_Service.getById(id_selected);
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
            case Cliente.ADD_STATE -> {
                this.entityLoadedForm.nro_TamanhoPizza = TamanhoPizza_Service.add(this.entityLoadedForm);
                success = this.entityLoadedForm.nro_TamanhoPizza != -1;
            }
            case Cliente.UPDATE_STATE -> success = TamanhoPizza_Service.updateById(this.entityLoadedForm.nro_TamanhoPizza, this.entityLoadedForm);
            case Cliente.INACTIVATE_STATE -> success = TamanhoPizza_Service.inactivateById(this.entityLoadedForm.nro_TamanhoPizza);
            case Cliente.ACTIVATE_STATE -> success = TamanhoPizza_Service.activateById(this.entityLoadedForm.nro_TamanhoPizza);
            case Cliente.DELETE_STATE -> success = TamanhoPizza_Service.deleteById(this.entityLoadedForm.nro_TamanhoPizza);
            default -> success = false;
        }

        // Change State
        this.state = Cliente.SELECT_STATE;
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
        this.state = Cliente.HOVER_STATE;
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
            case Cliente.HOVER_STATE:
                this.formularioPanel.getjButton_modificar().setEnabled(false);
            case Cliente.SELECT_STATE:
                this.formularioPanel.getjButton_adicionar().setEnabled(true);
                break;
            default:
                this.formularioPanel.getjButton_cancelar().setEnabled(true);
                this.formularioPanel.getjButton_actualizar().setEnabled(true);
                break;
        }
        
    }
}
