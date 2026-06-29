/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Prestamo {
    
    private int codigo;
    private LocalDate fechaDePrestamo;
    private LocalDate fechaDeDevolucion;
    private boolean pedidoHecho;
    private List<Libro> libros;
    
    public Prestamo(){
        this.libros = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaDePrestamo() {
        return fechaDePrestamo;
    }

    public void setFechaDePrestamo(LocalDate fechaDePrestamo) {
        this.fechaDePrestamo = fechaDePrestamo;
    }

    public LocalDate getFechaDeDevolucion() {
        return fechaDeDevolucion;
    }

    public void setFechaDeDevolucion(LocalDate fechaDeDevolucion) {
        this.fechaDeDevolucion = fechaDeDevolucion;
    }

    public boolean isPedidoHecho() {
        return pedidoHecho;
    }
    public List<Libro> getListaLibros(){
    return libros;
    }
    
    ////

    public void prestamoHecho(LocalDate fechaDePrestamo) {
        this.pedidoHecho = true;
        this.fechaDePrestamo = fechaDePrestamo;
    }
    
    public void agregarLibro(Libro libro){
        this.libros.add(libro);
    }
    
    
    // este toString sirve para imprimir bien lo que necesita ver el usuario 

    @Override
    public String toString() {
        if(fechaDeDevolucion==null && fechaDePrestamo==null){
            return "----PRESTAMO----" + "\n" +
                    "-codigo=" + codigo +  "\n" +
                    "-pedidoHecho=" + pedidoHecho + "\n" +
                    "-libros=" + "\n" + libros ;
        }
        
        else if(fechaDeDevolucion==null){
            return "----Prestamo----" + "\n" +
                   "-codigo: " + codigo + "\n" +
                   "-fechaDePrestamo: " + fechaDePrestamo + "\n" +
                   "-pedidoHecho: " + pedidoHecho + "\n" +
                   "-libro: "+ "\n" + libros ;
        }
        else{
        return "----PRESTAMO----: " + "\n" +
               "-codigo=" + codigo + "\n" +
               "-fechaDePrestamo=" + fechaDePrestamo + "\n" +
               "-fechaDeDevolucion=" + fechaDeDevolucion + "\n" +
               "-pedidoHecho=" + pedidoHecho + "\n" +
                libros ;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codigo;
        hash = 53 * hash + Objects.hashCode(this.fechaDePrestamo);
        hash = 53 * hash + Objects.hashCode(this.fechaDeDevolucion);
        hash = 53 * hash + (this.pedidoHecho ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.libros);
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
        final Prestamo other = (Prestamo) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.pedidoHecho != other.pedidoHecho) {
            return false;
        }
        if (!Objects.equals(this.fechaDePrestamo, other.fechaDePrestamo)) {
            return false;
        }
        if (!Objects.equals(this.fechaDeDevolucion, other.fechaDeDevolucion)) {
            return false;
        }
        return Objects.equals(this.libros, other.libros);
    }
      
    
}
