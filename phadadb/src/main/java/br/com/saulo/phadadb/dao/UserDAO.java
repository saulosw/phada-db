package br.com.saulo.phadadb.dao;

import java.util.List;
import java.util.Optional;

import br.com.saulo.phadadb.model.User;

public interface UserDAO {
    void save(User user);

    Optional<User> findById(int id);

    Optional<User> findByEmail(String email);
    
    List<User> findAll();

    void update(User user);

    void deleteById(int id);
}