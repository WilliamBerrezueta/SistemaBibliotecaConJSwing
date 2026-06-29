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
public class Autor extends Persona{
    //
    private String apellido;
    private String nacionalidad;

    public Autor(String apellido, String nacionalidad) {
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }
    
    public Autor(){
    }

    public Autor(String apellido, String nombre, String nacionalidad, int añoDeNacimiento) {
        super(nombre, añoDeNacimiento);
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        String resultado = super.toString();
        resultado += "\n" +
               "\t" +"Apellido=" + apellido + "\n" +
               "\t" +"Nacionalidad=" + nacionalidad ;
        return resultado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.apellido);
        hash = 29 * hash + Objects.hashCode(this.nacionalidad);
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
        final Autor other = (Autor) obj;
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        return Objects.equals(this.nacionalidad, other.nacionalidad);
    }
    
    
}