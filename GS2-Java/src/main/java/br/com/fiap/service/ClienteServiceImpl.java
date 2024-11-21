package br.com.fiap.service;

import br.com.fiap.config.DBConnectionFactory;
import br.com.fiap.dao.ClienteDao;
import br.com.fiap.dao.ClienteDaoFactory;
import br.com.fiap.exceptions.ClienteNotFoundException;
import br.com.fiap.exceptions.ClienteNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class ClienteServiceImpl implements ClienteService{

    private final ClienteDao dao = ClienteDaoFactory.get();

    @Override
    public Cliente create(Cliente c1) throws UnsupportedServiceOperationException, SQLException, ClienteNotSavedException {
        if(c1.getEmail() == null){
            Connection connection = DBConnectionFactory.create().get();
            try {
                c1 = this.dao.create(c1, connection);
                connection.commit();
                return c1;
            } catch( SQLException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Cliente> findByEmail(String email) {
        return this.dao.readByemail(email);
    }


    @Override
    public Cliente update(Cliente c1) throws ClienteNotFoundException, SQLException {
        Connection connection = DBConnectionFactory.create().get();
        c1 = this.dao.update(c1, connection);
        connection.commit();
        return c1;
    }

    @Override
    public void deleteByEmail(String email) throws ClienteNotFoundException, SQLException {
        Connection connection = DBConnectionFactory.create().get();
        this.dao.delete(email,connection);
        connection.commit();
    }
}
