/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ec.edu.ups.biblioteca.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PrestamoCrearView extends javax.swing.JInternalFrame {

    /**
     * Creates new form PrestamoCrearView
     */
    public PrestamoCrearView() {
        initComponents();
        configurarTabla();
        configurarFechaActual();
    }
    
    public void cambiarIdioma(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("ec.edu.ups.biblioteca.i18n.mensajes", locale);
        
        
        lblCodigoPrestamoCrear.setText(bundle.getString("prestamo.codigo"));
    lblCedulaPrestamoCrear.setText(bundle.getString("usuario.cedula"));
    lblNombrePrestamoCrear.setText(bundle.getString("usuario.nombre"));
    lblTelefonoPrestamoCrear.setText(bundle.getString("usuario.telefono"));
    lblLibrosPrestamoCrear.setText(bundle.getString("prestamo.libros"));

    btnBuscarPrestamoCrear.setText(bundle.getString("boton.buscar"));
    btnAgregarPrestamoCrear.setText(bundle.getString("boton.agregar"));
    btnCrearPrestamoCrear.setText(bundle.getString("boton.crear"));
    btnCancelarPrestamoView.setText(bundle.getString("boton.cancelar"));
    btnLimpiarPrestamoCrear.setText(bundle.getString("boton.limpiar"));
    }

    private void configurarTabla() {

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ISBN", "Título"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblLibrosAgregadosPrestamoCrear.setModel(modelo);

        tblLibrosAgregadosPrestamoCrear.getTableHeader().setReorderingAllowed(false);

        tblLibrosAgregadosPrestamoCrear.getTableHeader().setResizingAllowed(false);

    }

    private void configurarFechaActual() {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        txtFechaPrestamoPrestamoCrear.setText(
                LocalDate.now().format(formato)
        );
    }

    public JButton getBtnAgregarPrestamoCrear() {
        return btnAgregarPrestamoCrear;
    }

    public void setBtnAgregarPrestamoCrear(JButton btnAgregarPrestamoCrear) {
        this.btnAgregarPrestamoCrear = btnAgregarPrestamoCrear;
    }

    public JButton getBtnBuscarPrestamoCrear() {
        return btnBuscarPrestamoCrear;
    }

    public void setBtnBuscarPrestamoCrear(JButton btnBuscarPrestamoCrear) {
        this.btnBuscarPrestamoCrear = btnBuscarPrestamoCrear;
    }

    public JButton getBtnCancelarPrestamoView() {
        return btnCancelarPrestamoView;
    }

    public void setBtnCancelarPrestamoView(JButton btnCancelarPrestamoView) {
        this.btnCancelarPrestamoView = btnCancelarPrestamoView;
    }

    public JButton getBtnCrearPrestamoCrear() {
        return btnCrearPrestamoCrear;
    }

    public void setBtnCrearPrestamoCrear(JButton btnCrearPrestamoCrear) {
        this.btnCrearPrestamoCrear = btnCrearPrestamoCrear;
    }

    public JButton getBtnLimpiarPrestamoCrear() {
        return btnLimpiarPrestamoCrear;
    }

    public void setBtnLimpiarPrestamoCrear(JButton btnLimpiarPrestamoCrear) {
        this.btnLimpiarPrestamoCrear = btnLimpiarPrestamoCrear;
    }

    public JComboBox<String> getCbxLibrosListaPrestamoCrear() {
        return cbxLibrosListaPrestamoCrear;
    }

    public void setCbxLibrosListaPrestamoCrear(JComboBox<String> cbxLibrosListaPrestamoCrear) {
        this.cbxLibrosListaPrestamoCrear = cbxLibrosListaPrestamoCrear;
    }

    public JCheckBoxMenuItem getjCheckBoxMenuItem1() {
        return jCheckBoxMenuItem1;
    }

    public void setjCheckBoxMenuItem1(JCheckBoxMenuItem jCheckBoxMenuItem1) {
        this.jCheckBoxMenuItem1 = jCheckBoxMenuItem1;
    }

    public JMenu getjMenu1() {
        return jMenu1;
    }

    public void setjMenu1(JMenu jMenu1) {
        this.jMenu1 = jMenu1;
    }

    public JMenu getjMenu2() {
        return jMenu2;
    }

    public void setjMenu2(JMenu jMenu2) {
        this.jMenu2 = jMenu2;
    }

    public JMenuItem getjMenuItem1() {
        return jMenuItem1;
    }

    public void setjMenuItem1(JMenuItem jMenuItem1) {
        this.jMenuItem1 = jMenuItem1;
    }

    public JRadioButtonMenuItem getjRadioButtonMenuItem1() {
        return jRadioButtonMenuItem1;
    }

    public void setjRadioButtonMenuItem1(JRadioButtonMenuItem jRadioButtonMenuItem1) {
        this.jRadioButtonMenuItem1 = jRadioButtonMenuItem1;
    }

    public JTextField getTxtCedulaPrestamoCrear() {
        return txtCedulaPrestamoCrear;
    }

    public void setTxtCedulaPrestamoCrear(JTextField txtCedulaPrestamoCrear) {
        this.txtCedulaPrestamoCrear = txtCedulaPrestamoCrear;
    }

    public JTextField getTxtCodigoPrestamoCrear() {
        return txtCodigoPrestamoCrear;
    }

    public void setTxtCodigoPrestamoCrear(JTextField txtCodigoPrestamoCrear) {
        this.txtCodigoPrestamoCrear = txtCodigoPrestamoCrear;
    }

    public JTextField getTxtFechaPrestamoPrestamoCrear() {
        return txtFechaPrestamoPrestamoCrear;
    }

    public void setTxtFechaPrestamoPrestamoCrear(JTextField txtFechaPrestamoPrestamoCrear) {
        this.txtFechaPrestamoPrestamoCrear = txtFechaPrestamoPrestamoCrear;
    }

    public JTextField getTxtNombrePrestamoCrear() {
        return txtNombrePrestamoCrear;
    }

    public void setTxtNombrePrestamoCrear(JTextField txtNombrePrestamoCrear) {
        this.txtNombrePrestamoCrear = txtNombrePrestamoCrear;
    }

    public JTextField getTxtTelefonoPrestamoCrear() {
        return txtTelefonoPrestamoCrear;
    }

    public void setTxtTelefonoPrestamoCrear(JTextField txtTelefonoPrestamoCrear) {
        this.txtTelefonoPrestamoCrear = txtTelefonoPrestamoCrear;
    }

    public void mostarMensaje(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje);
    }

    public javax.swing.JTable getTblLibrosAgregadosPrestamoCrear() {
        return tblLibrosAgregadosPrestamoCrear;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        panelPrestamoCrear = new javax.swing.JPanel();
        lblCodigoPrestamoCrear = new javax.swing.JLabel();
        txtCodigoPrestamoCrear = new javax.swing.JTextField();
        lblCedulaPrestamoCrear = new javax.swing.JLabel();
        txtCedulaPrestamoCrear = new javax.swing.JTextField();
        lblNombrePrestamoCrear = new javax.swing.JLabel();
        txtNombrePrestamoCrear = new javax.swing.JTextField();
        lblLibrosPrestamoCrear = new javax.swing.JLabel();
        cbxLibrosListaPrestamoCrear = new javax.swing.JComboBox<>();
        btnBuscarPrestamoCrear = new javax.swing.JButton();
        lblTelefonoPrestamoCrear = new javax.swing.JLabel();
        txtTelefonoPrestamoCrear = new javax.swing.JTextField();
        btnAgregarPrestamoCrear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibrosAgregadosPrestamoCrear = new javax.swing.JTable();
        btnLimpiarPrestamoCrear = new javax.swing.JButton();
        btnCrearPrestamoCrear = new javax.swing.JButton();
        btnCancelarPrestamoView = new javax.swing.JButton();
        lblFechaPrestamoPrestamoCrear = new javax.swing.JLabel();
        txtFechaPrestamoPrestamoCrear = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        panelPrestamoCrear.setBackground(new java.awt.Color(207, 162, 124));
        panelPrestamoCrear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        lblCodigoPrestamoCrear.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        lblCodigoPrestamoCrear.setText("Código del préstamo:");

        lblCedulaPrestamoCrear.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        lblCedulaPrestamoCrear.setText("Cédula:");

        lblNombrePrestamoCrear.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        lblNombrePrestamoCrear.setText("Nombre:");

        txtNombrePrestamoCrear.setEnabled(false);

        lblLibrosPrestamoCrear.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        lblLibrosPrestamoCrear.setText("Libros");

        cbxLibrosListaPrestamoCrear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxLibrosListaPrestamoCrear.addActionListener(this::cbxLibrosListaPrestamoCrearActionPerformed);

        btnBuscarPrestamoCrear.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnBuscarPrestamoCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/LUPA_.png"))); // NOI18N
        btnBuscarPrestamoCrear.setText("Buscar");

        lblTelefonoPrestamoCrear.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        lblTelefonoPrestamoCrear.setText("Teléfono:");

        txtTelefonoPrestamoCrear.setEnabled(false);

        btnAgregarPrestamoCrear.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnAgregarPrestamoCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/Agg.png"))); // NOI18N
        btnAgregarPrestamoCrear.setText("Agregar");

        tblLibrosAgregadosPrestamoCrear.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLibrosAgregadosPrestamoCrear);

        btnLimpiarPrestamoCrear.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnLimpiarPrestamoCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/Escoba.png"))); // NOI18N
        btnLimpiarPrestamoCrear.setText("Limpiar");

        btnCrearPrestamoCrear.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnCrearPrestamoCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/Crear.png"))); // NOI18N
        btnCrearPrestamoCrear.setText("Crear");

        btnCancelarPrestamoView.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnCancelarPrestamoView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/X.png"))); // NOI18N
        btnCancelarPrestamoView.setText("Cancelar");

        lblFechaPrestamoPrestamoCrear.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        lblFechaPrestamoPrestamoCrear.setText("Fecha de préstamo:");

        txtFechaPrestamoPrestamoCrear.setEnabled(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/crearP.png"))); // NOI18N

        javax.swing.GroupLayout panelPrestamoCrearLayout = new javax.swing.GroupLayout(panelPrestamoCrear);
        panelPrestamoCrear.setLayout(panelPrestamoCrearLayout);
        panelPrestamoCrearLayout.setHorizontalGroup(
            panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLibrosPrestamoCrear)
                            .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                                .addComponent(cbxLibrosListaPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnAgregarPrestamoCrear)
                                .addGap(55, 55, 55)
                                .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrestamoCrearLayout.createSequentialGroup()
                                        .addComponent(btnCrearPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68)
                                        .addComponent(btnCancelarPrestamoView)
                                        .addGap(57, 57, 57))))))
                    .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                                .addComponent(lblTelefonoPrestamoCrear)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefonoPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                                    .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                                            .addGap(114, 114, 114)
                                            .addComponent(btnBuscarPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(74, 74, 74)
                                            .addComponent(btnLimpiarPrestamoCrear))
                                        .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                                            .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblCodigoPrestamoCrear)
                                                .addComponent(lblCedulaPrestamoCrear))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCodigoPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCedulaPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(82, 82, 82)
                                    .addComponent(jLabel1)
                                    .addGap(76, 76, 76))
                                .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                                    .addComponent(lblNombrePrestamoCrear)
                                    .addGap(27, 27, 27)
                                    .addComponent(txtNombrePrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(54, 54, 54)
                                    .addComponent(lblFechaPrestamoPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFechaPrestamoPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        panelPrestamoCrearLayout.setVerticalGroup(
            panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodigoPrestamoCrear)
                            .addComponent(txtCodigoPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCedulaPrestamoCrear)
                            .addComponent(txtCedulaPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLimpiarPrestamoCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscarPrestamoCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)))
                    .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombrePrestamoCrear)
                    .addComponent(txtNombrePrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaPrestamoPrestamoCrear)
                    .addComponent(txtFechaPrestamoPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefonoPrestamoCrear)
                    .addComponent(txtTelefonoPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(lblLibrosPrestamoCrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxLibrosListaPrestamoCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregarPrestamoCrear))
                    .addGroup(panelPrestamoCrearLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrestamoCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelarPrestamoView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCrearPrestamoCrear))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrestamoCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrestamoCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxLibrosListaPrestamoCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLibrosListaPrestamoCrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxLibrosListaPrestamoCrearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPrestamoCrear;
    private javax.swing.JButton btnBuscarPrestamoCrear;
    private javax.swing.JButton btnCancelarPrestamoView;
    private javax.swing.JButton btnCrearPrestamoCrear;
    private javax.swing.JButton btnLimpiarPrestamoCrear;
    private javax.swing.JComboBox<String> cbxLibrosListaPrestamoCrear;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCedulaPrestamoCrear;
    private javax.swing.JLabel lblCodigoPrestamoCrear;
    private javax.swing.JLabel lblFechaPrestamoPrestamoCrear;
    private javax.swing.JLabel lblLibrosPrestamoCrear;
    private javax.swing.JLabel lblNombrePrestamoCrear;
    private javax.swing.JLabel lblTelefonoPrestamoCrear;
    private javax.swing.JPanel panelPrestamoCrear;
    private javax.swing.JTable tblLibrosAgregadosPrestamoCrear;
    private javax.swing.JTextField txtCedulaPrestamoCrear;
    private javax.swing.JTextField txtCodigoPrestamoCrear;
    private javax.swing.JTextField txtFechaPrestamoPrestamoCrear;
    private javax.swing.JTextField txtNombrePrestamoCrear;
    private javax.swing.JTextField txtTelefonoPrestamoCrear;
    // End of variables declaration//GEN-END:variables
}
