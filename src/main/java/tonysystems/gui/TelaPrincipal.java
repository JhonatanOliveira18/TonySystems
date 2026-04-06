package tonysystems.gui;

public class TelaPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());

    public TelaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miCliente = new javax.swing.JMenuItem();
        miTV = new javax.swing.JMenuItem();
        miMaterial = new javax.swing.JMenuItem();
        miGerenciarOS = new javax.swing.JMenu();
        miAbrirOS = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        miListaCli = new javax.swing.JMenuItem();
        miListaMaterial = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema TonySystems");

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1103, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );

        jMenu1.setText("Cadastros ");

        miCliente.setText("Clientes");
        miCliente.addActionListener(this::miClienteActionPerformed);
        jMenu1.add(miCliente);

        miTV.setText("Televisões");
        miTV.addActionListener(this::miTVActionPerformed);
        jMenu1.add(miTV);

        miMaterial.setText("Materiais");
        miMaterial.addActionListener(this::miMaterialActionPerformed);
        jMenu1.add(miMaterial);

        jMenuBar1.add(jMenu1);

        miGerenciarOS.setText("Ordem de Serviço");

        miAbrirOS.setText("Abrir OS");
        miAbrirOS.addActionListener(this::miAbrirOSActionPerformed);
        miGerenciarOS.add(miAbrirOS);

        jMenuItem6.setText("Gerenciar/Finalizar OS");
        jMenuItem6.addActionListener(this::jMenuItem6ActionPerformed);
        miGerenciarOS.add(jMenuItem6);

        jMenuBar1.add(miGerenciarOS);

        jMenu4.setText("Listagem");

        miListaCli.setText("Clientes");
        miListaCli.addActionListener(this::miListaCliActionPerformed);
        jMenu4.add(miListaCli);

        miListaMaterial.setText("Materiais");
        miListaMaterial.addActionListener(this::miListaMaterialActionPerformed);
        jMenu4.add(miListaMaterial);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miListaCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miListaCliActionPerformed
        //Instanciando a classe do frame interno que desejo exibir
        TelaListagemClientes tela = new TelaListagemClientes();

        //Adicionando dentro do desktop (telhinha azul)
        Desktop.add(tela);

        //Tornando visivel a tela
        tela.setVisible(true);

        //Possibilitando a maximação da tela
        tela.setMaximizable(true);

        //Possibilitando o fechamento da tela através do X
        tela.setClosable(rootPaneCheckingEnabled);
    }//GEN-LAST:event_miListaCliActionPerformed

    private void miClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClienteActionPerformed
        //Instanciando a classe do frame interno que desejo exibir
        TelaCadastroCliente tela = new TelaCadastroCliente();

        //Adicionando dentro do desktop (telhinha azul)
        Desktop.add(tela);

        //Tornando visivel a tela
        tela.setVisible(true);

        //Possibilitando a maximação da tela
        tela.setMaximizable(true);

        //Possibilitando o fechamento da tela através do X
        tela.setClosable(rootPaneCheckingEnabled);
    }//GEN-LAST:event_miClienteActionPerformed

    private void miTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miTVActionPerformed
        //Instanciando a classe do frame interno que desejo exibir
        TelaCadastroTV tela = new TelaCadastroTV();

        //Adicionando dentro do desktop (telhinha azul)
        Desktop.add(tela);

        //Tornando visivel a tela
        tela.setVisible(true);

        //Possibilitando a maximação da tela
        tela.setMaximizable(true);

        //Possibilitando o fechamento da tela através do X
        tela.setClosable(rootPaneCheckingEnabled);
    }//GEN-LAST:event_miTVActionPerformed

    private void miMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miMaterialActionPerformed
        //Instanciando a classe do frame interno que desejo exibir
        TelaCadastroMaterial tela = new TelaCadastroMaterial();

        //Adicionando dentro do desktop (telhinha azul)
        Desktop.add(tela);

        //Tornando visivel a tela
        tela.setVisible(true);

        //Possibilitando a maximação da tela
        tela.setMaximizable(true);

        //Possibilitando o fechamento da tela através do X
        tela.setClosable(rootPaneCheckingEnabled);
    }//GEN-LAST:event_miMaterialActionPerformed

    private void miAbrirOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAbrirOSActionPerformed
        //Instanciando a classe do frame interno que desejo exibir
        TelaAbrirOS tela = new TelaAbrirOS();

        //Adicionando dentro do desktop (telhinha azul)
        Desktop.add(tela);

        //Tornando visivel a tela
        tela.setVisible(true);

        //Possibilitando a maximação da tela
        tela.setMaximizable(true);

        //Possibilitando o fechamento da tela através do X
        tela.setClosable(rootPaneCheckingEnabled);
    }//GEN-LAST:event_miAbrirOSActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        //Instanciando a classe do frame interno que desejo exibir
        TelaGerenciamentoOS tela = new TelaGerenciamentoOS();

        //Adicionando dentro do desktop (telhinha azul)
        Desktop.add(tela);

        //Tornando visivel a tela
        tela.setVisible(true);

        //Possibilitando a maximação da tela
        tela.setMaximizable(true);

        //Possibilitando o fechamento da tela através do X
        tela.setClosable(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void miListaMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miListaMaterialActionPerformed
        //Instanciando a classe do frame interno que desejo exibir
        TelaListagemMateriais tela = new TelaListagemMateriais();

        //Adicionando dentro do desktop (telhinha azul)
        Desktop.add(tela);

        //Tornando visivel a tela
        tela.setVisible(true);

        //Possibilitando a maximação da tela
        tela.setMaximizable(true);

        //Possibilitando o fechamento da tela através do X
        tela.setClosable(rootPaneCheckingEnabled);
    }//GEN-LAST:event_miListaMaterialActionPerformed
  
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
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem miAbrirOS;
    private javax.swing.JMenuItem miCliente;
    private javax.swing.JMenu miGerenciarOS;
    private javax.swing.JMenuItem miListaCli;
    private javax.swing.JMenuItem miListaMaterial;
    private javax.swing.JMenuItem miMaterial;
    private javax.swing.JMenuItem miTV;
    // End of variables declaration//GEN-END:variables
}
