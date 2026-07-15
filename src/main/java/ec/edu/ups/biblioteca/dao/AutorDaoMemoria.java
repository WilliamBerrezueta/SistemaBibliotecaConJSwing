/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Autor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class AutorDaoMemoria implements AutorDao {
    
    private List<Autor> listaAutores;

    public AutorDaoMemoria() {

        listaAutores = new ArrayList<>();

        crear(new Autor("Joshua Bloch",1961,"Estados Unidos"));
        crear(new Autor("Robert C. Martin",1952,"Estados Unidos"));
        crear(new Autor("Kathy Sierra",1957,"Estados Unidos"));
        crear(new Autor("Cay S. Horstmann",1960,"Alemania"));
        crear(new Autor("Abraham Silberschatz",1952,"Israel"));

    }

    @Override
    public void crear(Autor autor) {
        listaAutores.add(autor);
    }

    @Override
    public Autor buscar(String nombre) {

        for (Autor autor : listaAutores) {

            if (autor.getNombre().equalsIgnoreCase(nombre)) {
                return autor;
            }

        }

        return null;
    }

    @Override
    public void actualizar(Autor autor) {

        for(int i=0;i<listaAutores.size();i++){

            Autor encontrado = listaAutores.get(i);

            if(encontrado.getNombre().equalsIgnoreCase(autor.getNombre())){

                listaAutores.set(i,autor);

                break;

            }

        }

    }

    @Override
    public void eliminar(String nombre) {

        Autor autor = buscar(nombre);

        if(autor!=null){

            listaAutores.remove(autor);

        }

    }

    @Override
    public List<Autor> listar() {

        return new ArrayList<>(listaAutores);

    }
    
}
