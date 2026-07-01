package banco.service;

import banco.dao.UsuarioDAO;
import banco.model.Usuario;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String login, char[] senha) {
        if (login == null || senha == null || login.isBlank()) {
            return null;
        }

        Usuario usuario = usuarioDAO.buscarPorLogin(login);
        if (usuario == null) {
            return null;
        }

        String hash = hashSenha(new String(senha));
        return usuario.getSenha().equals(hash) ? usuario : null;
    }

    public boolean salvar(Usuario usuario, char[] senha, char[] confirmacao) {
        if (usuario == null || usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            return false;
        }
        if (senha == null || confirmacao == null || senha.length == 0) {
            return false;
        }
        if (!new String(senha).equals(new String(confirmacao))) {
            return false;
        }
        if (usuarioDAO.buscarPorLogin(usuario.getLogin()) != null) {
            return false;
        }

        usuario.setSenha(hashSenha(new String(senha)));
        return usuarioDAO.salvar(usuario);
    }

    public boolean atualizar(Usuario usuario, char[] senha, char[] confirmacao) {
        if (usuario == null || usuario.getId() <= 0) {
            return false;
        }
        if (senha == null || confirmacao == null || senha.length == 0) {
            return false;
        }
        if (!new String(senha).equals(new String(confirmacao))) {
            return false;
        }

        usuario.setSenha(hashSenha(new String(senha)));
        return usuarioDAO.atualizar(usuario);
    }

    public Usuario buscarPorId(long id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    public boolean excluir(long id) {
        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario != null && "KeslleyAntonio".equals(usuario.getLogin())) {
            throw new RuntimeException("O administrador inicial não pode ser excluído.");
        }
        return usuarioDAO.excluir(id);
    }

    private String hashSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte b : hash) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException("Erro ao calcular hash da senha.", exception);
        }
    }
}
