/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Curso;
import com.mycompany.model.Perfil;
import com.mycompany.model.PerfilJpaController;
import com.mycompany.model.Usuario;
import com.mycompany.model.UsuarioJpaController;
import com.mycompany.model.UsuarioJpaController;
import com.mycompany.model.exceptions.IllegalOrphanException;
import com.mycompany.model.exceptions.NonexistentEntityException;
import com.mycompany.view.ButtonsPanel;
import com.mycompany.view.CourseAdministrator.CourseAdministratorGUI;
import com.mycompany.view.ReportGUI;
import com.mycompany.view.TablePanel;
import com.mycompany.view.UserAdminitrator.DataUserAdministratorPanel;
import com.mycompany.view.UserAdminitrator.UserAdministratorGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luana
 */
public class UserController implements ActionListener, MouseListener{
    private DataUserAdministratorPanel dataUserPanel;
    private UserAdministratorGUI userAdministrator;
    private ButtonsPanel buttonsPanel;
    private TablePanel tablePanel;
    private ReportGUI reportGUI;
    private UsuarioJpaController userJPA;
    
    public UserController (UsuarioJpaController userJPA){
        this.userAdministrator= new UserAdministratorGUI();
        this.dataUserPanel= this.userAdministrator.getDataUserAdministratorPanel();
        this.buttonsPanel=this.userAdministrator.getButtonPanel();
        this.userJPA=userJPA;
        this.buttonsPanel.listen(this);
        this.dataUserPanel.listen(this);
        this.userAdministrator.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save":
                Usuario userAdd = null;
                userAdd = this.dataUserPanel.getUser();
                if (userAdd.getId() == null || userAdd.getId() == 0) {
                    UserAdministratorGUI.getMessage("Id field is empty!");
                } else if (userAdd.getCarnet().isEmpty()) {
                    UserAdministratorGUI.getMessage("Carnet field is empty!");
                } else if (userAdd.getNombre().isEmpty()) {
                    UserAdministratorGUI.getMessage("Name field is empty!");
                } else if (userAdd.getApellido().isEmpty()) {
                    UserAdministratorGUI.getMessage("LastName field is empty!");
                } else if (userAdd.getNombreUsuario().isEmpty()) {
                    UserAdministratorGUI.getMessage("User name field is empty!");
                } else if (userAdd.getTelefono().isEmpty()) {
                    UserAdministratorGUI.getMessage("Phone field is empty!");
                }else if (userAdd.getContrasena().isEmpty()) {
                    UserAdministratorGUI.getMessage("Password field is empty!");
                }else if (userAdd.getCorreo().isEmpty()) {
                    UserAdministratorGUI.getMessage("Mail field is empty!");
                }else if (userAdd.getPerfilList().isEmpty()) {
                    UserAdministratorGUI.getMessage("Perfile type field is empty!");
                } else {
                    System.out.println("User Created!");

                try {
                    this.userJPA.create(userAdd);
                    this.userJPA.saveToJson(userAdd, "perfil.json");
                     UserAdministratorGUI.getMessage("Perfil saved to JSON!");
                
                } catch (Exception ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.dataUserPanel.clean();
                }


                break;

                
            case "Search":
                    try {
                    // Mensaje de búsqueda
                    UserAdministratorGUI.getMessage("Searching...");
                    
                    Perfil userLoad = this.userJPA.readFromJson("perfil.json");
                    this.dataUserPanel.setUser(userLoad);
                    CourseAdministratorGUI.getMessage("Curso loaded from JSON!");
    
                    // Intentar convertir el texto a un número entero
                    String textIdNumber = this.dataUserPanel.getTextIdNumber();
                    int idNumber = Integer.parseInt(textIdNumber);

                    // Buscar el plan de estudios usando el ID
                    Usuario majorFind = this.userJPA.findUsuario(idNumber);

                    // Verificar si se encontró el plan de estudios y actualizar el panel
                    if (majorFind != null) {
                        this.dataUserPanel.setUser(majorFind);
                    } else {
                        UserAdministratorGUI.getMessage("Plan not found!");
                    }
                    } catch (NumberFormatException ee) {
                        // Manejar el caso en que la conversión falle
                        UserAdministratorGUI.getMessage("Invalid ID number format!");
                    } catch (Exception ee) {
                        // Manejar cualquier otra excepción que pueda ocurrir
                        UserAdministratorGUI.getMessage("An unexpected error occurred: " + ee.getMessage());
                    }
                break;
                
            case "Back":
                this.userAdministrator.dispose();
                break;
                
            case "Edit":
            {
                try {
                    this.userJPA.edit(this.userJPA.findUsuario(Integer.valueOf(this.dataUserPanel.getTextIdNumber())));
                    //this.userJPA.saveToJson(userEdit, "perfil.json");
                    UserAdministratorGUI.getMessage("User edited and saved to JSON!");
               
                } catch (Exception ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                System.out.println("Updated!");
                this.dataUserPanel.clean();
                
                break;

            
            case "Delete":
            {
                try {
                    this.userJPA.destroy(Integer.valueOf(this.dataUserPanel.getTextIdNumber()));
               Files.deleteIfExists(Paths.get("perfil.json"));
                    UserAdministratorGUI.getMessage("User deleted and JSON file removed!");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CareerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalOrphanException ex) {
                Logger.getLogger(StudyPlanController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                System.out.println("Deleted!");
                this.dataUserPanel.clean();
                
                break;

                
            case "Report":
                this.reportGUI=new ReportGUI();
                this.tablePanel=this.reportGUI.getTablePanel();
                this.tablePanel.setTable(Usuario.HEADER_USER,this.setTabla(Usuario.HEADER_USER, this.userJPA.findUsuario()));
                System.out.println("Reading register...");
                this.tablePanel.listenMouse(this);
                this.reportGUI.setVisible(true);
                
                break;
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Usuario usuarioTable= new Usuario (Integer.valueOf(this.tablePanel.getRow()[0]),this.tablePanel.getRow()[1],this.tablePanel.getRow()[2],this.tablePanel.getRow()[3], this.tablePanel.getRow()[4],this.tablePanel.getRow()[5], this.tablePanel.getRow()[6], this.tablePanel.getRow()[7]);
        this.dataUserPanel.setUser(usuarioTable);
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
    
    public String[][] setTabla(String[] headers, List<Usuario> usuarios) {
        String[][] data= new String[usuarios.size()][headers.length];
        
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            data[i][0] = usuario.getId().toString();
            data[i][1] = usuario.getApellido();
            data[i][2] = usuario.getNombre();
            data[i][3] = usuario.getCarnet();
            data[i][4] = usuario.getTelefono();
            data[i][5] = usuario.getContrasena();
            data[i][6] = usuario.getNombreUsuario();
            data[i][7] = usuario.getCorreo();
        }
        
        return data;
    }
    
    
    
}
