/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.LibroDao;
import ec.edu.ups.biblioteca.dao.PrestamoDao;
import ec.edu.ups.biblioteca.dao.UsuarioDao;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.models.Prestamo;
import ec.edu.ups.biblioteca.models.Usuario;
import ec.edu.ups.biblioteca.view.PrestamoCrearView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoController {

    private PrestamoDao prestamoDao;
    private UsuarioDao usuarioDao;
    private LibroDao libroDao;
    private PrestamoCrearView prestamoCrearView;

    private Usuario usuarioSeleccionado;
    private List<Libro> librosSeleccionados;

    public PrestamoController(PrestamoCrearView prestamoCrearView, PrestamoDao prestamoDao,
            UsuarioDao usuarioDao, LibroDao libroDao) {

        this.prestamoCrearView = prestamoCrearView;
        this.prestamoDao = prestamoDao;
        this.usuarioDao = usuarioDao;
        this.libroDao = libroDao;
        this.librosSeleccionados = new ArrayList<>();

        cargarLibrosDisponibles();
        idCodigo();
        configurarEventoPrestamoCrear();
    }
    // METODO PAR LA ID
    private void idCodigo() {
        int siguienteCodigo = prestamoDao.listar().size() + 1;
        // simplemente crea la ID de los prestamos
        prestamoCrearView.getTxtCodigoPrestamoCrear().setText(String.valueOf(siguienteCodigo));
    }
    // METODO PARA CREAR LOS LIBROS
    private void cargarLibrosDisponibles() {
        List<Libro> libros = libroDao.listar();
        javax.swing.DefaultComboBoxModel<String> modelo = new javax.swing.DefaultComboBoxModel<>();
        // el parantecis sirve para poder definir el tipo de variable antes de poner (un casteo)
        // el tipo javax.swing.blabla es el tipo del cual necesitamos lo metodos para hacerlo mas comodo 
        // de hacer la agragacion de los metodos
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                modelo.addElement(libro.getIsbn() + " - " + libro.getTitulo());
            }
        }
        prestamoCrearView.getCbxLibrosListaPrestamoCrear().setModel(modelo);
        // esto es para poder poner a la box el modelo que creamos
    }

    // METODO PARA BUSCAR EL LIBRO 
    public void buscarUsuario() {
        String cedula = prestamoCrearView.getTxtCedulaPrestamoCrear().getText();

        if (cedula.isEmpty()) {
            prestamoCrearView.mostarMensaje("Debe ingresar una cédula");
            return;
        }

        Usuario usuario = usuarioDao.buscar(cedula);

        if (usuario != null) {
            usuarioSeleccionado = usuario;
            prestamoCrearView.getTxtNombrePrestamoCrear().setText(usuario.getNombre());
            prestamoCrearView.getTxtTelefonoPrestamoCrear().setText(usuario.getNumero());
        } else {
            usuarioSeleccionado = null;
            prestamoCrearView.getTxtNombrePrestamoCrear().setText("");
            prestamoCrearView.getTxtTelefonoPrestamoCrear().setText("");
            prestamoCrearView.mostarMensaje("No se encontró un usuario con esa cédula");
        }
    }

    // METODO PARA AGREGAR EL LIBRO AL USUARIO 
    public void agregarLibro() {
        String seleccionado = (String) prestamoCrearView.getCbxLibrosListaPrestamoCrear().getSelectedItem();

        if (seleccionado == null) {
            prestamoCrearView.mostarMensaje("No hay libros disponibles para agregar");
            return;
        }
        
        String isbn = seleccionado.split(" - ")[0]; // ESTO genera una lista dividiendolo en 2 
                                                    // dependiendo el parametro que esta ahí
                                                    // y simplenete como es[0] coge el isbn     
        Libro libro = libroDao.buscar(isbn);

        if (libro == null) {
            prestamoCrearView.mostarMensaje("No se encontró el libro seleccionado");
            return;
        }

        if (librosSeleccionados.contains(libro)) {
            prestamoCrearView.mostarMensaje("Ese libro ya fue agregado");
            return;
        }

        librosSeleccionados.add(libro);

        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel)prestamoCrearView.getTblLibrosAgregadosPrestamoCrear().getModel();
        modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo()});
    }
    // METODO PARA CREAR EL PRESTAMO
    public void crearPrestamo() {

        if (usuarioSeleccionado == null) {
            prestamoCrearView.mostarMensaje("Debe buscar y seleccionar un usuario válido");
            return;
        }

        if (librosSeleccionados.isEmpty()) {
            prestamoCrearView.mostarMensaje("Debe agregar al menos un libro");
            return;
        }

        String codigoTexto = prestamoCrearView.getTxtCodigoPrestamoCrear().getText();
        if (codigoTexto.isEmpty()) {
            prestamoCrearView.mostarMensaje("Debe ingresar un código de préstamo");
            return;
        }
        int codigo = Integer.parseInt(codigoTexto);
        Prestamo prestamo = new Prestamo();
        prestamo.setCodigo(codigo);
        prestamo.setUsuario(usuarioSeleccionado);
        prestamo.prestamoHecho(LocalDate.now());

        for (Libro libro : librosSeleccionados) {
            prestamo.agregarLibro(libro);
            libro.setDisponible(false);      
            libroDao.actualizar(libro);
        }

        prestamoDao.crear(prestamo);
        usuarioSeleccionado.getPedidos().add(prestamo);

        prestamoCrearView.mostarMensaje("Préstamo creado correctamente");
        limpiarPrestamoCrear();
    }

    // SIMPLEMENTE LIMPIAR
    public void limpiarPrestamoCrear() {
        prestamoCrearView.getTxtCedulaPrestamoCrear().setText("");
        prestamoCrearView.getTxtNombrePrestamoCrear().setText("");
        prestamoCrearView.getTxtTelefonoPrestamoCrear().setText("");

        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) prestamoCrearView.getTblLibrosAgregadosPrestamoCrear().getModel();
        modelo.setRowCount(0);

        librosSeleccionados.clear();
        usuarioSeleccionado = null;

        cargarLibrosDisponibles();
        idCodigo();
    }
    
    public void refrescarLibrosDisponibles() {
    cargarLibrosDisponibles();
}
    
    // METODO DE LA CONFIGURACION DE TODO LOS BOTONES
    public void configurarEventoPrestamoCrear() {

        prestamoCrearView.getBtnBuscarPrestamoCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });

        prestamoCrearView.getBtnAgregarPrestamoCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });

        prestamoCrearView.getBtnCrearPrestamoCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPrestamo();
            }
        });

        prestamoCrearView.getBtnLimpiarPrestamoCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarPrestamoCrear();
            }
        });

        prestamoCrearView.getBtnCancelarPrestamoView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamoCrearView.dispose();
            }
        });
    }
}
