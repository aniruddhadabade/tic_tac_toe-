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

import com.lenden.TicTacToe.entity.GameMoves;
import com.lenden.TicTacToe.services.GameMovesService;
import com.lenden.TicTacToe.services.UserService;

@RestController
@RequestMapping("/game-moves")
public class GamesMovesController {	
	
	@Autowired
	private GameMovesService gameMovesService;

    // Create a new move
	@GetMapping("/")
	public ResponseEntity<List<GameMoves>> getAllMoves() {
	    List<GameMoves> allMoves = gameMovesService.getAllMoves();
	    return ResponseEntity.ok(allMoves);
	}

    // Get a move by ID
    @GetMapping("/{id}")
    public ResponseEntity<GameMoves> getMoveById(@PathVariable Long id) {
        Optional<GameMoves> gameMove = gameMovesService.getMoveById(id);
        return gameMove.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all moves for a specific game
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<GameMoves>> getMovesByGame(@PathVariable Long gameId) {
        List<GameMoves> gameMoves = gameMovesService.getMovesByGame(gameId);
        return ResponseEntity.ok(gameMoves);
    }

    // Update a move
    @PutMapping("/{id}")
    public ResponseEntity<GameMoves> updateMove(@PathVariable Long id, @RequestBody GameMoves gameMove) {
        GameMoves updatedMove = gameMovesService.updateMove(id, gameMove);
        return updatedMove != null ? ResponseEntity.ok(updatedMove) : ResponseEntity.notFound().build();
    }

    // Delete a move
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMove(@PathVariable Long id) {
        gameMovesService.deleteMove(id);
        return ResponseEntity.noContent().build();
    }

    // Get all moves made by a player
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<GameMoves>> getMovesByPlayer(@PathVariable Long playerId) {
        List<GameMoves> playerMoves = gameMovesService.getMovesByPlayer(playerId);
        return ResponseEntity.ok(playerMoves);
    }
}
