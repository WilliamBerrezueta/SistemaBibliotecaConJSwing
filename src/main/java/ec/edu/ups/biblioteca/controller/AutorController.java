/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.AutorDao;
import ec.edu.ups.biblioteca.models.Autor;
import ec.edu.ups.biblioteca.view.AutorActualizarView;
import ec.edu.ups.biblioteca.view.AutorBuscarView;
import ec.edu.ups.biblioteca.view.AutorCrearView;
import ec.edu.ups.biblioteca.view.AutorEliminarView;
import ec.edu.ups.biblioteca.view.AutorListarView;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author USER
 */
public class AutorController {

    private AutorDao autorDao;

    private AutorCrearView autorCrearView;
    private AutorBuscarView autorBuscarView;
    private AutorActualizarView autorActualizarView;
    private AutorEliminarView autorEliminarView;
    private AutorListarView autorListarView;
    
    // Referencia opcional al controlador de libros. Se usa únicamente para
    // refrescar el combo box de autores de LibroCrearView cada vez que se
    // crea, actualiza o elimina un autor, sin que ambos controladores
    // dependan uno del otro en su construcción.
    private LibroController libroController;

    public void setLibroController(LibroController libroController) {
        this.libroController = libroController;
    }

    // Centraliza el refresco del combo box de autores; si todavía no se
    // asignó el LibroController (por ejemplo, mientras se arma la ventana
    // principal) simplemente no hace nada.
    private void actualizarComboAutoresEnLibros() {
        if (libroController != null) {
            libroController.cargarAutoresCombo();
        }
    }

    public AutorController(
            AutorActualizarView autorActualizarView,
            AutorBuscarView autorBuscarView,
            AutorCrearView autorCrearView,
            AutorEliminarView autorEliminarView,
            AutorListarView autorListarView,
            AutorDao autorDao) {

        this.autorActualizarView = autorActualizarView;
        this.autorBuscarView = autorBuscarView;
        this.autorCrearView = autorCrearView;
        this.autorEliminarView = autorEliminarView;
        this.autorListarView = autorListarView;
        this.autorDao = autorDao;

        configurarEventoAutorCrear();
        configurarEventoAutorBuscar();
        configurarEventoAutorActualizar();
        configurarEventoAutorEliminar();

    }

    public void crearAutor() {

        String nombre = autorCrearView.getTxtNombre().getText();
        String yearTexto = autorCrearView.getTxtYear().getText();
        String nacionalidad = autorCrearView.getTxtNacionalidad().getText();

        if (nombre.isEmpty() || yearTexto.isEmpty() || nacionalidad.isEmpty()) {
            autorActualizarView.mostrarMensaje("mensaje.rellenar");
            return;
        }

        int year = Integer.parseInt(yearTexto);

        Autor autor = new Autor(nombre, year, nacionalidad);

        autorDao.crear(autor);

        listarAutores();
        
        actualizarComboAutoresEnLibros();

        autorActualizarView.mostrarMensaje("mensaje.autor.creado");

    }

    public void configurarEventoAutorCrear() {

        autorCrearView.getBtnCrear().addActionListener(e -> crearAutor());

        autorCrearView.getBtnLimpiar().addActionListener(e -> limpiarAutorCrear());

        autorCrearView.getBtnCancelar().addActionListener(e -> autorCrearView.dispose());

    }

    public void listarAutores() {
        autorListarView.cargarDatos(autorDao.listar());
    }

    public void limpiarAutorCrear() {

        autorCrearView.getTxtNombre().setText("");
        autorCrearView.getTxtYear().setText("");
        autorCrearView.getTxtNacionalidad().setText("");

    }

    public void buscarAutor() {

        String nombre = autorBuscarView.getTxtNombre().getText();

        Autor autor = autorDao.buscar(nombre);

        if (autor != null) {

            autorBuscarView.getTxtNombre().setText(autor.getNombre());
            autorBuscarView.getTxtYear().setText(String.valueOf(autor.getYearDeNacimiento()));
            autorBuscarView.getTxtNacionalidad().setText(autor.getNacionalidad());

        } else {

            autorActualizarView.mostrarMensaje("mensaje.error.noencontrado");

        }

    }

