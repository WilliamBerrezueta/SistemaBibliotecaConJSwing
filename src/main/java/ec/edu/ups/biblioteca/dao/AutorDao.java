/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Autor;
import java.util.List;

/**
 *
 * @author USER
 */
public interface AutorDao {
    
    void crear(Autor autor);
    Autor buscar(String nombre);
    void actualizar(Autor autor);
    void eliminar(String nombre);
    List<Autor> listar();
    
}
