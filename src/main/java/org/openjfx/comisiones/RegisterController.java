/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.comisiones;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import modelo.Usuario;
import modelo.UsuarioJDBC;
/**
 * FXML Controller class
 *
 * @author jeant
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txtRegistrarUsuario;
    @FXML
    private PasswordField txtRegistrarContraseña;
    @FXML
    private PasswordField txtConfirmarContraseña;
    

    @FXML
    void eventLinkAction(ActionEvent event) throws IOException {
        App.setRoot("Login", event);
    }

    @FXML
    void eventRegisterAction(ActionEvent event) {
        Usuario usuario = null;
        try {
            usuario = new Usuario(txtRegistrarUsuario.getText(), txtRegistrarContraseña.getText());
            if (txtRegistrarContraseña.getText().equals(txtConfirmarContraseña.getText())) {
                UsuarioJDBC.ejecutarConsulta(usuario, "INSERT");
                App.setRoot("Login", event);
            } else {
                new Alert(Alert.AlertType.WARNING, "Las contraseñas no son iguales").showAndWait();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Ha ocurrido un error").showAndWait();
        }
    }
    
    @FXML
    private void eventKey(KeyEvent event) {
        if (event.getCharacter().equals(' ')) {
            event.consume();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
}
