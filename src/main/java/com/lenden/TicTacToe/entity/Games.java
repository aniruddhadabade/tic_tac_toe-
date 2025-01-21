package com.lenden.TicTacToe.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Games {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User player1;

    @ManyToOne
    private User player2;

    private String status; 
    private String winner;
    
    private String boardState; 
    private int currentTurn;  
    
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GameMoves> moves;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
	public Games() {
		super();
	}

	public Games(Long id, User player1, User player2, String status, String winner, String boardState, int currentTurn,
			List<GameMoves> moves, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.player1 = player1;
		this.player2 = player2;
		this.status = status;
		this.winner = winner;
		this.boardState = boardState;
		this.currentTurn = currentTurn;
		this.moves = moves;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getBoardState() {
		return boardState;
	}

	public void setBoardState(String boardState) {
		this.boardState = boardState;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public List<GameMoves> getMoves() {
		return moves;
	}

	public void setMoves(List<GameMoves> moves) {
		this.moves = moves;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Games [id=" + id + ", player1=" + player1 + ", player2=" + player2 + ", status=" + status + ", winner="
				+ winner + ", boardState=" + boardState + ", currentTurn=" + currentTurn + ", moves=" + moves
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
	
	
    
	
    
}
