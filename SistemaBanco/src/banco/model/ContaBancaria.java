package banco.model;

import banco.interfaces.Operavel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

public abstract class ContaBancaria implements Operavel {
    private String numeroConta;
    private Cliente titular;
    private double saldo;
    private List<String> historico;
    private LocalDateTime evento;
    private DateTimeFormatter formatador;
    private StringBuilder sb = new StringBuilder();

    ContaBancaria(String numeroConta, Cliente titular, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        saldo = saldoInicial;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            evento = LocalDateTime.now();
            formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            historico.add(String.format("Depósito: R$%.2f - %s", valor, evento.format(formatador)));
            sb.append(String.format("Depósito: R$%.2f - %s\n", valor, evento.format(formatador)));
        } else {
            JOptionPane.showMessageDialog(null, "Valor de depósito inválido", "Erro no depósito",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            evento = LocalDateTime.now();
            formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            historico.add(String.format("Saque: R$%.2f - %s", valor, evento.format(formatador)));
            sb.append(String.format("Saque: R$%.2f - %s\n", valor, evento.format(formatador)));
            return true;
        } else if (valor > saldo) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Erro no saque",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Valor de saque inválido", "Erro no saque",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public void exibirSaldo() {
        JOptionPane.showMessageDialog(null, String.format("Saldo atual: R$%.2f", saldo), "Saldo Atual",
        JOptionPane.INFORMATION_MESSAGE);
    }
    
    protected void registrarTransacao(String descricao) {
        evento = LocalDateTime.now();
        formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        historico.add(String.format("%s - %s", descricao, evento.format(formatador)));
        sb.append(String.format("%s - %s\n", descricao, evento.format(formatador)));

    }

    void exibirHistorico() {
        JOptionPane.showMessageDialog(null, String.format("%s", sb.toString()), "Histórico de Transações",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    String getNumeroConta() {
        return numeroConta;
    }

    void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    Cliente getTitular() {
        return titular;
    }

    void setTitular(Cliente titular) {
        this.titular = titular;
    }

    double getSaldo() {
        return saldo;
    }

    void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    List<String> getHistorico() {
        return historico;
    }

    void setHistorico(List<String> historico) {
        this.historico = historico;
    }

    abstract void gerarExtrato();
}
