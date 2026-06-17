package banco.model;

public class Cliente {
    private String nome, cpf, telefone;
    private long id;

    public Cliente(long id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    long getId() {

        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    String getName() {

        return nome;
    }

    void setName(String nome) {
        this.nome = nome;
    }
    
    String getCpf() {

        return cpf;
    }

    void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    String getTelefone() {

        return telefone;
    }
    
    void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente[nome: "+nome+", cpf: "+cpf+", telefone: "+telefone+"]";
    }

}
