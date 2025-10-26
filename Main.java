/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        if (!Conexion.probarConexion()) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "No se pudo conectar a MySQL.\n" +
                "Verifica que MySQL esté activo y que la base 'gestion_cursos' exista.",
                "Error de conexión", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }
}
