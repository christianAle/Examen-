/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import CapaLogica.Gestor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author Christian Rodriguez
 */
public class UI {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static Gestor c = new Gestor();

    public static void main(String[] args) throws IOException, Exception {
        int opc;
        boolean noSalir = true;

        do {
            mostrarMenu();
            opc = leerOpcion();
            noSalir = ejecutarAccion(opc);
        } while (noSalir);

    }

    static void mostrarMenu() {
        out.println();
        out.println("Menu de opciones del Sistema");
        out.println();
        out.println("1.  Inciar Sesisión.");
        out.println("2.  Registrarse .");

        out.println("3.  Salir del Sistema.");
        out.println();
    }

    static int leerOpcion() throws IOException {
        int opcion;

        out.print("Selecciones su opcion: ");
        opcion = Integer.parseInt(in.readLine());
        out.println();

        return opcion;
    }

    public static boolean ejecutarAccion(int popcion) throws IOException, Exception {
        boolean noSalir = true;
        boolean validacion = true;

        switch (popcion) {
            case 1:
                validacion = iniciarSesion();
////                if (validacion == false) {
////                    mostrarMenuCliente();
////                }

                break;

            case 2:
                registrarEstudiante();
                break;

            case 3:
                noSalir = false;
                break;
            default:
                out.println("Opcion Invalidad");
                out.println();
                break;
        }
        return noSalir;
    }

    public static boolean iniciarSesion() throws IOException, Exception {
        String carnet, clave;
        boolean validacion = true;
        boolean validar = true;
        out.println("Digite su carnet ");
        carnet = in.readLine();
        out.println("Digite la clave");
        clave = in.readLine();

        try {
            TreeMap buscarEstudiante = c.estudianteBuscar(carnet, clave);
            if (buscarEstudiante == null) {
                out.println("Datos incorrectos");
                mostrarMenu();
            }
            if (buscarEstudiante != null) {
                validar = validacionPrimerRegistro(carnet, clave);
                if (validar == true) {
                    out.println("Bienvenido");
                    actualizarDatos(carnet, clave);
                } else {
                    out.println("Bienvenido");

                    menuSistemaPrematricula(carnet, clave);
                }

                validacion = false;
            }

        } catch (Exception e) {

        }

        return validacion;
    }

    public static boolean validacionPrimerRegistro(String carnet, String clave) {
        boolean validar = true;
        try {
            TreeMap buscarEstudiante = c.verificarPrematricula(carnet);

            if (buscarEstudiante == null) {
                validar = true;
            } else {
                // menuSistemaPrematricula(carnet, clave);
                validar = false;
            }
        } catch (Exception e) {

        }

        return validar;
    }

    public static void registrarEstudiante() throws IOException, Exception {

        String nombre;
        String carnet;
        String clave;
        String telefono;
        String email;

        out.println("Digite nombre completo");
        nombre = in.readLine();

        out.println("Digite su carnet");
        carnet = in.readLine();

        out.println("Digite la clave con que va ingresar al sistema ");
        clave = in.readLine();

        out.println("Digite su numero de telefono ");
        telefono = in.readLine();

        out.println("Digite su correo");
        email = in.readLine();

        c.estudianteAgregar(nombre, carnet, clave, telefono, email);

    }

    public static void menuSistemaPrematricula(String carnet, String clave) throws IOException, Exception {
        int opc;
        boolean noSalir = true;

        do {
            mostrarMenuEstudiante();
            opc = leerOpcionEstudiante();
            noSalir = ejecutarAccionEstudiante(opc, carnet, clave);
        } while (noSalir);

    }

    public static void mostrarMenuEstudiante() {

        out.println();
        out.println(" Sistema de prematrícula");
        out.println();

        out.println("1.  Actualizar datos personales.");
        out.println("2.  Prematicular curso");
        out.println("3.  Cerrar Sesión.");

        out.println();
    }

    static int leerOpcionEstudiante() throws IOException {
        int opcion;

        out.print("Seleccione una opcion: ");
        opcion = Integer.parseInt(in.readLine());
        out.println();

        return opcion;
    }

    public static boolean ejecutarAccionEstudiante(int popcion, String carnet, String clave) throws IOException, Exception {
        boolean noSalir = true;
        switch (popcion) {
            case 1:
                actualizarDatos(carnet, clave);

                break;

            case 2:

                prematricular(carnet, clave);
                break;

            case 3:
                noSalir = false;
                break;
            default:
                out.println("Opcion Invalidad");
                out.println();
                break;
        }
        return noSalir;
    }

    public static void actualizarDatos(String carnet, String clave) throws IOException, Exception {
        out.println("Para continuar tiene que actualizar sus datos");

        String nombre;
        String telefono;
        String email;

        out.println("Digite nombre a cambiar");
        nombre = in.readLine();

        out.println("Digite su numero de telefono ");
        telefono = in.readLine();

        out.println("Digite su correo");
        email = in.readLine();

        try {

            c.estudianteModificar(carnet, clave, nombre, telefono, email);
            menuSistemaPrematricula(carnet, clave);
        } catch (Exception e) {
            out.println(e.getMessage());

        }

    }

