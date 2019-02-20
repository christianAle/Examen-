/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaLogica;


import AccesoBD.Conector;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Christian
 */
public class MultiPrematricula {

    /**
     *
     * @param carnet
     * @param codigo
     * @param horario
     * @return
     * @throws Exception
     */
    public Prematricula agregar(String carnet, String codigo, String horario) throws Exception {
        Prematricula prema = null;
        String sql;
        sql = "INSERT INTO prematricula "
                + "(carnet,codigo,horario) "
                + "VALUES ('" + carnet + "', '" + codigo + "','" + horario + "');";
        try {
            Conector.getConector().ejecutarSQL(sql);
            prema = new Prematricula(horario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("El curso  ya est� en el sistema.");
        }
        return prema;
    }

    /**
     *
     * @param carnet
     * @param clave
     * @return
     * @throws SQLException
     * @throws Exception
     *
     * metodo para buscar si el estudiante esta previamente prematriculado,para
     * direccionarlo a un menu especifico
     */
    public Estudiante buscarEstudiante(String carnet) throws
            java.sql.SQLException, Exception {
        Estudiante estu = null;

        try {
            java.sql.ResultSet rs;
            String sql;
            sql = "SELECT carnet "
                    + "FROM prematricula "
                    + "WHERE carnet='" + carnet + "';";
            rs = Conector.getConector().ejecutarSQL(sql, true);
            if (rs.next()) {
                estu = new Estudiante(rs.getString("carnet"));
            } else {

                estu = null;
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return estu;
    }

    public List<Map<String, String>> listar(String carnet) {
        List<Map<String, String>> datos = new ArrayList<>();

        try {

            String sql;
            java.sql.ResultSet rs;
            sql = "SELECT p.carnet,p.codigo,p.horario,c.nombre "
                    + "FROM prematricula p "
                    + "JOIN curso c on (p.codigo=c.codigo) "
                    + "WHERE carnet='" + carnet + "';";
            rs = Conector.getConector().ejecutarSQL(sql, true);

            while (rs.next()) {

                Map<String, String> diccio = new HashMap<>();
                diccio.put("carnet", rs.getString("carnet"));
                diccio.put("codigo", rs.getString("codigo"));
                diccio.put("horario", rs.getString("horario"));
                diccio.put("nombre", rs.getString("nombre"));

                datos.add(diccio);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return datos;

    }

    public Curso buscarCurso(String codigo) {

        Curso cur = null;

        try {
            java.sql.ResultSet rs;
            String sql;

            sql = "SELECT codigo "
                    + "FROM prematricula "
                    + "WHERE codigo='" + codigo + "';";

            rs = Conector.getConector().ejecutarSQL(sql, true);
            if (rs.next()) {
                cur = new Curso(rs.getString("codigo"));
            } else {

                cur = null;
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cur;

    }

    public Prematricula buscarPre(String codigo, String carnet) {
        Prematricula pre = null;

        try {
            java.sql.ResultSet rs;
            String sql;

            sql = "SELECT codigo,carnet,horaio "
                    + "FROM prematricula "
                    + "WHERE codigo='" + codigo + "' and  carnet='" + carnet + "'  ;";
           
           
            rs = Conector.getConector().ejecutarSQL(sql, true);
            if (rs.next()) {
                
                pre = new Prematricula(rs.getString("horario"));
               
            } else {

                pre = null;
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pre;

    }

    public void borrar(Prematricula pre) throws
            java.sql.SQLException, Exception {
        String sql, sql2;
        
         System.out.println( pre.getCodigoCurso());
        sql2 = "DELETE FROM prematricula WHERE codigo='" + pre.getCodigoCurso() + "';";

         sql = "DELETE FROM prematricula WHERE codigo = "
                    + pre.getCodigoCurso() + ";";
        
       
        try {
            Conector.getConector().ejecutarSQL(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }


}
