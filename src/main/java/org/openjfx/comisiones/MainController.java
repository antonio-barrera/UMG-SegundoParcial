/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.comisiones;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import modelo.MdEmpleado;
import modelo.NominaEmpleados;
/**
 * FXML Controller class
 *
 * @author jeant
 */
public class MainController implements Initializable {
    private final String errorMessage = "Ha ocurrido un problema.";
    private final String emptyFieldMessage = "No se ha seleccionado ningun registro.";
    private final NominaEmpleados empleados = new NominaEmpleados();

    @FXML
    private TextField txtBuscarCodigo;

    @FXML
    private TextField txtMarzo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEnero;

    @FXML
    private TextField txtFebrero;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtPromedio;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtBuscarCantidad;

    @FXML
    private TextField txtBuscarMes;

    @FXML
    private TableView<MdEmpleado> tvLista;

    @FXML
    private TableColumn<MdEmpleado, Integer> colCodigo;

    @FXML
    private TableColumn<MdEmpleado, String> colNombre;

    @FXML
    private TableColumn<MdEmpleado, Double> colEnero;

    @FXML
    private TableColumn<MdEmpleado, Double> colFebrero;

    @FXML
    private TableColumn<MdEmpleado, Double> colMarzo;

    @FXML
    private TableColumn<MdEmpleado, Double> colTotal;

    @FXML
    private TableColumn<MdEmpleado, Double> colPromedio;

    @FXML
    private TextField txtTotalGeneral;

    @FXML
    private TextField txtPromedioGeneral;
    
    @FXML
    void eventLogoutAction(ActionEvent event) throws IOException {
        App.setRoot("Login", event);
    }
    
    @FXML
    void eventBuscarCodigo(ActionEvent event) {
        String resultado = null;
        String[] datos = null;
        try {
            if (!txtBuscarCodigo.getText().isBlank()) {
                int codigo = Integer.parseInt(txtBuscarCodigo.getText());
                resultado = empleados.buscarPorCodigo(codigo);
                datos = resultado.split("\\|");
                txtCodigo.setText(datos[0]);
                txtNombre.setText(datos[1]);
                txtEnero.setText(datos[2]);
                txtFebrero.setText(datos[3]);
                txtMarzo.setText(datos[4]);
                txtTotal.setText(datos[5]);
                txtPromedio.setText(datos[6]);
            } 
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            txtBuscarCodigo.clear();
        }
    }
    
    @FXML
    void eventGuardar(ActionEvent event) {
        try {
            if (checkTextFields()) {
                empleados.agregarEmpleado(txtNombre.getText(), Double.valueOf(txtEnero.getText()), 
                        Double.valueOf(txtFebrero.getText()), Double.valueOf(txtMarzo.getText()));
                new Alert(Alert.AlertType.INFORMATION, "El registro se ha guardado con exito.").showAndWait();
                clearTextFields();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Debe llenar todos los campos.").showAndWait();
            }
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            clearTextFields();
            actualizarDatos();
        }
    }
    
    @FXML
    void eventModificar(ActionEvent event) {
        try {
            if (!txtCodigo.getText().isBlank()) {
                empleados.editarDato(Integer.parseInt(txtCodigo.getText()), 
                txtNombre.getText(), Double.valueOf(txtEnero.getText()), 
                Double.valueOf(txtFebrero.getText()), Double.valueOf(txtMarzo.getText()));
                new Alert(Alert.AlertType.INFORMATION, "El registro se ha modificado con exito.").showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Busque un registro para modificar.").showAndWait();
            }
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            clearTextFields();
            actualizarDatos();
        }
    }

    @FXML
    void eventBorrar(ActionEvent event) {
        try {
            if (!txtCodigo.getText().isBlank()) {
                empleados.borrarEmpleado(Integer.valueOf(txtCodigo.getText()));
            } 
        } catch (NumberFormatException ex){
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            clearTextFields();
            actualizarDatos();
        }
    }

    @FXML
    void eventBuscarCantidad(ActionEvent event) {
        Double cantidad = null;
        String resultado = null;
        try {
            if (!txtBuscarCantidad.getText().isBlank()) {
                cantidad = Double.valueOf(txtBuscarCantidad.getText());
                if (cantidad != null) {
                    resultado = "Resultado: \n" + empleados.buscarPorCantidad(cantidad);
                } else {
                    resultado = "No se encontraron resultados.";
                }
                new Alert(Alert.AlertType.INFORMATION, resultado).showAndWait();
            }
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            txtBuscarCantidad.clear();
        }
    }

    @FXML
    void eventMayorVendedor(ActionEvent event) {
        String resultado = null;
        try {
            resultado = "Resultado: \n" + empleados.buscarMayorVendedorGeneral();
            new Alert(Alert.AlertType.INFORMATION, resultado).showAndWait();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
    }

    @FXML
    void eventBuscarMes(ActionEvent event) {
        String resultado = null;
        try {
            if (!txtBuscarMes.getText().isBlank()) {
                int mes = Integer.parseInt(txtBuscarMes.getText());
                if (mes > 0 && mes < 4) {
                    resultado = "Resultado: \n" + empleados.buscarMayorYMenorVendedorPorMes(mes);
                    
                } else {
                    resultado = "Ingrese un mes válido.";
                }
                new Alert(Alert.AlertType.INFORMATION, resultado).showAndWait();
            }
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            txtBuscarMes.clear();
        }
    }

    @FXML
    void eventKey(KeyEvent event) {
        if (event.getText().equals(" ")) {
            event.consume();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarDatos();
    }

    private Double calcularTotalGeneral() {
        List<MdEmpleado> todos = null;
        Double total = 0.0;
        try {
            todos = empleados.listarEmpleados();
            for (MdEmpleado empleado : todos) {
                total += empleado.getTotal();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
        return total;
    }
    
    void clearTextFields() {
        txtCodigo.clear();
        txtNombre.clear();
        txtEnero.clear();
        txtFebrero.clear();
        txtMarzo.clear();
        txtTotal.clear();
        txtPromedio.clear();
    }
    
    boolean checkTextFields() {
        boolean resultado = true;
        if (txtNombre.getText().isBlank())  {resultado = false;}
        if (txtEnero.getText().isBlank())   {resultado = false;}
        if (txtFebrero.getText().isBlank()) {resultado = false;}
        if (txtMarzo.getText().isBlank())   {resultado = false;}
        return resultado;
    }
    
    private ObservableList<MdEmpleado> getEmpleados(){
        ObservableList<MdEmpleado> datos = FXCollections.observableArrayList();
        List<MdEmpleado> todos = empleados.listarEmpleados();
        todos.forEach(empleado -> {
            datos.add(empleado);
        });
        return datos;
    }
    
    void actualizarDatos() {
        ObservableList<MdEmpleado> datos = null;
        Double total = 0.0;
        Double promedio = 0.0;
        try {
            total = calcularTotalGeneral();
            promedio = Math.round((total/3) * 100.0) / 100.0;
            txtTotalGeneral.setText("Q." + String.valueOf(total));
            txtPromedioGeneral.setText("Q." + String.valueOf(promedio));
            this.colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
            this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.colEnero.setCellValueFactory(new PropertyValueFactory("enero"));
            this.colFebrero.setCellValueFactory(new PropertyValueFactory("febrero"));
            this.colMarzo.setCellValueFactory(new PropertyValueFactory("marzo"));
            this.colTotal.setCellValueFactory(new PropertyValueFactory("total"));
            this.colPromedio.setCellValueFactory(new PropertyValueFactory("promedio"));
            datos = getEmpleados();
            this.tvLista.setItems(datos);
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
    }
}
