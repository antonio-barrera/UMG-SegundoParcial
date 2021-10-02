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
public class UsuarioJDBC {
    private static final String errorMessage = "Ha ocurrido un problema de autenticacion.";
    private static final String SQL_SELECT = "SELECT * FROM usuario";
    private static final String SQL_UPDATE = "UPDATE usuario SET username = ?, password = ? WHERE id_user = ?;";
    private static final String SQL_INSERT = "INSERT INTO usuario(username, password) VALUES(?, ?);";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_user = ?;";
    
    public static List<Usuario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = ClsConexion.getConnetion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String username = rs.getString("username");
                String password = rs.getString("password");
                usuario = new Usuario();
                usuario.setId_user(id_user);
                usuario.setUsername(username);
                usuario.setPassword(password);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return usuarios;
    }
    
    public static boolean select_validacion(Usuario datos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean permiso = false;
        try {
            String condicion = SQL_SELECT + " WHERE username = '" + datos.getUsername() + "' AND password = '" + datos.getPassword() + "';";
            conn = ClsConexion.getConnetion();
            stmt = conn.prepareStatement(condicion);
            rs = stmt.executeQuery();
            while (rs.next()) {
                permiso = true;
            }
        } catch (SQLException ex) { 
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
        } finally {
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return permiso;
    }
    
    public static void ejecutarConsulta(Usuario usuario, String consulta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String QUERY = null;
        try {
            conn = ClsConexion.getConnetion();
            if (Objects.equals(consulta, "DELETE")) {
                QUERY = SQL_DELETE;
                stmt = conn.prepareStatement(QUERY);
                stmt.setInt(1, usuario.getId_user());
            } else {
                if (Objects.equals(consulta, "UPDATE")) {
                    QUERY = SQL_UPDATE;
                    stmt = conn.prepareStatement(QUERY);
                } else if (Objects.equals(consulta, "INSERT")) {
                    QUERY = SQL_INSERT;
                    stmt = conn.prepareStatement(QUERY);
                }
                stmt.setString(1, usuario.getUsername());
                stmt.setString(2, usuario.getPassword());
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
