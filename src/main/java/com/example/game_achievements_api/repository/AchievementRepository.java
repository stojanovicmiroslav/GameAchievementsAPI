package com.example.game_achievements_api.repository;

import com.example.game_achievements_api.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    public boolean existsByName (String name);
    public Optional<Achievement> findById (Long id);
}
