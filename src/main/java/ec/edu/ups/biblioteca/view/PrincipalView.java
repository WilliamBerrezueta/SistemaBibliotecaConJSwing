/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package ec.edu.ups.biblioteca.view;

import ec.edu.ups.biblioteca.controller.LibroController;
import ec.edu.ups.biblioteca.controller.PrestamoController;
import ec.edu.ups.biblioteca.controller.UsuarioController;
import ec.edu.ups.biblioteca.dao.LibroDao;
import ec.edu.ups.biblioteca.dao.LibroDaoMemoria;
import ec.edu.ups.biblioteca.dao.PrestamoDao;
import ec.edu.ups.biblioteca.dao.PrestamoDaoMemoria;
import ec.edu.ups.biblioteca.dao.UsuarioDao;
import ec.edu.ups.biblioteca.dao.UsuarioDaoMemoria;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author USER
 */
public class PrincipalView extends javax.swing.JFrame {

    private LibroDao libroDao;
    private LibroController libroController;
    private LibroActualizarView libroActualizarView;
    private LibroBuscarView libroBuscarView;
    private LibroCrearView libroCrearView;
    private LibroEliminarView libroEliminarView;
    private LibroListarView libroListarView;

    private UsuarioDao usuarioDao;
    private UsuarioController usuarioController;
    private UsuarioActualizarView usuarioActualizarView;
    private UsuarioBuscarView usuarioBuscarView;
    private UsuarioCrearView usuarioCrearView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioListarView usuarioListarView;

    private PrestamoDao prestamoDao;
    private PrestamoController prestamoController;
    private RegistrarDevolucionView prestamoActualizarView;
    private PrestamoBuscarView prestamoBuscarView;
    private PrestamoCrearView prestamoCrearView;
    private PrestamoEliminarView prestamoEliminarView;
    private PrestamoListarView prestamoListarView;

    /**
     * Creates new form Principal
     */
    public PrincipalView() {
        initComponents();

        libroActualizarView = new LibroActualizarView();
        libroBuscarView = new LibroBuscarView();
        libroCrearView = new LibroCrearView();
        libroEliminarView = new LibroEliminarView();
        libroListarView = new LibroListarView();
        libroDao = new LibroDaoMemoria();
        libroController = new LibroController(libroActualizarView, libroBuscarView, libroCrearView, libroEliminarView, libroListarView, libroDao);

        libroController.listarLibros();

        usuarioActualizarView = new UsuarioActualizarView();
        usuarioBuscarView = new UsuarioBuscarView();
        usuarioCrearView = new UsuarioCrearView();
        usuarioEliminarView = new UsuarioEliminarView();
        usuarioListarView = new UsuarioListarView();
        usuarioDao = new UsuarioDaoMemoria();
        usuarioController = new UsuarioController(usuarioActualizarView, usuarioBuscarView, usuarioCrearView, usuarioEliminarView, usuarioListarView, usuarioDao);

        usuarioController.listarUsuarios();

        prestamoActualizarView = new RegistrarDevolucionView();
        prestamoBuscarView = new PrestamoBuscarView();
        prestamoCrearView = new PrestamoCrearView();
        prestamoEliminarView = new PrestamoEliminarView();
        prestamoEliminarView = new PrestamoEliminarView();
        prestamoListarView = new PrestamoListarView();
        prestamoDao = new PrestamoDaoMemoria();
        prestamoController = new PrestamoController(prestamoCrearView, prestamoDao, prestamoActualizarView, prestamoEliminarView, prestamoListarView, usuarioDao, libroDao, prestamoBuscarView);
    }

