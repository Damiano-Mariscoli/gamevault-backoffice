package org.gamevault.java.spring_gamevault.repo;

import java.util.List;

import org.gamevault.java.spring_gamevault.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category, Integer> {
public List<Category> findByNameContaining(String name);
}

