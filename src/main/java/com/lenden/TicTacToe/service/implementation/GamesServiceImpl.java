package com.lenden.TicTacToe.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenden.TicTacToe.entity.GameMoves;
import com.lenden.TicTacToe.entity.Games;
import com.lenden.TicTacToe.entity.User;
import com.lenden.TicTacToe.repository.GameMovesRepository;
import com.lenden.TicTacToe.repository.GamesRepository;
import com.lenden.TicTacToe.repository.UserRepository;
import com.lenden.TicTacToe.services.GameMovesService;
import com.lenden.TicTacToe.services.GamesService;

@Service
public class GamesServiceImpl implements GamesService{
	
	@Autowired
    private GamesRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameMovesRepository gameMovesRepository;

    @Autowired
    private GameMovesService gameMovesService;

    @Override
    public Games startGame(User player1, User player2) {
        Games game = new Games();
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        game.setStatus("in_progress");
        game.setBoardState("         "); // 3x3 empty board
        game.setCurrentTurn(1); // Player 1 starts the game
        game.setCreatedAt(LocalDateTime.now());
        return gameRepository.save(game);
    }

    @Override
    public Games makeMove(Long gameId, User player, int position) {
        Games game = findGameById(gameId);

        // Ensure the game is in progress before accepting the move
        if (!game.getStatus().equals("in_progress")) {
            throw new IllegalStateException("Game is not in progress");
        }

        StringBuilder board = new StringBuilder(game.getBoardState());
        if (board.charAt(position) != ' ') {
            throw new IllegalArgumentException("Invalid move: Position already occupied");
        }

        // Determine the current player's symbol (X for player 1, O for player 2)
        char playerSymbol = (game.getCurrentTurn() == 1) ? 'X' : 'O';
        board.setCharAt(position, playerSymbol);
        game.setBoardState(board.toString());
        game.setUpdatedAt(LocalDateTime.now());

        // Create the move entry and save it to the GameMoves table
        GameMoves move = new GameMoves();
        move.setGame(game);
        move.setPlayer(player);
        move.setPosition(position);
        move.setTurn(game.getCurrentTurn());
        move.setMoveTime(LocalDateTime.now());

        // Check for a winner after this move
        if (checkWinner(board.toString(), playerSymbol)) {
            game.setStatus("completed");
            String winnerUsername = (game.getCurrentTurn() == 1) ? game.getPlayer1().getUsername() : game.getPlayer2().getUsername();
            game.setWinner(winnerUsername); // Set the game winner

            // Set the winner in the move entity
            User winner = (game.getCurrentTurn() == 1) ? game.getPlayer1() : game.getPlayer2();
            move.setWinner(winner);
            move.setGameResult("win");
        } else if (!board.toString().contains(" ")) {
            game.setStatus("completed");
            game.setWinner("draw");

            // Set game result to "draw" for this move
            move.setWinner(null);
            move.setGameResult("draw");
        } else {
            // If no winner or draw, change turn
            game.setCurrentTurn(game.getCurrentTurn() == 1 ? 2 : 1);
            move.setGameResult("in_progress");
        }

        gameMovesRepository.save(move); // Save the move with game result
        return gameRepository.save(game);
    }

    @Override
    public Games findGameById(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    }

    @Override
    public List<Games> getGameHistoryForUser(Long userId) {
        return gameRepository.findByPlayer1_IdOrPlayer2_Id(userId, userId)
                .stream()
                .map(this::addGameDetails)
                .collect(Collectors.toList());
    }

    private Games addGameDetails(Games game) {
    	List<GameMoves> moves = gameMovesRepository.findByGame_Id(game.getId());
        moves.forEach(move -> {
            // Determine and set the opponent based on the current player
            if (game.getStatus().equals("completed")) {
                if (game.getWinner().equals("draw")) {
                    move.setGameResult("draw");
                } else if (game.getWinner().equals(move.getPlayer().getUsername())) {
                    move.setGameResult("win");
                } else {
                    move.setGameResult("loss");
                }
            }
        });
        game.setMoves(moves);
        return game;
    }
    

    private boolean checkWinner(String boardState, char symbol) {
        int[][] winPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] pos : winPositions) {
            if (boardState.charAt(pos[0]) == symbol &&
                boardState.charAt(pos[1]) == symbol &&
                boardState.charAt(pos[2]) == symbol) {
                return true;
            }
        }
        return false;
    }
}
