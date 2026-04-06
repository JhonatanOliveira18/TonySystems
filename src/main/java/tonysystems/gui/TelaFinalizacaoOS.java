package tonysystems.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tonysystems.dao.ManutencaoDAO;
import tonysystems.dao.MaterialDAO;
import tonysystems.dao.PagamentoDAO;
import tonysystems.dao.UsoMaterialDAO;
import tonysystems.model.FormaPagamento;
import tonysystems.model.Manutencao;
import tonysystems.model.Material;
import tonysystems.model.Pagamento;
import tonysystems.model.StatusOS;
import tonysystems.model.UsoMaterial;
import java.util.HashMap;
import java.util.Map;

public class TelaFinalizacaoOS extends javax.swing.JInternalFrame {

    //Criação de uma lista para armazenas os itens utilizados, no caso os materiais utilizados
    private List<UsoMaterial> itens = new ArrayList<>();

    private Manutencao manutencao;

    public TelaFinalizacaoOS() {

        initComponents();

        cbPagamento.removeAllItems();

        for (FormaPagamento fp : FormaPagamento.values()) {
            cbPagamento.addItem(fp);

        }
    }

    // ---------> MÉTODOS <----------
    public void preencherTabela(List<Material> lista) {

        DefaultTableModel model = (DefaultTableModel) tblConsultaMaterial.getModel();
        model.setRowCount(0);

        for (Material m : lista) {
            model.addRow(new Object[]{
                m.getId(),
                m.getNome(),
                m.getMarca(),
                m.getCategoria(),
                m.getPreco(),
                m.getQtdEstoque()
            });
        }
    }

    private void atualizarTabelaLista() {

        DefaultTableModel model = (DefaultTableModel) tblListaMateriais.getModel();
        model.setRowCount(0);

        for (UsoMaterial uso : itens) {
            model.addRow(new Object[]{
                uso.getMaterial().getId(),
                uso.getMaterial().getNome(),
                uso.getQtdUsada(),
                uso.getMaterial().getPreco()
            });
        }

    }

    private void atualizarTotal() {

        double total = 0;

        for (UsoMaterial uso : itens) {//Para cada item usado
            total += uso.getMaterial().getPreco() * uso.getQtdUsada(); //Total é igual ao preço do material do material vezes ele mesmo
        }

        lblTotal.setText("TOTAL: R$ " + total);
    }

    //Atua deixando claro qual manutencao está sendo finalizada
    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;

