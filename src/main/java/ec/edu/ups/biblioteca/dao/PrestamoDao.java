/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Prestamo;
import java.util.List;

/**
 *
 * @author USER
 */
public interface PrestamoDao {
    
    void crear(Prestamo prestamo);
    Prestamo buscar(int codigo);
    void actualizar(Prestamo prestamo);
    void eliminar(int codigo);
    List<Prestamo> listar();
    
}
