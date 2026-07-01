package banco.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {

    private static final String PROPERTIES_PATH = "db.properties";
    private static Connection connection;

    private ConexaoDB() {
    }

    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Properties properties = loadProperties();
                connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.usuario"),
                    properties.getProperty("db.senha")
                );
            }
            return connection;
        } catch (SQLException exception) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", exception);
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(input);
        } catch (IOException exception) {
            throw new RuntimeException("Arquivo de configuração db.properties não encontrado. Copie db.properties.example para db.properties e atualize as credenciais.", exception);
        }
        return properties;
    }
}
