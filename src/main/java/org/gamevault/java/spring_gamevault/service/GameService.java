package org.gamevault.java.spring_gamevault.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.gamevault.java.spring_gamevault.repo.CategoryRepo;
import org.gamevault.java.spring_gamevault.repo.GameRepo;
import java.util.List;
import java.util.Optional;

import org.gamevault.java.spring_gamevault.model.Category;
import org.gamevault.java.spring_gamevault.model.Game;

@Service
public class GameService {

    @Autowired
    private GameRepo gameRepository;

    @Autowired
    private CategoryRepo categoryRepository;



    public List <Game> findAll(){
        return gameRepository.findAll();
    }
    public List <Game> findAllSortedByName(){
        return gameRepository.findAll(Sort.by("name"));
    }

    public Optional<Game> findById(Integer id){
        return gameRepository.findById(id);

    }

    public Game getById(Integer id){
        Optional<Game> gameAttempt = gameRepository.findById(id);
        
        if(gameAttempt.isEmpty()){
            //lanciamo una not found    
        }
        return gameAttempt.get();
    }

    public List <Game> finByTitle(String title){
        return gameRepository.findByTitleContaining(title);
    }

    public Game create(Game game){
        //aggiornamento di alcuni campi solamente in seguito alla creazione
        return gameRepository.save(game);
    }


        public Game update(Game game){
            //aggiornamento di altri campi
        return gameRepository.save(game);
    }

    public void deleteById(Integer id){
        Game game = getById(id);  
        for(Category categoryToDelete : game.getCategories()){
        categoryRepository.delete(categoryToDelete);

        }
        gameRepository.delete(game);
        
    }


    public void delete(Game game){
        for(Category categoryToDelete : game.getCategories()){
        categoryRepository.delete(categoryToDelete);

        }
        gameRepository.delete(game);
    }


    public Boolean existById(Integer id){
        return gameRepository.existsById(id);
    }
    public Boolean exist(Game game){
        return existById(game.getId());
    }


    public List<Game> findByCategoriesId(Integer id){
        return gameRepository.findByCategoriesId(id);
    }
}
