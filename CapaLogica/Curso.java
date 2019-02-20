/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

/**
 *
 * @author Christian Rodriguez
 */
public class Curso {

    private String nombre;
    private String Codigo;

    public Curso(String nombre, String Codigo) {
        this.nombre = nombre;
        this.Codigo = Codigo;

    }

    public Curso(String Codigo) {

        this.Codigo = Codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

}
