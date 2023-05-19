package com.example.game_achievements_api.repository;

import com.example.game_achievements_api.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    boolean existsByName (String name);
    Optional<Achievement> findById (Long id);
    List<Achievement> findAllByGame_Id (Long idGame);
}
