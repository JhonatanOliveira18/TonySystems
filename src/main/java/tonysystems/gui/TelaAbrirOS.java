package tonysystems.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tonysystems.dao.ClienteDAO;
import tonysystems.dao.ManutencaoDAO;
import tonysystems.dao.TelevisaoDAO;
import tonysystems.model.Cliente;
import tonysystems.model.Manutencao;
import tonysystems.model.StatusOS;
import tonysystems.model.Televisao;

public class TelaAbrirOS extends javax.swing.JInternalFrame {

    //Variáveis globais
    private Cliente clienteColetado;
    private Televisao tvSelecionada;

    public TelaAbrirOS() {
        initComponents();
    }

    //  ---------------------> MÉTODOS <---------------------------
    public void coletarCliente() {

        //Verifica qual linha foi selecionada, sendo a primeira a linha de indice 0
        int linhaSelecionada = tblClientes.getSelectedRow();

        //Se a linha tiver o indice naior ou igual a 0 ele sabera qual foi selecionada
        if (linhaSelecionada >= 0) {
            // Pega o ID da linha que o usuário clicou
            int id = Integer.parseInt(tblClientes.getValueAt(linhaSelecionada, 0).toString());

            //Instanciando o dao para poder utilizar seus métodos
            ClienteDAO dao = new ClienteDAO();

            //Buscando o cliente Coletado através do ID
            clienteColetado = dao.buscarPorId(id);

            //Preenche a tabela de acordo com o id do cliente
            preencherTabelaTvs(clienteColetado.getId());

            //Mensagem de confirmação
            JOptionPane.showMessageDialog(this, "Cliente " + clienteColetado.getNome() + " selecionado!");
        }
    }

    public void selecionarTV() {

        //Verifica se alguma linha foi selecionada
        int linhaSelecionada = tblTvs.getSelectedRow();

        if (linhaSelecionada >= 0) {
            // Pega o ID da linha que o usuário clicou
            int idTv = Integer.parseInt(tblTvs.getValueAt(linhaSelecionada, 0).toString());

            //Instanciando o dao para poder utilizar seus métodos
            TelevisaoDAO dao = new TelevisaoDAO();

            //Buscando a tv selecionada através do ID
            tvSelecionada = dao.buscarPorId(idTv); // Agora o sistema sabe qual TV será consertada

            JOptionPane.showMessageDialog(this, "TV " + tvSelecionada.getModelo() + " selecionada!");
        }

    }

    public void preencherTabelaCliente(List<Cliente> lista) {

        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setRowCount(0);

        for (Cliente c : lista) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getCpf()
            });
        }
    }

    public void preencherTabelaTvs(int idCliente) {

        TelevisaoDAO dao = new TelevisaoDAO();

        List<Televisao> listaTVs = dao.buscarPorCliente(idCliente);

        DefaultTableModel model = (DefaultTableModel) tblTvs.getModel();
        model.setRowCount(0);

        for (Televisao tv : listaTVs) {
            model.addRow(new Object[]{
                tv.getId(),
                tv.getModelo(),
                tv.getMarca()});

        }
    }

    public void limparCampos() {

        txtNome.setText("");
        txtData.setText(null);
        txtProblema.setText("");

        DefaultTableModel model = (DefaultTableModel) tblTvs.getModel();
        model.setRowCount(0);
        DefaultTableModel modelCli = (DefaultTableModel) tblClientes.getModel();
        modelCli.setRowCount(0);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtProblema = new javax.swing.JTextArea();
        btnCadastrar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTvs = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        jButton5.setText("jButton5");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Abertura de Ordem de Serviço"));

        jLabel1.setText("Nome do cliente");

        txtNome.setToolTipText("Insira o nome do cliente");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(this::btnPesquisarActionPerformed);

        jLabel2.setText("Data inicio");

        jLabel3.setText("Problema relatado");

        try {
            txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtData.setToolTipText("Insira a data de inicio da tratativa");

        txtProblema.setColumns(20);
        txtProblema.setRows(5);
        txtProblema.setToolTipText("Insira o problema relatado pelo cliente");
        jScrollPane1.setViewportView(txtProblema);

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        jLabel4.setText("Cliente");

        jLabel5.setText("TV's");

        tblTvs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Modelo", "Marca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTvs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTvsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTvs);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        jScrollPane3.setViewportView(tblClientes);

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
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisar))
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(321, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpar)
                .addGap(18, 18, 18)
                .addComponent(btnCadastrar)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(97, 97, 97)
                .addComponent(jLabel5)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnLimpar))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(133, 133, 133)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(434, Short.MAX_VALUE)))
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

    //  ---------------------> AÇÕES <---------------------------

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        String nomeBusca = txtNome.getText();

        if (nomeBusca == null || nomeBusca.trim().isEmpty()) {
            return;
        }

        try {
            ClienteDAO dao = new ClienteDAO();

            List<Cliente> lista = dao.listarPorNome(nomeBusca);

            preencherTabelaCliente(lista);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR CLIENTE " + e.getMessage());
        }

    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        coletarCliente();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void tblTvsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTvsMouseClicked
        selecionarTV();
    }//GEN-LAST:event_tblTvsMouseClicked

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        
//Verificando primeiramente se algum cliente foi de fato selecionado
        if (tvSelecionada == null) {
            JOptionPane.showMessageDialog(null, "Selecione um cliente e depois selecione uma tv");
            return;
        }

        if (txtData.getText().isBlank() || txtProblema.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos para prosseguir");
            return;
        }
        try {

            Manutencao manutencao = new Manutencao();
            ManutencaoDAO dao = new ManutencaoDAO();

            manutencao.setTelevisao(tvSelecionada); // Faz o vínculo com a TV
            manutencao.setDataInicio(LocalDate.parse(txtData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            manutencao.setProblema(txtProblema.getText());
            manutencao.setStatusOS(StatusOS.PENDENTE); // Status inicial padrão

            // 3. Salvando no banco
            dao.cadastrar(manutencao);

            JOptionPane.showMessageDialog(this, "Ordem de Serviço aberta com sucesso!");

            limparCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir OS: " + e.getMessage());
        }

    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblTvs;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtProblema;
    // End of variables declaration//GEN-END:variables
}
