package com.example.game_achievements_api.controller;


import com.example.game_achievements_api.model.Achievement;
import com.example.game_achievements_api.model.Game;
import com.example.game_achievements_api.service.impl.AchievementServiceImpl;
import com.example.game_achievements_api.service.impl.GameServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/game")
@AllArgsConstructor
public class GameController {

    private final GameServiceImpl gameService;
    private final AchievementServiceImpl achievementService;



    @GetMapping ("/{id}")
    public ResponseEntity<Game> findByID(@PathVariable long id){
        try {
            Game game = gameService.findById(id);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ()
    public ResponseEntity <List<Game>> findAllGames(){
        List<Game> gameList = gameService.getAllGame();
        return new ResponseEntity<>(gameList, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<?> addGame(@Valid @RequestBody Game game){
        if (gameService.existsByName(game.getName())) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
     Game savedGame =  gameService.createGame(game);
        return new ResponseEntity<>(savedGame, HttpStatus.OK);
    }


    @PostMapping("/achievements")
    public  ResponseEntity<Achievement> addAchievement( @Valid @RequestBody Achievement achievement){
        if (!gameService.existsById(achievement.getGame().getId())) {
            System.out.println("Game does not exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        achievementService.save(achievement);
        return new ResponseEntity<>(achievement, HttpStatus.CREATED);
    }


    @GetMapping ("/achievements/{id}")
    public ResponseEntity <List<Achievement>> findAllAchievenetsByGame(@PathVariable Long id){
        List<Achievement> achievementsList = achievementService.findAllByGame_Id(id);
        return new ResponseEntity<>(achievementsList, HttpStatus.OK);
    }


    @PutMapping
    public  ResponseEntity<Game> updateGame( @Valid @RequestBody Game game){

        try {
            Game gamefound = gameService.getGame(game.getId());

            gamefound.setName(game.getName());
            gameService.update(game);
            return new ResponseEntity<>(gamefound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable long id){

        try {
            gameService.delete(id);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
