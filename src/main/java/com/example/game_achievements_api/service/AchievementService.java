package com.example.game_achievements_api.service;


import com.example.game_achievements_api.model.Achievement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AchievementService {

     Achievement createAchievement(Achievement achievement);
     List<Achievement> getAllAchievement();
     Achievement getAchievement(Long id);
     Achievement update (Achievement achievement);
     void delete (Long id);
     boolean existsByName (String name);
    boolean existsById (Long id);

     Achievement findById (Long id);
    Achievement save (Achievement achievement);
    List<Achievement> findAllByGame_Id (Long idGame);


}
