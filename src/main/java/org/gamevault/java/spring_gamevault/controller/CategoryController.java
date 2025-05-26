package org.gamevault.java.spring_gamevault.controller;

import org.gamevault.java.spring_gamevault.model.Category;
import org.gamevault.java.spring_gamevault.model.Game;
import org.gamevault.java.spring_gamevault.repo.CategoryRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping
    public String index(Model model){
        List <Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "categories/index";
    }


   @GetMapping("/{id}")
   public String show(@PathVariable Integer id, Model model) {
       model.addAttribute("category", categoryRepo.findById(id).get());
       return "categories/show";
   }


   @GetMapping("/create")
   public String create(Model model){
       model.addAttribute("category", new Category());
       return "categories/create-or-edit";
   }
    
   @PostMapping("/create")
   public String store(@Valid @ModelAttribute("category") Category formCategory , BindingResult bindingResult ,Model model ){
       if(bindingResult.hasErrors()){
           return "categories/create-or-edit";
       }
       categoryRepo.save(formCategory);        
       return "redirect:/categories";
   }
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model ){
    model.addAttribute(categoryRepo.findById(id).get());
    model.addAttribute("edit" , true);
    return "categories/create-or-edit";
  }
  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, Model model){
 
   if(bindingResult.hasErrors()){
           return "categories/create-or-edit";
       }
       categoryRepo.save(formCategory); 
          
       return "redirect:/categories";
   }
   @PostMapping("/delete/{id}")
   public String delete(@PathVariable Integer id ) {
    
       Category categoryToRemove = categoryRepo.findById(id).get();
       for(Game gameLinked : categoryToRemove.getGames()){
           gameLinked.getCategories().remove(categoryToRemove);
       }
       categoryRepo.delete(categoryToRemove);;
       return "redirect:/categories";
   }


    
    
}

