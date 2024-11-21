package br.com.fiap.dao;

import br.com.fiap.config.DBConnectionFactory;
import br.com.fiap.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao{
    @Override
    public Cliente create(Cliente c1, Connection connection) {
        String sql = "INSERT INTO cliente(email,senha) VALUES (?,?)";
        try (PreparedStatement pstat = connection.prepareStatement(sql)) {


            pstat.setString(1, c1.getEmail());
            pstat.setString(2, c1.getSenha());

            pstat.executeUpdate();
            pstat.close();
            System.out.println("Dados inseridos com sucesso");
            return c1;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível inserir os dados: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Cliente> readByemail(String email) {
        List<Cliente> result = new ArrayList<>();
        String sql = "SELECT * FROM cliente where email = ?";
        try (Connection connection = DBConnectionFactory.create().get()){
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, email);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                String senha = rs.getString("senha");
                String emailDb = rs.getString("email");
                result.add(new Cliente(emailDb, senha));
            }

            rs.close();
            stat.close();
        }catch (SQLException e){
            throw new RuntimeException("Não foi possivel buscar os dados: " + e.getMessage());
        }

        return result;
    }

    @Override
    public Cliente update(Cliente c1, Connection connection) {
        String sql = "UPDATE cliente SET senha = ? WHERE email = ?";
        try{
            PreparedStatement stat = connection.prepareStatement(sql);

            stat.setString(1, c1.getSenha());
            stat.setString(2, c1.getEmail());

            stat.executeUpdate();

            stat.close();

            System.out.println("Dados alterados com sucesso");
            return c1;
        }catch(SQLException e){
            throw new RuntimeException("Não foi possivel alterar os dados");
        }
    }

    @Override
    public void delete(String email, Connection connection) {
        String sql = "DELETE FROM cliente WHERE email = ?";
        try {

            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, email);

            stat.executeUpdate();

            stat.close();
            System.out.println("Dados deletados com sucesso");
        }catch (SQLException e){
            throw new RuntimeException("Não foi possivel deletar os dados");
        }
    }
}
