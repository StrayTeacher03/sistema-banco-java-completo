package banco.dao;

import java.sql.*;

public class ConexaoDB {
    private static final String url = "jdbc:postgresql://localhost:5432/nubank";
    private static final String user = "postgres";
    private static final String password = "2008Ksy!";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