        dadosCliente.setText("OS Nº: " + manutencao.getId()
                + "| Cliente: " + manutencao.getTelevisao().getCliente().getNome()
                + "| TV: " + manutencao.getTelevisao().getModelo());

    }

    private void finalizarOS() {

        //Verifica se a lista de itens está vazia
        if (itens.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos um material!");
            return;
        }
        
        //Verifica se alguma forma de pagamento foi escolhida
        if (cbPagamento.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione a forma de pagamento!");
            return;
        }

        try {

            MaterialDAO materialDAO = new MaterialDAO();

            // Agrupa e melhora a busca dos dados dentro do BD
            Map<Integer, Integer> consumo = new HashMap<>();

            for (UsoMaterial uso : itens) {
                int id = uso.getMaterial().getId();
                consumo.put(id, consumo.getOrDefault(id, 0) + uso.getQtdUsada()); //Esse getOrDefault significa: pegue o valor que ja esta coletado ex duas placas ou default(padrao 0) caso não tenha nenhuma placa que foi selecionada anteriormente e soma
            }

            //KeySet serve como uma lista de cada id dos diferentes materiais
            for (Integer idMaterial : consumo.keySet()) { //Esse for realiza com o primeiro Id tudo o que está dentro das chaves depois faz o mesmo com o proximo id

                //Busca todos os dados de determinado material atraves do id
                Material material = materialDAO.buscarPorId(idMaterial);
                
                //Define o estoque atual coletando a quantidade do material presente no estoque
                int estoqueAtual = material.getQtdEstoque();
                
                //Avalia quantos foram usados
                int quantidadeTotal = consumo.get(idMaterial);

                //Avalia se posso ou não utilizar determinado material com base se o que pedi é menor do que o que está presente no estoque
                if (quantidadeTotal > estoqueAtual) {
                    JOptionPane.showMessageDialog(this,
                            "Estoque insuficiente para: " + material.getNome());
                    return;
                }
                
                //Atualiza a quantidade presente no estoque removendo o que foi utilizado na manutencao
                material.setQtdEstoque(estoqueAtual - quantidadeTotal);
                materialDAO.atualizar(material); //atualiza o banco de dados
            }

            //Gravando o historico do que foi utilizado
            UsoMaterialDAO usoDAO = new UsoMaterialDAO();

            for (UsoMaterial uso : itens) {
                usoDAO.cadastrar(uso);
            }

            //Vai somando item por item de acordo com valor e quantidade utilizada e atualizando o valor total
            double total = 0;

            for (UsoMaterial uso : itens) {
                total += uso.getMaterial().getPreco() * uso.getQtdUsada();
            }

            //Coleta o que foi escolhido pelo usuário dentro do comboBox e atribui a variavel forma
            FormaPagamento forma = (FormaPagamento) cbPagamento.getSelectedItem();

            //Instanciando um novo pagamento e atribuindo informações a ele
            Pagamento pagamento = new Pagamento();
            pagamento.setValor(total);
            pagamento.setFormaPagamento(forma);
            pagamento.setManutencao(manutencao); //Atribui a manutencao o pagamento devido

            //Salva no banco de dados
            PagamentoDAO pagamentoDAO = new PagamentoDAO();
            pagamentoDAO.cadastrar(pagamento);

            //Atualiza o status da manutencao de pendente para concluido
            manutencao.setStatusOS(StatusOS.CONCLUIDO);

            //Atualizando a lista de serviços para indicar que essa manutencao foi concluida dentro do bd
            ManutencaoDAO manutencaoDAO = new ManutencaoDAO();
            manutencaoDAO.atualizar(manutencao);

            //Mensagem de confirmação
            JOptionPane.showMessageDialog(this, "OS finalizada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaterial = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        btnAdicionarMaterial = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaMateriais = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblConsultaMaterial = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        cbPagamento = new javax.swing.JComboBox<>();
        btnConfirmarPagamento = new javax.swing.JButton();
        dadosCliente = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1. Adicione o Material Utilizado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Material");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(this::btnPesquisarActionPerformed);

        jLabel3.setText("Quantidade");

        txtQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        btnAdicionarMaterial.setText("Adicionar Material");
        btnAdicionarMaterial.addActionListener(this::btnAdicionarMaterialActionPerformed);

        tblListaMateriais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Quantidade", "Valor Unitário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListaMateriais);

        jLabel4.setText("Lista de materiais");

        tblConsultaMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Marca", "Categoria", "Preço", "Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblConsultaMaterial);

        jButton1.setText("Remover Material");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel1.setText("Selecione os materiais utilizados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPesquisar))
                                    .addComponent(jLabel3)
                                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAdicionarMaterial)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel1)))))
                        .addGap(162, 162, 162)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar))
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdicionarMaterial)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2. Fechamento de orçamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTotal.setText("TOTAL:");

        cbPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(tonysystems.model.FormaPagamento.values()));

        btnConfirmarPagamento.setText("Confirmar Pagamento e Finalizar OS");
        btnConfirmarPagamento.addActionListener(this::btnConfirmarPagamentoActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmarPagamento)
                    .addComponent(lblTotal)
                    .addComponent(cbPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblTotal)
                .addGap(18, 18, 18)
                .addComponent(cbPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnConfirmarPagamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dadosCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // --------> AÇÕES <----------

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        String nomeBusca = txtMaterial.getText();

        if (nomeBusca == null || nomeBusca.trim().isEmpty()) {
            return;
        }

        try {
            MaterialDAO dao = new MaterialDAO();

            List<Material> lista = dao.listarPorNome(nomeBusca);

            preencherTabela(lista);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERRO AO BUSCAR CATEGORIA. " + e.getMessage());
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnAdicionarMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarMaterialActionPerformed
        int linha = tblConsultaMaterial.getSelectedRow();

        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um material!");
            return;
        }

        int quantidade;

        try {
            quantidade = Integer.parseInt(txtQuantidade.getText());

            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero!");
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Digite uma quantidade válida!");
            return;
        }

        int idMaterial = Integer.parseInt(
                tblConsultaMaterial.getValueAt(linha, 0).toString()
        );

        MaterialDAO dao = new MaterialDAO();
        Material material = dao.buscarPorId(idMaterial);

        for (UsoMaterial u : itens) {
            if (u.getMaterial().getId() == material.getId()) {
                u.setQtdUsada(u.getQtdUsada() + quantidade);
                atualizarTabelaLista();
                atualizarTotal();
                return;
            }
        }

        // adiciona novo
        UsoMaterial uso = new UsoMaterial(manutencao, material, quantidade);
        itens.add(uso);

        atualizarTabelaLista();
        atualizarTotal();
    }//GEN-LAST:event_btnAdicionarMaterialActionPerformed

    private void btnConfirmarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarPagamentoActionPerformed
        finalizarOS();
    }//GEN-LAST:event_btnConfirmarPagamentoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Descobrir qual linha o usuário selecionou
        int linhaSelecionada = tblListaMateriais.getSelectedRow();

        // Verificar se algo foi selecionado
        if (linhaSelecionada >= 0) {

            // Pergunta de segurança antes de excluir os dados
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente remover este material da lista?",
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                // Remover da nossa lista de objetos (ArrayList)
                itens.remove(linhaSelecionada);

                // Atualizar a tabela visual e o valor TOTAL
                atualizarTabelaLista();
                atualizarTotal();

                JOptionPane.showMessageDialog(this, "Material removido com sucesso!");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um material na lista da direita para remover.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarMaterial;
    private javax.swing.JButton btnConfirmarPagamento;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<tonysystems.model.FormaPagamento> cbPagamento;
    private javax.swing.JLabel dadosCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblConsultaMaterial;
    private javax.swing.JTable tblListaMateriais;
    private javax.swing.JTextField txtMaterial;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
