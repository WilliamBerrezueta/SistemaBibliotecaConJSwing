/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controller;

import ec.edu.ups.biblioteca.dao.UsuarioDao;
import ec.edu.ups.biblioteca.models.Usuario;
import ec.edu.ups.biblioteca.view.UsuarioActualizarView;
import ec.edu.ups.biblioteca.view.UsuarioBuscarView;
import ec.edu.ups.biblioteca.view.UsuarioCrearView;
import ec.edu.ups.biblioteca.view.UsuarioEliminarView;
import ec.edu.ups.biblioteca.view.UsuarioListarView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class UsuarioController {

    private UsuarioDao usuarioDao;
    private UsuarioActualizarView usuarioActualizarView;
    private UsuarioBuscarView usuarioBuscarView;
    private UsuarioCrearView usuarioCrearView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioListarView usuarioListarView;

    public UsuarioController(UsuarioActualizarView usuarioActualizarView, UsuarioBuscarView usuarioBuscarView, UsuarioCrearView usuarioCrearView, UsuarioEliminarView usuarioEliminarView, UsuarioListarView usuarioListarView, UsuarioDao usuarioDao) {

        this.usuarioActualizarView = usuarioActualizarView;
        this.usuarioBuscarView = usuarioBuscarView;
        this.usuarioCrearView = usuarioCrearView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioDao = usuarioDao;

        configurarEventoUsuarioCrear();
        configurarEventoUsuarioBuscar();
        configurarEventoUsuarioEliminar();
        configurarEventoUsuarioActualizar();

    }
    // METODOS CREAR

    public void crearUsuario() {
        String nombre = usuarioCrearView.getTxtNombreUsuarioCrear().getText();
        String cedula = usuarioCrearView.getTxtCedulaUsuarioCrear().getText();
        String celular = usuarioCrearView.getTxtTelefonoUsuarioCrear().getText();

        Usuario usuario = new Usuario(nombre, cedula, celular);
        usuarioDao.crear(usuario);
        listarUsuarios();
        usuarioCrearView.mostarMensaje("Se ha creado su usuario");
    }

    public void limpiarUsuarioCrear() {
        usuarioCrearView.getTxtNombreUsuarioCrear().setText("");
        usuarioCrearView.getTxtCedulaUsuarioCrear().setText("");
        usuarioCrearView.getTxtTelefonoUsuarioCrear().setText("");
    }

    public void configurarEventoUsuarioCrear() {
        usuarioCrearView.getBtnCrearUsuarioCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
            }
        });
        usuarioCrearView.getBtnCancelarUsuarioCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioCrearView.dispose();
            }
        });
        usuarioCrearView.getBtnLimpiarUsuarioCrear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarUsuarioCrear();
            }
        });
    }
    // METODO BUSCAR

    public void buscarUsuario() {
        if (usuarioBuscarView != null) {
            JTextField cedulaJ = usuarioBuscarView.getTxtCedulaUsuarioBuscar();
            String cedula = cedulaJ.getText();
            Usuario usuarioBuscar = usuarioDao.buscar(cedula);

            if (usuarioBuscar != null) {
                usuarioBuscarView.getTxtNombreUsuarioBuscar().setText(usuarioBuscar.getNombre());
                usuarioBuscarView.getTxtCedulaUsuarioBuscar().setText(usuarioBuscar.getCedula());
                usuarioBuscarView.getTxtTelefonoUsuarioBuscar().setText(usuarioBuscar.getNumero());
            }
        }
    }

    public void limpiarUsuarioBuscar() {
        usuarioBuscarView.getTxtNombreUsuarioBuscar().setText("");
        usuarioBuscarView.getTxtCedulaUsuarioBuscar().setText("");
        usuarioBuscarView.getTxtTelefonoUsuarioBuscar().setText("");
    }

    public void configurarEventoUsuarioBuscar() {
        usuarioBuscarView.getBtnBuscarUsuarioBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
        usuarioBuscarView.getBtnCancelarUsuarioBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioBuscarView.dispose();
            }
        });
        usuarioBuscarView.getBtnLimpiarUsuarioBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarUsuarioBuscar();
            }
        });
    }

