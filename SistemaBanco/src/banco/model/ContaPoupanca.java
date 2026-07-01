package banco.model;

public class ContaPoupanca extends ContaBancaria {

    private double taxaRendimento;


    public ContaPoupanca(long id,
                         String numeroConta,
                         Cliente titular,
                         double saldo,
                         double taxaRendimento) {

        super(id, numeroConta, titular, saldo);

        this.taxaRendimento = taxaRendimento;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public double calcularRendimento() {

        return saldo * (taxaRendimento / 100);
    }

    public void aplicarRendimento() {

        saldo += calcularRendimento();
    }

    @Override
    public String gerarExtrato() {
        return String.format("Conta Poupança: %s - Saldo: R$ %.2f - Rendimento: %.2f%%", numeroConta, saldo, taxaRendimento);
    }
}