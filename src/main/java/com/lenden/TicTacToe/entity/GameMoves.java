package com.lenden.TicTacToe.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class GameMoves {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "game_id")
    private Games game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private User player;

    private int position; 
    
    private int turn; 
    
    private String gameResult;  

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private User winner; // Added winner field
    
    private LocalDateTime moveTime;

	public GameMoves() {
		super();
	}

	public GameMoves(Long id, Games game, User player, int position, int turn, String gameResult, User winner,
			LocalDateTime moveTime) {
		super();
		this.id = id;
		this.game = game;
		this.player = player;
		this.position = position;
		this.turn = turn;
		this.gameResult = gameResult;
		this.winner = winner;
		this.moveTime = moveTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Games getGame() {
		return game;
	}

	public void setGame(Games game) {
		this.game = game;
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public String getGameResult() {
		return gameResult;
	}

	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

	public LocalDateTime getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(LocalDateTime moveTime) {
		this.moveTime = moveTime;
	}

	@Override
	public String toString() {
		return "GameMoves [id=" + id + ", game=" + game + ", player=" + player + ", position=" + position + ", turn="
				+ turn + ", gameResult=" + gameResult + ", winner=" + winner + ", moveTime=" + moveTime + "]";
	}

	
}
