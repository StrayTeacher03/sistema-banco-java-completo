package banco.dao;

import banco.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean salvar(Cliente cliente) {

        String sql =
            "INSERT INTO clientes(nome, cpf, telefone) VALUES (?, ?, ?)";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Cliente buscarPorCpf(String cpf) {

        String sql =
            "SELECT * FROM clientes WHERE cpf = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));

                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Cliente buscarPorId(long id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Cliente> buscarTodos() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM clientes";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public boolean atualizar(Cliente cliente) {

        String sql =
            "UPDATE clientes SET nome = ?, telefone = ? WHERE cpf = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getCpf());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluir(long id) {

        String sql =
            "DELETE FROM clientes WHERE id = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setLong(1, id);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}