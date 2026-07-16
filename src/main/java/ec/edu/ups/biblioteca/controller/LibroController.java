/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.AutorDao;
import ec.edu.ups.biblioteca.dao.LibroDao;
import ec.edu.ups.biblioteca.models.Autor;
import ec.edu.ups.biblioteca.models.Genero;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.view.LibroActualizarView;
import ec.edu.ups.biblioteca.view.LibroBuscarView;
import ec.edu.ups.biblioteca.view.LibroCrearView;
import ec.edu.ups.biblioteca.view.LibroEliminarView;
import ec.edu.ups.biblioteca.view.LibroListarView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class LibroController {

    private LibroDao libroDao;
    private AutorDao autorDao;
    private LibroActualizarView libroActualizarView;
    private LibroBuscarView libroBuscarView;
    private LibroCrearView libroCrearView;
    private LibroEliminarView libroEliminarView;
    private LibroListarView libroListarView;

    // Bandera para evitar que, mientras se rellenan los combos de ISBN por
    // código (removeAllItems + addItem), se disparen búsquedas como si el
    // usuario hubiese seleccionado algo manualmente.
    private boolean cargandoCombosIsbn = false;

    public LibroController(LibroActualizarView libroActualizarView, LibroBuscarView libroBuscarView, LibroCrearView libroCrearView, LibroEliminarView libroEliminarView, LibroListarView libroListarView, LibroDao libroDao, AutorDao autorDao) {

        this.libroActualizarView = libroActualizarView;
        this.libroBuscarView = libroBuscarView;
        this.libroCrearView = libroCrearView;
        this.libroEliminarView = libroEliminarView;
        this.libroListarView = libroListarView;
        this.libroDao = libroDao;
        this.autorDao = autorDao;

        configurarEventoLibroCrear();
        configurarEventoLibroBuscar();
        configurarEventoLibroEliminar();
        configurarEventoLibroActualizar();
        cargarAutoresActualizar();
        cargarGenerosActualizar();
        cargarAutoresCombo();
        cargarGenerosCombo();
        cargarIsbnsCombo();
    }

    // METODOS PARA CONFIGURARCREAR
    public void crearLibro() {
        String isbn = libroCrearView.getTxtIsbnLibroCrear().getText();
        String titulo = libroCrearView.getTxtTituloLibroCrear().getText();
        String añoTexto = libroCrearView.getTxtYearLibroCrear().getText();
        Genero genero = (Genero) libroCrearView.getCbxGeneroLibroCrear().getSelectedItem();
        boolean disponible = libroCrearView.getRbtnDisponibleLibroCrear().isSelected();
        String editorial = libroCrearView.getTxtEditorialLibroCrear().getText();
        Autor autor = (Autor) libroCrearView.getCbxAutorLibroCrear().getSelectedItem();

        if (isbn.isEmpty() || titulo.isEmpty() || añoTexto.isEmpty() || genero == null || editorial.isEmpty() || autor == null) {
            libroCrearView.mostrarMensaje("mensaje.error.llenarcampos.libro");
            return;
        }
        if (isbn.length() != 13) {
            libroCrearView.mostrarMensaje("mensaje.error.isbn.libro");
            return;
        }
        if (libroDao.buscar(isbn) != null) {
            libroCrearView.mostrarMensaje("mensaje.error.doble.libro");
        } else {
            int año;
            try {
                año = Integer.parseInt(añoTexto);
            } catch (ClassCastException e) {
                libroCrearView.mostrarMensaje("mensaje.error.numero.libro");
                return;
            }
            if (año > 2026 || año < 0) {
                libroCrearView.mostrarMensaje("mensaje.error.fecha.valida.libro");
                return;
            }

            Libro libro = new Libro(isbn, titulo, año, genero, disponible, editorial, autor);
            libroDao.crear(libro);
            listarLibros();
            cargarIsbnsCombo();
            libroCrearView.mostrarMensaje("mensaje.libro.creado.libro");
        }
    }

    public void limpiarLibroCrear() {
        libroCrearView.getTxtAutorLibroCrear().setText("");
        libroCrearView.getTxtEditorialLibroCrear().setText("");
        libroCrearView.getTxtGeneroLibroCrear().setText("");
        libroCrearView.getTxtIsbnLibroCrear().setText("");
        libroCrearView.getTxtTituloLibroCrear().setText("");
        libroCrearView.getTxtYearLibroCrear().setText("");
        libroCrearView.getRbtnDisponibleLibroCrear().setSelected(false);
        libroCrearView.getTxtDisponibleLibroCrear().setText("No");
        if (libroCrearView.getCbxGeneroLibroCrear().getItemCount() > 0) {
            libroCrearView.getCbxGeneroLibroCrear().setSelectedIndex(0);
        }
        if (libroCrearView.getCbxAutorLibroCrear().getItemCount() > 0) {
            libroCrearView.getCbxAutorLibroCrear().setSelectedIndex(0);
        }
    }

    // CONFIGURAR
    public void configurarEventoLibroCrear() {
        libroCrearView.getBtnCrearLibroCrear().addActionListener(new ActionListener() { //clase anonima
            @Override
            public void actionPerformed(ActionEvent e) {
                crearLibro();
            }
        });
        libroCrearView.getBtnCancelarLibroCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libroCrearView.dispose();
            }
        });
        libroCrearView.getBtnLimpiarLibroCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarLibroCrear();
            }
        });
    }
    // METODO PARA BUSCAR

    public void buscarLibro() {
        if (libroBuscarView != null) {

            JTextField codigoJ = libroBuscarView.getTxtIsbnLibroBuscar();
            String codigoInt = codigoJ.getText();
            Libro libroBuscar = libroDao.buscar(codigoInt);

            if (libroBuscar != null) {
                libroBuscarView.getTxtAutorLibroBuscar().setText(libroBuscar.getAutor().getNombre());
                libroBuscarView.getTxtEditorialLibroBuscar().setText(libroBuscar.getEditorial());
                libroBuscarView.getTxtGeneroLibroBuscar().setText(libroBuscar.getGenero() != null ? libroBuscar.getGenero().toString() : "");
                libroBuscarView.getTxtIsbnLibroBuscar().setText(libroBuscar.getIsbn());
                libroBuscarView.getTxtTituloLibroBuscar().setText(libroBuscar.getTitulo());
                libroBuscarView.getTxtYearLibroBuscar().setText(String.valueOf(libroBuscar.getAñoDePublicacion()));
                libroBuscarView.getRbtnDisponibleLibroBuscar().setSelected(libroBuscar.isDisponible());
                libroBuscarView.getTxtDisponibleLibroBuscar().setText(libroBuscar.isDisponible() ? "Sí" : "No");
            }
        }
    }

    public void limpiarLibroBuscar() {
        libroBuscarView.getTxtAutorLibroBuscar().setText("");
        libroBuscarView.getTxtEditorialLibroBuscar().setText("");
        libroBuscarView.getTxtGeneroLibroBuscar().setText("");
        libroBuscarView.getTxtIsbnLibroBuscar().setText("");
        libroBuscarView.getTxtTituloLibroBuscar().setText("");
        libroBuscarView.getTxtYearLibroBuscar().setText("");
        libroBuscarView.getRbtnDisponibleLibroBuscar().setSelected(false);
        libroBuscarView.getTxtDisponibleLibroBuscar().setText("No");
    }

    public void configurarEventoLibroBuscar() {
        libroBuscarView.getBtnCrearLibroBuscar().addActionListener(new ActionListener() { //clase anonima
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibro();
            }
        });
        libroBuscarView.getBtnCancelarLibroBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libroBuscarView.dispose();
            }
        });
        libroBuscarView.getBtnLimpiarLibroBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarLibroBuscar();
            }
        });
        libroBuscarView.getCbxIsbnLibroBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cargandoCombosIsbn) {
                    return;
                }
                Object seleccionado = libroBuscarView.getCbxIsbnLibroBuscar().getSelectedItem();
                if (seleccionado != null) {
                    String isbnSeleccionado = seleccionado.toString().split(" - ")[0];
                    libroBuscarView.getTxtIsbnLibroBuscar().setText(isbnSeleccionado);
                }
            }
        });
    }

    // METODOS ELIMINAR
    public void eliminarLibro() {
        if (libroEliminarView != null) {
            JTextField codigoJ = libroEliminarView.getTxtIsbnLibroEliminar();

            String isbn = codigoJ.getText();
            Libro eliminarLibro = libroDao.buscar(isbn);

            if (eliminarLibro != null) {
                int seguro = libroEliminarView.mostarMensaje("Seguro que quieres eliminar este libro");
                if (seguro == 0) {
                    libroDao.eliminar(isbn);
                    listarLibros();
                    cargarIsbnsCombo();
                }
                listarLibros();
            }

        }
    }

    public void limpiarLibroEliminar() {
        libroEliminarView.getTxtAutorLibroEliminar().setText("");
        libroEliminarView.getTxtEditorialLibroEliminar().setText("");
        libroEliminarView.getTxtGeneroLibroEliminar().setText("");
        libroEliminarView.getTxtIsbnLibroEliminar().setText("");
        libroEliminarView.getTxtTituloLibroEliminar().setText("");
        libroEliminarView.getTxtYearLibroEliminar().setText("");
        libroEliminarView.getRbtnDisponibleLibroEliminar().setSelected(false);
        libroEliminarView.getTxtDisponibleLibroEliminar().setText("No");
    }

    public void eliminarLibroBuscar() {
        if (libroEliminarView != null) {

            JTextField codigoJ = libroEliminarView.getTxtIsbnLibroEliminar();
            String codigoInt = codigoJ.getText();
            Libro libroBuscar = libroDao.buscar(codigoInt);

            if (libroBuscar != null) {
                libroEliminarView.getTxtAutorLibroEliminar().setText(libroBuscar.getAutor().getNombre());
                libroEliminarView.getTxtEditorialLibroEliminar().setText(libroBuscar.getEditorial());
                libroEliminarView.getTxtGeneroLibroEliminar().setText(libroBuscar.getGenero() != null ? libroBuscar.getGenero().toString() : "");
                libroEliminarView.getTxtIsbnLibroEliminar().setText(libroBuscar.getIsbn());
                libroEliminarView.getTxtTituloLibroEliminar().setText(libroBuscar.getTitulo());
                libroEliminarView.getTxtYearLibroEliminar().setText(String.valueOf(libroBuscar.getAñoDePublicacion()));
                libroEliminarView.getRbtnDisponibleLibroEliminar().setSelected(libroBuscar.isDisponible());
                libroEliminarView.getTxtDisponibleLibroEliminar().setText(libroBuscar.isDisponible() ? "Sí" : "No");
            }
        }
    }

    public void configurarEventoLibroEliminar() {
        libroEliminarView.getBtnCrearLibroBuscar().addActionListener(new ActionListener() { //clase anonima
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibroBuscar();
            }
        });
        libroEliminarView.getBtnCancelarLibroEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libroEliminarView.dispose();
            }
        });
        libroEliminarView.getBtnLimpiarLibroEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarLibroEliminar();
            }
        });
        libroEliminarView.getBtnEliminarLibroEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });
        libroEliminarView.getCbxIsbnLibroEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cargandoCombosIsbn) {
                    return;
                }
                Object seleccionado = libroEliminarView.getCbxIsbnLibroEliminar().getSelectedItem();
                if (seleccionado != null) {
                    String isbnSeleccionado = seleccionado.toString().split(" - ")[0];
                    libroEliminarView.getTxtIsbnLibroEliminar().setText(isbnSeleccionado);
                }
            }
        });
    }

    // METODOS ACTUALIZAR
    public void actualizarLibro() {
        String isbn = libroActualizarView.getTxtIsbnLibroActualizar().getText();
        Libro libro = libroDao.buscar(isbn);
        if (libro == null) {
            libroActualizarView.mostrarMensaje("mensaje.error.isbn.no.libro");
            return;
        }

        String titulo = libroActualizarView.getTxtTituloLibroActualizar().getText();
        String editorial = libroActualizarView.getTxtEditorialLibroActualizar().getText();
        String yearTexto = libroActualizarView.getTxtYearLibroActualizar().getText();

        if (titulo.isEmpty() || editorial.isEmpty() || yearTexto.isEmpty()) {
            libroActualizarView.mostrarMensaje("mensaje.error.llenarcampos.libro");
            return;
        }

        Genero genero;
        try {
            genero = Genero.fromTexto(libroActualizarView.getTxtGeneroLibroActualizar().getText());
        } catch (IllegalArgumentException ex) {
            libroActualizarView.mostrarMensaje("mensaje.error.genero.libro");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearTexto);
        } catch (NumberFormatException ex) {
            libroActualizarView.mostrarMensaje("mensaje.error.numero.libro");
            return;
        }

        if (year > 2026 || year < 0) {
            libroActualizarView.mostrarMensaje("mensaje.error.fecha.valida.libro");
            return;
        }

        libro.setTitulo(titulo);
        libro.setEditorial(editorial);
        libro.setGenero(genero);
        libro.setAñoDePublicacion(year);
        libro.setDisponible(libroActualizarView.getRbtnDisponibleLibroActualizar().isSelected());
        libroActualizarView.getTxtDisponibleLibroActualizar().setText(libro.isDisponible() ? "Sí" : "No");

        libroDao.actualizar(libro);
        listarLibros();
        cargarIsbnsCombo();
        libroActualizarView.mostrarMensaje("libro.actulizado.yes");
    }

    public void buscarLibroActualizar() {
        if (libroActualizarView != null) {

            JTextField codigoJ = libroActualizarView.getTxtIsbnLibroActualizar();
            String isbn = codigoJ.getText();
            Libro libroBuscar = libroDao.buscar(isbn);

            if (libroBuscar != null) {
                libroActualizarView.getTxtAutorLibroActualizar().setText(libroBuscar.getAutor().getNombre());
                libroActualizarView.getTxtEditorialLibroActualizar().setText(libroBuscar.getEditorial());
                libroActualizarView.getTxtGeneroLibroActualizar().setText(libroBuscar.getGenero() != null ? libroBuscar.getGenero().toString() : "");
                libroActualizarView.getTxtTituloLibroActualizar().setText(libroBuscar.getTitulo());
                libroActualizarView.getTxtYearLibroActualizar().setText(String.valueOf(libroBuscar.getAñoDePublicacion()));
                libroActualizarView.getRbtnDisponibleLibroActualizar().setSelected(libroBuscar.isDisponible());
                libroActualizarView.getTxtDisponibleLibroActualizar().setText(libroBuscar.isDisponible() ? "Sí" : "No");
            }
        }
    }

    public void limpiarLibroActualizar() {
        libroActualizarView.getTxtAutorLibroActualizar().setText("");
        libroActualizarView.getTxtEditorialLibroActualizar().setText("");
        libroActualizarView.getTxtGeneroLibroActualizar().setText("");
        libroActualizarView.getTxtIsbnLibroActualizar().setText("");
        libroActualizarView.getTxtTituloLibroActualizar().setText("");
        libroActualizarView.getTxtYearLibroActualizar().setText("");
        libroActualizarView.getRbtnDisponibleLibroActualizar().setSelected(false);
        libroActualizarView.getTxtDisponibleLibroActualizar().setText("No");
    }

    public void configurarEventoLibroActualizar() {
        libroActualizarView.getBtnActualizarLibroActualizar().addActionListener(new ActionListener() { //clase anonima
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarLibro();
            }
        });
        libroActualizarView.getBtnBuscarLibroActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibroActualizar();
            }
        });
        libroActualizarView.getBtnCancelarLibroActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libroActualizarView.dispose();
            }
        });
        libroActualizarView.getBtnLimpiarLibroActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarLibroActualizar();
            }
        });
        libroActualizarView.getCbxIsbnLibroActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cargandoCombosIsbn) {
                    return;
                }
                Object seleccionado = libroActualizarView.getCbxIsbnLibroActualizar().getSelectedItem();
                if (seleccionado != null) {
                    String isbnSeleccionado = seleccionado.toString().split(" - ")[0];
                    libroActualizarView.getTxtIsbnLibroActualizar().setText(isbnSeleccionado);
                }
            }
        });

        libroActualizarView.getCbxAutorLibroActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object seleccionado = libroActualizarView.getCbxAutorLibroActualizar().getSelectedItem();

                if (seleccionado != null) {
                    libroActualizarView.getTxtAutorLibroActualizar().setText(seleccionado.toString());
                }

            }
        });
        libroActualizarView.getCbxGeneroLibroActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object seleccionado = libroActualizarView.getCbxGeneroLibroActualizar().getSelectedItem();

                if (seleccionado != null) {
                    libroActualizarView.getTxtGeneroLibroActualizar().setText(seleccionado.toString());
                }

            }
        });

    }

    // METODOS LISTAR
    public void listarLibros() {
        libroListarView.cargarDatos(libroDao.listar());

    }

    //// METODOS PARA RELLENAR LOS COMBO BOX

    
