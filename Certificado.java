/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import java.sql.Date;

public class Certificado {
    private int id;
    private int idInscripcion; // FK hacia inscripcion
    private Date fechaEmision;
    private String archivo; // nombre o ruta del PDF generado

    public Certificado() {}

    public Certificado(int id, int idInscripcion, Date fechaEmision, String archivo) {
        this.id = id;
        this.idInscripcion = idInscripcion;
        this.fechaEmision = fechaEmision;
        this.archivo = archivo;
    }

    public Certificado(int idInscripcion, Date fechaEmision, String archivo) {
        this(0, idInscripcion, fechaEmision, archivo);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdInscripcion() { return idInscripcion; }
    public void setIdInscripcion(int idInscripcion) { this.idInscripcion = idInscripcion; }

    public Date getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }

    public String getArchivo() { return archivo; }
    public void setArchivo(String archivo) { this.archivo = archivo; }

    @Override
    public String toString() {
        return "Certificado #" + id + " - Inscripción " + idInscripcion + " - Fecha: " + fechaEmision;
    }
}
