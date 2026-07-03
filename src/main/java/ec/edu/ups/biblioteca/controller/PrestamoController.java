package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.LibroDao;
import ec.edu.ups.biblioteca.dao.PrestamoDao;
import ec.edu.ups.biblioteca.dao.UsuarioDao;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.models.Prestamo;
import ec.edu.ups.biblioteca.models.Usuario;
import ec.edu.ups.biblioteca.view.PrestamoBuscarView;
import ec.edu.ups.biblioteca.view.PrestamoCrearView;
import ec.edu.ups.biblioteca.view.PrestamoEliminarView;
import ec.edu.ups.biblioteca.view.RegistrarDevolucionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class PrestamoController {

    private PrestamoDao prestamoDao;
    private UsuarioDao usuarioDao;
    private LibroDao libroDao;
    private PrestamoCrearView prestamoCrearView;

    private Usuario usuarioSeleccionado;
    private List<Libro> librosSeleccionados;
    private PrestamoBuscarView prestamoBuscarView;

    private RegistrarDevolucionView registrarDevolucionView;
    private Prestamo prestamoEncontradoDevolucion; // guarda el préstamo que se buscó, para usarlo al presionar "Devolver" acuerdate

    private PrestamoEliminarView prestamoEliminarView;
    private Prestamo prestamoEncontradoEliminar; // los mismo del anterior,es para guardarlo

    public PrestamoController(PrestamoCrearView prestamoCrearView, PrestamoDao prestamoDao, RegistrarDevolucionView registrarDevolucionView, PrestamoEliminarView prestamoEliminarView, UsuarioDao usuarioDao, LibroDao libroDao, PrestamoBuscarView prestamoBuscarView) {

        this.prestamoCrearView = prestamoCrearView;
        this.prestamoDao = prestamoDao;
        this.usuarioDao = usuarioDao;
        this.libroDao = libroDao;
        this.librosSeleccionados = new ArrayList<>();
        this.prestamoBuscarView = prestamoBuscarView;
        this.registrarDevolucionView = registrarDevolucionView;
        this.prestamoEliminarView = prestamoEliminarView;

        cargarLibrosDisponibles();
        idCodigo();
        configurarEventoPrestamoCrear();
        configurarEventoPrestamoBuscar();
        configurarEventoRegistrarDevolucion();
        configurarEventoPrestamoEliminar();
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
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        // el parantecis sirve para poder definir el tipo de variable antes de poner (un casteo)
        // el tipo DefaultComboBoxModel es el tipo del cual necesitamos lo metodos para hacerlo mas comodo 
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

        DefaultTableModel modelo = (DefaultTableModel) prestamoCrearView.getTblLibrosAgregadosPrestamoCrear().getModel();
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

        DefaultTableModel modelo = (DefaultTableModel) prestamoCrearView.getTblLibrosAgregadosPrestamoCrear().getModel();
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
    // METODO PARA BUSCAR POR CÓDIGO

    public void buscarPorCodigo() {
        String codigoTexto = prestamoBuscarView.getTxtCodigoPrestamoBuscar().getText();

        if (codigoTexto.isEmpty()) {
            prestamoBuscarView.mostarMensaje("Debe ingresar un código");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);

        Prestamo prestamo = prestamoDao.buscar(codigo);

        if (prestamo != null) {
            List<Prestamo> resultado = new ArrayList<>();
            resultado.add(prestamo);
            mostrarResultadosEnTabla(resultado);
        } else {
            limpiarTablaBuscar();
            prestamoBuscarView.mostarMensaje("No se encontró un préstamo con ese código");
        }
    }

// METODO PARA BUSCAR POR CÉDULA DEL USUARIO
    public void buscarPorCedula() {
        String cedula = prestamoBuscarView.getTxtCedulaPrestamoBuscar().getText();

        if (cedula.isEmpty()) {
            prestamoBuscarView.mostarMensaje("Debe ingresar una cédula");
            return;
        }

        List<Prestamo> encontrados = new ArrayList<>();
        for (Prestamo prestamo : prestamoDao.listar()) {
            if (prestamo.getUsuario() != null && prestamo.getUsuario().getCedula().equalsIgnoreCase(cedula)) {
                encontrados.add(prestamo);
            }
        }
        if (!encontrados.isEmpty()) {
            mostrarResultadosEnTabla(encontrados);
        } else {
            limpiarTablaBuscar();
            prestamoBuscarView.mostarMensaje("No se encontraron préstamos para esa cédula");
        }
    }

// METODO QUE ARMA LA TABLA (Código, Usuario, Título del Libro, Fecha Préstamo, Estado)
// Como cada préstamo puede tener varios libros, se genera UNA FILA por cada libro
    private void mostrarResultadosEnTabla(List<Prestamo> prestamos) {
        DefaultTableModel modelo = (DefaultTableModel) prestamoBuscarView.getTblPrestamosPrestamosBuscar().getModel();
        modelo.setRowCount(0);

        for (Prestamo prestamo : prestamos) {
            String nombreUsuario = prestamo.getUsuario().getNombre();
            String estado = (prestamo.getFechaDeDevolucion() != null) ? "Devuelto" : "Prestado";// Angel EL ? es para poder hacer que si se cumple esa 
            // condicion se ejecuta lo que esta a la derecha de ? 
            // si no lo que esta despues de : (operador ternario)

            // SIMPLEMENTE al modelo que tenemos de la tabla le agragamos cuando esta vacio y cuando tiene pedidos
            if (prestamo.getListaLibros().isEmpty()) {
                modelo.addRow(new Object[]{prestamo.getCodigo(), nombreUsuario, "Sin libros", prestamo.getFechaDePrestamo(), estado});
            } else {
                for (Libro libro : prestamo.getListaLibros()) {
                    modelo.addRow(new Object[]{prestamo.getCodigo(), nombreUsuario, libro.getTitulo(), prestamo.getFechaDePrestamo(), estado});
                }
            }
        }
    }

    private void limpiarTablaBuscar() {
        DefaultTableModel modelo = (DefaultTableModel) prestamoBuscarView.getTblPrestamosPrestamosBuscar().getModel();
        modelo.setRowCount(0);
    }

// CONFIGURACION DE EVENTOS BOTONES
    public void configurarEventoPrestamoBuscar() {

        prestamoBuscarView.getBtnBuscarCodigoPrestamoBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigo();
            }
        });

        prestamoBuscarView.getBtnBuscarCedulaPrestamoBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCedula();
            }
        });

        prestamoBuscarView.getBtnCancelarPrestamoBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamoBuscarView.dispose();
            }
        });
    }

    // METODO PARA BUSCAR EL PRESTAMO A DEVOLVER
    public void buscarPrestamoParaDevolucion() {
        String codigoTexto = registrarDevolucionView.getTxtCodigoRegistarDevolucion().getText();

        if (codigoTexto.isEmpty()) {
            registrarDevolucionView.mostarMensaje("Debe ingresar un código de préstamo");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);

        Prestamo prestamo = prestamoDao.buscar(codigo);

        if (prestamo == null) {
            prestamoEncontradoDevolucion = null;
            limpiarCamposDevolucion();
            registrarDevolucionView.mostarMensaje("No se encontró un préstamo con ese código");
            return;
        }

        if (prestamo.getFechaDeDevolucion() != null) {
            prestamoEncontradoDevolucion = null;
            limpiarCamposDevolucion();
            registrarDevolucionView.mostarMensaje("Este préstamo ya fue devuelto");
            return;
        }

        prestamoEncontradoDevolucion = prestamo;

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // una forma para poder poner el formato del tiempo con formato 
        registrarDevolucionView.getTxtFechaPrestamoRegistrarDevolucion().setText(prestamo.getFechaDePrestamo() != null ? prestamo.getFechaDePrestamo().format(formato) : "");
        registrarDevolucionView.getTxtFechaDevolucionRegistrarDevolucion().setText(""); // para eliminarlo para evitar errores visuales 
        registrarDevolucionView.getTxtEstadoRegistrarDevolucion().setText("Prestado"); // porque ya no va a estar en el estado de prestamo
        DefaultTableModel modelo = (DefaultTableModel) registrarDevolucionView.getTblLibrosRegistrarDevolucion().getModel();
        modelo.setRowCount(0);

        for (Libro libro : prestamo.getListaLibros()) {
            modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo()});
        }
    }

