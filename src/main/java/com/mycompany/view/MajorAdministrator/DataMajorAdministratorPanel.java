/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.view.MajorAdministrator;

import com.mycompany.model.Carrera;
import java.awt.event.ActionListener;

/**
 *
 * @author luana
 */
public class DataMajorAdministratorPanel extends javax.swing.JPanel {

    /**
     * Creates new form DataMajorAdministratorPanel
     */
    public DataMajorAdministratorPanel() {
        initComponents();
        
    }
    
    public String getTextIdNumber(){
        return this.txtId.getText();
    }
    
    public Carrera getCarrera(){
        return new Carrera(this.txtId.getText(), this.txtName.getText(), this.txaDescription.getText(), this.txaProfessionalProfile.getText(), this.txaJobOpportunities.getText());
    }
    
    public void setCarrera(Carrera carrera){
        this.txtId.setText(carrera.getCodigo());
        this.txtName.setText(carrera.getNombre());
        this.txaDescription.setText(carrera.getDescripcion());
        this.txaProfessionalProfile.setText(carrera.getPerfilProfesional());
        this.txaJobOpportunities.setText(carrera.getMercadoLaboral());
    }
    
    public void clean(){
        this.txtId.setText("");
        this.txtName.setText("");
        this.txaDescription.setText("");
        this.txaProfessionalProfile.setText("");
        this.txaJobOpportunities.setText("");
    }
    
    public void listen(ActionListener controller){
        this.btnSearch.addActionListener(controller);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaJobOpportunities = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaProfessionalProfile = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Code");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Name");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Description");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Professional profile");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Job opportunities");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/7.png"))); // NOI18N
        btnSearch.setActionCommand("Search");
        btnSearch.setBorderPainted(false);
        btnSearch.setContentAreaFilled(false);
        add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, -1, -1));
        add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 240, -1));
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 240, -1));

        txaJobOpportunities.setColumns(20);
        txaJobOpportunities.setRows(5);
        jScrollPane3.setViewportView(txaJobOpportunities);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 240, 70));

        txaProfessionalProfile.setColumns(20);
        txaProfessionalProfile.setRows(5);
        jScrollPane2.setViewportView(txaProfessionalProfile);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 240, 70));

        txaDescription.setColumns(20);
        txaDescription.setRows(5);
        jScrollPane1.setViewportView(txaDescription);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 240, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/backgrounds/3.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 400));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txaDescription;
    private javax.swing.JTextArea txaJobOpportunities;
    private javax.swing.JTextArea txaProfessionalProfile;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