    public void cambiarIdioma(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("ec.edu.ups.biblioteca.i18n.mensajes", locale);

        menuItemLibro.setText(bundle.getString("menu.libro"));
        menuItemUsuario.setText(bundle.getString("menu.usuario"));
        menuItemPrestamo.setText(bundle.getString("menu.prestamo"));
        menuItemIdioma.setText(bundle.getString("menu.idioma"));
        menuItemSistema.setText(bundle.getString("menu.sistema"));

        menuItemSistemaSalir.setText(bundle.getString("menu.salir"));

        menuItemLibroCrear.setText(bundle.getString("menu.crear"));
        menuItemLibroBuscar.setText(bundle.getString("menu.buscar"));
        menuItemLibroActualizar.setText(bundle.getString("menu.actualizar"));
        menuItemLibroEliminar.setText(bundle.getString("menu.eliminar"));
        menuItemLibroListar.setText(bundle.getString("menu.listar"));

        menuItemUsuarioCrear.setText(bundle.getString("menu.crear"));
        menuItemUsuarioBuscar.setText(bundle.getString("menu.buscar"));
        menuItemUsuarioActualizar.setText(bundle.getString("menu.actualizar"));
        menuItemUsuarioEliminar.setText(bundle.getString("menu.eliminar"));
        menuItemUsuarioListar.setText(bundle.getString("menu.listar"));

        menuItemPrestamoCrear.setText(bundle.getString("menu.crear"));
        menuItemPrestamoBuscar.setText(bundle.getString("menu.buscar"));
        menuItemPrestamoActualizar.setText(bundle.getString("menu.actualizar"));
        menuItemPrestamoEliminar.setText(bundle.getString("menu.eliminar"));
        menuItemPrestamoListar.setText(bundle.getString("menu.listar"));

        menuItemIdiomaEspañol.setText(bundle.getString("menu.idioma.espanol"));
        menuItemIdiomaIngles.setText(bundle.getString("menu.idioma.ingles"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        desktopPane = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuItemLibro = new javax.swing.JMenu();
        menuItemLibroCrear = new javax.swing.JMenuItem();
        menuItemLibroBuscar = new javax.swing.JMenuItem();
        menuItemLibroActualizar = new javax.swing.JMenuItem();
        menuItemLibroEliminar = new javax.swing.JMenuItem();
        menuItemLibroListar = new javax.swing.JMenuItem();
        menuItemUsuario = new javax.swing.JMenu();
        menuItemUsuarioCrear = new javax.swing.JMenuItem();
        menuItemUsuarioBuscar = new javax.swing.JMenuItem();
        menuItemUsuarioActualizar = new javax.swing.JMenuItem();
        menuItemUsuarioEliminar = new javax.swing.JMenuItem();
        menuItemUsuarioListar = new javax.swing.JMenuItem();
        menuItemPrestamo = new javax.swing.JMenu();
        menuItemPrestamoCrear = new javax.swing.JMenuItem();
        menuItemPrestamoBuscar = new javax.swing.JMenuItem();
        menuItemPrestamoActualizar = new javax.swing.JMenuItem();
        menuItemPrestamoEliminar = new javax.swing.JMenuItem();
        menuItemPrestamoListar = new javax.swing.JMenuItem();
        menuItemIdioma = new javax.swing.JMenu();
        menuItemIdiomaEspañol = new javax.swing.JMenuItem();
        menuItemIdiomaIngles = new javax.swing.JMenuItem();
        menuItemSistema = new javax.swing.JMenu();
        menuItemSistemaSalir = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/converted_image (1).png"))); // NOI18N
        desktopPane.add(jLabel1);
        jLabel1.setBounds(0, 0, 1408, 795);

        menuItemLibro.setMnemonic('f');
        menuItemLibro.setText("Libro");

        menuItemLibroCrear.setMnemonic('o');
        menuItemLibroCrear.setText("Crear");
        menuItemLibroCrear.addActionListener(this::menuItemLibroCrearActionPerformed);
        menuItemLibro.add(menuItemLibroCrear);

        menuItemLibroBuscar.setMnemonic('s');
        menuItemLibroBuscar.setText("Buscar");
        menuItemLibroBuscar.addActionListener(this::menuItemLibroBuscarActionPerformed);
        menuItemLibro.add(menuItemLibroBuscar);

        menuItemLibroActualizar.setMnemonic('a');
        menuItemLibroActualizar.setText("Actualizar");
        menuItemLibroActualizar.addActionListener(this::menuItemLibroActualizarActionPerformed);
        menuItemLibro.add(menuItemLibroActualizar);

        menuItemLibroEliminar.setMnemonic('x');
        menuItemLibroEliminar.setText("Eliminar");
        menuItemLibroEliminar.addActionListener(this::menuItemLibroEliminarActionPerformed);
        menuItemLibro.add(menuItemLibroEliminar);

        menuItemLibroListar.setText("Lista");
        menuItemLibroListar.addActionListener(this::menuItemLibroListarActionPerformed);
        menuItemLibro.add(menuItemLibroListar);

        menuBar.add(menuItemLibro);

        menuItemUsuario.setMnemonic('e');
        menuItemUsuario.setText("Usuario");

        menuItemUsuarioCrear.setMnemonic('t');
        menuItemUsuarioCrear.setText("Crear");
        menuItemUsuarioCrear.addActionListener(this::menuItemUsuarioCrearActionPerformed);
        menuItemUsuario.add(menuItemUsuarioCrear);

        menuItemUsuarioBuscar.setMnemonic('y');
        menuItemUsuarioBuscar.setText("Buscar");
        menuItemUsuarioBuscar.addActionListener(this::menuItemUsuarioBuscarActionPerformed);
        menuItemUsuario.add(menuItemUsuarioBuscar);

        menuItemUsuarioActualizar.setMnemonic('p');
        menuItemUsuarioActualizar.setText("Actualizar");
        menuItemUsuarioActualizar.addActionListener(this::menuItemUsuarioActualizarActionPerformed);
        menuItemUsuario.add(menuItemUsuarioActualizar);

        menuItemUsuarioEliminar.setMnemonic('d');
        menuItemUsuarioEliminar.setText("Eliminar");
        menuItemUsuarioEliminar.addActionListener(this::menuItemUsuarioEliminarActionPerformed);
        menuItemUsuario.add(menuItemUsuarioEliminar);

        menuItemUsuarioListar.setText("Lista");
        menuItemUsuarioListar.addActionListener(this::menuItemUsuarioListarActionPerformed);
        menuItemUsuario.add(menuItemUsuarioListar);

        menuBar.add(menuItemUsuario);

        menuItemPrestamo.setMnemonic('h');
        menuItemPrestamo.setText("Prestamo");

        menuItemPrestamoCrear.setMnemonic('c');
        menuItemPrestamoCrear.setText("Crear");
        menuItemPrestamoCrear.addActionListener(this::menuItemPrestamoCrearActionPerformed);
        menuItemPrestamo.add(menuItemPrestamoCrear);

        menuItemPrestamoBuscar.setMnemonic('a');
        menuItemPrestamoBuscar.setText("Buscar");
        menuItemPrestamoBuscar.addActionListener(this::menuItemPrestamoBuscarActionPerformed);
        menuItemPrestamo.add(menuItemPrestamoBuscar);

        menuItemPrestamoActualizar.setText("Devolver");
        menuItemPrestamoActualizar.addActionListener(this::menuItemPrestamoActualizarActionPerformed);
        menuItemPrestamo.add(menuItemPrestamoActualizar);

        menuItemPrestamoEliminar.setText("Eliminar");
        menuItemPrestamoEliminar.addActionListener(this::menuItemPrestamoEliminarActionPerformed);
        menuItemPrestamo.add(menuItemPrestamoEliminar);

        menuItemPrestamoListar.setText("Lista");
        menuItemPrestamoListar.addActionListener(this::menuItemPrestamoListarActionPerformed);
        menuItemPrestamo.add(menuItemPrestamoListar);

        menuBar.add(menuItemPrestamo);

        menuItemIdioma.setText("Idioma");

        menuItemIdiomaEspañol.setText("Español");
        menuItemIdiomaEspañol.addActionListener(this::menuItemIdiomaEspañolActionPerformed);
        menuItemIdioma.add(menuItemIdiomaEspañol);

        menuItemIdiomaIngles.setText("Ingles");
        menuItemIdiomaIngles.addActionListener(this::menuItemIdiomaInglesActionPerformed);
        menuItemIdioma.add(menuItemIdiomaIngles);

        menuBar.add(menuItemIdioma);

        menuItemSistema.setText("Sistema");

        menuItemSistemaSalir.setText("Salir");
        menuItemSistemaSalir.addActionListener(this::menuItemSistemaSalirActionPerformed);
        menuItemSistema.add(menuItemSistemaSalir);

        menuBar.add(menuItemSistema);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemLibroEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLibroEliminarActionPerformed
        if (!desktopPane.isAncestorOf(libroEliminarView)) {
            desktopPane.add(libroEliminarView);
        }

        libroEliminarView.setVisible(true);
        libroEliminarView.toFront();
        desktopPane.getDesktopManager().activateFrame(libroEliminarView);
    }//GEN-LAST:event_menuItemLibroEliminarActionPerformed

    private void menuItemLibroCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLibroCrearActionPerformed
        if (!desktopPane.isAncestorOf(libroCrearView)) {
            desktopPane.add(libroCrearView);
        }
        libroCrearView.setVisible(true);
        libroCrearView.toFront();
        desktopPane.getDesktopManager().activateFrame(libroCrearView);
    }//GEN-LAST:event_menuItemLibroCrearActionPerformed

    private void menuItemLibroBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLibroBuscarActionPerformed
        if (!desktopPane.isAncestorOf(libroBuscarView)) {
            desktopPane.add(libroBuscarView);
        }
        libroBuscarView.setVisible(true);
        libroBuscarView.toFront();
        desktopPane.getDesktopManager().activateFrame(libroBuscarView);
    }//GEN-LAST:event_menuItemLibroBuscarActionPerformed

