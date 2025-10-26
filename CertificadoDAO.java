/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificadoDAO {

    public List<Certificado> obtenerTodos() {
        List<Certificado> lista = new ArrayList<>();
        String sql = "SELECT id, id_inscripcion, fecha_emision, archivo FROM certificado ORDER BY id";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Certificado(
                        rs.getInt("id"),
                        rs.getInt("id_inscripcion"),
                        rs.getDate("fecha_emision"),
                        rs.getString("archivo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Certificado cert) {
        String sql = "INSERT INTO certificado (id_inscripcion, fecha_emision, archivo) VALUES (?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cert.getIdInscripcion());
            ps.setDate(2, cert.getFechaEmision());
            ps.setString(3, cert.getArchivo());
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
        String sql = "DELETE FROM certificado WHERE id=?";
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
