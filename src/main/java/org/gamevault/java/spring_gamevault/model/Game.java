package org.gamevault.java.spring_gamevault.model;


import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "games")
public class Game {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@NotBlank
private String title;

@Lob
private String description;

@NotNull
private LocalDate  releaseDate;

@NotNull
private String publisher;


private String gameImg;


    @Override
    public String toString(){
        return String.format( "%d %s %s %s %s %s",this.id, this.title ,this.description, releaseDate,  this.publisher, this.gameImg);
    
    }

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
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return LocalDate return the releaseDate
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return String return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return String return the gameImg
     */
    public String getGameImg() {
        return gameImg;
    }

    /**
     * @param gameImg the gameImg to set
     */
    public void setGameImg(String gameImg) {
        this.gameImg = gameImg;
    }
    



}
