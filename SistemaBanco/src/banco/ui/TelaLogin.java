package banco.ui;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
    }

    // GEN-BEGIN:initComponents
    private void initComponents() {
        btnEntrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Frame");
        getContentPane().setLayout(null);

        btnEntrar.setText("Button");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerfomed(evt);
            }
        });

        btnEntrar.setBounds(200, 248, 100, 30);
        getContentPane().add(btnEntrar);
        setSize(600, 450);
        setLocationRelativeTo(null);
    } // GEN-END:initComponents

    // GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    // GEN-END:variables

    private void btnEntrarActionPerfomed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
