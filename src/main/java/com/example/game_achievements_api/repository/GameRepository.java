package com.example.game_achievements_api.repository;

import com.example.game_achievements_api.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

     boolean existsByName (String name);
     boolean existsById (Long id);
     Optional<Game> findById(Long id);
}
