package org.gamevault.java.spring_gamevault.controller;

import java.util.List;

import org.gamevault.java.spring_gamevault.model.Category;
import org.gamevault.java.spring_gamevault.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categories")

public class CategoryRestController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> index(){
        List<Category> categories = service.findAll();
        return categories;
    }

    @GetMapping("/{id}")
    public Category show(@PathVariable Integer id){
        Category category = service.getById(id);
        return category;
    }

}
