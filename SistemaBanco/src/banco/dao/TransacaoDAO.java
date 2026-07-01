package banco.dao;

import banco.model.Transacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    public boolean salvar(Transacao transacao) {
        String sql = "INSERT INTO transacoes(conta_id, tipo_conta, descricao, valor, data_hora) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, transacao.getContaId());
            stmt.setString(2, transacao.getTipoConta());
            stmt.setString(3, transacao.getDescricao());
            stmt.setDouble(4, transacao.getValor());
            stmt.setTimestamp(5, Timestamp.valueOf(transacao.getDataHora()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Transacao> buscarPorContaId(long contaId) {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE conta_id = ? ORDER BY data_hora DESC";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, contaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                transacoes.add(new Transacao(
                    rs.getLong("id"),
                    rs.getLong("conta_id"),
                    rs.getString("tipo_conta"),
                    rs.getString("descricao"),
                    rs.getDouble("valor"),
                    rs.getTimestamp("data_hora").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transacoes;
    }
}
