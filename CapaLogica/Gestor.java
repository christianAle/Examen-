/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Christian Rodriguez Alvarez
 */
public class Gestor {

    public void estudianteAgregar(String nombre, String carnet, String clave, String telefono, String email) throws Exception {
        Estudiante miEstu;
        miEstu = (new MultiEstudiante().agregar(nombre, carnet, clave, telefono, email));

    }

    public TreeMap estudianteBuscar(String clave, String usario) throws Exception {
        TreeMap datos = null;
        Estudiante estudi = null;
        String nombre;
        datos = new TreeMap();
        estudi = (new MultiEstudiante()).buscar(clave, usario);
        datos.put("nombre", estudi.getNombre());
        datos.put("carnet", estudi.getCarnet());
        return datos;

    }

    public TreeMap verificarPrematricula(String carnet) throws Exception {
        TreeMap datos = null;
        Estudiante estudi = null;
        String nombre;
        datos = new TreeMap();
        estudi = (new MultiPrematricula().buscarEstudiante(carnet));
        datos.put("nombre", estudi.getNombre());
        datos.put("carnet", estudi.getCarnet());
        return datos;

    }

    public void estudianteModificar(String carnet, String clave, String nombre, String telefono, String email) throws Exception {
        try {
            Estudiante estu;
            estu = (new MultiEstudiante().buscar(carnet, clave));
            estu.setNombre(nombre);
            estu.setTelefono(telefono);
            estu.setEmail(email);
            estu.setCarnet(carnet);
            (new MultiEstudiante()).actualizar(estu);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Map<String, String>> listarCurso() throws Exception {

        List<Map<String, String>> listarCurso = new MultiCurso().listar();

        return listarCurso;
    }

    public List<Map<String, String>> listarprematriculados(String carnet) throws Exception {

        List<Map<String, String>> listarpre = new MultiPrematricula().listar(carnet);

        return listarpre;
    }

    public void prematricular(String carnet, String clave, String codigo, String horario) throws Exception {
        Estudiante estu;
        Prematricula pre;
        estu = (new MultiEstudiante()).buscar(carnet, clave);
        estu.agregarPrematricula(carnet, codigo, horario);
    }

    public TreeMap buscarCurso(String codigo) throws Exception {
        TreeMap datos = null;
        Curso miCurso = null;
        String nombre;
        datos = new TreeMap();
        miCurso = (new MultiPrematricula().buscarCurso(codigo));
        datos.put("codigo", miCurso.getCodigo());

        return datos;

    }

    public void borrarPrematicula(String codigo, String carnet) throws Exception {
        Prematricula pre = new Prematricula();
        Curso miCurso;
        miCurso = (new MultiCurso().buscarCurso(codigo));
        miCurso.setCodigo(codigo);
        miCurso.getCodigo();

        pre.setCodigoCurso(miCurso.getCodigo());

        (new MultiPrematricula()).borrar(pre);
    }
    
}
