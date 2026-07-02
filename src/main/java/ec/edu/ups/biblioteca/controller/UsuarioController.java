/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.UsuarioDao;
import ec.edu.ups.biblioteca.view.UsuarioActualizarView;
import ec.edu.ups.biblioteca.view.UsuarioBuscarView;
import ec.edu.ups.biblioteca.view.UsuarioCrearView;
import ec.edu.ups.biblioteca.view.UsuarioEliminarView;
import ec.edu.ups.biblioteca.view.UsuarioListarView;

/**
 *
 * @author USER
 */
public class UsuarioController {
    
    private UsuarioDao usuarioDao;
    private UsuarioActualizarView usuarioActualizarView;
    private UsuarioBuscarView usuarioBuscarView;
    private UsuarioCrearView usuarioCrearView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioListarView usuarioListarView;
    
    public UsuarioController(UsuarioActualizarView usuarioActualizarView, UsuarioBuscarView usuarioBuscarView, UsuarioCrearView usuarioCrearView, UsuarioEliminarView usuarioEliminarView, UsuarioListarView usuarioListarView, UsuarioDao usuarioDao){
        
        this.usuarioActualizarView = usuarioActualizarView;
        this.usuarioBuscarView = usuarioBuscarView;
        this.usuarioCrearView = usuarioCrearView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioListarView = usuarioListarView;
        
        configurarEventos();
    }
    
    private void configurarEventos(){
        
    }
    
}
