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
public class Persona {
    private String nombre;
    private int yearDeNacimiento;
    
    public Persona(){
        
    }    
    public Persona(String nombre, int añoDeNacimiento) {
        this.nombre = nombre;
        this.yearDeNacimiento = yearDeNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getYearDeNacimiento() {
        return yearDeNacimiento;
    }

    public void setYearDeNacimiento(int yearDeNacimiento) {
        this.yearDeNacimiento = yearDeNacimiento;
    }

    @Override
    public String toString() {
        return  
                "\n" + "\t" +"Nombre=" + nombre + "\n" +
                "\t" + "AñoDeNacimiento=" + yearDeNacimiento ;
    }   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + this.yearDeNacimiento;
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + this.yearDeNacimiento;
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
        final Persona other = (Persona) obj;
        if (this.yearDeNacimiento != other.yearDeNacimiento) {
            return false;
        }
        if (this.yearDeNacimiento != other.yearDeNacimiento) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
    
}
