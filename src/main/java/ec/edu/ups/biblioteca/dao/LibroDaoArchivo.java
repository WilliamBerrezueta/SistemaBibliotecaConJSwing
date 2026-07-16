/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Genero;
import ec.edu.ups.biblioteca.models.Libro;
import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class LibroDaoArchivo implements LibroDao {

    private final int TAMANIO_ISBN = 18;
    private final int TAMANIO_TITULO = 50;
    private final int TAMANIO_GENERO = 30;
    private final int TAMANIO_EDITORIAL = 40;
    private final int TAMANIO_AUTOR = 40;
    // 4 = añoDePublicacion (int), 1 = disponible (boolean)
    private final int TAMANIO_REGISTRO = (TAMANIO_ISBN * 2) + (TAMANIO_TITULO * 2) + 4 + (TAMANIO_GENERO * 2) + 1 + (TAMANIO_EDITORIAL * 2) + (TAMANIO_AUTOR * 2);

    private final String rutaArchivo;
    private final AutorDao autorDao; // para validar la clave foránea antes de guardar

    public LibroDaoArchivo(String rutaArchivo, AutorDao autorDao) {
        this.rutaArchivo = rutaArchivo;
        this.autorDao = autorDao;
        
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

    // metodo para escribir un libro desde una posicion
    private void escribirLibro(RandomAccessFile raf, Libro libro) throws IOException {
        escribirString(raf, libro.getIsbn(), TAMANIO_ISBN);
        escribirString(raf, libro.getTitulo(), TAMANIO_TITULO);
        raf.writeInt(libro.getAñoDePublicacion());
        String nombreGenero = libro.getGenero() != null ? libro.getGenero().name() : "";
        escribirString(raf, nombreGenero, TAMANIO_GENERO);
        raf.writeBoolean(libro.isDisponible());
        escribirString(raf, libro.getEditorial(), TAMANIO_EDITORIAL);
        String nombreAutor = libro.getAutor() != null ? libro.getAutor().getNombre() : "";
        escribirString(raf, nombreAutor, TAMANIO_AUTOR);
    }

    // metodo para leer un libro desde una posicion y retornarlo como su clase
    private Libro leerLibro(RandomAccessFile raf) throws IOException {
        String isbn = leerString(raf, TAMANIO_ISBN);
        String titulo = leerString(raf, TAMANIO_TITULO);
        int año = raf.readInt();
        String genero = leerString(raf, TAMANIO_GENERO);
        boolean disponible = raf.readBoolean();
        String editorial = leerString(raf, TAMANIO_EDITORIAL);
        String nombreAutor = leerString(raf, TAMANIO_AUTOR);

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAñoDePublicacion(año);
        libro.setGenero(!genero.isEmpty() ? Genero.valueOf(genero) : null);
        libro.setDisponible(disponible);
        libro.setEditorial(editorial);
        libro.setAutor(!nombreAutor.isEmpty() ? autorDao.buscar(nombreAutor) : null); // reconstruye el objeto Autor completo
        return libro;
    }

    // metodo para escribir en el archivo
    private void escribirString(RandomAccessFile raf, String valor, int longitud) throws IOException {
        if (valor == null) {
            valor = "";
        }
        StringBuilder sb = new StringBuilder(valor);
        sb.setLength(longitud);
        raf.writeChars(sb.toString());
    }

    // metodo para leer lo que hay en el archivo
    private String leerString(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();
    }

    // metodo que busca la posicion (indice) de un libro por isbn 
    private long localizarIndice(RandomAccessFile raf, String isbn) throws IOException {
        long totalRegistros = raf.length() / TAMANIO_REGISTRO;
        for (long i = 0; i < totalRegistros; i++) {
            raf.seek(i * TAMANIO_REGISTRO);
            Libro actual = leerLibro(raf);
            if (actual.getIsbn().equalsIgnoreCase(isbn)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void crear(Libro libro) {
        // integridad referencial: no se guarda un libro cuyo autor no existe en autores.dat
        if (libro.getAutor() == null || autorDao.buscar(libro.getAutor().getNombre()) == null) {
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            raf.seek(raf.length());
            escribirLibro(raf, libro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Libro buscar(String isbn) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long indice = localizarIndice(raf, isbn);
            if (indice == -1) {
                return null;
            }
            raf.seek(indice * TAMANIO_REGISTRO);
            return leerLibro(raf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Libro> listar() {
        List<Libro> libros = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long totalRegistros = raf.length() / TAMANIO_REGISTRO;
            for (long i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANIO_REGISTRO);
                libros.add(leerLibro(raf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    @Override
    public void actualizar(Libro libro) {
        if (libro.getAutor() == null || autorDao.buscar(libro.getAutor().getNombre()) == null) {
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            long indice = localizarIndice(raf, libro.getIsbn());
            if (indice == -1) {
                return;
            }
            raf.seek(indice * TAMANIO_REGISTRO);
            escribirLibro(raf, libro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String isbn) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            long totalRegistros = raf.length() / TAMANIO_REGISTRO;
            long indice = localizarIndice(raf, isbn);
            if (indice == -1) {
                return;
            }

            // desplazamos cada registro siguiente una posicion hacia atras
            for (long i = indice; i < totalRegistros - 1; i++) {
                raf.seek((i + 1) * TAMANIO_REGISTRO);
                Libro siguiente = leerLibro(raf);
                raf.seek(i * TAMANIO_REGISTRO);
                escribirLibro(raf, siguiente);
            }

            // recortamos el archivo, ahora tiene un registro menos
            raf.setLength((totalRegistros - 1) * TAMANIO_REGISTRO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
