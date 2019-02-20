/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;

import AccesoBD.Conector;



/**
 *
 * @author Christian Rodriguez
 */
public class MultiEstudiante {

    public Estudiante agregar(String nombre, String carnet, String clave, String telefono, String email) throws Exception {
        Estudiante miEstudi = null;
        String estado = "activo";
        String sql;
        sql = "INSERT INTO Estudiante "
                + "(carnet, nombre,clave,estado,email,telefono) "
                + "VALUES ('" + carnet + "', '" + nombre + "','" + clave + "','" + estado + "','" + email + "','" + telefono + "');";
        try {
            Conector.getConector().ejecutarSQL(sql);
            miEstudi = new Estudiante(nombre, carnet, clave, telefono, email);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return miEstudi;
    }

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

    public void actualizar(Estudiante pcarnet) throws
            java.sql.SQLException, Exception {
        String sql;
        sql = "UPDATE Estudiante "
                + "SET nombre='" + pcarnet.getNombre() + "'"
                + ", Telefono='" + pcarnet.getTelefono() + "'"
                + ", email='" + pcarnet.getEmail() + "'"
                + "WHERE carnet='" + pcarnet.getCarnet() + "';";
        try {
            Conector.getConector().ejecutarSQL(sql);
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

}
