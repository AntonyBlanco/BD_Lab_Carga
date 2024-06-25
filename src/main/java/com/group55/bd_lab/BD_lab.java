/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.group55.bd_lab;

import com.group55.bd_lab.controllers.*;
import java.awt.Font;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author anjab
 */
public class BD_lab {

    public static void main(String[] args) {
        Articulo_Cargo tamanhoPizza_GUI = new Articulo_Cargo();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            setUIFont(new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 10)); // Change the font size here
            SwingUtilities.updateComponentTreeUI(tamanhoPizza_GUI);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Error setting up WINDOWS Look And Feel");
        }
        tamanhoPizza_GUI.setVisible(true);
        
    }
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}
