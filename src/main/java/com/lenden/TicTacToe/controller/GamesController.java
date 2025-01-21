package com.lenden.TicTacToe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lenden.TicTacToe.entity.Games;
import com.lenden.TicTacToe.entity.User;
import com.lenden.TicTacToe.service.implementation.GamesServiceImpl;

@RestController
@RequestMapping("/games")
public class GamesController {
	
	@Autowired
    private GamesServiceImpl gamesService;

    @PostMapping("/start")
    public ResponseEntity<Games> startGame(@RequestBody GameRequest gameRequest) {
        User player1 = gameRequest.getPlayer1();
        User player2 = gameRequest.getPlayer2();

        Games game = gamesService.startGame(player1, player2);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PostMapping("/move/{gameId}")
    public ResponseEntity<Games> makeMove(@PathVariable Long gameId, 
                                          @RequestParam Long userId, 
                                          @RequestParam int position) {
        User player = new User();
        player.setId(userId); // You can use a service to fetch the user instead

        Games game = gamesService.makeMove(gameId, player, position);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Games> getGameById(@PathVariable Long gameId) {
        Games game = gamesService.findGameById(gameId);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Games>> getGameHistoryForUser(@PathVariable Long userId) {
        List<Games> gameHistory = gamesService.getGameHistoryForUser(userId);
        return new ResponseEntity<>(gameHistory, HttpStatus.OK);
    }

    public static class GameRequest {
        private User player1;
        private User player2;

        public User getPlayer1() {
            return player1;
        }

        public void setPlayer1(User player1) {
            this.player1 = player1;
        }

        public User getPlayer2() {
            return player2;
        }

        public void setPlayer2(User player2) {
            this.player2 = player2;
        }
    }
}
