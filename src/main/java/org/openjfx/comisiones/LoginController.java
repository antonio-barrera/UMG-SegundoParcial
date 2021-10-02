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
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContraseña;

    @FXML
    void eventKey(KeyEvent event) {
        if (event.getCharacter().equals(' ')) {
            event.consume();
        }
    }
    
    @FXML
    void eventLinkAction(ActionEvent event) throws IOException {
        App.setRoot("Register", event);
    }

    @FXML
    void eventLoginAction(ActionEvent event) {
        Usuario usuario = null;
        try {
            usuario = new Usuario(txtUsuario.getText(), txtContraseña.getText());
            if (UsuarioJDBC.select_validacion(usuario)) {
                App.setRoot("Main", event);
            } else {
                new Alert(Alert.AlertType.WARNING, "Usuario no valido.").showAndWait();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.toString()).showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
}
