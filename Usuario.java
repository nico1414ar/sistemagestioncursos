/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

public class Usuario implements Identificable {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol; // "Administrador" | "Alumno"

    // Arreglo de roles válidos (uso de arreglos para el TPN4)
    public static final String[] ROLES_VALIDOS = {"Administrador", "Alumno"};

    public Usuario() {}

    public Usuario(int id, String nombre, String apellido, String email, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String nombre, String apellido, String email, String password, String rol) {
        this(0, nombre, apellido, email, password, rol);
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

    public String getApellido() { 
        return apellido; 
    }

    public void setApellido(String apellido) { 
        this.apellido = apellido; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getPassword() { 
        return password; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getRol() { 
        return rol; 
    }

    public void setRol(String rol) { 
        this.rol = rol; 
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " " + apellido + " (" + rol + ")";
    }
}
