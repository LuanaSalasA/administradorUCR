/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view.CourseAdministrator;

import com.mycompany.view.ButtonsPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author luana
 */
public class CourseAdministratorGUI extends javax.swing.JFrame {

    /**
     * Creates new form CourseAdministratorGUI
     */
    public CourseAdministratorGUI() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public static void getMessage(String msj){
        JOptionPane.showMessageDialog(null, msj);
    }
    
    public DataCourseAdministratorPanel getDataCourseAdministratorPanel(){
       
        return this.dataCourseAdministratorPanel1;
    }
    
    public ButtonsPanel getButtonPanel(){
        return this.buttonsPanel1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonsPanel1 = new com.mycompany.view.ButtonsPanel();
        dataCourseAdministratorPanel1 = new com.mycompany.view.CourseAdministrator.DataCourseAdministratorPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(buttonsPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, -1, -1));
        getContentPane().add(dataCourseAdministratorPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.view.ButtonsPanel buttonsPanel1;
    private com.mycompany.view.CourseAdministrator.DataCourseAdministratorPanel dataCourseAdministratorPanel1;
    // End of variables declaration//GEN-END:variables
}
