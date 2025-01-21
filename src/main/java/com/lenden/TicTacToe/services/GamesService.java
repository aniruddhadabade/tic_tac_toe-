package com.lenden.TicTacToe.services;

import java.util.List;

import com.lenden.TicTacToe.entity.Games;
import com.lenden.TicTacToe.entity.User;


public interface GamesService {
	Games startGame(User player1, User player2);
    Games makeMove(Long gameId, User player, int position);
    Games findGameById(Long gameId);
    List<Games> getGameHistoryForUser(Long userId);
}
