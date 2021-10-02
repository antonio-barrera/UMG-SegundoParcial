/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.*;
import javafx.scene.control.Alert;

/**
 *
 * @author jeant
 */
public class NominaEmpleados {
    private final AccesoDatosJDBC datos;
    private final String errorMessage = "Ha ocurrido un problema procesamiento de datos";
    
    public NominaEmpleados(){
        this.datos = new AccesoDatosJDBC();
    }
    
    public Double calcularTotal() {
        Double total = 0.0;
        List<MdEmpleado> empleados = null;
        try {
            empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                total += empleado.getTotal();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }  
        return total;
    }

    public void agregarEmpleado(String nombreEmpleado, Double enero, Double febrero, Double marzo) {
        MdEmpleado empleado = new MdEmpleado(nombreEmpleado, enero, febrero, marzo);
        try {
            datos.ejecutarConsulta(empleado, "INSERT");
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
    }

    public String buscarMayorYMenorVendedorPorMes(int mes) {
        mes++;
        Double mayor = 0.0;
        Double menor = calcularTotal();
        String nombreMayor = null;
        String nombreMenor = null;
        String resultado = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                String[] empDatos = empleado.toString().split("\\|");
                if (Double.valueOf(empDatos[mes]) > mayor) {
                    mayor = Double.valueOf(empDatos[mes]);
                    nombreMayor = empleado.getNombre();
                }
                if (Double.valueOf(empDatos[mes]) < menor) {
                    menor = Double.valueOf(empDatos[mes]);
                    nombreMenor = empleado.getNombre();
                }
            }
            if (nombreMayor != null && nombreMenor != null) {
                resultado = "MAYOR vendedor: " + nombreMayor + " con Q." + mayor + "\nMENOR vendedor: " + nombreMenor + " con Q." + menor;
            }
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
        return resultado;
    }

    public String buscarMayorVendedorGeneral() {
        Double mayor = 0.0;
        String resultado = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for(MdEmpleado empleado : empleados) {
                if (empleado.getTotal() > mayor) {
                    resultado = "El MAYOR vendedor general es " + empleado.getNombre() + " con Q." + empleado.getTotal();
                    mayor = empleado.getTotal();
                }
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
        return resultado;
    }

    public void editarDato(int codigo, String nombre, Double enero, Double febrero, Double marzo) {
        MdEmpleado nuevoEmpleado = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                if (empleado.getCodigo() == codigo) {
                    nuevoEmpleado = new MdEmpleado(nombre, enero, febrero, marzo);
                    nuevoEmpleado.setCodigo(codigo);
                    break;
                }
            }
            datos.ejecutarConsulta(nuevoEmpleado, "UPDATE");
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } 
    }

    public String buscarPorCantidad(Double cantidad) {
        String resultado = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                if (Objects.equals(empleado.getEnero(), cantidad)) {
                    resultado = empleado.getNombre() + " en el mes de enero.";
                    break;
                } else if (Objects.equals(empleado.getFebrero(), cantidad)) {
                    resultado = empleado.getNombre() + " en el mes de febrero.";
                    break;
                } else if (Objects.equals(empleado.getMarzo(), cantidad)) {
                    resultado = empleado.getNombre() + " en el mes de marzo.";
                    break;
                }
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
        return resultado;
    }
    
    public String buscarPorCodigo(int codigo) {
        String resultado = null;
        try {
            List<MdEmpleado> empleados = datos.listar();
            for (MdEmpleado empleado : empleados) {
                if (codigo == empleado.getCodigo()) {
                    resultado = empleado.toString();
                }
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
        return resultado;
    }

    public List<MdEmpleado> listarEmpleados() {
        List<MdEmpleado> empleados = null;
        try {
            empleados = datos.listar();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
        return empleados;
    }
    
    public void borrarEmpleado(int codigo) {
        MdEmpleado empleado = null;
        try {
            empleado = new MdEmpleado(codigo);
            datos.ejecutarConsulta(empleado, "DELETE");
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
    }
}
