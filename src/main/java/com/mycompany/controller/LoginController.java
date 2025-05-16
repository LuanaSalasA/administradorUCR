/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Usuario;
import com.mycompany.model.UsuarioJpaController;
import com.mycompany.view.Login.LoginGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author luana
 */
public class LoginController implements ActionListener{
    
    
    private LoginGUI login;
    private MainController mainController;
    private UsuarioJpaController usuarioJpaController;
    
    
    public LoginController (){
        this.usuarioJpaController = new UsuarioJpaController(); 
        this.login= new LoginGUI();
        this.login.listen(this);
        this.login.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
        String username = login.getUserName();
        String password = new String(login.getPasswordField().getPassword()); // Obtener la contraseña como String
        
        // Validar que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            LoginGUI.getMessage("Username and password cannot be empty");
            return;
        }

        Usuario usuario = usuarioJpaController.findUsuarioByUsernameAndPassword(username, password);

        if (usuario != null) {
            this.mainController = new MainController(usuario);
            this.login.setVisible(false);
        } else {
            LoginGUI.getMessage("Invalid username or password");
        }       
    }     
    }
    
}

