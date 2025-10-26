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

public class MenuAdminGUI extends JFrame {
    private final Usuario usuario;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final CursoDAO cursoDAO = new CursoDAO();
    private final InscripcionDAO inscripcionDAO = new InscripcionDAO();
    private final PagoDAO pagoDAO = new PagoDAO();
    private final AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
    private final CertificadoDAO certificadoDAO = new CertificadoDAO();

    public MenuAdminGUI(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Administrador - " + usuario.getNombre());
        setSize(600, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));

        // ==== Usuarios ====
        JButton btnListUsuarios = new JButton("Listar usuarios");
        JButton btnCrearUsuario = new JButton("Crear alumno");
        JButton btnEliminarUsuario = new JButton("Eliminar usuario por ID");

        // ==== Cursos ====
        JButton btnListCursos = new JButton("Listar cursos");
        JButton btnCrearCurso = new JButton("Crear curso");
        JButton btnEliminarCurso = new JButton("Eliminar curso por ID");

        // ==== Inscripciones ====
        JButton btnListIns = new JButton("Listar inscripciones");
        JButton btnCrearIns = new JButton("Crear inscripción (idUsuario, idCurso)");
        JButton btnEliminarIns = new JButton("Eliminar inscripción por ID");

        // ==== Pagos ====
        JButton btnListPagos = new JButton("Listar pagos");
        JButton btnRegistrarPago = new JButton("Registrar pago");
        JButton btnEliminarPago = new JButton("Eliminar pago por ID");

        // ==== Asistencias ====
        JButton btnListAsist = new JButton("Listar asistencias");
        JButton btnRegistrarAsist = new JButton("Registrar asistencia");
        JButton btnEliminarAsist = new JButton("Eliminar asistencia por ID");

        // ==== Certificados ====
        JButton btnListCert = new JButton("Listar certificados");
        JButton btnEmitirCert = new JButton("Emitir certificado");
        JButton btnEliminarCert = new JButton("Eliminar certificado por ID");

        // ==== Asignación de acciones ====
        btnListUsuarios.addActionListener(e -> listarUsuarios());
        btnCrearUsuario.addActionListener(e -> crearAlumno());
        btnEliminarUsuario.addActionListener(e -> eliminarUsuario());

        btnListCursos.addActionListener(e -> listarCursos());
        btnCrearCurso.addActionListener(e -> crearCurso());
        btnEliminarCurso.addActionListener(e -> eliminarCurso());

        btnListIns.addActionListener(e -> listarInscripciones());
        btnCrearIns.addActionListener(e -> crearInscripcion());
        btnEliminarIns.addActionListener(e -> eliminarInscripcion());

        btnListPagos.addActionListener(e -> listarPagos());
        btnRegistrarPago.addActionListener(e -> registrarPago());
        btnEliminarPago.addActionListener(e -> eliminarPago());

        btnListAsist.addActionListener(e -> listarAsistencias());
        btnRegistrarAsist.addActionListener(e -> registrarAsistencia());
        btnEliminarAsist.addActionListener(e -> eliminarAsistencia());

        btnListCert.addActionListener(e -> listarCertificados());
        btnEmitirCert.addActionListener(e -> emitirCertificado());
        btnEliminarCert.addActionListener(e -> eliminarCertificado());

        // ==== Construcción visual ====
        panel.add(new JLabel("=== Gestión de Usuarios ==="));
        panel.add(btnListUsuarios);
        panel.add(btnCrearUsuario);
        panel.add(btnEliminarUsuario);
        panel.add(new JSeparator());

        panel.add(new JLabel("=== Gestión de Cursos ==="));
        panel.add(btnListCursos);
        panel.add(btnCrearCurso);
        panel.add(btnEliminarCurso);
        panel.add(new JSeparator());

        panel.add(new JLabel("=== Inscripciones ==="));
        panel.add(btnListIns);
        panel.add(btnCrearIns);
        panel.add(btnEliminarIns);
        panel.add(new JSeparator());

        panel.add(new JLabel("=== Pagos ==="));
        panel.add(btnListPagos);
        panel.add(btnRegistrarPago);
        panel.add(btnEliminarPago);
        panel.add(new JSeparator());

        panel.add(new JLabel("=== Asistencias ==="));
        panel.add(btnListAsist);
        panel.add(btnRegistrarAsist);
        panel.add(btnEliminarAsist);
        panel.add(new JSeparator());

        panel.add(new JLabel("=== Certificados ==="));
        panel.add(btnListCert);
        panel.add(btnEmitirCert);
        panel.add(btnEliminarCert);

