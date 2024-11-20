package br.com.fiap.config;

import java.sql.SQLException;
public class DBConnectionFactory {
    private DBConnectionFactory() {
        throw new UnsupportedOperationException();
    }

    public static DBConnection create() throws SQLException {
        return DBConnectionImpl.getInstance();
    }
}
