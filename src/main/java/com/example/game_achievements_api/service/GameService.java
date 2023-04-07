package com.example.game_achievements_api.service;

import com.example.game_achievements_api.model.Game;

import java.util.List;
import java.util.Optional;


public interface GameService {

    public Game createGame(Game game);
    public List<Game> getAllGame();
    public Optional<Game> getGame(Long id);
    public Game update (Game game);
    public void delete (Long id);
    public boolean existsByName (String name);
    public boolean existsById (Long id);
    public Game save (Game game);
    public Optional<Game> findById(Long id);
}
