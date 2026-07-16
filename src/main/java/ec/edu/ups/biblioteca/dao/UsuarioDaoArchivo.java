/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;
 
import ec.edu.ups.biblioteca.models.Usuario;
import java.io.File;
 
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
 
public class UsuarioDaoArchivo implements UsuarioDao {
 
    private final int TAMANIO_NOMBRE = 40;
    private final int TAMANIO_CEDULA = 11;
    private final int TAMANIO_NUMERO = 15;
    private final int TAMANIO_REGISTRO = (TAMANIO_NOMBRE * 2) + (TAMANIO_CEDULA * 2) + (TAMANIO_NUMERO * 2);
 
    private final String rutaArchivo;
 
    public UsuarioDaoArchivo(String rutaArchivo) {
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
 
    // metodo para escribir un usuario desde una posicion
    // pedidos no se guarda aqui, porque es una lista de tamaño variable
    // y ademas duplicaria datos que ya estan en prestamos.dat. Si necesitas
    // los prestamos de un usuario, se consultan en PrestamoDao filtrando por cedula.
    private void escribirUsuario(RandomAccessFile raf, Usuario usuario) throws IOException {
        escribirString(raf, usuario.getNombre(), TAMANIO_NOMBRE);
        escribirString(raf, usuario.getCedula(), TAMANIO_CEDULA);
        escribirString(raf, usuario.getNumero(), TAMANIO_NUMERO);
    }
 
    // metodo para leer un usuario desde una posicion y retornarlo como su clase
    private Usuario leerUsuario(RandomAccessFile raf) throws IOException {
        String nombre = leerString(raf, TAMANIO_NOMBRE);
        String cedula = leerString(raf, TAMANIO_CEDULA);
        String numero = leerString(raf, TAMANIO_NUMERO);
 
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCedula(cedula);
        usuario.setNumero(numero);
        usuario.setPedidos(new ArrayList<>()); // si tu clase ya inicializa esto en el constructor, borra esta línea
        return usuario;
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
 
    // metodo que busca la posicion (indice) de un usuario por cedula, lo reutilizan buscar/actualizar/eliminar
    private long localizarIndice(RandomAccessFile raf, String cedula) throws IOException {
        long totalRegistros = raf.length() / TAMANIO_REGISTRO;
        for (long i = 0; i < totalRegistros; i++) {
            raf.seek(i * TAMANIO_REGISTRO);
            Usuario actual = leerUsuario(raf);
            if (actual.getCedula().equalsIgnoreCase(cedula)) {
                return i;
            }
        }
        return -1;
    }
 
    @Override
    public void crear(Usuario usuario) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            raf.seek(raf.length());
            escribirUsuario(raf, usuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public Usuario buscar(String cedula) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long indice = localizarIndice(raf, cedula);
            if (indice == -1) {
                return null;
            }
            raf.seek(indice * TAMANIO_REGISTRO);
            return leerUsuario(raf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long totalRegistros = raf.length() / TAMANIO_REGISTRO;
            for (long i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANIO_REGISTRO);
                usuarios.add(leerUsuario(raf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
 
    @Override
    public void actualizar(Usuario usuario) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            long indice = localizarIndice(raf, usuario.getCedula());
            if (indice == -1) {
                return;
            }
            raf.seek(indice * TAMANIO_REGISTRO);
            escribirUsuario(raf, usuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(String cedula) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            long totalRegistros = raf.length() / TAMANIO_REGISTRO;
            long indice = localizarIndice(raf, cedula);
            if (indice == -1) {
                return;
            }
 
            for (long i = indice; i < totalRegistros - 1; i++) {
                raf.seek((i + 1) * TAMANIO_REGISTRO);
                Usuario siguiente = leerUsuario(raf);
                raf.seek(i * TAMANIO_REGISTRO);
                escribirUsuario(raf, siguiente);
            }
 
            raf.setLength((totalRegistros - 1) * TAMANIO_REGISTRO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}