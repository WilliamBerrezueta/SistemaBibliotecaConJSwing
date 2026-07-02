/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.PrestamoDao;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.models.Prestamo;
import ec.edu.ups.biblioteca.models.Usuario;
import ec.edu.ups.biblioteca.view.PrestamoActualizarView;
import ec.edu.ups.biblioteca.view.PrestamoBuscarView;
import ec.edu.ups.biblioteca.view.PrestamoCrearView;
import ec.edu.ups.biblioteca.view.PrestamoEliminarView;
import ec.edu.ups.biblioteca.view.PrestamoListarView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;



/**
 *
 * @author USER
 */
public class PrestamoController {
    private PrestamoDao prestamoDao;
    private PrestamoActualizarView prestamoActualizarView;
    private PrestamoBuscarView prestamoBuscarView;
    private PrestamoCrearView prestamoCrearView;
    private PrestamoEliminarView prestamoEliminarView;
    private PrestamoListarView prestamoListarView;

    public PrestamoController(PrestamoDao prestamoDao, PrestamoActualizarView prestamoActualizarView, PrestamoBuscarView prestamoBuscarView, PrestamoCrearView prestamoCrearView, PrestamoEliminarView prestamoEliminarView, PrestamoListarView prestamoListarView) {
        this.prestamoDao = prestamoDao;
        this.prestamoActualizarView = prestamoActualizarView;
        this.prestamoBuscarView = prestamoBuscarView;
        this.prestamoCrearView = prestamoCrearView;
        this.prestamoEliminarView = prestamoEliminarView;
        this.prestamoListarView = prestamoListarView;
        
        configurarEventoLibroCrear();
    }
    
    public void crearPrestamo() {
    
}
    public void configurarEventoLibroCrear() {
        /*
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
        */
    }

    
}
