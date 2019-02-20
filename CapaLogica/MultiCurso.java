/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;


import AccesoBD.Conector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Christian Rodriguez Alvarez
 */
public class MultiCurso {

    public Estudiante buscar(String carnet, String clave) throws
            java.sql.SQLException, Exception {
        Estudiante estud = null;

        try {
            java.sql.ResultSet rs;
            String sql;
            sql = "SELECT clave,carnet "
                    + "FROM Estudiante "
                    + "WHERE clave='" + clave + "';";
            rs = Conector.getConector().ejecutarSQL(sql, true);
            if (rs.next()) {
                estud = new Estudiante(rs.getString("clave"), rs.getString("carnet"));
            } else {

                throw new Exception("El estudiante no est� registrado.");
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return estud;
    }

    public Curso buscarCurso(String codigo) throws java.sql.SQLException, Exception {
        Curso miCurso = null;

        try {
            java.sql.ResultSet rs;
            String sql;
   
            
               sql = "SELECT codigo,nombre "
                    + "FROM curso "
                    + "WHERE codigo='" + codigo + "';";
            rs = Conector.getConector().ejecutarSQL(sql, true);
            if (rs.next()) {
                miCurso = new Curso(rs.getString("codigo"), rs.getString("nombre"));
            } else {

                throw new Exception("El estudiante no est� registrado.");
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return miCurso;
    }

    public List<Map<String, String>> listar() {
        List<Map<String, String>> datos = new ArrayList<>();

        try {

            String sql;
            java.sql.ResultSet rs;
            sql = "SELECT codigo,nombre FROM curso";

            rs = Conector.getConector().ejecutarSQL(sql, true);

            while (rs.next()) {

                Map<String, String> diccio = new HashMap<>();
                diccio.put("codigo", rs.getString("codigo"));
                diccio.put("nombre", rs.getString("nombre"));

                datos.add(diccio);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return datos;

    }

}
