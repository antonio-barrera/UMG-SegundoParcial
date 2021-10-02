/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import javafx.scene.control.Alert;

/**
 *
 * @author jeant
 */
public class ClsConexion {
    private static final String errorMessage = "Ha ocurrido un problema de conexion.";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db_comisiones?allowPublicKeyRetrieval=true&zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC"; 
    private static final String JDBC_USER = "root";
    private static final String JDBC_PWD = "root";
    
    public static Connection getConnetion() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
    }
    
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
    }
    
    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
    }
    
    public static void close(Connection cn) {
        try {
            cn.close();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        }
    }
}