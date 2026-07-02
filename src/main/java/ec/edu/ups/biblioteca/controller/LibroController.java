/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.LibroDao;
import ec.edu.ups.biblioteca.view.LibroActualizarView;
import ec.edu.ups.biblioteca.view.LibroBuscarView;
import ec.edu.ups.biblioteca.view.LibroCrearView;
import ec.edu.ups.biblioteca.view.LibroEliminarView;
import ec.edu.ups.biblioteca.view.LibroListarView;

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
        
        configurarEventos();
    }
    
    private void configurarEventos(){
        
    }
    
}
