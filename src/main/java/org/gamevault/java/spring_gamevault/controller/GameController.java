package org.gamevault.java.spring_gamevault.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.gamevault.java.spring_gamevault.model.Category;
import org.gamevault.java.spring_gamevault.model.Game;
import org.gamevault.java.spring_gamevault.repo.CategoryRepo;
import org.gamevault.java.spring_gamevault.service.CategoryService;
import org.gamevault.java.spring_gamevault.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/games")
public class GameController {

  @Autowired
    private final CategoryService categoryService;

    

    @Autowired
    private GameService gameService;


    @Autowired
    private CategoryRepo categoryRepo;

    GameController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model){
        List <Game> games = gameService.findAll();
        model.addAttribute("games", games);
        model.addAttribute("categories", categoryRepo.findAll());
        return "games/index";
    } 

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id ){
        Game game = gameService.findById(id).get(); 
        model.addAttribute("game", game);
        return "games/show";
    }
    
     @GetMapping("/searchByTitle")
   public String searchByTitle(@RequestParam() String title, Model model){
    List <Game> games = gameService.finByTitle(title);
    model.addAttribute("categories", categoryRepo.findAll());
    model.addAttribute("games", games);
    return "games/index";
   }

   @GetMapping("/searchByCategory")
   public String searchByCategory(@RequestParam() Integer id, Model model){
    
     List <Game> games = gameService.findByCategoriesId(id);
     model.addAttribute("categories", categoryRepo.findAll());
     model.addAttribute("games", games);
     return "games/index";
   }

   
   @GetMapping("/create")
   public String create(Model model){
     model.addAttribute("game", new Game());
     model.addAttribute("categories", categoryRepo.findAll());
     return "games/create-or-edit";
   }

   @PostMapping("/create")
   public String store(@Valid @ModelAttribute("game") Game formGame, BindingResult bindingResult, Model model){

     
    if (bindingResult.hasErrors()){
      return "games/create-or-edit";
    }
    gameService.create(formGame);
     return "redirect:/games";
   }

   @GetMapping("/edit/{id}")
   public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("categories", categoryRepo.findAll());
     model.addAttribute("game", gameService.findById(id).get());
      
      
     model.addAttribute("edit", true);
    
     return "games/create-or-edit";
   }

   @PostMapping("/edit/{id}")
   public String update(@Valid @ModelAttribute("game") Game formGame, BindingResult bindingResult, Model model){

     
    if (bindingResult.hasErrors()){
       model.addAttribute("edit", true);
       model.addAttribute("categories", categoryRepo.findAll());
      return "games/create-or-edit";
    }
    gameService.update(formGame);
     return "redirect:/games";
   }

   @PostMapping("/delete/{id}")
   public String delete(@PathVariable Integer id){
    

    Game game = gameService.getById(id);

       game.getCategories().clear();
    
    // Delete the game
    gameService.delete(game);


   
    return "redirect:/games";
   }



}
