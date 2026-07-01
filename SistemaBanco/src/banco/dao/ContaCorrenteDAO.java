package banco.dao;

import banco.model.Cliente;
import banco.model.ContaCorrente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrenteDAO {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public boolean salvar(ContaCorrente conta) {
        String sql = "INSERT INTO contas_correntes(numero_conta, saldo, limite_cheque, cliente_id) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, conta.getNumeroConta());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setDouble(3, conta.getLimiteCheque());
            stmt.setLong(4, conta.getTitular().getId());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                conta.setId(keys.getLong(1));
            }
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public ContaCorrente buscarPorNumeroConta(String numeroConta) {
        String sql = "SELECT * FROM contas_correntes WHERE numero_conta = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, numeroConta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorId(rs.getLong("cliente_id"));
                if (cliente == null) {
                    return null;
                }
                return new ContaCorrente(
                    rs.getLong("id"),
                    rs.getString("numero_conta"),
                    cliente,
                    rs.getDouble("saldo"),
                    rs.getDouble("limite_cheque")
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public List<ContaCorrente> listarTodos() {
        List<ContaCorrente> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas_correntes";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorId(rs.getLong("cliente_id"));
                if (cliente != null) {
                    contas.add(new ContaCorrente(
                        rs.getLong("id"),
                        rs.getString("numero_conta"),
                        cliente,
                        rs.getDouble("saldo"),
                        rs.getDouble("limite_cheque")
                    ));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return contas;
    }

    public boolean atualizarSaldo(ContaCorrente conta) {
        String sql = "UPDATE contas_correntes SET saldo = ?, limite_cheque = ? WHERE id = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setDouble(2, conta.getLimiteCheque());
            stmt.setLong(3, conta.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
