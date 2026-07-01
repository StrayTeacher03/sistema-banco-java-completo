package banco.model;

import banco.interfaces.Operavel;

public abstract class ContaBancaria implements Operavel {

    protected long id;
    protected String numeroConta;
    protected Cliente titular;
    protected double saldo;

    public ContaBancaria(long id,
                         String numeroConta,
                         Cliente titular,
                         double saldo) {

        this.id = id;
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public void depositar(double valor) {

        if (valor > 0) {
            saldo += valor;
        }
    }

    @Override
    public boolean sacar(double valor) {

        if (valor > 0 && saldo >= valor) {

            saldo -= valor;
            return true;
        }

        return false;
    }

    @Override
    public String exibirSaldo() {
        return String.format("Saldo: R$ %.2f", saldo);
    }

    public abstract String gerarExtrato();
}