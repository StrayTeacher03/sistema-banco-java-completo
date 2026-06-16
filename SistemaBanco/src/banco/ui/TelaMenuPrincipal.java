package banco.ui;

public class TelaMenuPrincipal extends javax.swing.JFrame {

    public TelaMenuPrincipal() {
        initComponents();
    }

    // GEN-BEGIN:initComponents
    private void initComponents() {
        lMsgSaud = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        menuP = new javax.swing.JMenuBar();
        gerencia = new javax.swing.JMenu();
        gUsuarios = new javax.swing.JMenuItem();
        eConta = new javax.swing.JMenuItem();
        relGe = new javax.swing.JMenuItem();
        cadastro = new javax.swing.JMenu();
        cadCliente = new javax.swing.JMenuItem();
        cadCC = new javax.swing.JMenuItem();
        cadCP = new javax.swing.JMenuItem();
        operacoes = new javax.swing.JMenu();
        opDep = new javax.swing.JMenuItem();
        opSaque = new javax.swing.JMenuItem();
        opTrans = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TelaMenuPrincipal");
        getContentPane().setLayout(null);

        lMsgSaud.setText("Bem-vindo, [Nome do Usuário]");

        btnSair.setText("Sair");


        gerencia.setText("Gerenciamento");

        gUsuarios.setText("Gerenciar Usuários");

        eConta.setText("Extrato da Conta");

        relGe.setText("Relatório Geral do Banco");

        cadastro.setText("Cadastro");

        cadCliente.setText("Cadastrar Cliente");

        cadCC.setText("Cadastrar Conta Corrente");

        cadCP.setText("Cadastrar Conta Poupança");

        operacoes.setText("Operações");

        opDep.setText("Depósito");

        opSaque.setText("Saque");

        opTrans.setText("Transferência");

        lMsgSaud.setBounds(56, 8, 184, 30);
        getContentPane().add(lMsgSaud);
        btnSair.setBounds(536, 8, 100, 30);
        getContentPane().add(btnSair);
        menuP.setBounds(0, 88, 296, 56);
        getContentPane().add(menuP);
        menuP.add(gerencia);
        gerencia.add(gUsuarios);
        gerencia.add(eConta);
        gerencia.add(relGe);
        menuP.add(cadastro);
        cadastro.add(cadCliente);
        cadastro.add(cadCC);
        cadastro.add(cadCP);
        menuP.add(operacoes);
        operacoes.add(opDep);
        operacoes.add(opSaque);
        operacoes.add(opTrans);
        setSize(640, 480);
        setLocationRelativeTo(null);
    } // GEN-END:initComponents

    // GEN-BEGIN:variables
    private javax.swing.JLabel lMsgSaud;
    private javax.swing.JButton btnSair;
    private javax.swing.JMenuBar menuP;
    private javax.swing.JMenu gerencia;
    private javax.swing.JMenuItem gUsuarios;
    private javax.swing.JMenuItem eConta;
    private javax.swing.JMenuItem relGe;
    private javax.swing.JMenu cadastro;
    private javax.swing.JMenuItem cadCliente;
    private javax.swing.JMenuItem cadCC;
    private javax.swing.JMenuItem cadCP;
    private javax.swing.JMenu operacoes;
    private javax.swing.JMenuItem opDep;
    private javax.swing.JMenuItem opSaque;
    private javax.swing.JMenuItem opTrans;
    // GEN-END:variables
}
