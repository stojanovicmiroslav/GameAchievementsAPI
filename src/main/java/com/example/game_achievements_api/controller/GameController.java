package com.example.game_achievements_api.controller;

import com.example.game_achievements_api.model.Achievement;
import com.example.game_achievements_api.model.AchievementGame;
import com.example.game_achievements_api.model.Game;
import com.example.game_achievements_api.repository.AchievementGameRespository;
import com.example.game_achievements_api.service.AchievementService;
import com.example.game_achievements_api.service.impl.AchievementGameServiceImpl;
import com.example.game_achievements_api.service.impl.AchievementServiceImpl;
import com.example.game_achievements_api.service.impl.GameServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/game")
@AllArgsConstructor
public class GameController {

    private final GameServiceImpl gameService;
    private final AchievementServiceImpl achievementService;
    private final AchievementGameServiceImpl achievementGameService;

    @GetMapping ("/{id}")
    public ResponseEntity<Game> findByID(@PathVariable long id){
        Optional<Game> gameoptional = gameService.getGame(id);
        if (gameoptional.isEmpty()){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        Game game = gameoptional.get();
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping ()
    public ResponseEntity <List<Game>> findAllGames(){
        List<Game> gameList = gameService.getAllGame();
        return new ResponseEntity<>(gameList, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Game> addGame(@Valid @RequestBody Game game){
        if (gameService.existsByName(game.getName())) {
            System.out.println("name of the Game alreadyExists");
            // TODO kako da dodam u exception error ?
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     Game savedGame =  gameService.createGame(game);
        return new ResponseEntity<>(savedGame, HttpStatus.OK);
    }

    @PostMapping("{id}/addAchievement")
    public  ResponseEntity<AchievementGame> addAchievement(@PathVariable Long id, @RequestParam  Long achievementID){
        if (!gameService.existsById(id)) {
            System.out.println("Game does not exist");
            // TODO kako da dodam u exception error ?
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!achievementService.existsById(achievementID)) {
            System.out.println("Achievement does not exist to be added to the game");
            // TODO kako da dodam u exception error ?
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AchievementGame achievementGame = new AchievementGame();
        achievementGame.setAchievement(achievementService.findById(achievementID).get());
        achievementGame.setGame(gameService.findById(id).get());

        achievementGameService.save(achievementGame);

        return new ResponseEntity<>(achievementGame, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public  ResponseEntity<Game> updateGame(@PathVariable long id, @Valid @RequestBody Game game){
        Optional<Game> optionalGame = gameService.getGame(id);
        if (optionalGame.isPresent()) {
            Game gameFound = optionalGame.get();
            gameFound.setId(id);
            gameFound.setName(game.getName());
         gameService.update(gameFound);
            return new ResponseEntity<>(gameFound, HttpStatus.OK);
        }

        System.out.println("Game with id: " + id + " not found in database");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable long id){
        Optional<Game> gameoptional = gameService.getGame(id);
        if (gameoptional.isEmpty()){
            // TODO kako da dodam u exception error ?
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        Game game = gameoptional.get();
        gameService.delete(id);
        return new ResponseEntity<>("Game "+ game.getName() + " successfully deleted", HttpStatus.OK);
    }

}
