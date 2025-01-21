package com.lenden.TicTacToe.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenden.TicTacToe.entity.GameMoves;
import com.lenden.TicTacToe.repository.GameMovesRepository;
import com.lenden.TicTacToe.services.GameMovesService;

@Service
public class GameMovesServiceImpl implements GameMovesService{
	
	private GameMovesRepository gameMovesRepository;

    @Autowired
    public GameMovesServiceImpl(GameMovesRepository gameMovesRepository) {
        this.gameMovesRepository = gameMovesRepository;
    }

    @Override
    public GameMoves createMove(GameMoves gameMove) {
        return gameMovesRepository.save(gameMove);  
    }
    
    @Override
    public List<GameMoves> getAllMoves() {
        return gameMovesRepository.findAll();
    }

    @Override
    public Optional<GameMoves> getMoveById(Long id) {
        return gameMovesRepository.findById(id);  
    }

    @Override
    public List<GameMoves> getMovesByGame(Long gameId) {
        return gameMovesRepository.findByGame_Id(gameId);  
    }

    @Override
    public GameMoves updateMove(Long id, GameMoves gameMove) {
        if (gameMovesRepository.existsById(id)) {
            gameMove.setId(id);
            return gameMovesRepository.save(gameMove);  
        }
        return null; 
    }

    @Override
    public void deleteMove(Long id) {
        gameMovesRepository.deleteById(id); 
    }

    @Override
    public List<GameMoves> getMovesByPlayer(Long playerId) {
        return gameMovesRepository.findByPlayer_Id(playerId);  
    }
}
