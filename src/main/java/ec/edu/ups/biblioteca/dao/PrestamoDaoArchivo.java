/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.models.Prestamo;
import ec.edu.ups.biblioteca.models.Usuario;
import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDaoArchivo implements PrestamoDao {
    
    private final int MAX_LIBROS_POR_PRESTAMO = 5; // necesito tener un maximo de libros por prestamo 

    private final int TAMANIO_ISBN_FK = 18;   // isbn guardado dentro del prestamo (no se de otra forma)
    private final int TAMANIO_CEDULA_FK = 11; // cedula guardada dentro del prestamo (no se de otra forma)

    // 4 = codigo (int), 8+8 = fechaDePrestamo/fechaDeDevolucion (long), 1 = pedidoHecho (boolean)
    private final int TAMANIO_REGISTRO = 4 + 8 + 8 + 1 + (MAX_LIBROS_POR_PRESTAMO * TAMANIO_ISBN_FK * 2) + (TAMANIO_CEDULA_FK * 2);

    // LocalDate no se puede escribir directo en RandomAccessFile, se guarda como epochDay (long).
    private final long SIN_FECHA = Long.MIN_VALUE;

    private final String rutaArchivo;
    private final LibroDao libroDao;
    private final UsuarioDao usuarioDao;

    public PrestamoDaoArchivo(String rutaArchivo, LibroDao libroDao, UsuarioDao usuarioDao) {
        this.rutaArchivo = rutaArchivo;
        this.libroDao = libroDao;
        this.usuarioDao = usuarioDao;
        
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

    // metodo para escribir un prestamo desde una posicion
    private void escribirPrestamo(RandomAccessFile raf, Prestamo prestamo) throws IOException {
        raf.writeInt(prestamo.getCodigo());
        raf.writeLong(prestamo.getFechaDePrestamo() != null ? prestamo.getFechaDePrestamo().toEpochDay() : SIN_FECHA);
        raf.writeLong(prestamo.getFechaDeDevolucion() != null ? prestamo.getFechaDeDevolucion().toEpochDay() : SIN_FECHA);
        raf.writeBoolean(prestamo.isPedidoHecho());

        List<Libro> libros = prestamo.getListaLibros();
        for (int i = 0; i < MAX_LIBROS_POR_PRESTAMO; i++) {
            String isbn = (libros != null && i < libros.size()) ? libros.get(i).getIsbn() : "";
            escribirString(raf, isbn, TAMANIO_ISBN_FK);
        }

        String cedula = prestamo.getUsuario() != null ? prestamo.getUsuario().getCedula() : "";
        escribirString(raf, cedula, TAMANIO_CEDULA_FK);
    }

    // metodo para leer un prestamo desde una posicion y retornarlo como su clase
    // aqui se reconstruyen los objetos Libro y Usuario consultando sus propios dao,
    // en vez de guardar copias completas dentro del registro de Prestamo
    private Prestamo leerPrestamo(RandomAccessFile raf) throws IOException {
        int codigo = raf.readInt();
        long epochPrestamo = raf.readLong();
        long epochDevolucion = raf.readLong();
        boolean pedidoHecho = raf.readBoolean();

        List<Libro> libros = new ArrayList<>();
        for (int i = 0; i < MAX_LIBROS_POR_PRESTAMO; i++) {
            String isbn = leerString(raf, TAMANIO_ISBN_FK);
            if (!isbn.isEmpty()) {
                Libro libro = libroDao.buscar(isbn);
                if (libro != null) {
                    libros.add(libro);
                }
            }
        }

        String cedula = leerString(raf, TAMANIO_CEDULA_FK);

        Prestamo prestamo = new Prestamo();
        prestamo.setCodigo(codigo);
        prestamo.setFechaDePrestamo(epochPrestamo != SIN_FECHA ? LocalDate.ofEpochDay(epochPrestamo) : null); // forma Tri, para poder guardar una fecha lo convertimos a long
        prestamo.setFechaDeDevolucion(epochDevolucion != SIN_FECHA ? LocalDate.ofEpochDay(epochDevolucion) : null);
        prestamo.setPedidoHecho(pedidoHecho);
        prestamo.setLibros(libros);
        prestamo.setUsuario(!cedula.isEmpty() ? usuarioDao.buscar(cedula) : null);
        return prestamo;
    }

    private void escribirString(RandomAccessFile raf, String valor, int longitud) throws IOException {
        if (valor == null) {
            valor = "";
        }
        StringBuilder sb = new StringBuilder(valor);
        sb.setLength(longitud);
        raf.writeChars(sb.toString());
    }

    private String leerString(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();
    }

    // metodo que busca la posicion (indice) de un prestamo por codigo, lo reutilizan buscar/actualizar/eliminar
    private long localizarIndice(RandomAccessFile raf, int codigo) throws IOException {
        long totalRegistros = raf.length() / TAMANIO_REGISTRO;
        for (long i = 0; i < totalRegistros; i++) {
            raf.seek(i * TAMANIO_REGISTRO);
            Prestamo actual = leerPrestamo(raf);
            if (actual.getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void crear(Prestamo prestamo) {
        if (prestamo.getListaLibros() != null && prestamo.getListaLibros().size() > MAX_LIBROS_POR_PRESTAMO) {
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            raf.seek(raf.length());
            escribirPrestamo(raf, prestamo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Prestamo buscar(int codigo) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long indice = localizarIndice(raf, codigo);
            if (indice == -1) {
                return null;
            }
            raf.seek(indice * TAMANIO_REGISTRO);
            return leerPrestamo(raf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Prestamo> listar() {
        List<Prestamo> prestamos = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long totalRegistros = raf.length() / TAMANIO_REGISTRO;
            for (long i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANIO_REGISTRO);
                prestamos.add(leerPrestamo(raf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public void actualizar(Prestamo prestamo) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            long indice = localizarIndice(raf, prestamo.getCodigo());
            if (indice == -1) {
                return;
            }
            raf.seek(indice * TAMANIO_REGISTRO);
            escribirPrestamo(raf, prestamo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int codigo) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            long totalRegistros = raf.length() / TAMANIO_REGISTRO;
            long indice = localizarIndice(raf, codigo);
            if (indice == -1) {
                return;
            }

            for (long i = indice; i < totalRegistros - 1; i++) {
                raf.seek((i + 1) * TAMANIO_REGISTRO);
                Prestamo siguiente = leerPrestamo(raf);
                raf.seek(i * TAMANIO_REGISTRO);
                escribirPrestamo(raf, siguiente);
            }

            raf.setLength((totalRegistros - 1) * TAMANIO_REGISTRO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}