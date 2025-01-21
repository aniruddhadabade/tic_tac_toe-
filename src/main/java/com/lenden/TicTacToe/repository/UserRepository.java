package com.lenden.TicTacToe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lenden.TicTacToe.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
