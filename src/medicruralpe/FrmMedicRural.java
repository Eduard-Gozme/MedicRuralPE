/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package medicruralpe;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FrmMedicRural extends javax.swing.JFrame {
    
    private final Inventario inventario = Inventario.getInstancia();
    private final AnalizadorInventario analizador = new AnalizadorInventario();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmMedicRural.class.getName());

    public FrmMedicRural() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtStockActual.setText("");
        txtStockMinimo.setText("");
        txtDetalle.setText("");
        cmbTipo.setSelectedIndex(0);
        txtResponsable.setText("");
    }
    
    
    private void mostrarInventario() {

        DefaultTableModel modelo =
                (DefaultTableModel) tblInventario.getModel();

        modelo.setRowCount(0);

        for (RecursoMedico recurso : inventario.obtenerRecursos()) {

            String tipo;
            String detalle;

            if (recurso instanceof Medicamento) {
                tipo = "Medicamento";
                detalle = ((Medicamento) recurso).getPresentacion();
            } else {
                tipo = "Insumo médico";
                detalle = ((InsumoMedico) recurso).getUnidadMedida();
            }

            String estado;

            if (recurso.esCritico()) {
                estado = "CRÍTICO";
            } else {
                estado = "NORMAL";
            }

            modelo.addRow(new Object[]{
                recurso.getCodigo(),
                recurso.getNombre(),
                tipo,
                recurso.getStockActual(),
                recurso.getStockMinimo(),
                detalle,
                estado
            });
        }
    }
    
    private void registrarRecurso() {

        try {

            String tipo = cmbTipo.getSelectedItem().toString();
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String detalle = txtDetalle.getText().trim();

            int stockActual =
                    Integer.parseInt(txtStockActual.getText().trim());

            int stockMinimo =
                    Integer.parseInt(txtStockMinimo.getText().trim());

            RecursoMedico recurso;

            if (tipo.equals("Medicamento")) {

                recurso = RecursoFactory.crearMedicamento(
                        codigo,
                        nombre,
                        stockActual,
                        stockMinimo,
                        detalle
                );

            } else {

                recurso = RecursoFactory.crearInsumo(
                        codigo,
                        nombre,
                        stockActual,
                        stockMinimo,
                        detalle
                );
            }
            String identificadorResponsable =
                txtResponsable.getText().trim();

            recurso.asignarResponsable(identificadorResponsable);

            inventario.registrarRecurso(recurso);

            mostrarInventario();

            JOptionPane.showMessageDialog(
                    this,
                    "Recurso registrado correctamente."
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Stock actual y stock mínimo deben ser números enteros.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void actualizarRecurso() {

        try {

            String codigo = txtCodigo.getText().trim();

            if (codigo.isBlank()) {
                throw new IllegalArgumentException(
                        "El codigo es obligatorio."
                );
            }

            int nuevoStock =
                    Integer.parseInt(txtStockActual.getText().trim());

            inventario.actualizarStock(codigo, nuevoStock);

            mostrarInventario();

            JOptionPane.showMessageDialog(
                    this,
                    "Stock actualizado correctamente."
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El stock actual debe ser un numero entero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void mostrarRecursosCriticos() {

        DefaultTableModel modelo =
                (DefaultTableModel) tblInventario.getModel();

        modelo.setRowCount(0);

        for (RecursoMedico recurso :
                analizador.priorizarRecursosCriticos(
                        inventario.obtenerRecursos()
                )) {

            String tipo;
            String detalle;

            if (recurso instanceof Medicamento) {
                tipo = "Medicamento";
                detalle = ((Medicamento) recurso).getPresentacion();
            } else {
                tipo = "Insumo médico";
                detalle = ((InsumoMedico) recurso).getUnidadMedida();
            }

            modelo.addRow(new Object[]{
                recurso.getCodigo(),
                recurso.getNombre(),
                tipo,
                recurso.getStockActual(),
                recurso.getStockMinimo(),
                detalle,
                "CRÍTICO"
            });
        }
    }
    
    private void mostrarResumen() {

        int totalRecursos =
                inventario.obtenerRecursos().size();

        long recursosCriticos =
                analizador.contarRecursosCriticos(
                        inventario.obtenerRecursos()
                );

        int totalUnidades =
                analizador.calcularTotalUnidades(
                        inventario.obtenerRecursos()
                );

        String mensaje =
                "=== RESUMEN DEL INVENTARIO ===\n\n"
                + "Total de recursos: " + totalRecursos + "\n"
                + "Recursos críticos: " + recursosCriticos + "\n"
                + "Total de unidades disponibles: " + totalUnidades;

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Resumen del inventario",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStockActual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStockMinimo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDetalle = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventario = new javax.swing.JTable();
        btnVerTodos = new javax.swing.JButton();
        btnVerCriticos = new javax.swing.JButton();
        btnResumen = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MedicRural-PE");

        jLabel1.setText("MedicRural-PE - Gestión de medicamentos e insumos");

        jLabel2.setText("Código:");

        jLabel3.setText("Tipo:");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medicamento", "Insumo médico" }));

        jLabel4.setText("Nombre:");

        jLabel5.setText("Stock actual:");

        jLabel6.setText("Stock mínimo:");

        jLabel7.setText("Presentación / Unidad:");

        btnRegistrar.setText("Registrar ");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        btnActualizar.setText("Actualizar stock");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre ", "Tipo ", "Stock actual ", "Stock mínimo ", "Detalle ", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInventario);

        btnVerTodos.setText("Ver todos");
        btnVerTodos.addActionListener(this::btnVerTodosActionPerformed);

        btnVerCriticos.setText("Ver críticos");
        btnVerCriticos.addActionListener(this::btnVerCriticosActionPerformed);

        btnResumen.setText("Ver resumen");
        btnResumen.addActionListener(this::btnResumenActionPerformed);

        jLabel8.setText("ID responsable ficticio:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtStockActual)
                            .addComponent(txtStockMinimo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVerTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVerCriticos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(btnResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtStockMinimo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtDetalle)
                    .addComponent(txtResponsable)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerCriticos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrarRecurso();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumenActionPerformed
        mostrarResumen();        // TODO add your handling code here:
    }//GEN-LAST:event_btnResumenActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarRecurso();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        mostrarInventario();        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerTodosActionPerformed

    private void btnVerCriticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCriticosActionPerformed
        mostrarRecursosCriticos();        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerCriticosActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmMedicRural().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnResumen;
    private javax.swing.JButton btnVerCriticos;
    private javax.swing.JButton btnVerTodos;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventario;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDetalle;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JTextField txtStockActual;
    private javax.swing.JTextField txtStockMinimo;
    // End of variables declaration//GEN-END:variables
}
