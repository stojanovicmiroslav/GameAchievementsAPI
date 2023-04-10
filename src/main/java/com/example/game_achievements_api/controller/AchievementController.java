package com.example.game_achievements_api.controller;


import com.example.game_achievements_api.model.Achievement;
import com.example.game_achievements_api.model.Game;
import com.example.game_achievements_api.service.impl.AchievementServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/achievement")
@AllArgsConstructor
public class AchievementController {

private final AchievementServiceImpl achievementService;


    @GetMapping("/{id}")
    public ResponseEntity<Achievement> findByID(@PathVariable long id){
        Optional<Achievement> achievementOptional = achievementService.getAchievement(id);
        if (achievementOptional.isEmpty()){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        Achievement achievement = achievementOptional.get();
        return new ResponseEntity<>(achievement, HttpStatus.OK);
    }

    @GetMapping ()
    public ResponseEntity <List<Achievement>> findAllGames(){
        List<Achievement> achievementList = achievementService.getAllAchievement();
        return new ResponseEntity<>(achievementList, HttpStatus.OK);
    }






    @PutMapping("/{id}")
    public  ResponseEntity<Achievement> updateAchievement(@PathVariable long id, @Valid @RequestBody Achievement achievement, @RequestParam Game game){
        Optional<Achievement> optionalAchievement = achievementService.getAchievement(id);
        if (optionalAchievement.isPresent()) {
            Achievement achievementFound = optionalAchievement.get();
            achievementFound.setId(id);
            achievementFound.setName(achievement.getName());
            achievementFound.setDisplayOrder(achievement.getDisplayOrder());
            achievementFound.setGame(game);
            achievementFound.setIcon(achievement.getIcon());
            achievementService.update(achievementFound);
            return new ResponseEntity<>(achievementFound, HttpStatus.OK);
        }

        System.out.println("Achievement with id: " + id + " not found in database");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable long id){
        Optional<Achievement> achievementOptional = achievementService.getAchievement(id);
        if (achievementOptional.isEmpty()){
            // TODO kako da dodam u exception error ?
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        Achievement achievement = achievementOptional.get();
        achievementService.delete(id);
        return new ResponseEntity<>("Achievement "+ achievement.getName() + " successfully deleted", HttpStatus.OK);
    }

    //TODO da li moram da pravim novu tabelu da povezujem Achievement i Game,
    // da li mogu da kada pravim Achievement i da dodam Game, da ne dodajem posle
    // exception error moram da uradim
}
