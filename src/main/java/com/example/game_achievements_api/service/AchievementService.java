package com.example.game_achievements_api.service;


import com.example.game_achievements_api.model.Achievement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AchievementService {

    public Achievement createAchievement(Achievement achievement);
    public List<Achievement> getAllAchievement();
    public Optional<Achievement> getAchievement(Long id);
    public Achievement update (Achievement achievement);
    public void delete (Long id);
    public boolean existsByName (String name);
    public boolean existsById (Long id);

    public Optional<Achievement> findById (Long id);
    public Achievement save (Achievement achievement);
    List<Achievement> findAllByGame_Id (Long idGame);


}