    private void menuItemLibroActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLibroActualizarActionPerformed
        if (!desktopPane.isAncestorOf(libroActualizarView)) {
            desktopPane.add(libroActualizarView);
        }
        libroActualizarView.setVisible(true);
        libroActualizarView.toFront();
        desktopPane.getDesktopManager().activateFrame(libroActualizarView);
    }//GEN-LAST:event_menuItemLibroActualizarActionPerformed

    private void menuItemLibroListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLibroListarActionPerformed
        if (!desktopPane.isAncestorOf(libroListarView)) {
            desktopPane.add(libroListarView);
        }
        libroListarView.setVisible(true);
        libroListarView.toFront();
        desktopPane.getDesktopManager().activateFrame(libroListarView);
    }//GEN-LAST:event_menuItemLibroListarActionPerformed

    private void menuItemUsuarioCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUsuarioCrearActionPerformed
        if (!desktopPane.isAncestorOf(usuarioCrearView)) {
            desktopPane.add(usuarioCrearView);
        }
        usuarioCrearView.setVisible(true);
        usuarioCrearView.toFront();
        desktopPane.getDesktopManager().activateFrame(usuarioCrearView);
    }//GEN-LAST:event_menuItemUsuarioCrearActionPerformed

    private void menuItemUsuarioBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUsuarioBuscarActionPerformed
        if (!desktopPane.isAncestorOf(usuarioBuscarView)) {
            desktopPane.add(usuarioBuscarView);
        }
        usuarioBuscarView.setVisible(true);
        usuarioBuscarView.toFront();
        desktopPane.getDesktopManager().activateFrame(usuarioBuscarView);
    }//GEN-LAST:event_menuItemUsuarioBuscarActionPerformed

    private void menuItemUsuarioActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUsuarioActualizarActionPerformed
        if (!desktopPane.isAncestorOf(usuarioActualizarView)) {
    desktopPane.add(usuarioActualizarView);
}