    public void limpiarAutorBuscar() {

        autorBuscarView.getTxtNombre().setText("");
        autorBuscarView.getTxtYear().setText("");
        autorBuscarView.getTxtNacionalidad().setText("");

    }

    public void configurarEventoAutorBuscar() {

        autorBuscarView.getBtnBuscar().addActionListener(e -> buscarAutor());

        autorBuscarView.getBtnLimpiar().addActionListener(e -> limpiarAutorBuscar());

        autorBuscarView.getBtnCancelar().addActionListener(e -> autorBuscarView.dispose());

    }

    public void buscarAutorActualizar() {

        String nombre = autorActualizarView.getTxtNombre().getText();

        Autor autor = autorDao.buscar(nombre);

        if (autor != null) {

            autorActualizarView.getTxtNombre().setText(autor.getNombre());
            autorActualizarView.getTxtYear().setText(String.valueOf(autor.getYearDeNacimiento()));
            autorActualizarView.getTxtNacionalidad().setText(autor.getNacionalidad());

        } else {

            autorActualizarView.mostrarMensaje("mensaje.error.noencontrado");

        }

    }

    public void actualizarAutor() {

        String nombre = autorActualizarView.getTxtNombre().getText();

        Autor autor = autorDao.buscar(nombre);

        if (autor != null) {

            autor.setNombre(autorActualizarView.getTxtNombre().getText());
            autor.setYearDeNacimiento(Integer.parseInt(autorActualizarView.getTxtYear().getText()));
            autor.setNacionalidad(autorActualizarView.getTxtNacionalidad().getText());

            autorDao.actualizar(autor);

            listarAutores();
            
            actualizarComboAutoresEnLibros();

            autorActualizarView.mostrarMensaje("mensaje.autor.actualizado");

        }

    }

    public void limpiarAutorActualizar() {

        autorActualizarView.getTxtNombre().setText("");
        autorActualizarView.getTxtYear().setText("");
        autorActualizarView.getTxtNacionalidad().setText("");

    }

    public void configurarEventoAutorActualizar() {

        autorActualizarView.getBtnBuscarAutorActualizar().addActionListener(e -> buscarAutorActualizar());

        autorActualizarView.getBtnActualizarAutorActualizar().addActionListener(e -> actualizarAutor());

        autorActualizarView.getBtnLimpiar().addActionListener(e -> limpiarAutorActualizar());

        autorActualizarView.getBtnCancelar().addActionListener(e -> autorActualizarView.dispose());

    }

    public void buscarAutorEliminar() {

        String nombre = autorEliminarView.getTxtNombre().getText();

        Autor autor = autorDao.buscar(nombre);

        if (autor != null) {

            autorEliminarView.getTxtNombre().setText(autor.getNombre());
            autorEliminarView.getTxtYear().setText(String.valueOf(autor.getYearDeNacimiento()));
            autorEliminarView.getTxtNacionalidad().setText(autor.getNacionalidad());

        } else {

            autorActualizarView.mostrarMensaje("mensaje.error.noencontrado");

        }

    }

    public void eliminarAutor() {

        String nombre = autorEliminarView.getTxtNombre().getText();

        Autor autor = autorDao.buscar(nombre);

        if (autor != null) {

            int opcion = autorEliminarView.mostrarConfirmacion("mensaje.confirmacion.eliminar");

            if (opcion == 0) {

                autorDao.eliminar(nombre);

                listarAutores();
                
                actualizarComboAutoresEnLibros();

            }

        }

    }

    public void limpiarAutorEliminar() {

        autorEliminarView.getTxtNombre().setText("");
        autorEliminarView.getTxtYear().setText("");
        autorEliminarView.getTxtNacionalidad().setText("");

    }

    public void configurarEventoAutorEliminar() {

        autorEliminarView.getBtnBuscar().addActionListener(e -> buscarAutorEliminar());

        autorEliminarView.getBtnEliminar().addActionListener(e -> eliminarAutor());

        autorEliminarView.getBtnLimpiar().addActionListener(e -> limpiarAutorEliminar());

        autorEliminarView.getBtnCancelar().addActionListener(e -> autorEliminarView.dispose());

    }

}
