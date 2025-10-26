/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {

    public List<Inscripcion> obtenerTodos() {
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT id, id_usuario, id_curso, fecha_inscripcion FROM inscripcion ORDER BY id";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Inscripcion(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_curso"),
                        rs.getDate("fecha_inscripcion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion (id_usuario, id_curso, fecha_inscripcion) VALUES (?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, insc.getIdUsuario());
            ps.setInt(2, insc.getIdCurso());
            ps.setDate(3, insc.getFecha());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM inscripcion WHERE id=?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Para listar inscripciones de un usuario (Alumno)
    public List<String> obtenerResumenPorUsuario(int idUsuario) {
        List<String> out = new ArrayList<>();
        String sql = """
            SELECT i.id, c.nombre AS curso, i.fecha_inscripcion
            FROM inscripcion i
            JOIN curso c ON c.id = i.id_curso
            WHERE i.id_usuario = ?
            ORDER BY i.id
        """;
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add("Inscripción #" + rs.getInt("id") +
                            " - Curso: " + rs.getString("curso") +
                            " - Fecha: " + rs.getDate("fecha_inscripcion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }
}
