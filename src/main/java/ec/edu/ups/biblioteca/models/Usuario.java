/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Usuario {
    
    private String nombre;
    private String cedula;
    private List<Prestamo> pedidos;
  
    public Usuario(){
        
    }

    public Usuario(String nombre, String cedula, List<Prestamo> pedidos) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.pedidos = new ArrayList<>();
    }

    public List<Prestamo> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Prestamo> pedidos) {
        this.pedidos = pedidos;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.cedula);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.cedula, other.cedula);
    }
    
}
