package br.com.saulo.phadadb.service;

import java.util.Optional;

import br.com.saulo.phadadb.dao.UserDAO;
import br.com.saulo.phadadb.model.User;
import br.com.saulo.phadadb.util.SecurityUtil;

public class UserService {
    
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    public void registerUser(String name, String email, String plainTextPassword) throws Exception {
        validateUserCredentials(name, email, plainTextPassword);

        if (userDAO.findByEmail(email).isPresent()) {
            throw new Exception("O e-mail '" + email + "' já está em uso.");
        }

        String hashedPassword = SecurityUtil.hashPassword(plainTextPassword);
        
        User newUser = new User(name, email, hashedPassword);
        userDAO.save(newUser);
    }

    public Optional<User> authenticate(String email, String plainTextPassword) {
        Optional<User> optionalUser = userDAO.findByEmail(email);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (SecurityUtil.checkPassword(plainTextPassword, user.getPasswordHash())) {
                return optionalUser;
            }
        }
        
        return Optional.empty();
    }

    public void validateUserCredentials(String name, String email, String plainTextPassword) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty() || !name.trim().contains(" ")) {
            throw new IllegalArgumentException("Por favor, insira seu nome completo (nome e sobrenome).");
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email == null || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("O formato do e-mail fornecido é inválido.");
        }

        if (plainTextPassword == null || plainTextPassword.isEmpty()) {
            throw new IllegalArgumentException("O campo de senha não pode estar vazio.");
        }

        if (plainTextPassword.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }

        if (!plainTextPassword.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um número.");
        }

        if (!plainTextPassword.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra.");
        }

        if (!plainTextPassword.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um caractere especial (ex: !@#$).");
        }
    }

    public Optional<User> getUserById(int id) {
        return userDAO.findById(id);
    }
}