// METODO PARA REGISTRAR LA DEVOLUCION
    public void registrarDevolucion() {

        if (prestamoEncontradoDevolucion == null) {
            registrarDevolucionView.mostarMensaje("Primero debe buscar un préstamo válido");
            return;
        }

        LocalDate fechaDevolucion = LocalDate.now();
        prestamoEncontradoDevolucion.setFechaDeDevolucion(fechaDevolucion);

        // Marca todos los libros del préstamo como disponibles otra vez
        for (Libro libro : prestamoEncontradoDevolucion.getListaLibros()) {
            libro.setDisponible(true);
            libroDao.actualizar(libro);
        }

        prestamoDao.actualizar(prestamoEncontradoDevolucion);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // ponemos el formato que queremos que tenga
        registrarDevolucionView.getTxtFechaDevolucionRegistrarDevolucion().setText(fechaDevolucion.format(formato));// le ponemos el formato y el tiempo
        registrarDevolucionView.getTxtEstadoRegistrarDevolucion().setText("Devuelto");

        registrarDevolucionView.mostarMensaje("Devolución registrada correctamente");

        prestamoEncontradoDevolucion = null;
        cargarLibrosDisponibles(); // volver a cargarlo por si acaso
    }

    public void limpiarCamposDevolucion() {
        registrarDevolucionView.getTxtCodigoRegistarDevolucion().setText("");
        registrarDevolucionView.getTxtFechaPrestamoRegistrarDevolucion().setText("");
        registrarDevolucionView.getTxtFechaDevolucionRegistrarDevolucion().setText("");
        registrarDevolucionView.getTxtEstadoRegistrarDevolucion().setText("");

        DefaultTableModel modelo = (DefaultTableModel) registrarDevolucionView.getTblLibrosRegistrarDevolucion().getModel();
        modelo.setRowCount(0);

        prestamoEncontradoDevolucion = null;
    }

