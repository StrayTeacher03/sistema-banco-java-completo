package banco.ui;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
    }

    // GEN-BEGIN:initComponents
    private void initComponents() {
        btnEntrar = new javax.swing.JButton();
        botao = new javax.swing.JButton();
        Textp = new javax.swing.JLabel();
        Campo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Frame");
        getContentPane().setLayout(null);

        btnEntrar.setText("Button");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerfomed(evt);
            }
        });

        botao.setText("ok");

        Textp.setText("Texto do teste");

        Campo.setText("oi");

        jPanel1.setLayout(null);

        jButton1.setText("Button");

        btnEntrar.setBounds(64, 184, 100, 30);
        getContentPane().add(btnEntrar);
        botao.setBounds(152, 96, 100, 30);
        getContentPane().add(botao);
        Textp.setBounds(16, 96, 100, 30);
        getContentPane().add(Textp);
        Campo.setBounds(360, 96, 100, 30);
        getContentPane().add(Campo);
        jPanel1.setBounds(264, 184, 200, 150);
        getContentPane().add(jPanel1);
        jButton1.setBounds(32, 64, 100, 30);
        jPanel1.add(jButton1);
        setSize(600, 450);
        setLocationRelativeTo(null);
    } // GEN-END:initComponents

    // GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton botao;
    private javax.swing.JLabel Textp;
    private javax.swing.JTextField Campo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jButton1;
    // GEN-END:variables

    private String usuario = "";

    private void btnEntrarActionPerfomed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
