package com.lenden.TicTacToe.services;

import java.util.List;
import java.util.Optional;

import com.lenden.TicTacToe.entity.GameMoves;

public interface GameMovesService {
	GameMoves createMove(GameMoves gameMove);
    Optional<GameMoves> getMoveById(Long id);
    List<GameMoves> getMovesByGame(Long gameId);
    GameMoves updateMove(Long id, GameMoves gameMove);
    void deleteMove(Long id);
    List<GameMoves> getMovesByPlayer(Long playerId);
    List<GameMoves> getAllMoves();
}
