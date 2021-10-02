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
public class MdEmpleado {
    private int codigo;
    private String nombre;
    private Double enero;
    private Double febrero;
    private Double marzo;
    private Double total;
    private Double promedio;
    
    public MdEmpleado(int codigo) {
        this.codigo = codigo;
    }
    
    public MdEmpleado(String nombre, Double enero, Double febrero, Double marzo) {
        this.nombre = nombre;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.total = enero + febrero + marzo;
        this.promedio = Math.round(((enero + febrero + marzo)/3) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return getCodigo() + "|" + getNombre() + "|" + getEnero() + "|" + getFebrero() + "|" + getMarzo() + "|" + getTotal() + "|" + getPromedio();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getEnero() {
        return enero;
    }

    public Double getFebrero() {
        return febrero;
    }

    public Double getMarzo() {
        return marzo;
    }

    public Double getTotal() {
        return total;
    }

    public Double getPromedio() {
        return promedio;
    }
}