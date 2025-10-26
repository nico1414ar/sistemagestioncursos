/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import java.sql.Date;

public class Pago {
    private int id;
    private int idInscripcion; // FK hacia inscripcion
    private double monto;
    private Date fechaPago;
    private String estado; // confirmado / pendiente

    public Pago() {}

    public Pago(int id, int idInscripcion, double monto, Date fechaPago, String estado) {
        this.id = id;
        this.idInscripcion = idInscripcion;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.estado = estado;
    }

    public Pago(int idInscripcion, double monto, Date fechaPago, String estado) {
        this(0, idInscripcion, monto, fechaPago, estado);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdInscripcion() { return idInscripcion; }
    public void setIdInscripcion(int idInscripcion) { this.idInscripcion = idInscripcion; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Pago #" + id + " - Inscripción " + idInscripcion + " - $" + monto + " - " + estado;
    }
}