usuarioActualizarView.setVisible(true);
usuarioActualizarView.toFront();
desktopPane.getDesktopManager().activateFrame(usuarioActualizarView);
    }//GEN-LAST:event_menuItemUsuarioActualizarActionPerformed

    private void menuItemUsuarioEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUsuarioEliminarActionPerformed
        if (!desktopPane.isAncestorOf(usuarioEliminarView)) {
    desktopPane.add(usuarioEliminarView);
}

usuarioEliminarView.setVisible(true);
usuarioEliminarView.toFront();
desktopPane.getDesktopManager().activateFrame(usuarioEliminarView);
    }//GEN-LAST:event_menuItemUsuarioEliminarActionPerformed

    private void menuItemUsuarioListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemUsuarioListarActionPerformed
        if (!desktopPane.isAncestorOf(usuarioListarView)) {
    desktopPane.add(usuarioListarView);
}

usuarioListarView.setVisible(true);
usuarioListarView.toFront();
desktopPane.getDesktopManager().activateFrame(usuarioListarView);
    }//GEN-LAST:event_menuItemUsuarioListarActionPerformed

    private void menuItemPrestamoCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPrestamoCrearActionPerformed
        if (!desktopPane.isAncestorOf(prestamoCrearView)) {
    desktopPane.add(prestamoCrearView);
}

