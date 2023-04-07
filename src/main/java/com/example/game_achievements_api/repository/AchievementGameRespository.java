package com.example.game_achievements_api.repository;

import com.example.game_achievements_api.model.AchievementGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementGameRespository extends JpaRepository<AchievementGame, Long> {


}