// CONFIGURACION DE EVENTOS
    public void configurarEventoRegistrarDevolucion() {

        registrarDevolucionView.getBtnBuscarRegistrarDevolucion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPrestamoParaDevolucion();
            }
        });

        registrarDevolucionView.getBtnDevolverRegistrarDevolucion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarDevolucion();
            }
        });

        registrarDevolucionView.getBtnLimpiarRegistrarDevolucion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposDevolucion();
            }
        });

        registrarDevolucionView.getBtnCancelarRegistrarDevolucion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarDevolucionView.dispose();
            }
        });
    }
    // METODO PARA BUSCAR EL PRESTAMO A ELIMINAR

    public void buscarPrestamoEliminar() {
        String codigoTexto = prestamoEliminarView.getTxtCodigoPrestamoEliminar().getText();

        if (codigoTexto.isEmpty()) {
            prestamoEliminarView.mostarMensaje("Debe ingresar un código de préstamo");
            return;
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoTexto);
        } catch (NumberFormatException e) {
            prestamoEliminarView.mostarMensaje("El código debe ser un número");
            return;
        }

        Prestamo prestamo = prestamoDao.buscar(codigo);

        if (prestamo == null) {
            prestamoEncontradoEliminar = null;
            limpiarPrestamoEliminar();
            prestamoEliminarView.mostarMensaje("No se encontró un préstamo con ese código");
            return;
        }

        prestamoEncontradoEliminar = prestamo;

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        prestamoEliminarView.getTxtCedulaPrestamoEliminar().setText(prestamo.getUsuario().getCedula());
        prestamoEliminarView.getTxtNombrePrestamoEliminar().setText(prestamo.getUsuario().getNombre());
        prestamoEliminarView.getTxtTelefonoPrestamoEliminar().setText(prestamo.getUsuario().getNumero());
        prestamoEliminarView.getTxtFechaPrestamoEliminar().setText(
                prestamo.getFechaDePrestamo() != null ? prestamo.getFechaDePrestamo().format(formato) : ""
        );

        DefaultTableModel modelo = (DefaultTableModel) prestamoEliminarView.getTblLibrosPrestamoEliminar().getModel();
        modelo.setRowCount(0);

        for (Libro libro : prestamo.getListaLibros()) {
            modelo.addRow(new Object[]{libro.getIsbn(), libro.getTitulo()});
        }
    }

// METODO PARA ELIMINAR EL PRESTAMO
    public void eliminarPrestamo() {

        if (prestamoEncontradoEliminar == null) {
            prestamoEliminarView.mostarMensaje("Primero debe buscar un préstamo válido");
            return;
        }

        int opcion = javax.swing.JOptionPane.showConfirmDialog(prestamoEliminarView,"¿Está seguro de eliminar este préstamo?","Confirmar eliminación",javax.swing.JOptionPane.YES_NO_OPTION);

        if (opcion != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        // Se quita el préstamo de la lista de pedidos del usuario
        if (prestamoEncontradoEliminar.getUsuario() != null && prestamoEncontradoEliminar.getUsuario().getPedidos() != null) {
            prestamoEncontradoEliminar.getUsuario().getPedidos().remove(prestamoEncontradoEliminar);
        }

        prestamoDao.eliminar(prestamoEncontradoEliminar.getCodigo());

        prestamoEliminarView.mostarMensaje("Préstamo eliminado correctamente");

        limpiarPrestamoEliminar();
        cargarLibrosDisponibles(); // refresca el coso de crear Préstamo para que funcione
    }

    public void limpiarPrestamoEliminar() {
        prestamoEliminarView.getTxtCodigoPrestamoEliminar().setText("");
        prestamoEliminarView.getTxtCedulaPrestamoEliminar().setText("");
        prestamoEliminarView.getTxtNombrePrestamoEliminar().setText("");
        prestamoEliminarView.getTxtTelefonoPrestamoEliminar().setText("");
        prestamoEliminarView.getTxtFechaPrestamoEliminar().setText("");

        DefaultTableModel modelo = (DefaultTableModel) prestamoEliminarView.getTblLibrosPrestamoEliminar().getModel();
        modelo.setRowCount(0);

        prestamoEncontradoEliminar = null;
    }

// CONFIGURACION DE EVENTOS
    public void configurarEventoPrestamoEliminar() {

        prestamoEliminarView.getBtnBuscarPrestamoEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPrestamoEliminar();
            }
        });

        prestamoEliminarView.getBtnEliminarPrestamoEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPrestamo();
            }
        });

        prestamoEliminarView.getBtnLimpiarPrestamoEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarPrestamoEliminar();
            }
        });

        prestamoEliminarView.getBtnCancelarPrestamoEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamoEliminarView.dispose();
            }
        });
    }

}
