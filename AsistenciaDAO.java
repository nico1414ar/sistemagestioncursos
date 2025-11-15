/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO {

    public List<Asistencia> obtenerTodas() {
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT id, id_inscripcion, fecha, presente FROM asistencia ORDER BY id";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Asistencia(
                        rs.getInt("id"),
                        rs.getInt("id_inscripcion"),
                        rs.getDate("fecha"),
                        rs.getBoolean("presente")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Asistencia a) {
        String sql = "INSERT INTO asistencia (id_inscripcion, fecha, presente) VALUES (?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, a.getIdInscripcion());
            ps.setDate(2, a.getFechaClase());
            ps.setBoolean(3, a.isPresente());
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
        String sql = "DELETE FROM asistencia WHERE id=?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