prestamoCrearView.setVisible(true);
usuarioListarView.toFront();
desktopPane.getDesktopManager().activateFrame(usuarioListarView);
    }//GEN-LAST:event_menuItemPrestamoCrearActionPerformed

    private void menuItemPrestamoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPrestamoBuscarActionPerformed
        if (!prestamoBuscarView.isVisible()) {
            desktopPane.remove(prestamoBuscarView);
            prestamoBuscarView.setVisible(true);
            desktopPane.add(prestamoBuscarView);
        }
    }//GEN-LAST:event_menuItemPrestamoBuscarActionPerformed

    private void menuItemPrestamoActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPrestamoActualizarActionPerformed
        if (!prestamoActualizarView.isVisible()) {
            desktopPane.remove(prestamoActualizarView);
            prestamoActualizarView.setVisible(true);
            desktopPane.add(prestamoActualizarView);
        }
    }//GEN-LAST:event_menuItemPrestamoActualizarActionPerformed

    private void menuItemPrestamoEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPrestamoEliminarActionPerformed
        if (!prestamoEliminarView.isVisible()) {
            desktopPane.remove(prestamoEliminarView);
            prestamoEliminarView.setVisible(true);
            desktopPane.add(prestamoEliminarView);
        }
    }//GEN-LAST:event_menuItemPrestamoEliminarActionPerformed

    private void menuItemPrestamoListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPrestamoListarActionPerformed
        if (!prestamoListarView.isVisible()) {
            desktopPane.remove(prestamoListarView);
            prestamoListarView.setVisible(true);
            desktopPane.add(prestamoListarView);
        }
    }//GEN-LAST:event_menuItemPrestamoListarActionPerformed

    private void menuItemIdiomaInglesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemIdiomaInglesActionPerformed
        Locale locale = new Locale("en", "US");
        this.cambiarIdioma(locale);
        usuarioCrearView.cambiarIdioma(locale);
        usuarioBuscarView.cambiarIdioma(locale);
        usuarioActualizarView.cambiarIdioma(locale);
        usuarioEliminarView.cambiarIdioma(locale);
        usuarioListarView.cambiarIdioma(locale);

        libroActualizarView.cambiarIdioma(locale);
        libroBuscarView.cambiarIdioma(locale);
        libroCrearView.cambiarIdioma(locale);
        libroEliminarView.cambiarIdioma(locale);
        libroListarView.cambiarIdioma(locale);

        prestamoActualizarView.cambiarIdioma(locale);
        prestamoBuscarView.cambiarIdioma(locale);
        prestamoCrearView.cambiarIdioma(locale);
        prestamoEliminarView.cambiarIdioma(locale);
        prestamoListarView.cambiarIdioma(locale);
    }//GEN-LAST:event_menuItemIdiomaInglesActionPerformed

    private void menuItemIdiomaEspañolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemIdiomaEspañolActionPerformed
        Locale locale = new Locale("es", "EC");
        this.cambiarIdioma(locale);
        usuarioCrearView.cambiarIdioma(locale);
        usuarioBuscarView.cambiarIdioma(locale);
        usuarioActualizarView.cambiarIdioma(locale);
        usuarioEliminarView.cambiarIdioma(locale);
        usuarioListarView.cambiarIdioma(locale);

        libroActualizarView.cambiarIdioma(locale);
        libroBuscarView.cambiarIdioma(locale);
        libroCrearView.cambiarIdioma(locale);
        libroEliminarView.cambiarIdioma(locale);
        libroListarView.cambiarIdioma(locale);

        prestamoActualizarView.cambiarIdioma(locale);
        prestamoBuscarView.cambiarIdioma(locale);
        prestamoCrearView.cambiarIdioma(locale);
        prestamoEliminarView.cambiarIdioma(locale);
        prestamoListarView.cambiarIdioma(locale);
    }//GEN-LAST:event_menuItemIdiomaEspañolActionPerformed

    private void menuItemSistemaSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSistemaSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSistemaSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuItemIdioma;
    private javax.swing.JMenuItem menuItemIdiomaEspañol;
    private javax.swing.JMenuItem menuItemIdiomaIngles;
    private javax.swing.JMenu menuItemLibro;
    private javax.swing.JMenuItem menuItemLibroActualizar;
    private javax.swing.JMenuItem menuItemLibroBuscar;
    private javax.swing.JMenuItem menuItemLibroCrear;
    private javax.swing.JMenuItem menuItemLibroEliminar;
    private javax.swing.JMenuItem menuItemLibroListar;
    private javax.swing.JMenu menuItemPrestamo;
    private javax.swing.JMenuItem menuItemPrestamoActualizar;
    private javax.swing.JMenuItem menuItemPrestamoBuscar;
    private javax.swing.JMenuItem menuItemPrestamoCrear;
    private javax.swing.JMenuItem menuItemPrestamoEliminar;
    private javax.swing.JMenuItem menuItemPrestamoListar;
    private javax.swing.JMenu menuItemSistema;
    private javax.swing.JMenuItem menuItemSistemaSalir;
    private javax.swing.JMenu menuItemUsuario;
    private javax.swing.JMenuItem menuItemUsuarioActualizar;
    private javax.swing.JMenuItem menuItemUsuarioBuscar;
    private javax.swing.JMenuItem menuItemUsuarioCrear;
    private javax.swing.JMenuItem menuItemUsuarioEliminar;
    private javax.swing.JMenuItem menuItemUsuarioListar;
    // End of variables declaration//GEN-END:variables

}
