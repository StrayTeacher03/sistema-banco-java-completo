package banco.model;

public class ContaCorrente extends ContaBancaria {

    private double limiteCheque;

    public ContaCorrente(long id,
                         String numeroConta,
                         Cliente titular,
                         double saldo,
                         double limiteCheque) {

        super(id, numeroConta, titular, saldo);

        this.limiteCheque = limiteCheque;
    }

    public double getLimiteCheque() {
        return limiteCheque;
    }

    public void setLimiteCheque(double limiteCheque) {
        this.limiteCheque = limiteCheque;
    }

    @Override
    public boolean sacar(double valor) {

        if (valor > 0 && valor <= saldo + limiteCheque) {

            saldo -= valor;
            return true;
        }

        return false;
    }

    @Override
    public String gerarExtrato() {
        return String.format("Conta Corrente: %s - Saldo: R$ %.2f - Limite: R$ %.2f", numeroConta, saldo, limiteCheque);
    }
}