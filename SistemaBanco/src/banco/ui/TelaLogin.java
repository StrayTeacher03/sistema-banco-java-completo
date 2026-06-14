package banco.ui;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
    }

    // GEN-BEGIN:initComponents
    private void initComponents() {
        btnEntrar = new javax.swing.JButton();
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

        jButton1.setText("Button");

        btnEntrar.setBounds(232, 264, 100, 30);
        getContentPane().add(btnEntrar);
        jButton1.setBounds(152, 96, 100, 30);
        getContentPane().add(jButton1);
        setSize(600, 450);
        setLocationRelativeTo(null);
    } // GEN-END:initComponents

    // GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton jButton1;
    // GEN-END:variables

    private String usuario = "";

    private void btnEntrarActionPerfomed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
