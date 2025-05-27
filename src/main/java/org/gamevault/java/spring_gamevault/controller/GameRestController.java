package org.gamevault.java.spring_gamevault.controller;



import java.util.List;
import org.gamevault.java.spring_gamevault.model.Game;
import org.gamevault.java.spring_gamevault.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameRestController {
    @Autowired
    private GameService service;

    @GetMapping
    public List<Game> index(){
        List<Game> games = service.findAll();
        return games;
    }

    @GetMapping("/{id}")
    public Game show(@PathVariable Integer id){
        Game game = service.getById(id);
        return game;
    }

}