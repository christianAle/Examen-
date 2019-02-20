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
public class Estudiante {

    private String nombre;
    private String carnet;
    private String clave;
    private String telefono;
    private String email;
    private Prematricula miPre;

    public Estudiante(String nombre, String carnet, String clave, String telefono, String email) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.clave = clave;
        this.telefono = telefono;
        this.email = email;
        miPre = null;
    }

    ///constructor para crear una instancia que reciba los atributos para iniciar sesión
    public Estudiante(String carnet, String clave) {
        this.carnet = carnet;
        this.clave = clave;

    }

    ///constructor para buscar un estudiante en la tabla de prematriculas
    public Estudiante(String carnet) {
        this.carnet = carnet;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Prematricula getMiPre() {
        return miPre;
    }

    public void setMiPre(Prematricula miPre) {
        this.miPre = miPre;
    }

    public void agregarPrematricula(String carnet, String codigo, String horario
    ) throws Exception {

           Prematricula pre;
           pre = (new MultiPrematricula().agregar(carnet, codigo, horario));
    }
}
