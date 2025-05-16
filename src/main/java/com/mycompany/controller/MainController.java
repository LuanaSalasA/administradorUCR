/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.CarreraJpaController;
import com.mycompany.model.CursoJpaController;
import com.mycompany.model.Perfil;
import com.mycompany.model.PerfilJpaController;
import com.mycompany.model.PlanEstudiosJpaController;
import com.mycompany.model.Usuario;
import com.mycompany.model.UsuarioJpaController;
import com.mycompany.view.MainGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luana
 */
public class MainController implements ActionListener{
    
    private MainGUI mainGUI;
    private CareerController careerController;
    private CourseController courseController;
    private StudyPlanController studyPlanController;
    private CarreraJpaController carreraJPA;
    private CursoJpaController cursoJPA;
    private PlanEstudiosJpaController planEstudiosJPA;
    private UsuarioJpaController usuarioJPA;
    private UserController userController;
    private Usuario usuario;
    
    public MainController(Usuario usuario) {
        this.mainGUI = new MainGUI();
        this.usuario = usuario;
        this.carreraJPA= new CarreraJpaController();
        this.cursoJPA= new CursoJpaController();
        this.planEstudiosJPA= new PlanEstudiosJpaController();
        this.usuarioJPA=new UsuarioJpaController();
        this.mainGUI.listen(this);
        this.mainGUI.setVisible(true);
        
        if (hasRole("admin")) {
            // Mostrar opciones de administrador
        } else {
            // Mostrar opciones de vista
        }
        
    }
    
    private boolean hasRole(String roleName) {
        for (Perfil perfil : usuario.getPerfilList()) {
            if (perfil.getTipo().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Exit":
                System.exit(0);
                break;
            case "Course Administrator":
                this.courseController= new CourseController(cursoJPA);
                break;
            case "Major Administrator":
                this.careerController=new CareerController(carreraJPA);
                break;
            case "Study Plan Administrator":
                this.studyPlanController=new StudyPlanController(planEstudiosJPA);
                break;
            case "User Administrator":
                this.userController=new UserController(usuarioJPA);
                break;
        }
    }
    
    

    
}