    public static void prematricular(String carnet, String clave) throws Exception {
        boolean validar = true;
        List<Map<String, String>> datos = c.listarCurso();
        List<Map<String, String>> matriculados = c.listarprematriculados(carnet);
        validar = validacionPrimerRegistro(carnet, clave);

        out.println("Lista de Cursos");
        for (Map<String, String> lista : datos) {

            out.println("Codigo :" + " " + lista.get("codigo") + " , " + "Nombre: " + lista.get("nombre"));

        }

        if (validar == true) {
            out.println("Por el momento no tiene cursos prematriculados");
        } else {
            out.println("Lista de Cursos prematrículados");
            for (Map<String, String> listaMatri : matriculados) {

                out.println("Carnet estudiante " + listaMatri.get("carnet") + "," + "Codigo curso: " + listaMatri.get("codigo") + "," + " Horario "
                        + listaMatri.get("horario") + "," + "Nombre del curso : " + listaMatri.get("nombre"));

            }
        }
        menuPrema(carnet, clave);

    }

    public static void menuPrema(String carnet, String clave) throws IOException, Exception {
        int opc;
        boolean noSalir = true;

        do {
            mosubmenu();
            opc = submenuOpciones(carnet, clave);
            noSalir = leerOpcinCurso(opc, carnet, clave);
        } while (noSalir);

    }

    public static void mosubmenu() {

        out.println("1. Agregar Curso");
        out.println("2. Eliminar Curso");
        out.println("3. Volver");
        out.println();
    }

    static int submenuOpciones(String carnet, String clave) throws IOException, Exception {
        int opc;

        out.println("Digite una opcion");
        opc = Integer.parseInt(in.readLine());

        return opc;
    }

    public static boolean leerOpcinCurso(int opc, String carnet, String clave) throws IOException, Exception {
        boolean noSalir = true;
        switch (opc) {
            case 1:
                agregarCurso(carnet, clave);
                break;
            case 2:
                eliminarCurso(carnet, clave);
                break;
            case 3:
                noSalir = false;
                //   menuSistemaPrematricula(carnet, clave);
                break;
            default:
                out.println("Opcion Invalida");
                out.println();
                break;
        }
        return noSalir;
    }

    public static void agregarCurso(String carnet, String clave) throws Exception {

        String codigo = "";
        String curso = "";
        String horario;
        int opc;
        boolean repetido = true;

        List<Map<String, String>> datos = c.listarCurso();

        out.println("Lista de Cursos");
        for (Map<String, String> lista : datos) {

            out.println(" " + lista.get("codigo") + " , " + "Nombre: " + lista.get("nombre"));

        }

        out.println("Seleccione el curso que desea prematricular");
        opc = Integer.parseInt(in.readLine());
        switch (opc) {
            case 1:
                codigo = "1";
                curso = "Fundamentos de Programacion";
                break;

            case 2:
                codigo = "2";
                curso = "Introduccion a TI";
                break;
            case 3:
                codigo = "3";
                curso = "Ingles 1";
                break;
            case 4:
                codigo = "4";
                curso = "Proyecto software 1";
                break;

            case 5:
                codigo = "5";
                curso = " Estructuras";
                break;

            default:
                out.println("Opcion Invalida");
                out.println();
                break;

        }

        horario = horarios();
        repetido = validarRepetidos(codigo);

        if (repetido == false) {
            out.println("Ya tiene el curso prematriculado");
        } else {

            c.prematricular(carnet, clave, codigo, horario);

        }

    }

    public static String horarios() throws IOException {
        int opc;

        String horario = "";

        out.println("1.Mañana");
        out.println("2.Tarde");
        out.println("3.Noche");

        out.println("Seleccione un horario");
        opc = Integer.parseInt(in.readLine());

        switch (opc) {
            case 1:
                horario = "M";
                break;
            case 2:
                horario = "T";
                break;
            case 3:
                horario = "N";
                break;
            default:
                out.println("Opcion Invalida");
                out.println();
                break;

        }
        return horario;
    }

    public static void eliminarCurso(String carnet, String clave) throws Exception {

        List<Map<String, String>> matriculados = c.listarprematriculados(carnet);
        String codigo = "";
        String curso;
        int opc;
        boolean repetido = true;

        out.println("Lista de Cursos prematrículados");
        for (Map<String, String> listaMatri : matriculados) {

            out.println(listaMatri.get("codigo") + ". " + "Carnet estudiante " + listaMatri.get("carnet") + "," + "Codigo curso: " + listaMatri.get("codigo") + "," + " Horario "
                    + listaMatri.get("horario") + "," + "Nombre del curso : " + listaMatri.get("nombre"));

        }
        out.println("Seleccione el curso que desea eliminar de la prematricula");

        opc = Integer.parseInt(in.readLine());
        switch (opc) {
            case 1:
                codigo = "1";
                curso = "Fundamentos de Programacion";
                break;

            case 2:
                codigo = "2";
                curso = "Introduccion a TI";
                break;
            case 3:
                codigo = "3";
                curso = "Ingles 1";
                break;
            case 4:
                codigo = "4";
                curso = "Proyecto software 1";
                break;

            case 5:
                codigo = "5";
                curso = " Estructuras";
                break;

            default:
                out.println("Opcion Invalida");
                out.println();
                break;

        }
        try {

            repetido = validarRepetidos(codigo);

            if (repetido == false) {
              c.borrarPrematicula(codigo, carnet);
            } else {
                out.println("El curso no está prematrículado");

            }

          //  out.println("1" + codigo);
        } catch (Exception e) {

        }

    }

    public static boolean validarRepetidos(String codigo) {
        boolean validar = true;
        try {
            TreeMap buscarCurso = c.buscarCurso(codigo);

            if (buscarCurso == null) {
                validar = true;
            } else {
                // menuSistemaPrematricula(carnet, clave);
                validar = false;
            }
        } catch (Exception e) {

        }

        return validar;
    }

}
