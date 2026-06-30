/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Usuario;
import java.util.List;

/**
 *
 * @author USER
 */
public interface UsuarioDao {
    
    void crear(Usuario usuario);
    Usuario buscar(String cedula);
    void actualizar(Usuario usuario);
    void eliminar(String cedula);
    List<Usuario> listar();
}
