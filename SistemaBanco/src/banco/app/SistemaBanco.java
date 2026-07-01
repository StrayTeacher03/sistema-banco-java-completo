package banco.app;

import banco.dao.ConexaoDB;
import banco.ui.TelaLogin;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SistemaBanco {

    private static final Logger logger = Logger.getLogger(SistemaBanco.class.getName());
    private static final String MIGRATIONS_DIR = "src/banco/resources/db/migration";

    public static void main(String[] args) {
        executarMigracoes();
        java.awt.EventQueue.invokeLater(() -> new TelaLogin().setVisible(true));
    }

    private static void executarMigracoes() {
        try (Connection connection = ConexaoDB.getConnection()) {
            boolean previousAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            try {
                criarTabelaMigracoes(connection);
                List<Path> scripts = listarArquivosMigracao();
                for (Path script : scripts) {
                    String nomeScript = script.getFileName().toString();
                    if (!migracaoAplicada(connection, nomeScript)) {
                        aplicarScript(connection, script);
                        marcarMigracaoAplicada(connection, nomeScript);
                        connection.commit();
                    }
                }
            } catch (Exception exception) {
                connection.rollback();
                throw exception;
            } finally {
                connection.setAutoCommit(previousAutoCommit);
            }
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Falha ao executar migrações do banco de dados.", exception);
            throw new RuntimeException("Falha ao executar migrações do banco de dados.", exception);
        }
    }

    private static void criarTabelaMigracoes(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS schema_migrations (script_name VARCHAR(255) PRIMARY KEY, applied_at TIMESTAMP NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private static List<Path> listarArquivosMigracao() throws IOException {
        Path migrationsPath = Paths.get(MIGRATIONS_DIR);
        if (!Files.exists(migrationsPath) || !Files.isDirectory(migrationsPath)) {
            throw new RuntimeException("Diretório de migrações não encontrado: " + MIGRATIONS_DIR);
        }

        return Files.list(migrationsPath)
            .filter(path -> path.toString().endsWith(".sql"))
            .sorted(Comparator.comparing(Path::getFileName))
            .toList();
    }

    private static boolean migracaoAplicada(Connection connection, String nomeScript) throws SQLException {
        String sql = "SELECT 1 FROM schema_migrations WHERE script_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nomeScript);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static void aplicarScript(Connection connection, Path scriptPath) throws IOException, SQLException {
        String script = Files.readString(scriptPath, StandardCharsets.UTF_8);
        for (String statement : script.split("(?m);\\s*(?:\\r?\\n|$)")) {
            if (statement.isBlank()) {
                continue;
            }
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(statement);
            }
        }
    }

    private static void marcarMigracaoAplicada(Connection connection, String nomeScript) throws SQLException {
        String sql = "INSERT INTO schema_migrations (script_name, applied_at) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nomeScript);
            statement.setObject(2, LocalDateTime.now());
            statement.executeUpdate();
        }
    }
}
