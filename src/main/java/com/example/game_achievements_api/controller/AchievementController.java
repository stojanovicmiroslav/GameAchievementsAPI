package com.example.game_achievements_api.controller;


import com.example.game_achievements_api.exceptions.CustomExecutionHandler;
import com.example.game_achievements_api.model.Achievement;
import com.example.game_achievements_api.model.Game;
import com.example.game_achievements_api.service.impl.AchievementServiceImpl;
import com.example.game_achievements_api.service.impl.GameServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/achievement")
@AllArgsConstructor
public class AchievementController {

private final AchievementServiceImpl achievementService;
private final GameServiceImpl gameService;
private final CustomExecutionHandler customExecutionHandler;

    @GetMapping("/{id}")
    public ResponseEntity<Achievement> findByID(@PathVariable long id){
        Achievement achievement = achievementService.getAchievement(id);

        return new ResponseEntity<>(achievement, HttpStatus.OK);
    }

    @GetMapping ()
    public ResponseEntity <List<Achievement>> findAllGames(){
        List<Achievement> achievementList = achievementService.getAllAchievement();
        return new ResponseEntity<>(achievementList, HttpStatus.OK);
    }


    @PutMapping()
    public  ResponseEntity<?> updateAchievement(@Valid @RequestBody Achievement achievement, HttpServletRequest servletRequest){
        // HttpServletRequest je inteface koji prestavlja httprequest od klijenta. ovaj objekat daje mogucnost o informacijama o requestu
        // npr httpmethod url, header, request param body...

        try {
            Achievement optionalAchievement = achievementService.getAchievement(achievement.getId());
            Game game = gameService.findById(achievement.getGame().getId());

            achievementService.update(achievement);
            return new ResponseEntity<>(achievement, HttpStatus.OK);
        } catch (NotFoundException e) {
            servletRequest.getServletPath(); // od njega smo uzeli samo path, ostale sve smo uzeli od NotFoundException e
            return new ResponseEntity<>(customExecutionHandler.notFound(servletRequest.getServletPath(), e.getMessage(), e.getLocalizedMessage() ), HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable long id){
        Achievement achievement = achievementService.getAchievement(id);
//

        achievementService.delete(id);
        return new ResponseEntity<>("Achievement "+ achievement.getName() + " successfully deleted", HttpStatus.OK);
    }


}
