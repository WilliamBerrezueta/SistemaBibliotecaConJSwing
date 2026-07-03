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

    public UsuarioDaoMemoria() {
        listaUsuarios = new ArrayList<>();

        crear(new Usuario("Andrés Jaramillo", "1111111111", "0991111111"));
        crear(new Usuario("Daniela Cárdenas", "2222222222", "0992222222"));
        crear(new Usuario("José González", "3333333333", "0993333333"));
        crear(new Usuario("Valeria Mora", "4444444444", "0994444444"));
        crear(new Usuario("Mateo Ortega", "5555555555", "0995555555"));
        crear(new Usuario("Emily Castillo", "6666666666", "0996666666"));
        crear(new Usuario("Sebastián Romero", "7777777777", "0997777777"));
        crear(new Usuario("Camila Vázquez", "8888888888", "0998888888"));
        crear(new Usuario("Nicolás Herrera", "9999999999", "0999999999"));
        crear(new Usuario("Isabella Torres", "0000000000", "0981234567"));
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
