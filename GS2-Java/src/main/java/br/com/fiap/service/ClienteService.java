package br.com.fiap.service;

import br.com.fiap.exceptions.ClienteNotFoundException;
import br.com.fiap.exceptions.ClienteNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteService {

    Cliente create(Cliente c1) throws UnsupportedServiceOperationException, SQLException, ClienteNotSavedException;

    List<Cliente> findByEmail(String email);

    Cliente update(Cliente c1) throws ClienteNotFoundException, SQLException;

    void deleteByEmail(String email) throws ClienteNotFoundException, SQLException;
}
