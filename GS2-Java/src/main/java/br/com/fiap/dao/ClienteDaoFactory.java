package br.com.fiap.dao;

public class ClienteDaoFactory {
    public ClienteDaoFactory() {
    }

    public static ClienteDao get() {
        return new ClienteDaoImpl();
    }
}
