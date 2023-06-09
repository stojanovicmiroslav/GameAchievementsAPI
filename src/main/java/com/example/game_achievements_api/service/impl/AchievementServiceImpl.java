package com.example.game_achievements_api.service.impl;

import com.example.game_achievements_api.model.Achievement;
import com.example.game_achievements_api.repository.AchievementRepository;
import com.example.game_achievements_api.service.AchievementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;


    @Override
    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    @Override
    public List<Achievement> getAllAchievement() {
        return achievementRepository.findAll();
    }

    @Override
    public Achievement getAchievement(Long id) { // u buducnosti da ne koristim vise Optional
        return achievementRepository.findById(id).orElseThrow(()->new NotFoundException("User not found 1"));
    }

    @Override
    public Achievement update(Achievement achievement) {
        return achievementRepository.saveAndFlush(achievement);
    }

    @Override
    public void delete(Long id) {
         achievementRepository.deleteById(id);

    }

    @Override
    public boolean existsByName(String name) {
        return achievementRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return achievementRepository.existsById(id);
    }

    @Override
    public Achievement findById(Long id) {
        return achievementRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found 2"));
    }

    @Override
    public Achievement save(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    @Override
    public List<Achievement> findAllByGame_Id(Long idGame) {
        return achievementRepository.findAllByGame_Id(idGame);
    }
}
