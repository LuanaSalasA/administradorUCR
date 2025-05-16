/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Carrera;
import com.mycompany.model.CarreraJpaController;
import com.mycompany.model.Curso;
import com.mycompany.model.exceptions.IllegalOrphanException;
import com.mycompany.model.exceptions.NonexistentEntityException;
import com.mycompany.view.ButtonsPanel;
import com.mycompany.view.CourseAdministrator.CourseAdministratorGUI;
import com.mycompany.view.MajorAdministrator.DataMajorAdministratorPanel;
import com.mycompany.view.MajorAdministrator.MajorAdministratorGUI;
import com.mycompany.view.ReportGUI;
import com.mycompany.view.TablePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luana
 */
public class CareerController implements ActionListener, MouseListener{
    
    private DataMajorAdministratorPanel dataMajorPanel;
    private MajorAdministratorGUI majorAdministrator;
    private ButtonsPanel buttonsPanel;
    private CarreraJpaController carreraJPA;
    private ReportGUI reportGUI;
    private TablePanel tablePanel;
    private Carrera carreraEdit;
    
    public CareerController (CarreraJpaController carreraJPA){
        this.majorAdministrator= new MajorAdministratorGUI();
        this.dataMajorPanel= this.majorAdministrator.getDataMajorAdministratorPanel();
        this.buttonsPanel=this.majorAdministrator.getButtonPanel();
        this.carreraJPA=carreraJPA;
        this.buttonsPanel.listen(this);
        this.dataMajorPanel.listen(this);
        this.majorAdministrator.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save":
                Carrera majorAdd = this.dataMajorPanel.getCarrera();
                if (majorAdd.getCodigo().isEmpty()) {
                 
                    MajorAdministratorGUI.getMessage("Code field is empty!");
                
                } else if(majorAdd.getNombre().isEmpty()){
                    
                    MajorAdministratorGUI.getMessage("Name field is empty!");
 
                }else if(majorAdd.getDescripcion().isEmpty()){
                    
                    MajorAdministratorGUI.getMessage("Description field is empty!");
                    
                }else if(majorAdd.getPerfilProfesional().isEmpty()){
                    
                    MajorAdministratorGUI.getMessage("Professional profile field is empty!");
                    
                }else if (majorAdd.getMercadoLaboral().isEmpty()) {
                
                    MajorAdministratorGUI.getMessage("Job opportunities field is empty!");
                    
                }else{
                
                    System.out.println("Major Created!");
                    
                try {
                    this.carreraJPA.create(majorAdd);
                   this.carreraJPA.saveToJson(majorAdd, "carrera.json");
                   MajorAdministratorGUI.getMessage("Carrera saved to JSON!");
                }
                
                catch (Exception ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.dataMajorPanel.clean();
                
                }

                break;
                
            case "Search":
//                    MajorAdministratorGUI.getMessage("Searching...");
//                    Carrera majorFind=this.carreraJPA.findCarrera(this.dataMajorPanel.getTextIdNumber());
//                    if(majorFind!=null){
//                        this.dataMajorPanel.setCarrera(majorFind);
//                    }
//                break;
                
                 MajorAdministratorGUI.getMessage("Searching...");
                try {
                    // Leer desde JSON antes de buscar en la base de datos
                    Carrera majorLoad = this.carreraJPA.readFromJson("curso.json");
                    this.dataMajorPanel.setCarrera(majorLoad);
                    MajorAdministratorGUI.getMessage("Major loaded from JSON!");
                } catch (IOException ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Carrera majorFind = this.carreraJPA.findCarrera((this.dataMajorPanel.getTextIdNumber()));
                if (majorFind != null) {
                    this.dataMajorPanel.setCarrera(majorFind);
                }
                break;
                
            case "Back":
                this.majorAdministrator.dispose();
                break;
                
            case "Edit":
            {
                try {
                    this.carreraJPA.edit(this.carreraJPA.findCarrera(this.dataMajorPanel.getTextIdNumber()));
                    this.carreraJPA.saveToJson(carreraEdit, "carrera.json");
                    MajorAdministratorGUI.getMessage("Carrera edited and saved to JSON!");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                System.out.println("Updated!");
                this.dataMajorPanel.clean();
                
                break;

            
            case "Delete":
            {
                try {
                    this.carreraJPA.destroy(this.dataMajorPanel.getTextIdNumber());
                    Files.deleteIfExists(Paths.get("carrera.json"));
                    MajorAdministratorGUI.getMessage("Carrera deleted and JSON file removed!");
                
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                System.out.println("Deleted!");
                this.dataMajorPanel.clean();
                
                break;

                
            case "Report":
                this.reportGUI=new ReportGUI();
                this.tablePanel=this.reportGUI.getTablePanel();
                this.tablePanel.setTable(Carrera.HEADER_CARRERA,this.setTabla(Carrera.HEADER_CARRERA, this.carreraJPA.findCarrera()));
                System.out.println("Reading register...");
                this.tablePanel.listenMouse(this);
                this.reportGUI.setVisible(true);
                
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Carrera carreraTable= new Carrera (this.tablePanel.getRow()[0],this.tablePanel.getRow()[1],this.tablePanel.getRow()[2],this.tablePanel.getRow()[3], this.tablePanel.getRow()[4]);
        this.dataMajorPanel.setCarrera(carreraTable);
        this.reportGUI.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    public String[][] setTabla(String[] headers, List<Carrera> carreras) {
        String[][] data= new String[carreras.size()][headers.length];
        
        for (int i = 0; i < carreras.size(); i++) {
            Carrera carrera = carreras.get(i);
            data[i][0] = carrera.getCodigo();
            data[i][1] = carrera.getNombre();
            data[i][2] = carrera.getDescripcion();
            data[i][3] = carrera.getPerfilProfesional();
            data[i][4] = carrera.getMercadoLaboral();
            
        }
        
        return data;
    }
    
}
