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
public class Cuenta {
    //
    private String correo;
    private String contraseña;

    public Cuenta(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.correo);
        hash = 29 * hash + Objects.hashCode(this.contraseña);
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
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return Objects.equals(this.contraseña, other.contraseña);
    }

    
    
    
}
