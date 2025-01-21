package com.lenden.TicTacToe.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenden.TicTacToe.entity.User;
import com.lenden.TicTacToe.repository.UserRepository;
import com.lenden.TicTacToe.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userrepo;
	
	@Override
    public User registerUser(User user) {
        return userrepo.save(user);  // Saves the user (for registration)
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userrepo.findById(id);  // Find a user by their ID
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userrepo.findByUsername(username);  // Find a user by their username
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        Optional<User> user = userrepo.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);  // Simple password validation
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userrepo.existsById(id)) {
            user.setId(id);
            return userrepo.save(user);  // Update user details
        }
        return null;  // User not found
    }

    @Override
    public void deleteUser(Long id) {
    	userrepo.deleteById(id);  // Delete user by ID
    }

    @Override
    public List<User> getAllUsers() {
        return userrepo.findAll();  // Get all users
    }
	
}
