package br.com.saulo.phadadb.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Properties properties = new Properties();
        String resourcePath = "/db-config.properties"; 
        
        try (InputStream input = ConnectionFactory.class.getResourceAsStream(resourcePath)) {
            
            if (input == null) {
                throw new IOException("Arquivo de propriedades não encontrado no classpath: " + resourcePath);
            }

            properties.load(input);
            
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo de propriedades de configuração.", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e);
        }
    }

    private ConnectionFactory() {}
}
