/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class UsuarioDaoMemoria implements UsuarioDao{
    
    private List<Usuario> listaUsuarios;
    
    public UsuarioDaoMemoria(){
        listaUsuarios = new ArrayList<>();
    }
    
    @Override
    public void crear(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    @Override
    public Usuario buscar(String cedula) {
        for(Usuario usuario : listaUsuarios){
            if(usuario.getCedula().equalsIgnoreCase(cedula)){
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Usuario usuario) {
        for(int i = 0;i < listaUsuarios.size() ;i++){
           Usuario usuarioEnconrtado = listaUsuarios.get(i);
           if(usuarioEnconrtado.getCedula().equalsIgnoreCase(usuario.getCedula())){
               listaUsuarios.set(i,usuario);
               break;
           }
       }
    }

    @Override
    public void eliminar(String cedula) {
        Usuario usuarioEncontrado = buscar(cedula);
        if(usuarioEncontrado != null)
            listaUsuarios.remove(usuarioEncontrado); 
    }

    @Override
    public List<Usuario> listar() {
        return listaUsuarios;
    }

    
    
}
