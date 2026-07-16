/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Autor;
import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class AutorDaoArchivo implements AutorDao {

    private final int TAMANIO_NOMBRE = 40;
    private final int TAMANIO_NACIONALIDAD = 30;
    private final int TAMANIO_REGISTRO = (TAMANIO_NOMBRE * 2) + (TAMANIO_NACIONALIDAD * 2) + 4;// el 4 es el año en bytes

    private final String rutaArchivo;

    public AutorDaoArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;

        File archivo = new File(rutaArchivo);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // metodo para ecribir un autor desde una posicion
    private void escribirAutor(RandomAccessFile raf, Autor autor) throws IOException {// se debe de declarar esta Exepcion, si no, no compila
        escribirString(raf, autor.getNombre(), TAMANIO_NOMBRE);
        escribirString(raf, autor.getNacionalidad(), TAMANIO_NACIONALIDAD);
        raf.writeInt(autor.getYearDeNacimiento());
    }

    // metodo para leer un autor desde una posicion y retornalo como su clase
    private Autor leerAutor(RandomAccessFile raf) throws IOException {
        String nombre = leerString(raf, TAMANIO_NOMBRE);
        String nacionalidad = leerString(raf, TAMANIO_NACIONALIDAD);
        int year = raf.readInt();

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);
        autor.setYearDeNacimiento(year);
        return autor;
    }

    // metodo para escribir en el archivo
    private void escribirString(RandomAccessFile raf, String valor, int longitud) throws IOException {
        if (valor == null) {
            valor = "";
        }
        StringBuilder sb = new StringBuilder(valor); // StringBuffer da muchos problemas
        sb.setLength(longitud); // pone un tamaño definido y rellena todo con '\u0000' si es mas chico y lo corta si es mas largo
        raf.writeChars(sb.toString()); // es para ecribir el con bytes el String
    }

    // metodo para leer lo que hay en el archivo
    private String leerString(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());// agrega cada caracter al String sb  
        }
        return sb.toString().trim();
    }

    // metodo que busca la posicion de un autor por nombre.
    private long localizarIndice(RandomAccessFile raf, String nombre) throws IOException {
        long totalRegistros = raf.length() / TAMANIO_REGISTRO;
        for (long i = 0; i < totalRegistros; i++) {
            raf.seek(i * TAMANIO_REGISTRO);
            Autor actual = leerAutor(raf);
            if (actual.getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void crear(Autor autor) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            raf.seek(raf.length());
            escribirAutor(raf, autor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Autor buscar(String nombre) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long indice = localizarIndice(raf, nombre);
            if (indice == -1) {
                return null;
            }
            raf.seek(indice * TAMANIO_REGISTRO);
            return leerAutor(raf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Autor> listar() {
        List<Autor> autores = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long totalRegistros = raf.length() / TAMANIO_REGISTRO;
            for (long i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANIO_REGISTRO);
                autores.add(leerAutor(raf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return autores;
    }

    @Override
    public void actualizar(Autor autor) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            long indice = localizarIndice(raf, autor.getNombre());
            if (indice == -1) {
                return;
            }
            raf.seek(indice * TAMANIO_REGISTRO);
            escribirAutor(raf, autor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String nombre) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            long totalRegistros = raf.length() / TAMANIO_REGISTRO;
            long indice = localizarIndice(raf, nombre);
            if (indice == -1) {
                return;
            }

            // desplazamos cada registro siguiente una posicion hacia atras
            for (long i = indice; i < totalRegistros - 1; i++) {
                raf.seek((i + 1) * TAMANIO_REGISTRO);
                Autor siguiente = leerAutor(raf);
                raf.seek(i * TAMANIO_REGISTRO);
                escribirAutor(raf, siguiente);
            }

            // recortamos el archivo, ahora tiene un registro menos
            raf.setLength((totalRegistros - 1) * TAMANIO_REGISTRO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
