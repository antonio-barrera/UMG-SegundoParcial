/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jeant
 */
public class Usuario {
    private int id_user;
    private String username;
    private String password;
    
    public Usuario(){
        
    }
    
    public Usuario(int id_user) {
        this.id_user = id_user;
    }
    
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Usuario(int id_user, String username, String password) {
        this.id_user = id_user;
        this.username = username;
        this.username = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_user=" + id_user + ", username=" + username + ", password=" + password + '}';
    }

    public int getId_user() {
        return id_user;
    }
    
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
