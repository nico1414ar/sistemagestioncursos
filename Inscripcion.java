/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import java.sql.Date;

public class Inscripcion implements Identificable {
    private int id;
    private int idUsuario;
    private int idCurso;
    private Date fecha;

    public Inscripcion() {}

    public Inscripcion(int id, int idUsuario, int idCurso, Date fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCurso = idCurso;
        this.fecha = fecha;
    }

    public Inscripcion(int idUsuario, int idCurso, Date fecha) {
        this(0, idUsuario, idCurso, fecha);
    }

    @Override
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public int getIdUsuario() { 
        return idUsuario; 
    }

    public void setIdUsuario(int idUsuario) { 
        this.idUsuario = idUsuario; 
    }

    public int getIdCurso() { 
        return idCurso; 
    }

    public void setIdCurso(int idCurso) { 
        this.idCurso = idCurso; 
    }

    public Date getFecha() { 
        return fecha; 
    }

    public void setFecha(Date fecha) { 
        this.fecha = fecha; 
    }
}
