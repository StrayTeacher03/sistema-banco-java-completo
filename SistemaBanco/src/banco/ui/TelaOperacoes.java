/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package banco.ui;

import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.service.BancoService;

/**
 *
 * @author keslleyjr
 */
public class TelaOperacoes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaOperacoes.class.getName());

    private final BancoService bancoService = new BancoService();
    private ContaBancaria contaAtual;

    /**
     * Creates new form TelaOperacoes
     */
    public TelaOperacoes() {
        initComponents();
        esconderErros();
        btnBuscar.addActionListener(this::buscarConta);
        btnDep.addActionListener(this::depositar);
        btnSaque.addActionListener(this::sacar);
        btnTrans.addActionListener(this::transferir);
        
        fBusca.setColumns(15);
        fDep.setColumns(15);
        fSaque.setColumns(15);
        fNumConta.setColumns(15);
        fValorTrans.setColumns(15);
        setSize(700, 500);
        setLocationRelativeTo(null);
    }
    
    private void esconderErros() {
        lErroBusca.setVisible(false);
        lErroDep.setVisible(false);
        lErroSaque.setVisible(false);
        lErroNum.setVisible(false);
        lErroValorTrans.setVisible(false);
        lLimite.setText("");
    }
    
    private void atualizarInfoConta() {
        if (contaAtual != null) {
            lMsgTitular.setText(contaAtual.getTitular().getNome());
            lMsgTipoConta.setText(contaAtual instanceof ContaCorrente ? "Corrente" : "Poupança");
            lMsgSaldoAtual.setText(String.format("R$ %.2f", contaAtual.getSaldo()));
            if (contaAtual instanceof ContaCorrente) {
                lLimite.setText(String.format("Limite Disponível: R$ %.2f", ((ContaCorrente)contaAtual).getLimiteCheque()));
            } else {
                lLimite.setText("");
            }
        }
    }
    
    private void buscarConta(java.awt.event.ActionEvent evt) {
        esconderErros();
        String numero = fBusca.getText().trim();
        contaAtual = bancoService.buscarContaPorNumero(numero);
        if (contaAtual != null) {
            atualizarInfoConta();
        } else {
            lMsgTitular.setText("");
            lMsgTipoConta.setText("");
            lMsgSaldoAtual.setText("");
            lErroBusca.setText("Conta não encontrada.");
            lErroBusca.setVisible(true);
        }
    }
    
    private void depositar(java.awt.event.ActionEvent evt) {
        esconderErros();
        if (contaAtual == null) {
            lErroDep.setText("Busque uma conta primeiro.");
            lErroDep.setVisible(true);
            return;
        }
        try {
            double valor = Double.parseDouble(fDep.getText().replaceAll("[^0-9,]", "").replace(",", "."));
            if (bancoService.depositar(contaAtual.getNumeroConta(), valor)) {
                fDep.setText("");
                buscarConta(null); // recarrega a conta e atualiza as infos
            } else {
                lErroDep.setText("Valor inválido.");
                lErroDep.setVisible(true);
            }
        } catch (NumberFormatException e) {
            lErroDep.setText("Valor inválido.");
            lErroDep.setVisible(true);
        }
    }
    
    private void sacar(java.awt.event.ActionEvent evt) {
        esconderErros();
        if (contaAtual == null) {
            lErroSaque.setText("Busque uma conta primeiro.");
            lErroSaque.setVisible(true);
            return;
        }
        try {
            double valor = Double.parseDouble(fSaque.getText().replaceAll("[^0-9,]", "").replace(",", "."));
            if (bancoService.sacar(contaAtual.getNumeroConta(), valor)) {
                fSaque.setText("");
                buscarConta(null);
            } else {
                lErroSaque.setText("Saldo insuficiente ou limite atingido.");
                lErroSaque.setVisible(true);
            }
        } catch (NumberFormatException e) {
            lErroSaque.setText("Valor inválido.");
            lErroSaque.setVisible(true);
        }
    }
    
    private void transferir(java.awt.event.ActionEvent evt) {
        esconderErros();
        if (contaAtual == null) {
            lErroValorTrans.setText("Busque uma conta de origem primeiro.");
            lErroValorTrans.setVisible(true);
            return;
        }
        String contaDestino = fNumConta.getText().trim();
        if (contaDestino.isEmpty()) {
            lErroNum.setText("Informe a conta destino.");
            lErroNum.setVisible(true);
            return;
        }
        try {
            double valor = Double.parseDouble(fValorTrans.getText().replaceAll("[^0-9,]", "").replace(",", "."));
            if (bancoService.transferir(contaAtual.getNumeroConta(), contaDestino, valor)) {
                fValorTrans.setText("");
                fNumConta.setText("");
                buscarConta(null);
            } else {
                lErroValorTrans.setText("Erro na transferência (Saldo insuficiente ou conta inválida).");
                lErroValorTrans.setVisible(true);
            }
        } catch (NumberFormatException e) {
            lErroValorTrans.setText("Valor inválido.");
            lErroValorTrans.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        lDep = new javax.swing.JLabel();
        btnDep = new javax.swing.JButton();
        fDep = new javax.swing.JTextField();
        lErroDep = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnSaque = new javax.swing.JButton();
        fSaque = new javax.swing.JTextField();
        lErroSaque = new javax.swing.JLabel();
        lLimite = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lNumConta = new javax.swing.JLabel();
        fNumConta = new javax.swing.JTextField();
        lValorTrans = new javax.swing.JLabel();
        fValorTrans = new javax.swing.JTextField();
        btnTrans = new javax.swing.JButton();
        lErroNum = new javax.swing.JLabel();
        lErroValorTrans = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lErroBusca = new javax.swing.JLabel();
        fBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lTitular = new javax.swing.JLabel();
        lMsgTitular = new javax.swing.JLabel();
        lTipoConta = new javax.swing.JLabel();
        lMsgTipoConta = new javax.swing.JLabel();
        lSaldoAtual = new javax.swing.JLabel();
        lMsgSaldoAtual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));
        setSize(new java.awt.Dimension(600, 400));

        lDep.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        lDep.setText("Faça um depósito");

        btnDep.setText("Depositar");
        btnDep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        fDep.setColumns(4);
        fDep.setText("Valor");
        fDep.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        lErroDep.setForeground(new java.awt.Color(255, 0, 0));
        lErroDep.setText("Valor Inválido");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(lDep))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lErroDep)
                            .addComponent(fDep, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDep))))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lDep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(fDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lErroDep)
                .addGap(18, 18, 18)
                .addComponent(btnDep))
        );

        jTabbedPane1.addTab("Depósito", jPanel3);

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        jLabel10.setText("Faça um saque");

        btnSaque.setText("Sacar");
        btnSaque.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        fSaque.setColumns(4);
        fSaque.setText("Valor");

        lErroSaque.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        lErroSaque.setForeground(new java.awt.Color(255, 0, 0));
        lErroSaque.setText("Valor Inválido");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fSaque, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaque))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lErroSaque, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lLimite)
                        .addGap(34, 34, 34)))
                .addGap(239, 239, 239))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lLimite)
                .addGap(18, 18, 18)
                .addComponent(fSaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lErroSaque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnSaque))
        );

        jTabbedPane1.addTab("Saque", jPanel4);

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        jLabel13.setText("Faça uma transferência");

        lNumConta.setText("Número conta:");

        fNumConta.setColumns(4);
        fNumConta.setText("Num");

        lValorTrans.setText("Valor da transferência:");

        fValorTrans.setColumns(4);
        fValorTrans.setText("Valor");

        btnTrans.setText("Transferir");
        btnTrans.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lErroNum.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        lErroNum.setForeground(new java.awt.Color(255, 0, 0));
        lErroNum.setText("Número de conta inválido");

        lErroValorTrans.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        lErroValorTrans.setForeground(new java.awt.Color(255, 0, 0));
        lErroValorTrans.setText("Valor inválido");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(161, 161, 161))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lValorTrans)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fValorTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lNumConta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fNumConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(83, 83, 83)
                                .addComponent(btnTrans))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(lErroValorTrans))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(lErroNum)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lErroNum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNumConta)
                            .addComponent(fNumConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnTrans)
                        .addGap(1, 1, 1)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lValorTrans)
                    .addComponent(fValorTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lErroValorTrans))
        );

        jTabbedPane1.addTab("Transferência", jPanel5);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lErroBusca.setForeground(new java.awt.Color(255, 0, 0));
        lErroBusca.setText("Erro");
        jPanel1.add(lErroBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 9, -1, -1));

        fBusca.setColumns(4);
        fBusca.setText("Número");
        fBusca.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(fBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 6, 65, -1));

        btnBuscar.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 6, -1, -1));

        lTitular.setText("Titular:");
        jPanel1.add(lTitular, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 65, -1, -1));
        jPanel1.add(lMsgTitular, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 65, -1, -1));

        lTipoConta.setText("Tipo de Conta:");
        jPanel1.add(lTipoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 101, -1, -1));
        jPanel1.add(lMsgTipoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 101, -1, -1));

        lSaldoAtual.setText("Saldo Atual:");
        jPanel1.add(lSaldoAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 137, -1, -1));
        jPanel1.add(lMsgSaldoAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 137, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(() -> new TelaOperacoes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDep;
    private javax.swing.JButton btnSaque;
    private javax.swing.JButton btnTrans;
    private javax.swing.JTextField fBusca;
    private javax.swing.JTextField fDep;
    private javax.swing.JTextField fNumConta;
    private javax.swing.JTextField fSaque;
    private javax.swing.JTextField fValorTrans;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lDep;
    private javax.swing.JLabel lErroBusca;
    private javax.swing.JLabel lErroDep;
    private javax.swing.JLabel lErroNum;
    private javax.swing.JLabel lErroSaque;
    private javax.swing.JLabel lErroValorTrans;
    private javax.swing.JLabel lLimite;
    private javax.swing.JLabel lMsgSaldoAtual;
    private javax.swing.JLabel lMsgTipoConta;
    private javax.swing.JLabel lMsgTitular;
    private javax.swing.JLabel lNumConta;
    private javax.swing.JLabel lSaldoAtual;
    private javax.swing.JLabel lTipoConta;
    private javax.swing.JLabel lTitular;
    private javax.swing.JLabel lValorTrans;
    // End of variables declaration//GEN-END:variables
}
