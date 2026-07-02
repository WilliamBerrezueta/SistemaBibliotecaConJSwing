/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.LibroDao;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.view.LibroActualizarView;
import ec.edu.ups.biblioteca.view.LibroBuscarView;
import ec.edu.ups.biblioteca.view.LibroCrearView;
import ec.edu.ups.biblioteca.view.LibroEliminarView;
import ec.edu.ups.biblioteca.view.LibroListarView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class LibroController {
    
    private LibroDao libroDao;
    private LibroActualizarView libroActualizarView;
    private LibroBuscarView libroBuscarView;
    private LibroCrearView libroCrearView;
    private LibroEliminarView libroEliminarView;
    private LibroListarView libroListarView;
    
    public LibroController(LibroActualizarView libroActualizarView,LibroBuscarView libroBuscarView, LibroCrearView libroCrearView, LibroEliminarView libroEliminarView,LibroListarView libroListarView, LibroDao libroDao){
        
        this.libroActualizarView = libroActualizarView;
        this.libroBuscarView = libroBuscarView;
        this.libroCrearView = libroCrearView;
        this.libroEliminarView = libroEliminarView;
        this.libroListarView = libroListarView;
        this.libroDao = libroDao;
        
        configurarEventoLibroCrear();
        configurarEventoLibroBuscar();
        configurarEventoLibroEliminar();
        configurarEventoLibroActualizar();
    }
    
    
    // METODOS PARA CONFIGURARCREAR
    public void crearLibro(){
        String isbn = libroCrearView.getTxtIsbnLibroCrear().getText();
        String titulo = libroCrearView.getTxtTituloLibroCrear().getText();
        int año = Integer.parseInt(libroCrearView.getTxtYearLibroCrear().getText());
        String genero = libroCrearView.getTxtGeneroLibroCrear().getText();
        boolean disponible = libroCrearView.getRbtnDisponibleLibroCrear().isSelected();
        String editorial = libroCrearView.getTxtEditorialLibroCrear().getText();
        String autor = libroCrearView.getTxtAutorLibroCrear().getText();
        
        Libro libro = new Libro(isbn, titulo, año, genero, disponible, editorial, autor);
        libroDao.crear(libro);
        listarLibros();
        libroCrearView.mostarMensaje("Se ha creado su libro");
    }
    
    public void limpiarLibroCrear(){
    libroCrearView.getTxtAutorLibroCrear().setText("");
    libroCrearView.getTxtEditorialLibroCrear().setText("");
    libroCrearView.getTxtGeneroLibroCrear().setText("");
    libroCrearView.getTxtIsbnLibroCrear().setText("");
    libroCrearView.getTxtTituloLibroCrear().setText("");
    libroCrearView.getTxtYearLibroCrear().setText("");
    libroCrearView.getRbtnDisponibleLibroCrear().setSelected(false);
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
    
    public void buscarLibro(){
        if (libroBuscarView != null) {

            JTextField codigoJ = libroBuscarView.getTxtIsbnLibroBuscar();
            String codigoInt = codigoJ.getText();
            Libro libroBuscar = libroDao.buscar(codigoInt);

            if (libroBuscar != null) {
                libroBuscarView.getTxtAutorLibroBuscar().setText(libroBuscar.getAutor());
                libroBuscarView.getTxtEditorialLibroBuscar().setText(libroBuscar.getEditorial());
                libroBuscarView.getTxtGeneroLibroBuscar().setText(libroBuscar.getGenero());
                libroBuscarView.getTxtIsbnLibroBuscar().setText(libroBuscar.getIsbn());
                libroBuscarView.getTxtTituloLibroBuscar().setText(libroBuscar.getTitulo());
                libroBuscarView.getTxtYearLibroBuscar().setText(String.valueOf(libroBuscar.getAñoDePublicacion()));
                libroBuscarView.getRbtnDisponibleLibroBuscar().setSelected(libroBuscar.isDisponible());
            }
        }
    }
    
    public void limpiarLibroBuscar(){
    libroBuscarView.getTxtAutorLibroBuscar().setText("");
    libroBuscarView.getTxtEditorialLibroBuscar().setText("");
    libroBuscarView.getTxtGeneroLibroBuscar().setText("");
    libroBuscarView.getTxtIsbnLibroBuscar().setText("");
    libroBuscarView.getTxtTituloLibroBuscar().setText("");
    libroBuscarView.getTxtYearLibroBuscar().setText("");
    libroBuscarView.getRbtnDisponibleLibroBuscar().setSelected(false);
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
    }
    // METODOS ELIMINAR
    public void eliminarLibro(){
    if (libroEliminarView != null) {
            JTextField codigoJ = libroEliminarView.getTxtIsbnLibroEliminar();

            String isbn = codigoJ.getText();
            Libro eliminarLibro = libroDao.buscar(isbn);

            if (eliminarLibro != null) {
                int seguro = libroEliminarView.mostarMensaje("Seguro que quieres eliminar este libro");
                if (seguro == 0) {
                    libroDao.eliminar(isbn);
                    listarLibros();
                }
                listarLibros();
            }

        }
    }
    public void limpiarLibroEliminar(){
    libroEliminarView.getTxtAutorLibroEliminar().setText("");
    libroEliminarView.getTxtEditorialLibroEliminar().setText("");
    libroEliminarView.getTxtGeneroLibroEliminar().setText("");
    libroEliminarView.getTxtIsbnLibroEliminar().setText("");
    libroEliminarView.getTxtTituloLibroEliminar().setText("");
    libroEliminarView.getTxtYearLibroEliminar().setText("");
    libroEliminarView.getRbtnDisponibleLibroEliminar().setSelected(false);
}
    public void eliminarLibroBuscar() {
        if (libroEliminarView != null) {

            JTextField codigoJ = libroEliminarView.getTxtIsbnLibroEliminar();
            String codigoInt = codigoJ.getText();
            Libro libroBuscar = libroDao.buscar(codigoInt);

            if (libroBuscar != null) {
                libroEliminarView.getTxtAutorLibroEliminar().setText(libroBuscar.getAutor());
                libroEliminarView.getTxtEditorialLibroEliminar().setText(libroBuscar.getEditorial());
                libroEliminarView.getTxtGeneroLibroEliminar().setText(libroBuscar.getGenero());
                libroEliminarView.getTxtIsbnLibroEliminar().setText(libroBuscar.getIsbn());
                libroEliminarView.getTxtTituloLibroEliminar().setText(libroBuscar.getTitulo());
                libroEliminarView.getTxtYearLibroEliminar().setText(String.valueOf(libroBuscar.getAñoDePublicacion()));
                libroEliminarView.getRbtnDisponibleLibroEliminar().setSelected(libroBuscar.isDisponible());
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
    }
    
    // METODOS ACTUALIZAR
    
    public void actualizarLibro() {
        String isbn = libroActualizarView.getTxtIsbnLibroActualizar().getText();

    Libro libro = libroDao.buscar(isbn);

    if(libro != null){

        libro.setTitulo(libroActualizarView.getTxtTituloLibroActualizar().getText());
        libro.setAutor(libroActualizarView.getTxtAutorLibroActualizar().getText());
        libro.setEditorial(libroActualizarView.getTxtEditorialLibroActualizar().getText());
        libro.setGenero(libroActualizarView.getTxtGeneroLibroActualizar().getText());
        libro.setAñoDePublicacion(
                Integer.parseInt(libroActualizarView.getTxtYearLibroActualizar().getText()));

        libro.setDisponible(
                libroActualizarView.getRbtnDisponibleLibroActualizar().isSelected());

        libroDao.actualizar(libro);

        listarLibros();

        libroActualizarView.mostarMensaje("Libro actualizado");
    }
    }

    public void buscarLibroActualizar() {
        if (libroActualizarView != null) {

            JTextField codigoJ = libroActualizarView.getTxtIsbnLibroActualizar();
            String isbn = codigoJ.getText();
            Libro libroBuscar = libroDao.buscar(isbn);

            if (libroBuscar != null) {
                libroActualizarView.getTxtAutorLibroActualizar().setText(libroBuscar.getAutor());
                libroActualizarView.getTxtEditorialLibroActualizar().setText(libroBuscar.getEditorial());
                libroActualizarView.getTxtGeneroLibroActualizar().setText(libroBuscar.getGenero());
                libroActualizarView.getTxtTituloLibroActualizar().setText(libroBuscar.getTitulo());
                libroActualizarView.getTxtYearLibroActualizar().setText(String.valueOf(libroBuscar.getAñoDePublicacion()));
                libroActualizarView.getRbtnDisponibleLibroActualizar().setSelected(libroBuscar.isDisponible());
            }
        }
    }
    public void limpiarLibroActualizar(){
    libroActualizarView.getTxtAutorLibroActualizar().setText("");
    libroActualizarView.getTxtEditorialLibroActualizar().setText("");
    libroActualizarView.getTxtGeneroLibroActualizar().setText("");
    libroActualizarView.getTxtIsbnLibroActualizar().setText("");
    libroActualizarView.getTxtTituloLibroActualizar().setText("");
    libroActualizarView.getTxtYearLibroActualizar().setText("");
    libroActualizarView.getRbtnDisponibleLibroActualizar().setSelected(false);
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
    }
    // METODOS LISTAR
    public void listarLibros() {
        libroListarView.cargarDatos(libroDao.listar());
        
    }
}
