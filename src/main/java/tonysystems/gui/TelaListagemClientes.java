package tonysystems.gui;

import java.awt.Desktop;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tonysystems.dao.ClienteDAO;
import tonysystems.model.Cliente;

public class TelaListagemClientes extends javax.swing.JInternalFrame {

    public TelaListagemClientes() {
        initComponents();

        ClienteDAO dao = new ClienteDAO();

        preencherTabela(dao.listarTodos());
    }

    // ---------> MÉTODOS <----------
    public void preencherTabela(List<Cliente> lista) {

        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setRowCount(0);

        for (Cliente c : lista) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getCpf(),
                c.getTelefone(),
                c.getEndereco()
            });
        }
    }

    // ---------> AÇÕES <-----------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta de clientes"));

        jLabel1.setText("Cliente");

        txtCliente.setToolTipText("Insira o nome do cliente");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(this::btnPesquisarActionPerformed);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Telefone", "Endereço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        btnExcluir.setText("Excluir Cliente");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnAtualizar.setText("Atualizar Dados");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnAtualizar))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        String nomeBusca = txtCliente.getText();

        if (nomeBusca == null || nomeBusca.trim().isEmpty()) {
            return;
        }

        try {
            ClienteDAO dao = new ClienteDAO();

            List<Cliente> lista = dao.listarPorNome(nomeBusca);

            preencherTabela(lista);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERRO AO BUSCAR CATEGORIA. " + e.getMessage());
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked

    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        int linhaSelecionada = tblClientes.getSelectedRow();

        if (linhaSelecionada >= 0) {
            // 1. Pegar o ID da coluna 0 (ajuste se for outra coluna)
            int id = Integer.parseInt(tblClientes.getValueAt(linhaSelecionada, 0).toString());

            int resposta = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente remover o cadastro deste cliente?",
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    ClienteDAO dao = new ClienteDAO();
                    // 2. Passar o ID e não a LINHA
                    dao.excluir(id);

                    // 3. Atualizar a tabela para o cliente sumir da tela
                    preencherTabela(dao.listarTodos());

                    JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
                }
            }
        }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // 1. Pega o ID da linha selecionada
        int linha = tblClientes.getSelectedRow();
        
        int id = (int) tblClientes.getValueAt(linha, 0);

        
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = dao.buscarPorId(id);

        //Instancia a tela de cadastro
        TelaCadastroCliente telaCad = new TelaCadastroCliente();

        //Adiciona a tela no mesmo painel (DesktopPane) da tela atual
        //Adicionando dentro do desktop (telhinha azul)
        this.getDesktopPane().add(telaCad);
        telaCad.setVisible(true);

        //CHAMA O MÉTODO que criamos no passo anterior
        telaCad.atualizarCampos(cliente);

        // Fecha a tela de consulta para não acumular janelas
        this.dispose();
    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliente;
    // End of variables declaration//GEN-END:variables
}
