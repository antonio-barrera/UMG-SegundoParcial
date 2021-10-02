/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.*;
import javafx.scene.control.Alert;

/**
 *
 * @author jeant
 */
public class AccesoDatosJDBC {
    private static final String errorMessage = "Ha ocurrido un problema de acceso a datos.";
    private static final String SQL_SELECT = "SELECT * FROM tb_empleados";
    private static final String SQL_INSERT = "INSERT INTO tb_empleados(nombre, enero, febrero, marzo, total, promedio) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tb_empleados SET nombre = ?, enero = ?, febrero = ?, marzo = ?, total = ?, promedio = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM tb_empleados WHERE codigo = ?";
    
    public List<MdEmpleado> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MdEmpleado empleado = null;
        List<MdEmpleado> empleados = new ArrayList<>();
        try {
            conn = ClsConexion.getConnetion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt(1);
                String nombre = rs.getString(2);
                Double enero = rs.getDouble(3);
                Double febrero = rs.getDouble(4);
                Double marzo = rs.getDouble(5);
                empleado = new MdEmpleado(nombre, enero, febrero, marzo);
                empleado.setCodigo(codigo);
                empleados.add(empleado);
            }
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            ClsConexion.close(conn);
            ClsConexion.close(stmt);
            ClsConexion.close(rs);
        }
        return empleados;
    }
    
    public void ejecutarConsulta(MdEmpleado empleado, String consulta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String QUERY = null;
        try {
            conn = ClsConexion.getConnetion();
            if (Objects.equals(consulta, "DELETE")) {
                QUERY = SQL_DELETE;
                stmt = conn.prepareStatement(QUERY);
                stmt.setInt(1, empleado.getCodigo());
            } else {
                if (Objects.equals(consulta, "UPDATE")) {
                    QUERY = SQL_UPDATE;
                    stmt = conn.prepareStatement(QUERY);
                    stmt.setInt(7, empleado.getCodigo());
                } else if (Objects.equals(consulta, "INSERT")) {
                    QUERY = SQL_INSERT;
                    stmt = conn.prepareStatement(QUERY);
                }
                stmt.setString(1, empleado.getNombre());
                stmt.setDouble(2, empleado.getEnero());
                stmt.setDouble(3, empleado.getFebrero());
                stmt.setDouble(4, empleado.getMarzo());
                stmt.setDouble(5, empleado.getTotal());
                stmt.setDouble(6, empleado.getPromedio());
            }
            stmt.execute();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
    }
}