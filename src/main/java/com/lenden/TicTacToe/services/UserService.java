package com.lenden.TicTacToe.services;

import java.util.List;
import java.util.Optional;

import com.lenden.TicTacToe.entity.User;

public interface UserService {
	User registerUser(User user);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    boolean validateCredentials(String username, String password);
    
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
