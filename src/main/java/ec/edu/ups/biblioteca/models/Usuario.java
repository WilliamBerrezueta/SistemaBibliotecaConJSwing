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
public class Usuario extends Persona{
    
    private String telefono;
    
    private Cuenta cuenta;
    private Prestamo prestamo;

    public Usuario(String telefono, Cuenta cuenta) {
        this.telefono = telefono;
        this.cuenta = cuenta;
    }
    
    public Usuario(){
    }

    public Usuario(String nombre, int añoDeNacimiento,String telefono,String cuenta,String contraseña) {
        super(nombre, añoDeNacimiento);
        this.telefono = telefono;
        this.cuenta = new Cuenta(cuenta,contraseña);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }
    
    //metodo

    public void añadirPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "telefono=" + telefono + ", cuenta=" + cuenta + ", prestamo=" + prestamo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.telefono);
        hash = 53 * hash + Objects.hashCode(this.cuenta);
        hash = 53 * hash + Objects.hashCode(this.prestamo);
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
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.cuenta, other.cuenta)) {
            return false;
        }
        return Objects.equals(this.prestamo, other.prestamo);
    }
    
}
