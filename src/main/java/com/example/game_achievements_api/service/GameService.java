package com.example.game_achievements_api.service;

import com.example.game_achievements_api.model.Achievement;
import com.example.game_achievements_api.model.Game;

import java.util.List;
import java.util.Optional;


public interface GameService {

     Game createGame(Game game);
     List<Game> getAllGame();
     Game getGame(Long id);
     Game update (Game game);
     void delete (Long id);
     boolean existsByName (String name);
     boolean existsById (Long id);
     Game save (Game game);
     Game findById(Long id);

}
