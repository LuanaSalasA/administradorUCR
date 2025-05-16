/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Carrera;
import com.mycompany.model.PlanEstudios;
import com.mycompany.model.PlanEstudiosJpaController;
import com.mycompany.model.exceptions.IllegalOrphanException;
import com.mycompany.model.exceptions.NonexistentEntityException;
import com.mycompany.view.ButtonsPanel;
import com.mycompany.view.PlanAdministrator.DataPlanAdministratorPanel;
import com.mycompany.view.PlanAdministrator.PlanAdministratorGUI;
import com.mycompany.view.ReportGUI;
import com.mycompany.view.TablePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luana
 */
public class StudyPlanController implements ActionListener, MouseListener{
    
    private DataPlanAdministratorPanel dataPlanPanel;
    private PlanAdministratorGUI planAdministrator;
    private ButtonsPanel buttonsPanel;
    private TablePanel tablePanel;
    private ReportGUI reportGUI;
    private PlanEstudiosJpaController planJPA;
    
    public StudyPlanController (PlanEstudiosJpaController planJPA){
        this.planAdministrator= new PlanAdministratorGUI();
        this.dataPlanPanel= this.planAdministrator.getDataPlanAdministratorPanel();
        this.buttonsPanel=this.planAdministrator.getButtonPanel();
        this.planJPA= planJPA;
        this.buttonsPanel.listen(this);
        this.dataPlanPanel.listen(this);
        this.planAdministrator.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
           case "Save":
                PlanEstudios planAdd = null;
            try {
                planAdd = this.dataPlanPanel.getPlanEstudios();
            } catch (ParseException ex) {
                Logger.getLogger(StudyPlanController.class.getName()).log(Level.SEVERE, null, ex);
            }
                if (planAdd.getId() == null || planAdd.getId() == 0) {
                PlanAdministratorGUI.getMessage("Id field is empty!");
                } else if (planAdd.getCarreraCodigo() == null) {
                    PlanAdministratorGUI.getMessage("Major Code field is empty!");
                } else if (planAdd.getDescripcion() == null || planAdd.getDescripcion().isEmpty()) {
                    PlanAdministratorGUI.getMessage("Description field is empty!");
                } else if (planAdd.getCantidadCreditos() == 0) {
                    PlanAdministratorGUI.getMessage("Number of credits field is empty!");
                } else if (planAdd.getFechaAprobacion() == null) {
                    PlanAdministratorGUI.getMessage("Approval date field is empty!");
                } else if (planAdd.getFechaVigor() == null) {
                    PlanAdministratorGUI.getMessage("Effective date field is empty!");
                } else {
                    System.out.println("Course Created!");

                try {
                    this.planJPA.create(planAdd);
                } catch (Exception ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.dataPlanPanel.clean();
                }


                break;

                
            case "Search":
                    try {
                    // Mensaje de búsqueda
                    PlanAdministratorGUI.getMessage("Searching...");
    
                    // Intentar convertir el texto a un número entero
                    String textIdNumber = this.dataPlanPanel.getTextIdNumber();
                    int idNumber = Integer.parseInt(textIdNumber);

                    // Buscar el plan de estudios usando el ID
                    PlanEstudios majorFind = this.planJPA.findPlanEstudios(idNumber);

                    // Verificar si se encontró el plan de estudios y actualizar el panel
                    if (majorFind != null) {
                        this.dataPlanPanel.setPlanEstudios(majorFind);
                    } else {
                        PlanAdministratorGUI.getMessage("Plan not found!");
                    }
                    } catch (NumberFormatException ee) {
                        // Manejar el caso en que la conversión falle
                        PlanAdministratorGUI.getMessage("Invalid ID number format!");
                    } catch (Exception ee) {
                        // Manejar cualquier otra excepción que pueda ocurrir
                        PlanAdministratorGUI.getMessage("An unexpected error occurred: " + ee.getMessage());
                    }
                break;
                
            case "Back":
                this.planAdministrator.dispose();
                break;
                
            case "Edit":
            {
                try {
                    this.planJPA.edit(this.planJPA.findPlanEstudios(Integer.valueOf(this.dataPlanPanel.getTextIdNumber())));
                } catch (Exception ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                System.out.println("Updated!");
                this.dataPlanPanel.clean();
                
                break;

            
            case "Delete":
            {
                try {
                    this.planJPA.destroy(Integer.valueOf(this.dataPlanPanel.getTextIdNumber()));
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalOrphanException ex) {
                Logger.getLogger(StudyPlanController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                System.out.println("Deleted!");
                this.dataPlanPanel.clean();
                
                break;

                
            case "Report":
                this.reportGUI=new ReportGUI();
                this.tablePanel=this.reportGUI.getTablePanel();
                this.tablePanel.setTable(PlanEstudios.HEADER_STUDY_PLAN,this.setTabla(PlanEstudios.HEADER_STUDY_PLAN, this.planJPA.findPlanEstudios()));
                System.out.println("Reading register...");
                this.tablePanel.listenMouse(this);
                this.reportGUI.setVisible(true);
                
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
        // Conversión de cadenas a enteros
        int valor1 = Integer.parseInt(this.tablePanel.getRow()[0]);
        int valor2 = Integer.parseInt(this.tablePanel.getRow()[2]);

        // Conversión de cadenas a fechas
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaAprobacion = dateFormat.parse(this.tablePanel.getRow()[4]);
        Date fechaVigor = dateFormat.parse(this.tablePanel.getRow()[5]);

        // Conversión de cadenas
        String valorString1 = this.tablePanel.getRow()[1];

        // Crear el objeto Carrera (asegúrate de que tienes un método para obtener un objeto Carrera)
        Carrera carrera = obtenerCarreraDesdeCodigo(this.tablePanel.getRow()[6]);

        // Crear el objeto PlanEstudios
        PlanEstudios planTable = new PlanEstudios(valor1, valorString1, valor2, fechaAprobacion, fechaVigor, carrera);

        // Establecer el plan de estudios en el panel correspondiente
        this.dataPlanPanel.setPlanEstudios(planTable);
        this.reportGUI.dispose();

    } catch (NumberFormatException ex) {
        System.out.println("Error al convertir cadena a entero: " + ex.getMessage());
    } catch (ParseException ex) {
        System.out.println("Error al convertir cadena a fecha: " + ex.getMessage());
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
    
    public String[][] setTabla(String[] headers, List<PlanEstudios> planEstudios) {
        String[][] data= new String[planEstudios.size()][headers.length];
        
        for (int i = 0; i < planEstudios.size(); i++) {
            PlanEstudios plan = planEstudios.get(i);
            data[i][0] = plan.getId().toString();
            data[i][1] = plan.getDescripcion();
            data[i][4] = String.valueOf(plan.getCantidadCreditos());
            data[i][5] = plan.getFechaAprobacion().toString();
            data[i][6] = plan.getFechaVigor().toString();
            data[i][7] = plan.getCarreraCodigo().getNombre();
        }
        
        return data;
    }
    
    // Método para obtener un objeto Carrera 
    private Carrera obtenerCarreraDesdeCodigo(String codigo) {
        return new Carrera(codigo);
    }
}
