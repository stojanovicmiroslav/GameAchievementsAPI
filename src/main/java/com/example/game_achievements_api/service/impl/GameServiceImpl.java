package com.example.game_achievements_api.service.impl;

import com.example.game_achievements_api.model.Game;
import com.example.game_achievements_api.repository.GameRepository;
import com.example.game_achievements_api.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;


    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public List<Game> getAllGame() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGame(Long id) {
        return gameRepository.findById(id).orElseThrow(()-> new NotFoundException("Game with not found "));
    }

    @Override
    public Game update(Game game) {
        return gameRepository.saveAndFlush(game);
    }

    @Override
    public void delete(Long id) {
gameRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return gameRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return gameRepository.existsById(id);
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(()-> new NotFoundException("Game with not found "));
    }
}
