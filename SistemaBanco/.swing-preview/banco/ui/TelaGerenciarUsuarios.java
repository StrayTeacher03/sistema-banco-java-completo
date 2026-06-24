package banco.ui;

public class TelaGerenciarUsuarios extends javax.swing.JFrame {

    public TelaGerenciarUsuarios() {
        initComponents();
    }

    // GEN-BEGIN:initComponents
    private void initComponents() {
        lTituloGeUsuarios = new javax.swing.JLabel();
        pScroUsuarios = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCriar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TelaGerenciarUsuarios");
        getContentPane().setLayout(null);

        lTituloGeUsuarios.setText("Gerenciador de Usuários");
        lTituloGeUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTituloGeUsuarios.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        lTituloGeUsuarios.setFont(new java.awt.Font("Dialog", 0, 16));


        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Login", "Nome", "Perfil"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);

        btnCriar.setText("Novo Usuário");

        btnEditar.setText("Editar");

        btnExcluir.setText("Excluir");

        lTituloGeUsuarios.setBounds(224, 0, 192, 92);
        getContentPane().add(lTituloGeUsuarios);
        pScroUsuarios.setBounds(0, 92, 640, 240);
        getContentPane().add(pScroUsuarios);
        pScroUsuarios.setViewportView(jTable1);
        btnCriar.setBounds(0, 408, 100, 30);
        getContentPane().add(btnCriar);
        btnEditar.setBounds(224, 408, 100, 30);
        getContentPane().add(btnEditar);
        btnExcluir.setBounds(416, 408, 100, 30);
        getContentPane().add(btnExcluir);
        setSize(640, 480);
        setLocationRelativeTo(null);
    } // GEN-END:initComponents

    // GEN-BEGIN:variables
    private javax.swing.JLabel lTituloGeUsuarios;
    private javax.swing.JScrollPane pScroUsuarios;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    // GEN-END:variables
}
