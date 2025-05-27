package org.gamevault.java.spring_gamevault.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name= "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;


    @NotNull
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "categories"  )
    private List <Game> games;

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return List <Game> return the games
     */
    public List <Game> getGames() {
        return games;
    }

    /**
     * @param games the games to set
     */
    public void setGames(List <Game> games) {
        this.games = games;
    }

}
