/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

public class Curso implements Identificable {
    private int id;
    private String nombre;
    private String descripcion;
    private int cupo;

    public Curso() {}

    public Curso(int id, String nombre, String descripcion, int cupo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cupo = cupo;
    }

    public Curso(String nombre, String descripcion, int cupo) {
        this(0, nombre, descripcion, cupo);
    }

    @Override
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getNombre() { 
        return nombre; 
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }

    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    public int getCupo() { 
        return cupo; 
    }

    public void setCupo(int cupo) { 
        this.cupo = cupo; 
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (cupo: " + cupo + ")";
    }
}