// METODOS ELIMINAR
    public void eliminarUsuario() {
        if (usuarioEliminarView != null) {
            JTextField cedulaJ = usuarioEliminarView.getTxtCedulaUsuarioEliminar();
            String cedula = cedulaJ.getText();
            Usuario eliminarUsuario = usuarioDao.buscar(cedula);

            if (eliminarUsuario != null) {
                int seguro = usuarioEliminarView.mostarMensaje("Seguro que quieres eliminar este usuario");
                if (seguro == 0) {
                    usuarioDao.eliminar(cedula);
                    listarUsuarios();
                }
            }
        }
    }

    public void limpiarUsuarioEliminar() {
        usuarioEliminarView.getTxtNombreUsuarioEliminar().setText("");
        usuarioEliminarView.getTxtCedulaUsuarioEliminar().setText("");
        usuarioEliminarView.getTxtTelefonoUsuarioEliminar().setText("");
    }

    public void buscarUsuarioEliminar() {
        if (usuarioEliminarView != null) {
            JTextField cedulaJ = usuarioEliminarView.getTxtCedulaUsuarioEliminar();
            String cedula = cedulaJ.getText();
            Usuario usuarioBuscar = usuarioDao.buscar(cedula);

            if (usuarioBuscar != null) {
                usuarioEliminarView.getTxtNombreUsuarioEliminar().setText(usuarioBuscar.getNombre());
                usuarioEliminarView.getTxtCedulaUsuarioEliminar().setText(usuarioBuscar.getCedula());
                usuarioEliminarView.getTxtTelefonoUsuarioEliminar().setText(usuarioBuscar.getNumero());
            }
        }
    }

    public void configurarEventoUsuarioEliminar() {
        usuarioEliminarView.getBtnBuscarUsuarioEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioEliminar();
            }
        });
        usuarioEliminarView.getBtnCancelarUsuarioEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioEliminarView.dispose();
            }
        });
        usuarioEliminarView.getBtnEliminarUsuarioEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarUsuarioEliminar();
            }
        });
        usuarioEliminarView.getBtnEliminarUsuarioEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
    }

// METODOS ACTUALIZAR
    public void actualizarUsuario() {
        String cedula = usuarioActualizarView.getTxtCedulaUsuarioActualizar().getText();
        Usuario usuario = usuarioDao.buscar(cedula);

        if (usuario != null) {
            usuario.setNombre(usuarioActualizarView.getTxtNombreUsuarioActualizar().getText());
            usuario.setNumero(usuarioActualizarView.getTxtTelefonoUsuarioActualizar().getText());

            usuarioDao.actualizar(usuario);
            listarUsuarios();
            usuarioActualizarView.mostarMensaje("Usuario actualizado");
        }
    }

    public void buscarUsuarioActualizar() {
        if (usuarioActualizarView != null) {
            JTextField cedulaJ = usuarioActualizarView.getTxtCedulaUsuarioActualizar();
            String cedula = cedulaJ.getText();
            Usuario usuarioBuscar = usuarioDao.buscar(cedula);

            if (usuarioBuscar != null) {
                usuarioActualizarView.getTxtNombreUsuarioActualizar().setText(usuarioBuscar.getNombre());
                usuarioActualizarView.getTxtTelefonoUsuarioActualizar().setText(usuarioBuscar.getNumero());
            }
        }
    }

    public void limpiarUsuarioActualizar() {
        usuarioActualizarView.getTxtNombreUsuarioActualizar().setText("");
        usuarioActualizarView.getTxtCedulaUsuarioActualizar().setText("");
        usuarioActualizarView.getTxtTelefonoUsuarioActualizar().setText("");
    }

    public void configurarEventoUsuarioActualizar() {
        usuarioActualizarView.getBtnActualizarUsuarioActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });
        usuarioActualizarView.getBtnBuscarUsuarioActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioActualizar();
            }
        });
        usuarioActualizarView.getBtnCancelarUsuarioActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioActualizarView.dispose();
            }
        });
        usuarioActualizarView.getBtnLimpiarUsuarioActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarUsuarioActualizar();
            }
        });
    }
    
    //METODO LISTAR
    
    public void listarUsuarios() {
        usuarioListarView.cargarDatos(usuarioDao.listar());
    }

}
