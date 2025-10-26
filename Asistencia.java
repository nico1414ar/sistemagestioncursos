/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import java.sql.Date;

public class Asistencia {
    private int id;
    private int idInscripcion;  
    private Date fechaClase;
    private boolean presente;

    public Asistencia() {}

    public Asistencia(int id, int idInscripcion, Date fechaClase, boolean presente) {
        this.id = id;
        this.idInscripcion = idInscripcion;
        this.fechaClase = fechaClase;
        this.presente = presente;
    }

    public Asistencia(int idInscripcion, Date fechaClase, boolean presente) {
        this(0, idInscripcion, fechaClase, presente);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdInscripcion() { return idInscripcion; }
    public void setIdInscripcion(int idInscripcion) { this.idInscripcion = idInscripcion; }

    public Date getFechaClase() { return fechaClase; }
    public void setFechaClase(Date fechaClase) { this.fechaClase = fechaClase; }

    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }

    @Override
    public String toString() {
        return "Asistencia #" + id + " - Inscripción " + idInscripcion + " - Fecha: " + fechaClase +
                " - Presente: " + (presente ? "Sí" : "No");
    }
}

