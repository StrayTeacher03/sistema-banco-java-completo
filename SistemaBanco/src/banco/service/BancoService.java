package banco.service;

import banco.dao.ClienteDAO;
import banco.dao.ContaCorrenteDAO;
import banco.dao.ContaPoupancaDAO;
import banco.dao.TransacaoDAO;
import banco.model.Cliente;
import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.model.Transacao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BancoService {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
    private final ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();

    public boolean cadastrarCliente(Cliente cliente) {
        return clienteDAO.salvar(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.buscarTodos();
    }

    public boolean cadastrarContaCorrente(String numeroConta, double saldo, double limiteCheque, Cliente titular) {
        if (numeroConta == null || numeroConta.isBlank() || titular == null) {
            return false;
        }
        ContaCorrente conta = new ContaCorrente(0, numeroConta, titular, saldo, limiteCheque);
        return contaCorrenteDAO.salvar(conta);
    }

    public boolean cadastrarContaPoupanca(String numeroConta, double saldo, double taxaRendimento, Cliente titular) {
        if (numeroConta == null || numeroConta.isBlank() || titular == null) {
            return false;
        }
        ContaPoupanca conta = new ContaPoupanca(0, numeroConta, titular, saldo, taxaRendimento);
        return contaPoupancaDAO.salvar(conta);
    }

    public ContaBancaria buscarContaPorNumero(String numeroConta) {
        ContaCorrente cc = contaCorrenteDAO.buscarPorNumeroConta(numeroConta);
        if (cc != null) {
            return cc;
        }
        return contaPoupancaDAO.buscarPorNumeroConta(numeroConta);
    }

    public List<Transacao> buscarTransacoes(String numeroConta) {
        ContaBancaria conta = buscarContaPorNumero(numeroConta);
        if (conta == null) {
            return new ArrayList<>();
        }
        return transacaoDAO.buscarPorContaId(conta.getId());
    }

    public boolean depositar(String numeroConta, double valor) {
        if (valor <= 0) {
            return false;
        }
        ContaBancaria conta = buscarContaPorNumero(numeroConta);
        if (conta == null) {
            return false;
        }
        conta.depositar(valor);
        boolean atualizado = atualizarConta(conta);
        if (atualizado) {
            Transacao transacao = new Transacao(0, conta.getId(), getTipoConta(conta), "Depósito", valor, LocalDateTime.now());
            transacaoDAO.salvar(transacao);
        }
        return atualizado;
    }

    public boolean sacar(String numeroConta, double valor) {
        if (valor <= 0) {
            return false;
        }
        ContaBancaria conta = buscarContaPorNumero(numeroConta);
        if (conta == null) {
            return false;
        }
        if (!conta.sacar(valor)) {
            return false;
        }
        boolean atualizado = atualizarConta(conta);
        if (atualizado) {
            Transacao transacao = new Transacao(0, conta.getId(), getTipoConta(conta), "Saque", valor, LocalDateTime.now());
            transacaoDAO.salvar(transacao);
        }
        return atualizado;
    }

    public boolean transferir(String contaOrigem, String contaDestino, double valor) {
        if (contaOrigem == null || contaDestino == null || valor <= 0 || contaOrigem.equals(contaDestino)) {
            return false;
        }
        ContaBancaria origem = buscarContaPorNumero(contaOrigem);
        ContaBancaria destino = buscarContaPorNumero(contaDestino);
        if (origem == null || destino == null) {
            return false;
        }
        if (!origem.sacar(valor)) {
            return false;
        }
        destino.depositar(valor);
        boolean origemAtualizada = atualizarConta(origem);
        boolean destinoAtualizada = atualizarConta(destino);
        if (origemAtualizada && destinoAtualizada) {
            transacaoDAO.salvar(new Transacao(0, origem.getId(), getTipoConta(origem), "Transferência para " + contaDestino, valor, LocalDateTime.now()));
            transacaoDAO.salvar(new Transacao(0, destino.getId(), getTipoConta(destino), "Transferência de " + contaOrigem, valor, LocalDateTime.now()));
            return true;
        }
        return false;
    }

    public boolean aplicarRendimento(String numeroConta) {
        ContaBancaria conta = buscarContaPorNumero(numeroConta);
        if (!(conta instanceof ContaPoupanca)) {
            return false;
        }
        ContaPoupanca poupanca = (ContaPoupanca) conta;
        poupanca.aplicarRendimento();
        boolean atualizado = contaPoupancaDAO.atualizarSaldo(poupanca);
        if (atualizado) {
            transacaoDAO.salvar(new Transacao(0, poupanca.getId(), getTipoConta(poupanca), "Rendimento aplicado", poupanca.calcularRendimento(), LocalDateTime.now()));
        }
        return atualizado;
    }

    public int contarCorrentes() {
        return contaCorrenteDAO.listarTodos().size();
    }

    public int contarPoupancas() {
        return contaPoupancaDAO.listarTodos().size();
    }

    public double patrimonioTotal() {
        double total = 0;
        for (ContaBancaria conta : contaCorrenteDAO.listarTodos()) {
            total += conta.getSaldo();
        }
        for (ContaBancaria conta : contaPoupancaDAO.listarTodos()) {
            total += conta.getSaldo();
        }
        return total;
    }

    public List<ContaBancaria> listarTodasContas() {
        List<ContaBancaria> contas = new ArrayList<>();
        contas.addAll(contaCorrenteDAO.listarTodos());
        contas.addAll(contaPoupancaDAO.listarTodos());
        return contas;
    }

    private boolean atualizarConta(ContaBancaria conta) {
        if (conta instanceof ContaCorrente) {
            return contaCorrenteDAO.atualizarSaldo((ContaCorrente) conta);
        }
        if (conta instanceof ContaPoupanca) {
            return contaPoupancaDAO.atualizarSaldo((ContaPoupanca) conta);
        }
        return false;
    }

    private String getTipoConta(ContaBancaria conta) {
        return conta instanceof ContaCorrente ? "CORRENTE" : "POUPANCA";
    }
}
