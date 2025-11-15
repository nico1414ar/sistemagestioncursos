/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class LoginGUI extends JFrame {
    private final JTextField txtEmail = new JTextField(20);
    private final JPasswordField txtPass = new JPasswordField(20);
    private final JButton btnLogin = new JButton("Iniciar sesión");

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LoginGUI() {
        setTitle("Gestión Cursos - Login");
        setSize(380, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; 
        gbc.gridy = 0; 
        p.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; 
        p.add(txtEmail, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 1; 
        p.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; 
        p.add(txtPass, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 2; 
        gbc.gridwidth = 2;
        btnLogin.addActionListener(this::login);
        p.add(btnLogin, gbc);

        setContentPane(p);
    }

    private void login(ActionEvent e) {
        String email = txtEmail.getText().trim();
        String pass = new String(txtPass.getPassword());

        if (email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete email y contraseña");
            return;
        }

        Usuario u = usuarioDAO.autenticar(email, pass);
        if (u == null) {
            JOptionPane.showMessageDialog(this, "Credenciales inválidas");
            return;
        }

        JOptionPane.showMessageDialog(this, "Bienvenido " + u.getNombre() + " (" + u.getRol() + ")");

        // ===== Registro en archivo de texto (log de sesiones) =====
        try (FileWriter fw = new FileWriter("log_sesiones.txt", true)) {
            fw.write("Login de: " + u.getEmail() + " - Rol: " + u.getRol() + System.lineSeparator());
        } catch (IOException ex) {
            ex.printStackTrace();
            // No detenemos el programa si falla el log
        }
        // ==========================================================

        this.dispose();

        if ("Administrador".equalsIgnoreCase(u.getRol())) {
            new MenuAdminGUI(u).setVisible(true);
        } else {
            new MenuAlumnoGUI(u).setVisible(true);
        }
    }
}
