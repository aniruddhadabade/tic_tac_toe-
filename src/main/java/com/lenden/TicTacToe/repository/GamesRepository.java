package com.lenden.TicTacToe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lenden.TicTacToe.entity.Games;

public interface GamesRepository extends JpaRepository<Games, Long>{
	List<Games> findByPlayer1_IdOrPlayer2_Id(Long player1Id, Long player2Id);
}
