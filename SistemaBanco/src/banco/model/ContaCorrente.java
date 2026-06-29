package banco.model;

import javax.swing.JOptionPane;

public class ContaCorrente extends ContaBancaria {
    double limiteChequEspecial;

    ContaCorrente(String numeroConta, Cliente titular, double saldoInicial, double limiteChequEspecial) {
        super(numeroConta, titular, saldoInicial);
        this.limiteChequEspecial = limiteChequEspecial;
    }

    @Override
    public boolean sacar(double valor) {
        double saldo = getSaldo();
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            setSaldo(saldo);
            registrarTransacao(String.format("Saque: R$%.2f", valor));
            return true;
        } else if (valor > saldo) {
            int resp = JOptionPane.showConfirmDialog(null,
                    "Saldo insuficiente. Deseja usar o limite do cheque especial?", "Cheque especial",
                    JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION && valor <= saldo + limiteChequEspecial) {
                JOptionPane.showMessageDialog(null,
                        String.format("Saque autorizado usando cheque especial. Limite restante: R$%.2f",
                                limiteChequEspecial - (valor - saldo)),
                        "Cheque especial",
                        JOptionPane.INFORMATION_MESSAGE);
                usarChequeEspecial(valor);
                registrarTransacao("Cheque especial usado: R$" + (valor - saldo));
                return true;
            } else if (resp == JOptionPane.YES_OPTION && valor > saldo + limiteChequEspecial) {
                JOptionPane.showMessageDialog(null, "Saque não autorizado. Saldo e limite insuficientes.",
                        "Erro no saque",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Saque cancelado", "Erro no saque",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Valor de saque inválido", "Erro no saque",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    void usarChequeEspecial(double valor) {
        double saldo = getSaldo();
        setSaldo(0);
        limiteChequEspecial -= (valor - saldo);
    }

    @Override
    void gerarExtrato() {
        
    }

}
