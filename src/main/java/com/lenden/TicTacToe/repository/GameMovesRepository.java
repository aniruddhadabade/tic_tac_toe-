package com.lenden.TicTacToe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lenden.TicTacToe.entity.GameMoves;

public interface GameMovesRepository extends JpaRepository<GameMoves, Long>{
	List<GameMoves> findByGame_Id(Long gameId);
	
    List<GameMoves> findByPlayer_Id(Long playerId);
}
