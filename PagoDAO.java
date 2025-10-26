/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    public List<Pago> obtenerTodos() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT id, id_inscripcion, monto, fecha_pago, estado FROM pago ORDER BY id";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Pago(
                        rs.getInt("id"),
                        rs.getInt("id_inscripcion"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha_pago"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int agregar(Pago pago) {
        String sql = "INSERT INTO pago (id_inscripcion, monto, fecha_pago, estado) VALUES (?,?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pago.getIdInscripcion());
            ps.setDouble(2, pago.getMonto());
            ps.setDate(3, pago.getFechaPago());
            ps.setString(4, pago.getEstado());
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
        String sql = "DELETE FROM pago WHERE id=?";
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
