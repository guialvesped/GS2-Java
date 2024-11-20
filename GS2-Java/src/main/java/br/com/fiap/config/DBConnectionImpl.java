package br.com.fiap.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBConnectionImpl implements DBConnection {
    private static DBConnectionImpl dbConnection;

    private static Connection connection;

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private DBConnectionImpl() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(
                    DBConfig.getUrl(),
                    DBConfig.getUser(),
                    DBConfig.getPassword()
            );
        } catch (ClassNotFoundException e) {
            logger.severe("Não foi localizada a classe Driver do Oracle");
            throw new RuntimeException("Driver do Oracle não encontrado", e);
        }

        if (connection == null) {
            throw new SQLException("Falha ao estabelecer a conexão com o banco de dados");
        }

    }

    public static synchronized DBConnectionImpl getInstance() throws SQLException {
        if(dbConnection == null || connection.isClosed()){
            dbConnection = new DBConnectionImpl();
        }
        return dbConnection;
    }


    @Override
    public Connection get() throws SQLException {
        connection.setAutoCommit(false);
        return connection;
    }

}
