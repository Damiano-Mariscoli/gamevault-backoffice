package org.gamevault.java.spring_gamevault.repo;

import java.util.List;


import org.gamevault.java.spring_gamevault.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<Game, Integer>{

    public List<Game> findByTitleContaining(String title);
    
    public List<Game> findByCategoriesId(Integer id);
    
}
