/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Libro;
import java.util.List;

/**
 *
 * @author USER
 */
public interface LibroDao {
    void crear(Libro libro);
    Libro buscar(String isbn);
    void actualizar(Libro libro);
    void eliminar(String isbn);
    List<Libro> listar();
}
