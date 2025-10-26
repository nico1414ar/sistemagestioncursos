/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nico.gestion.gestioncyt;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class MenuAlumnoGUI extends JFrame {
    private final Usuario usuario;
    private final CursoDAO cursoDAO = new CursoDAO();
    private final InscripcionDAO inscripcionDAO = new InscripcionDAO();
    private final PagoDAO pagoDAO = new PagoDAO();
    private final AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
    private final CertificadoDAO certificadoDAO = new CertificadoDAO();

    public MenuAlumnoGUI(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Alumno - " + usuario.getNombre());
        setSize(480, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        JButton btnVerCursos = new JButton("Ver cursos disponibles");
        JButton btnInscribirme = new JButton("Inscribirme a un curso (ID)");
        JButton btnMisInscripciones = new JButton("Ver mis inscripciones");

        JButton btnMisPagos = new JButton("Ver mis pagos");
        JButton btnMiAsistencia = new JButton("Ver mi asistencia");
        JButton btnMisCertificados = new JButton("Ver mis certificados");

        btnVerCursos.addActionListener(e -> verCursos());
        btnInscribirme.addActionListener(e -> inscribirme());
        btnMisInscripciones.addActionListener(e -> verMisInscripciones());

        btnMisPagos.addActionListener(e -> verMisPagos());
        btnMiAsistencia.addActionListener(e -> verMiAsistencia());
        btnMisCertificados.addActionListener(e -> verMisCertificados());

        panel.add(btnVerCursos);
        panel.add(btnInscribirme);
        panel.add(btnMisInscripciones);
        panel.add(new JSeparator());
        panel.add(btnMisPagos);
        panel.add(btnMiAsistencia);
        panel.add(btnMisCertificados);

        setContentPane(panel);
    }

    private void verCursos() {
        List<Curso> lista = cursoDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder("Cursos disponibles:\n");
        for (Curso c : lista) sb.append(c).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void inscribirme() {
        String s = JOptionPane.showInputDialog(this, "Ingrese ID de Curso:");
        if (s == null) return;
        try {
            int idCurso = Integer.parseInt(s);
            Inscripcion i = new Inscripcion(usuario.getId(), idCurso, Date.valueOf(LocalDate.now()));
            int id = inscripcionDAO.agregar(i);
            JOptionPane.showMessageDialog(this, id > 0 ? "Inscripción creada ID: " + id : "No se pudo inscribir");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    private void verMisInscripciones() {
        List<String> resumen = inscripcionDAO.obtenerResumenPorUsuario(usuario.getId());
        if (resumen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tenés inscripciones aún.");
            return;
        }
        StringBuilder sb = new StringBuilder("Mis inscripciones:\n");
        for (String r : resumen) sb.append(r).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void verMisPagos() {
        List<Pago> pagos = pagoDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder("Mis pagos:\n");
        boolean tiene = false;
        for (Pago p : pagos) {
            // buscamos pagos de inscripciones de este usuario
            List<Inscripcion> ins = inscripcionDAO.obtenerTodos();
            for (Inscripcion i : ins) {
                if (i.getIdUsuario() == usuario.getId() && p.getIdInscripcion() == i.getId()) {
                    sb.append(p).append("\n");
                    tiene = true;
                }
            }
        }
        if (!tiene) sb.append("No se encontraron pagos.");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void verMiAsistencia() {
        List<Asistencia> asist = asistenciaDAO.obtenerTodas();
        StringBuilder sb = new StringBuilder("Mi asistencia:\n");
        boolean tiene = false;
        List<Inscripcion> ins = inscripcionDAO.obtenerTodos();
        for (Asistencia a : asist) {
            for (Inscripcion i : ins) {
                if (i.getIdUsuario() == usuario.getId() && a.getIdInscripcion() == i.getId()) {
                    sb.append(a).append("\n");
                    tiene = true;
                }
            }
        }
        if (!tiene) sb.append("No hay registros de asistencia.");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void verMisCertificados() {
        List<Certificado> certs = certificadoDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder("Mis certificados:\n");
        boolean tiene = false;
        List<Inscripcion> ins = inscripcionDAO.obtenerTodos();
        for (Certificado c : certs) {
            for (Inscripcion i : ins) {
                if (i.getIdUsuario() == usuario.getId() && c.getIdInscripcion() == i.getId()) {
                    sb.append(c).append("\n");
                    tiene = true;
                }
            }
        }
        if (!tiene) sb.append("No tenés certificados emitidos aún.");
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
