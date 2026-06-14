package banco.ui;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
    }

    // GEN-BEGIN:initComponents
    private void initComponents() {
        btnEntrar = new javax.swing.JButton();
        Teste = new javax.swing.JButton();
        Textp = new javax.swing.JLabel();
        Campo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Frame");
        getContentPane().setLayout(null);

        btnEntrar.setText("Button");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerfomed(evt);
            }
        });

        Teste.setText("ok");

        Textp.setText("Texto do teste");

        Campo.setText("oi");

        btnEntrar.setBounds(232, 264, 100, 30);
        getContentPane().add(btnEntrar);
        Teste.setBounds(152, 96, 100, 30);
        getContentPane().add(Teste);
        Textp.setBounds(16, 96, 100, 30);
        getContentPane().add(Textp);
        Campo.setBounds(360, 96, 100, 30);
        getContentPane().add(Campo);
        setSize(600, 450);
        setLocationRelativeTo(null);
    } // GEN-END:initComponents

    // GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton Teste;
    private javax.swing.JLabel Textp;
    private javax.swing.JTextField Campo;
    // GEN-END:variables

    private String usuario = "";

    private void btnEntrarActionPerfomed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
