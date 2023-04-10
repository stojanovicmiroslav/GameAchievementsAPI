package com.example.game_achievements_api.controller;

import com.example.game_achievements_api.exceptions.ApiError;
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
import java.util.Optional;

@RestController
@RequestMapping("api/v1/game")
@AllArgsConstructor
public class GameController {

    private final GameServiceImpl gameService;
    private final AchievementServiceImpl achievementService;



// games - GET, POST
// games/id - GET, PUT, DELETE


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
    public  ResponseEntity<?> addGame(@Valid @RequestBody Game game){ // ovde
        if (gameService.existsByName(game.getName())) {

            return new ResponseEntity<>(new ApiError("name of the Game alreadyExists"), HttpStatus.BAD_REQUEST);
        }
     Game savedGame =  gameService.createGame(game);
        return new ResponseEntity<>(savedGame, HttpStatus.OK);
    }


    @PostMapping("{id}/achievements")
    public  ResponseEntity<Achievement> addAchievement(@PathVariable Long id, @RequestBody Achievement achievement){
        if (!gameService.existsById(id)) {
            System.out.println("Game does not exist");
            // TODO kako da dodam u exception error ?
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Game gamefound = gameService.getGame(id).get();

        achievement.setGame(gamefound);

        achievementService.save(achievement);

        return new ResponseEntity<>(achievement, HttpStatus.CREATED);
    }


    @GetMapping ("{id}/achievements")
    public ResponseEntity <List<Achievement>> findAllAchievenetsByGame(@PathVariable Long id){
        List<Achievement> achievementsList = achievementService.findAllByGame_Id(id);
        return new ResponseEntity<>(achievementsList, HttpStatus.OK);
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
            // TODO sta ako izbrisem Game, kako ce da mi bude tabela GameAchievement
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        Game game = gameoptional.get();
        gameService.delete(id);
        return new ResponseEntity<>("Game "+ game.getName() + " successfully deleted", HttpStatus.OK);
    }

}
