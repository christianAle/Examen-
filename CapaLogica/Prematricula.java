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
public class Prematricula {

    private String horario;
    private Curso miCurso;
    private Estudiante miEstudiante;
    private String codigoCurso;

    public Prematricula() {

    }

    public Prematricula(String horario) throws Exception {
        this.horario = horario;
        miCurso = getCurso();
        miEstudiante = null;
    }
    
    public Prematricula(String horario,Curso pCurso){
      this.horario = horario;
       this.miCurso = pCurso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Curso getMiCurso() {
        return miCurso;
    }

    public void setMiCurso(Curso miCurso) {
        this.miCurso = miCurso;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }
    public void setCodigoCurso(String pcodigoCurso){
       this.codigoCurso=pcodigoCurso;
    }

    public void CodigoCurso(String pcodigoCurso) {
        codigoCurso = pcodigoCurso;
    }

    public Curso getCurso() throws Exception {
        if (miCurso == null) {
            setCurso((new MultiCurso()).buscarCurso(codigoCurso));
        }
        return miCurso;
    }

    public void setCurso(Curso pCodigo) {
        miCurso = pCodigo;
    }

}
