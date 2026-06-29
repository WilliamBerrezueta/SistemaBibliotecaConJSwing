/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.models;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class Libro {
    
    // ISBN en String porque este tiene - en su composicion de 13 numeros
    private String ISBN;
    private String titulo;
    private int añoDePublicacion;
    private String genero;
    private boolean disponible;
    private String editorial;
    private Autor autor;
    
    public Libro(){
    }

    public Libro(String ISBN, String titulo, int añoDePublicacion, String genero, boolean disponible, String editorial) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.añoDePublicacion = añoDePublicacion;
        this.genero = genero;
        this.disponible = disponible;
        this.editorial = editorial;
    }

    public Libro(String ISBN, String titulo, int añoDePublicacion, String genero, boolean disponible, String editorial, Autor autor) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.añoDePublicacion = añoDePublicacion;
        this.genero = genero;
        this.disponible = disponible;
        this.editorial = editorial;
        this.autor = autor;
    }
    
    

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAñoDePublicacion() {
        return añoDePublicacion;
    }

    public void setAñoDePublicacion(int añoDePublicacion) {
        this.añoDePublicacion = añoDePublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro" + "\n" +
               "-ISBN:" + ISBN + "\n" +
               "-Titulo: " + titulo + "\n" +
               "-Año de publicacion: " + añoDePublicacion + "\n" +
               "-Genero: " + genero + "\n" +
               "-Disponible: " + disponible + "\n" +
               "-Editorial: " + editorial + "\n" +
               "-Autor: " + autor ;
    }
    
    
    
    public void reservarLibro(){
        this.disponible = false;
    }
    
    public void devolverLibro(){
        this.disponible = true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.ISBN);
        hash = 29 * hash + Objects.hashCode(this.titulo);
        hash = 29 * hash + this.añoDePublicacion;
        hash = 29 * hash + Objects.hashCode(this.genero);
        hash = 29 * hash + (this.disponible ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.editorial);
        hash = 29 * hash + Objects.hashCode(this.autor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if (this.añoDePublicacion != other.añoDePublicacion) {
            return false;
        }
        if (this.disponible != other.disponible) {
            return false;
        }
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.editorial, other.editorial)) {
            return false;
        }
        return Objects.equals(this.autor, other.autor);
    }
   
    
    
    
}
    

