package banco.dao;

import banco.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios(login, senha, nome, perfil) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, usuario.getPerfil());
            stmt.executeUpdate();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public Usuario buscarPorLogin(String login) {
        String sql = "SELECT * FROM usuarios WHERE login = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("nome"),
                    rs.getString("perfil")
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Usuario buscarPorId(long id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("nome"),
                    rs.getString("perfil")
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuarios.add(
                    new Usuario(
                        rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("nome"),
                        rs.getString("perfil")
                    )
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return usuarios;
    }

    public boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET login = ?, senha = ?, nome = ?, perfil = ? WHERE id = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, usuario.getPerfil());
            stmt.setLong(5, usuario.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean excluir(long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (
            Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
