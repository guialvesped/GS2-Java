package br.com.fiap.dao;

import br.com.fiap.model.Cliente;

import java.sql.Connection;
import java.util.List;

public interface ClienteDao {
    Cliente create(Cliente c1, Connection connection);

    List<Cliente> readByemail(String email);

    Cliente update(Cliente c1, Connection connection);

    void delete( String email, Connection connection);
}
