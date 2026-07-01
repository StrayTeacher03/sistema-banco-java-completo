package banco.model;

public class Cliente {
    private String nome, cpf, telefone;
    private long id;

    public Cliente() {
    }

    public Cliente(long id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getTelefone() {

        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente[id: " + id+ ", nome: "+nome+", cpf: "+cpf+", telefone: "+telefone+"]";
    }

}
