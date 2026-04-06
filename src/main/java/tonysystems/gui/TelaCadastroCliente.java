package tonysystems.gui;

import javax.swing.JOptionPane;
import tonysystems.dao.ClienteDAO;
import tonysystems.model.Cliente;

public class TelaCadastroCliente extends javax.swing.JInternalFrame {

    private Cliente clienteAlterado = null;

    public TelaCadastroCliente() {
        initComponents();
        btnAtualizar.setEnabled(false);
    }

    // --------> MÉTODOS <---------
    //Validar campos
    public void validarCampos() {

        if (txtNome.getText().isBlank() || txtTelefone.getText().isBlank() || txtCPF.getText().isBlank() || txtEndereco.getText().isBlank()) {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }
        cadastrarDados();

    }

    public void cadastrarDados() {

        ClienteDAO dao = new ClienteDAO();
        String verificaCpf = txtCPF.getText().trim();

        try {

            Cliente clienteExistente = dao.buscarPorCpf(verificaCpf);

            if (clienteExistente != null) {
                JOptionPane.showMessageDialog(this, "Erro: Já existe um cliente cadastrado com este CPF!");
                txtCPF.requestFocus(); // Foca no campo para o usuário corrigir
                return; // Interrompe o cadastro
            }

            //Coletando os dados passados e validando para criação de um novo cliente
            Cliente cliente = new Cliente();
            cliente.setNome(txtNome.getText());
            cliente.setCpf(txtCPF.getText());
            cliente.setTelefone(txtTelefone.getText());
            cliente.setEndereco(txtEndereco.getText());

            //Chama o dao para cadastrar o cliente no banco
            dao.cadastrar(cliente);

            //Mensagem de confirmação
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

            //Limpar os campos após o cadastro
            limparCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO." + e.getMessage());

        }

    }

    public void limparCampos() {

        txtNome.setText("");
        txtEndereco.setText("");
        txtCPF.setText(null);
        txtTelefone.setText(null);
        txtNome.requestFocus();
    }

    public void atualizarCampos(Cliente c) { //Recebe como parametro o cliente que será alterado

        try {
            this.clienteAlterado = c; // Guarda o objeto que veio da busca
            txtNome.setText(c.getNome());
            txtCPF.setText(c.getCpf());
            txtCPF.setEnabled(false);
            txtTelefone.setText(c.getTelefone());
            txtEndereco.setText(c.getEndereco());

            btnCadastrar.setEnabled(false); // Trava o botão de novo cadastro
            btnAtualizar.setEnabled(true);  // Destrava o botão de edição

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERRO " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCPF = new javax.swing.JFormattedTextField();
        txtEndereco = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(153, 153, 153))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Pessoais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabel1.setText("Nome");

        jLabel2.setText("Telefone");

        jLabel3.setText("Endereço");

        jLabel4.setText("CPF");

        txtNome.setToolTipText("Insira o nome do cliente");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setToolTipText("Telefone celular do cliente");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtEndereco.setToolTipText("Nome da rua simples e bairo");

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnCadastrar)
                .addGap(18, 18, 18)
                .addComponent(btnAtualizar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnAtualizar)
                    .addComponent(btnLimpar))
                .addContainerGap(174, Short.MAX_VALUE))
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

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        validarCampos();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        //Verificando se a variavel clienteAlterado recebeu algo
        if (clienteAlterado != null) {
            try {
                ClienteDAO dao = new ClienteDAO();

                // 1. Pegamos os novos dados que o usuário digitou nos campos
                clienteAlterado.setNome(txtNome.getText());
                clienteAlterado.setTelefone(txtTelefone.getText());
                clienteAlterado.setEndereco(txtEndereco.getText());
                

                // 2. Chamamos o método atualizar do DAO (que faz o manager.merge)
                dao.atualizar(clienteAlterado);

                // 3. Feedback para o usuário
                JOptionPane.showMessageDialog(this, "Dados do cliente atualizados com sucesso!");

                // 4. Limpamos o estado da tela
                clienteAlterado = null;
                limparCampos();
                this.dispose(); // Fecha a tela de edição após o sucesso

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + e.getMessage());
            }

        } else {
            // Caso o botão seja clicado sem um cliente carregado
            JOptionPane.showMessageDialog(this, "Nenhum cliente selecionado para edição.");
        }


    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
