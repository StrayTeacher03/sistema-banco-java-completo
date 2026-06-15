package banco.ui;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
    }

    // GEN-BEGIN:initComponents
    private void initComponents() {
        lUser = new javax.swing.JLabel();
        lPassword = new javax.swing.JLabel();
        fLogin = new javax.swing.JTextField();
        fSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TelaLogin");
        getContentPane().setLayout(null);

        lUser.setText("Usuário: ");

        lPassword.setText("Senha: ");

        fLogin.setText("");

        fSenha.setText("");

        btnEntrar.setText("Button");

        lUser.setBounds(80, 64, 100, 30);
        getContentPane().add(lUser);
        lPassword.setBounds(80, 128, 100, 30);
        getContentPane().add(lPassword);
        fLogin.setBounds(136, 64, 384, 40);
        getContentPane().add(fLogin);
        fSenha.setBounds(136, 128, 384, 40);
        getContentPane().add(fSenha);
        btnEntrar.setBounds(264, 264, 100, 32);
        getContentPane().add(btnEntrar);
        setSize(640, 480);
        setLocationRelativeTo(null);
    } // GEN-END:initComponents

    // GEN-BEGIN:variables
    private javax.swing.JLabel lUser;
    private javax.swing.JLabel lPassword;
    private javax.swing.JTextField fLogin;
    private javax.swing.JPasswordField fSenha;
    private javax.swing.JButton btnEntrar;
    // GEN-END:variables
}
