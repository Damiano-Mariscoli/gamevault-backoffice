package org.gamevault.java.spring_gamevault.service;



import java.util.List;

import org.gamevault.java.spring_gamevault.model.Category;
import org.gamevault.java.spring_gamevault.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {



    
    @Autowired
    private CategoryRepo categoryRepository;




    public List <Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category getById(Integer id){
        return categoryRepository.findById(id).get(); 
    }

    public Category create(Category category){
        return categoryRepository.save(category);
    }
    public Category update(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findByNome(){
        return categoryRepository.findAll(Sort.by("name"));

    }

     public void deleteById(Integer id){
    categoryRepository.deleteById(id);
 }
  public void delete(Category category){
    categoryRepository.delete(category);
    
 }
}
