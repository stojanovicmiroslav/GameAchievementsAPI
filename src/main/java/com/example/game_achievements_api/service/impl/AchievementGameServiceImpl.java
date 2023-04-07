package com.example.game_achievements_api.service.impl;

import com.example.game_achievements_api.model.AchievementGame;
import com.example.game_achievements_api.repository.AchievementGameRespository;
import com.example.game_achievements_api.service.AchievementGameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AchievementGameServiceImpl implements AchievementGameService {
    private final AchievementGameRespository achievementGameRespository;

    @Override
    public AchievementGame save(AchievementGame achievementGame) {
        return achievementGameRespository.save(achievementGame);
    }
}