        setContentPane(new JScrollPane(panel));
    }

    // ==== Gestión de Usuarios ====
    private void listarUsuarios() {
        List<Usuario> lista = usuarioDAO.listarTodos();
        StringBuilder sb = new StringBuilder("Usuarios:\n");
        for (Usuario u : lista) sb.append(u).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void crearAlumno() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        if (nombre == null) return;
        String apellido = JOptionPane.showInputDialog(this, "Apellido:");
        if (apellido == null) return;
        String email = JOptionPane.showInputDialog(this, "Email:");
        if (email == null) return;
        String pass = JOptionPane.showInputDialog(this, "Password:");
        if (pass == null) return;
        Usuario u = new Usuario(nombre, apellido, email, pass, "Alumno");
        int id = usuarioDAO.crear(u);
        JOptionPane.showMessageDialog(this, id > 0 ? "Alumno creado ID: " + id : "Error al crear");
    }

    private void eliminarUsuario() {
        String s = JOptionPane.showInputDialog(this, "ID usuario a eliminar:");
        if (s == null) return;
        try {
            int id = Integer.parseInt(s);
            JOptionPane.showMessageDialog(this, usuarioDAO.eliminar(id) ? "Eliminado" : "Error al eliminar");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    // ==== Gestión de Cursos ====
    private void listarCursos() {
        List<Curso> lista = cursoDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder("Cursos:\n");
        for (Curso c : lista) sb.append(c).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void crearCurso() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del curso:");
        if (nombre == null) return;
        String desc = JOptionPane.showInputDialog(this, "Descripción:");
        if (desc == null) return;
        String scupo = JOptionPane.showInputDialog(this, "Cupo máximo:");
        if (scupo == null) return;
        try {
            int cupo = Integer.parseInt(scupo);
            int id = cursoDAO.agregar(new Curso(nombre, desc, cupo));
            JOptionPane.showMessageDialog(this, id > 0 ? "Curso creado ID: " + id : "Error al crear");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cupo inválido");
        }
    }

    private void eliminarCurso() {
        String s = JOptionPane.showInputDialog(this, "ID curso a eliminar:");
        if (s == null) return;
        try {
            int id = Integer.parseInt(s);
            JOptionPane.showMessageDialog(this, cursoDAO.eliminar(id) ? "Eliminado" : "Error al eliminar");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    // ==== Gestión de Inscripciones ====
    private void listarInscripciones() {
        List<Inscripcion> lista = inscripcionDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder("Inscripciones:\n");
        for (Inscripcion i : lista)
            sb.append("ID ").append(i.getId())
              .append(" - Usuario#").append(i.getIdUsuario())
              .append(" - Curso#").append(i.getIdCurso())
              .append(" - Fecha ").append(i.getFecha()).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void crearInscripcion() {
        String su = JOptionPane.showInputDialog(this, "ID Usuario:");
        if (su == null) return;
        String sc = JOptionPane.showInputDialog(this, "ID Curso:");
        if (sc == null) return;
        try {
            int idU = Integer.parseInt(su);
            int idC = Integer.parseInt(sc);
            int id = inscripcionDAO.agregar(new Inscripcion(idU, idC, Date.valueOf(LocalDate.now())));
            JOptionPane.showMessageDialog(this, id > 0 ? "Inscripción creada ID: " + id : "Error al crear");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "IDs inválidos");
        }
    }

    private void eliminarInscripcion() {
        String s = JOptionPane.showInputDialog(this, "ID de inscripción a eliminar:");
        if (s == null) return;
        try {
            int id = Integer.parseInt(s);
            JOptionPane.showMessageDialog(this, inscripcionDAO.eliminar(id) ? "Eliminada" : "Error al eliminar");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    // ==== Pagos ====
    private void listarPagos() {
        List<Pago> lista = pagoDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder("Pagos:\n");
        for (Pago p : lista) sb.append(p).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void registrarPago() {
        try {
            int idIns = Integer.parseInt(JOptionPane.showInputDialog(this, "ID Inscripción:"));
            double monto = Double.parseDouble(JOptionPane.showInputDialog(this, "Monto:"));
            String estado = JOptionPane.showInputDialog(this, "Estado (confirmado/pendiente):");
            int id = pagoDAO.agregar(new Pago(idIns, monto, Date.valueOf(LocalDate.now()), estado));
            JOptionPane.showMessageDialog(this, id > 0 ? "Pago registrado ID: " + id : "Error al registrar");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void eliminarPago() {
        String s = JOptionPane.showInputDialog(this, "ID pago a eliminar:");
        if (s == null) return;
        try {
            int id = Integer.parseInt(s);
            JOptionPane.showMessageDialog(this, pagoDAO.eliminar(id) ? "Eliminado" : "Error al eliminar");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    // ==== Asistencias ====
    private void listarAsistencias() {
        List<Asistencia> lista = asistenciaDAO.obtenerTodas();
        StringBuilder sb = new StringBuilder("Asistencias:\n");
        for (Asistencia a : lista) sb.append(a).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void registrarAsistencia() {
        try {
            int idIns = Integer.parseInt(JOptionPane.showInputDialog(this, "ID Inscripción:"));
            boolean presente = JOptionPane.showConfirmDialog(this, "¿Presente?") == JOptionPane.YES_OPTION;
            int id = asistenciaDAO.agregar(new Asistencia(idIns, Date.valueOf(LocalDate.now()), presente));
            JOptionPane.showMessageDialog(this, id > 0 ? "Asistencia registrada ID: " + id : "Error al registrar");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void eliminarAsistencia() {
        String s = JOptionPane.showInputDialog(this, "ID asistencia a eliminar:");
        if (s == null) return;
        try {
            int id = Integer.parseInt(s);
            JOptionPane.showMessageDialog(this, asistenciaDAO.eliminar(id) ? "Eliminada" : "Error al eliminar");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    // ==== Certificados ====
    private void listarCertificados() {
        List<Certificado> lista = certificadoDAO.obtenerTodos();
        StringBuilder sb = new StringBuilder("Certificados:\n");
        for (Certificado c : lista) sb.append(c).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void emitirCertificado() {
        try {
            int idIns = Integer.parseInt(JOptionPane.showInputDialog(this, "ID Inscripción:"));
            String archivo = JOptionPane.showInputDialog(this, "Nombre del archivo (ej: certificado1.pdf):");
            int id = certificadoDAO.agregar(new Certificado(idIns, Date.valueOf(LocalDate.now()), archivo));
            JOptionPane.showMessageDialog(this, id > 0 ? "Certificado emitido ID: " + id : "Error al emitir");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void eliminarCertificado() {
        String s = JOptionPane.showInputDialog(this, "ID certificado a eliminar:");
        if (s == null) return;
        try {
            int id = Integer.parseInt(s);
            JOptionPane.showMessageDialog(this, certificadoDAO.eliminar(id) ? "Eliminado" : "Error al eliminar");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }
}
