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
//        configurarEventoAutorBuscar();
//        configurarEventoAutorActualizar();
//        configurarEventoAutorEliminar();

    }
    public void crearAutor(){

    String nombre = autorCrearView.getTxtNombre().getText();
    String yearTexto = autorCrearView.getTxtYear().getText();
    String nacionalidad = autorCrearView.getTxtNacionalidad().getText();

    if(nombre.isEmpty() || yearTexto.isEmpty() || nacionalidad.isEmpty()){
        autorCrearView.mostrarMensaje("Debe llenar todos los campos");
        return;
    }

    int year = Integer.parseInt(yearTexto);

    Autor autor = new Autor(nombre,year,nacionalidad);

    autorDao.crear(autor);

    listarAutores();

    autorCrearView.mostrarMensaje("Autor creado correctamente");

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
}
