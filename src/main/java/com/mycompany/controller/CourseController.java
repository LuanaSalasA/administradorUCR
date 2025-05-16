/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Curso;
import com.mycompany.model.CursoJpaController;
import com.mycompany.model.exceptions.IllegalOrphanException;
import com.mycompany.model.exceptions.NonexistentEntityException;
import com.mycompany.view.ButtonsPanel;
import com.mycompany.view.CourseAdministrator.CourseAdministratorGUI;
import com.mycompany.view.CourseAdministrator.DataCourseAdministratorPanel;
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
public class CourseController implements ActionListener, MouseListener{
    
    private DataCourseAdministratorPanel dataCoursePanel;
    private CourseAdministratorGUI courseAdministrator;
    private ButtonsPanel buttonsPanel;
    private CursoJpaController cursoJPA;
    private ReportGUI reportGUI;
    private TablePanel tablePanel;
    private Curso cursoEdit;
    
    public CourseController (CursoJpaController cursoJPA){
        this.courseAdministrator= new CourseAdministratorGUI();
        this.dataCoursePanel= this.courseAdministrator.getDataCourseAdministratorPanel();
        this.buttonsPanel=this.courseAdministrator.getButtonPanel();
        this.cursoJPA=cursoJPA;
        this.buttonsPanel.listen(this);
        this.dataCoursePanel.listen(this);
        this.courseAdministrator.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
           case "Save":
                Curso cursoAdd = this.dataCoursePanel.getCurso();
                if (cursoAdd.getSigla().equals("")) {
                 
                    CourseAdministratorGUI.getMessage("Acronym field is empty!");
                
                } else if(cursoAdd.getNombre().isEmpty()){
                    
                    CourseAdministratorGUI.getMessage("Name field is empty!");
 
                }else if(cursoAdd.getBloque().isEmpty()){
                    
                    CourseAdministratorGUI.getMessage("Block field is empty!");
                    
                }else if(cursoAdd.getModalidad().isEmpty()){
                    
                    CourseAdministratorGUI.getMessage("Modality field is empty!");
                    
                }else if (cursoAdd.getDescripcion().isEmpty()) {
                
                    CourseAdministratorGUI.getMessage("Description field is empty!");
                    
                }else{
                
                    System.out.println("Course Created!");
                    
                try {
                    this.cursoJPA.create(cursoAdd);
                    this.cursoJPA.saveToJson(cursoAdd, "curso.json");
                     CourseAdministratorGUI.getMessage("Curso saved to JSON!");
                }
                
                catch (Exception ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.dataCoursePanel.clean();
                
                }

                break;
                
            case "Search":
//                    CourseAdministratorGUI.getMessage("Searching...");
//                    Curso majorFind=this.cursoJPA.findCurso(Integer.parseInt(this.dataCoursePanel.getTextIdNumber()));
//                    if(majorFind!=null){
//                        this.dataCoursePanel.setCurso(majorFind);
//                    }
//                break;
                
                CourseAdministratorGUI.getMessage("Searching...");
                try {
                    // Leer desde JSON antes de buscar en la base de datos
                    Curso cursoLoad = this.cursoJPA.readFromJson("curso.json");
                    this.dataCoursePanel.setCurso(cursoLoad);
                    CourseAdministratorGUI.getMessage("Curso loaded from JSON!");
                } catch (IOException ex) {
                    Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Curso majorFind = this.cursoJPA.findCurso(Integer.parseInt(this.dataCoursePanel.getTextIdNumber()));
                if (majorFind != null) {
                    this.dataCoursePanel.setCurso(majorFind);
                }
                break;
                
            case "Back":
                this.courseAdministrator.dispose();
                break;
                
            case "Edit":
            {
                try {
                    this.cursoJPA.edit(this.cursoJPA.findCurso(Integer.parseInt(this.dataCoursePanel.getTextIdNumber())));
                     this.cursoJPA.saveToJson(cursoEdit, "curso.json");
                    CourseAdministratorGUI.getMessage("Curso edited and saved to JSON!");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                System.out.println("Updated!");
                this.dataCoursePanel.clean();
                
                break;

            
            case "Delete":
            {
                try {
                    this.cursoJPA.destroy(Integer.parseInt(this.dataCoursePanel.getTextIdNumber()));
                    Files.deleteIfExists(Paths.get("curso.json"));
                    CourseAdministratorGUI.getMessage("Curso deleted and JSON file removed!");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                System.out.println("Deleted!");
                this.dataCoursePanel.clean();
                
                break;

                
            case "Report":
                this.reportGUI=new ReportGUI();
                this.tablePanel=this.reportGUI.getTablePanel();
                this.tablePanel.setTable(Curso.HEADER_CURSO,this.setTabla(Curso.HEADER_CURSO, this.cursoJPA.findCurso()));
                System.out.println("Reading register...");
                this.tablePanel.listenMouse(this);
                this.reportGUI.setVisible(true);
                
                break;
        }
        }
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            
        int valor1 = Integer.parseInt(this.tablePanel.getRow()[0]);
        int valor2 = Integer.parseInt(this.tablePanel.getRow()[2]);
        int valor3 = Integer.parseInt(this.tablePanel.getRow()[3]);
        int valor4 = Integer.parseInt(this.tablePanel.getRow()[4]);

        
        String valorString1 = this.tablePanel.getRow()[1];
        String valorString2 = this.tablePanel.getRow()[5];
        String valorString3 = this.tablePanel.getRow()[6];
        String valorString4 = this.tablePanel.getRow()[7];

        
        Curso cursoTable = new Curso(valor1, valorString1, valor2, valor3, valor4, valorString2, valorString3, valorString4);

        this.dataCoursePanel.setCurso(cursoTable);
        this.reportGUI.dispose();

        } catch (NumberFormatException ex) {
            
            System.out.println("Error al convertir cadena a entero: " + ex.getMessage());
        }
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
    
    public String[][] setTabla(String[] headers, List<Curso> cursos) {
        String[][] data= new String[cursos.size()][headers.length];
        
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            data[i][0] = curso.getSigla().toString();
            data[i][1] = curso.getBloque();
            data[i][2] = String.valueOf(curso.getHorasTrabajo());
            data[i][3] = String.valueOf(curso.getHorasLectivas());
            data[i][4] = String.valueOf(curso.getCantidadCreditos());
            data[i][5] = curso.getModalidad();
            data[i][6] = curso.getDescripcion();
            data[i][7] = curso.getNombre();
        }
        
        return data;
    }
    
    }

    
    

