package br.com.saulo.phadadb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.saulo.phadadb.config.ConnectionFactory;
import br.com.saulo.phadadb.dao.UserDAO;
import br.com.saulo.phadadb.model.User;

public class UserDataBase implements UserDAO {

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (full_name, email, password_hash) VALUES (?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash()); 
            
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário no banco de dados.", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, email);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUser(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por e-mail: " + e);
        }
        
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(int id) {

        String sql = "SELECT * FROM users WHERE id = ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUser(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID.", e);
        }
        
        return Optional.empty();
    }
    
    @Override
    public List<User> findAll() {

        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os usuários.", e);
        }
        
        return users;
    }

    @Override
    public void update(User user) {
        System.out.println("Método update() ainda não implementado.");
    }

    @Override
    public void deleteById(int id) {

        String sql = "DELETE FROM users WHERE id = ?";
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário.", e);
        }
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String fullName = resultSet.getString("full_name");
        String email = resultSet.getString("email");
        String passwordHash = resultSet.getString("password_hash");

        User user = new User(fullName, email, passwordHash);
        
        user.setId(id);
        
        return user;
    }
}