//      Recarga el combo de autores del formulario de creación de libros con
//      los autores actualmente registrados. Este método debe volver a
//      ejecutarse cada vez que se crea, actualiza o elimina un autor para que
//      el combo box siempre refleje la lista más reciente
     
    public void cargarAutoresCombo() {

        Object seleccionActual = libroCrearView.getCbxAutorLibroCrear().getSelectedItem();

        libroCrearView.getCbxAutorLibroCrear().removeAllItems();

        for (Autor autor : autorDao.listar()) {
            libroCrearView.getCbxAutorLibroCrear().addItem(autor);
        }

        if (seleccionActual instanceof Autor) {
            libroCrearView.getCbxAutorLibroCrear().setSelectedItem(seleccionActual);
        }
    }

//      Rellena el combo de género del formulario de creación de libros con
//      todos los valores de la enumeración
    public void cargarGenerosCombo() {

        libroCrearView.getCbxGeneroLibroCrear().removeAllItems();

        for (Genero genero : Genero.values()) {
            libroCrearView.getCbxGeneroLibroCrear().addItem(genero);
        }
    }

//      Rellena los combos de ISBN de las vistas Buscar, Actualizar y Eliminar
//      con "isbn - titulo" de cada libro registrado. Debe volver a ejecutarse
//      cada vez que se crea, actualiza o elimina un libro para que los combos
//      siempre reflejen la lista más reciente.
    public void cargarIsbnsCombo() {

        cargandoCombosIsbn = true;

        java.util.List<Libro> libros = libroDao.listar();

        llenarComboIsbns(libroBuscarView.getCbxIsbnLibroBuscar(), libros);
        llenarComboIsbns(libroActualizarView.getCbxIsbnLibroActualizar(), libros);
        llenarComboIsbns(libroEliminarView.getCbxIsbnLibroEliminar(), libros);

        cargandoCombosIsbn = false;
    }

    private void llenarComboIsbns(javax.swing.JComboBox<String> combo, java.util.List<Libro> libros) {

        Object seleccionActual = combo.getSelectedItem();

        combo.removeAllItems();

        for (Libro libro : libros) {
            combo.addItem(libro.getIsbn() + " - " + libro.getTitulo());
        }

        if (seleccionActual != null) {
            combo.setSelectedItem(seleccionActual);
        }
    }

    public void cargarAutoresActualizar() {

        libroActualizarView.getCbxAutorLibroActualizar().removeAllItems();

        for (Autor autor : autorDao.listar()) {
            libroActualizarView.getCbxAutorLibroActualizar().addItem(autor.getNombre());
        }
    }

    public void cargarGenerosActualizar() {

        libroActualizarView.getCbxGeneroLibroActualizar().removeAllItems();

        for (Genero genero : Genero.values()) {
            libroActualizarView.getCbxGeneroLibroActualizar().addItem(genero.toString());
        }
    }
}
