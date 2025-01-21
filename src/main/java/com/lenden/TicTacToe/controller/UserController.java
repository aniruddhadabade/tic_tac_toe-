package com.lenden.TicTacToe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lenden.TicTacToe.entity.User;
import com.lenden.TicTacToe.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	 @PostMapping("/register")
	    public ResponseEntity<User> registerUser(@RequestBody User user) {
	        User createdUser = userservice.registerUser(user);
	        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	    }

	    // Get user by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable Long id) {
	        Optional<User> user = userservice.findUserById(id);
	        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // Get user by username
	    @GetMapping("/username/{username}")
	    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
	        Optional<User> user = userservice.findUserByUsername(username);
	        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // Update user details
	    @PutMapping("/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
	        User updatedUser = userservice.updateUser(id, user);
	        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
	    }

	    // Delete a user
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	    	userservice.deleteUser(id);
	        return ResponseEntity.noContent().build();
	    }

	    // Get all users
	    @GetMapping("/")
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = userservice.getAllUsers();
	        return ResponseEntity.ok(users);
	    }

	    // Validate user credentials (simple example)
	    @PostMapping("/validate")
	    public ResponseEntity<Boolean> validateCredentials(@RequestBody User user) {
	        boolean isValid = userservice.validateCredentials(user.getUsername(), user.getPassword());
	        return ResponseEntity.ok(isValid);
	    }
}
