package org.gamevault.java.spring_gamevault.repo;

import java.util.List;

import org.gamevault.java.spring_gamevault.model.Category;
import org.gamevault.java.spring_gamevault.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

