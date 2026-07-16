/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ec.edu.ups.biblioteca.models;

import static ec.edu.ups.biblioteca.models.Genero.values;

/**
 *
 * @author USER
 */
public enum Genero {

    NOVELA("Novela"),
    CIENCIA_FICCION("Ciencia Ficción"),
    FANTASIA("Fantasía"),
    HISTORIA("Historia"),
    INFANTIL("Infantil"),
    POESIA("Poesía"),
    TERROR("Terror"),
    ROMANCE("Romance"),
    BIOGRAFIA("Biografía"),
    TECNICO("Técnico");

    private final String nombre;

    Genero(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


//      Busca un Genero a partir de un texto libre (nombre visible o nombre de
//      la constante), ignorando mayúsculas/minúsculas. Útil para validar
//      campos de texto que todavía no fueron migrados a un JComboBox.
//     
//      @param texto texto ingresado por el usuario
//      @return el Genero encontrado
//      @throws IllegalArgumentException si el texto no corresponde a ningún género válido
     
    public static Genero fromTexto(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("El género no puede ser nulo");
        }
        String t = texto.trim();
        for (Genero g : values()) {
            if (g.nombre.equalsIgnoreCase(t)
                    || g.name().equalsIgnoreCase(t)
                    || g.name().replace('_', ' ').equalsIgnoreCase(t)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Género no válido: " + texto);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
