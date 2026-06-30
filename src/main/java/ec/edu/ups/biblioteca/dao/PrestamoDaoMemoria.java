/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Prestamo;
import ec.edu.ups.biblioteca.models.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class PrestamoDaoMemoria implements PrestamoDao {

    private List<Prestamo> listaPrestamo;
    
    public PrestamoDaoMemoria(){
        listaPrestamo = new ArrayList<>();
    }
    
    @Override
    public void crear(Prestamo prestamo) {
        listaPrestamo.add(prestamo);
    }

    @Override
    public Prestamo buscar(int codigo) {
        for(Prestamo prestamo : listaPrestamo){
            if(prestamo.getCodigo() == (codigo)){
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Prestamo prestamo) {
        for(int i = 0;i < listaPrestamo.size() ;i++){
           Prestamo prestamoEnconrtado = listaPrestamo.get(i);
           if(prestamoEnconrtado.getCodigo() == prestamo.getCodigo()){
               listaPrestamo.set(i,prestamo);
               break;
           }
       }
    }

    @Override
    public void eliminar(int codigo) {
        Prestamo prestamoEncontrado = buscar(codigo);
        if(prestamoEncontrado != null)
            listaPrestamo.remove(prestamoEncontrado); 
    }

    @Override
    public List<Prestamo> listar() {
        return listaPrestamo;
    }
    
}
