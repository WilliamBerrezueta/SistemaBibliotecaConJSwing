/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Autor;
import ec.edu.ups.biblioteca.models.Genero;
import ec.edu.ups.biblioteca.models.Libro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class LibroDaoMemoria implements LibroDao{
    private List<Libro> listaLibros;
    
    public LibroDaoMemoria() {
    listaLibros = new ArrayList<>();

    crear(new Libro("111", "Effective Java", 2018, Genero.TECNICO, true,
                "Addison-Wesley",
                new Autor("Joshua Bloch", 1961, "Estados Unidos")));

        crear(new Libro("112", "Clean Code", 2008, Genero.TECNICO, true,
                "Prentice Hall",
                new Autor("Robert C. Martin", 1952, "Estados Unidos")));

        crear(new Libro("113", "Head First Java", 2022, Genero.TECNICO, true,
                "O'Reilly",
                new Autor("Kathy Sierra", 1957, "Estados Unidos")));

        crear(new Libro("114", "Core Java Volume I", 2018, Genero.TECNICO, true,
                "Pearson",
                new Autor("Cay S. Horstmann", 1960, "Alemania")));

        crear(new Libro("115", "Database System Concepts", 2019, Genero.TECNICO, true,
                "McGraw-Hill",
                new Autor("Abraham Silberschatz", 1952, "Israel")));
}

    @Override
    public void crear(Libro libro) {
        listaLibros.add(libro);
    }

    @Override
    public Libro buscar(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Libro libro) {
        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libroEncontrado = listaLibros.get(i);
            if (libroEncontrado.getIsbn().equalsIgnoreCase(libro.getIsbn())) {
                listaLibros.set(i, libro);
                break;
            }
        }
    }

    @Override
    public void eliminar(String isbn) {
        Libro libroEncontrado = buscar(isbn);
        if (libroEncontrado != null) {
            listaLibros.remove(libroEncontrado);
        }
    }

    @Override
    public List<Libro> listar() {
        return new ArrayList<>(listaLibros);
    }
    
}
