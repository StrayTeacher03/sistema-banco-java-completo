package banco.model;

import java.time.LocalDateTime;

public class Transacao {

    private long id;
    private long contaId;
    private String tipoConta;
    private String descricao;
    private double valor;
    private LocalDateTime dataHora;

    public Transacao() {
    }

    public Transacao(long id, long contaId, String tipoConta, String descricao, double valor, LocalDateTime dataHora) {
        this.id = id;
        this.contaId = contaId;
        this.tipoConta = tipoConta;
        this.descricao = descricao;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContaId() {
        return contaId;
    }

    public void setContaId(long contaId) {
        this.contaId = contaId;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
